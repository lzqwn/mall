package com.lzqwn.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.product.entity.CategoryEntity;
import com.lzqwn.mall.product.vo.Catelog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
public interface CategoryService extends IService<CategoryEntity> {
    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> treeShapeListAll();

    void removelogicByIds(List<Long> asList);

    Long[] getcatelogPath(Long catelogId);

    void updateCategory(CategoryEntity category);

    List<CategoryEntity> getLevel1Categorys();

    Map<String, List<Catelog2Vo>> getCatalogJson();
}

