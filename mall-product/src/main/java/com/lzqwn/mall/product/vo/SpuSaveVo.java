/**
 * Copyright 2020 bejson.com
 */
package com.lzqwn.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SpuSaveVo {
    /**
     * spu名称
     */
    private String spuName;
    /**
     * spu描述
     */
    private String spuDescription;

    /**
     * 分类id
     */
    private Long catalogId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 重量(kg)
     */
    private BigDecimal weight;
    /**
     * 上架状态[0 - 下架，1 - 上架]
     */
    private int publishStatus;
    /**
     * spu描述图片
     */
    private List<String> decript;
    /**
     * spu图集
     */
    private List<String> images;
    /**
     * 积分
     */
    private Bounds bounds;
    /**
     * spu的规格参数
     */
    private List<BaseAttrs> baseAttrs;
    /**
     * sku信息
     */
    private List<Skus> skus;


}
