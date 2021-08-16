package com.lzqwn.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lzqwn.common.valid.AddGroup;
import com.lzqwn.common.valid.ListValue;
import com.lzqwn.common.valid.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * 品牌
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId
    @NotNull(groups = {UpdateGroup.class},message = "修改时必须携带id")
    @Null(groups = {AddGroup.class},message = "新增时不能携带id")
    private Long brandId;

    /**
     * 品牌名
     */
    @NotBlank(groups = {AddGroup.class, UpdateGroup.class},message = "品牌名称不能为空")
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
    @ListValue(value = {0,1},groups = {AddGroup.class, UpdateGroup.class})
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

}
