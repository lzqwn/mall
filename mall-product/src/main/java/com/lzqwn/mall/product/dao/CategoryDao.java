package com.lzqwn.mall.product.dao;

import com.lzqwn.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
