package com.lzqwn.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.Query;
import com.lzqwn.mall.product.dao.CategoryBrandRelationDao;
import com.lzqwn.mall.product.entity.CategoryBrandRelationEntity;
import com.lzqwn.mall.product.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

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

        this.update(categoryBrand, new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id",brandId));
        //this.baseMapper.updateBrandName(brandId,name);
    }

    @Override
    public void updateCategoryName(Long catId, String name) {
        CategoryBrandRelationEntity categoryBrand = new CategoryBrandRelationEntity();
        categoryBrand.setCatelogName(name);
        //categoryBrand.setBrandId(brandId);
        this.update(categoryBrand, new UpdateWrapper<CategoryBrandRelationEntity>().eq("catelog_id",catId));
    }
}