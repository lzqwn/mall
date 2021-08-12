package com.lzqwn.mall.coupon.dao;

import com.lzqwn.mall.coupon.entity.CouponHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 * 
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:44:06
 */
@Mapper
public interface CouponHistoryDao extends BaseMapper<CouponHistoryEntity> {
	
}
