package com.lzqwn.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzqwn.mall.product.entity.CategoryBrandRelationEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌分类关联
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {
}
