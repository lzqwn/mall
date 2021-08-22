package com.lzqwn.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.Query;
import com.lzqwn.mall.product.dao.CategoryBrandRelationDao;
import com.lzqwn.mall.product.entity.BrandEntity;
import com.lzqwn.mall.product.entity.CategoryBrandRelationEntity;
import com.lzqwn.mall.product.service.BrandService;
import com.lzqwn.mall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private BrandService brandService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void updateBrandName(Long brandId, String name) {
        CategoryBrandRelationEntity categoryBrand = new CategoryBrandRelationEntity();
        categoryBrand.setBrandName(name);

        this.update(categoryBrand, new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));
        //this.baseMapper.updateBrandName(brandId,name);
    }

    /**
     * 根据分类id修改分类名称
     *
     * @param catId:
     * @param name:
     * @return void
     * @author lzqwm
     */
    @Override
    public void updateCategoryName(Long catId, String name) {
        CategoryBrandRelationEntity categoryBrand = new CategoryBrandRelationEntity();
        categoryBrand.setCatelogName(name);
        //categoryBrand.setBrandId(brandId);
        this.update(categoryBrand, new UpdateWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
    }

    /**
     * 根据分类id查询品牌信息
     *
     * @param catId:
     * @return java.util.List<com.lzqwn.mall.product.entity.BrandEntity>
     * @author lzqwn
     */
    @Override
    public List<BrandEntity> getByCategoryId(Long catId) {
        List<CategoryBrandRelationEntity> categoryBrandRelationEntityList = this.baseMapper
                .selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
        List<Long> brandIdList = categoryBrandRelationEntityList.stream().map(CategoryBrandRelationEntity::getBrandId).collect(Collectors.toList());
        return brandService.getBaseMapper().selectBatchIds(brandIdList);
    }
}