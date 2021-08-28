package com.lzqwn.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.to.SkuHasStockVo;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.ware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:56:58
 */
public interface WareSkuService extends IService<WareSkuEntity> {
    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);
}

