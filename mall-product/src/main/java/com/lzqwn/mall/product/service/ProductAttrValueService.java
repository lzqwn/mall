package com.lzqwn.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:52
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {
    PageUtils queryPage(Map<String, Object> params);

    void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities);

    List<ProductAttrValueEntity> baseAttrListforspu(Long spuId);
}

