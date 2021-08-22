package com.lzqwn.mall.product.vo;

import com.lzqwn.common.valid.AddGroup;
import com.lzqwn.common.valid.ListValue;
import com.lzqwn.common.valid.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SpuInfoVo {
    private Long brandId;

    /**
     * 品牌名
     */
    @NotBlank(groups = {AddGroup.class, UpdateGroup.class}, message = "品牌名称不能为空")
    private String name;
    /**
     * 品牌logo地址
     */
    @NotNull(groups = {AddGroup.class})
    @URL(groups = {AddGroup.class, UpdateGroup.class})
    private String logo;

    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @ListValue(value = {0, 1}, groups = {AddGroup.class, UpdateGroup.class})
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotNull(groups = {AddGroup.class, UpdateGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 品牌名称
     */
    private String brandName;
}
