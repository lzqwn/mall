package com.lzqwn.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.product.entity.BrandEntity;
import com.lzqwn.mall.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {
    PageUtils queryPage(Map<String, Object> params);

    void updateBrandName(Long brandId, String name);

    /**
     * 根据分类id修改分类名称
     *
     * @param catId:
     * @param name:
     * @return void
     * @author lzqwm
     */
    void updateCategoryName(Long catId, String name);

    /**
     * 根据分类id查询品牌信息
     *
     * @param catId:
     * @return java.util.List<com.lzqwn.mall.product.entity.BrandEntity>
     * @author lzqwn
     */
    List<BrandEntity> getByCategoryId(Long catId);
}

