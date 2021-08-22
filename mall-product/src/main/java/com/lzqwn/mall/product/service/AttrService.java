package com.lzqwn.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.product.entity.AttrEntity;
import com.lzqwn.mall.product.vo.AttrGroupRelationVo;
import com.lzqwn.mall.product.vo.AttrRespVo;
import com.lzqwn.mall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
public interface AttrService extends IService<AttrEntity> {
    PageUtils queryPage(Map<String, Object> params,Long catelogId,String attrType);

    PageUtils queryPage(Map<String, Object> params);
    void saveAttr(AttrVo attr);

    AttrRespVo getAttrById(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);


    void deleteRelation(AttrGroupRelationVo[] vos);

    void updateAttrById(AttrVo attr);
}

