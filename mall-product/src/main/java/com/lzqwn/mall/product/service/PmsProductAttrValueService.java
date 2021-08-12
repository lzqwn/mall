package com.lzqwn.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.product.entity.PmsProductAttrValueEntity;

import java.util.Map;

/**
 * spu属性值
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:12:23
 */
public interface PmsProductAttrValueService extends IService<PmsProductAttrValueEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

