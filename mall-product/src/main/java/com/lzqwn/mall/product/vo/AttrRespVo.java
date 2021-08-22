package com.lzqwn.mall.product.vo;

import lombok.Data;

@Data
public class AttrRespVo extends AttrVo {
    /**
     * 分组名称
     */
    private String attrGroupName;
    /**
     * 分类名称
     */
    private String catelogName;
    /**
     * 分类完整路径
     */
    private Long[] catelogPath;
}
