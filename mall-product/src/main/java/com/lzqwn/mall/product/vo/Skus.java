/**
 * Copyright 2020 bejson.com
 */
package com.lzqwn.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


/**
 * 发布商品Vo
 * @author lzwqn
 */
@Data
public class Skus {
    /**
     * sku属性信息
     */
    private List<Attr> attr;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 标题
     */
    private String skuTitle;
    /**
     * 副标题
     */
    private String skuSubtitle;
    /**
     * sku图集
     */
    private List<Images> images;
    /**
     *
     */
    private List<String> descar;
    /**
     * 满几件
     */
    private int fullCount;
    /**
     * 打几折
     */
    private BigDecimal discount;
    /**
     * 是否叠加其他优惠[0-不可叠加，1-可叠加]
     */
    private int countStatus;
    /**
     * 满多少
     */
    private BigDecimal fullPrice;
    /**
     * 减多少
     */
    private BigDecimal reducePrice;
    /**
     *
     */
    private int priceStatus;
    /**
     * 各会员价格
     */
    private List<MemberPrice> memberPrice;

}
