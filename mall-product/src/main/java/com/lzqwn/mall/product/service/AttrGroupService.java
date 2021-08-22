package com.lzqwn.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.product.entity.AttrGroupEntity;
import com.lzqwn.mall.product.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {
    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Integer catelogId);

    /**
     * 根据分类id查询属性信息(分组包属性)
     * @author lzqwn
     * @param catId:
     * @return java.util.List<com.lzqwn.mall.product.vo.AttrGroupWithAttrsVo>
     */
    List<AttrGroupWithAttrsVo> getAttrGroupWithattr(Long catId);
}

