package com.lzqwn.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.coupon.entity.CouponSpuRelationEntity;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:44:06
 */
public interface CouponSpuRelationService extends IService<CouponSpuRelationEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

