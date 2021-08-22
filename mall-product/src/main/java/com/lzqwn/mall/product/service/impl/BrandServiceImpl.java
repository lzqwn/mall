package com.lzqwn.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.Query;
import com.lzqwn.mall.product.dao.BrandDao;
import com.lzqwn.mall.product.entity.BrandEntity;
import com.lzqwn.mall.product.service.BrandService;
import com.lzqwn.mall.product.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 分页查询品牌信息,key为部分字段模糊检索
     * @param params:
     * @return com.lzqwn.common.utils.PageUtils
     */
    @Transactional
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = params.get("key").toString();
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)){
            queryWrapper.and((wrapper)->wrapper.like("name",key).or().like("descript",key));
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void updateBrand(BrandEntity brand) {
        if(StringUtils.isNotBlank(brand.getName()))
        {
            categoryBrandRelationService.updateBrandName(brand.getBrandId(),brand.getName());
        }
        this.updateById(brand);
    }
}