package com.lzqwn.mall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzqwn.mall.order.entity.PaymentInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 *
 * @author lzqwn
 * @email 286924558@qq.com
 * @date 2021-08-11 22:02:39
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {

}
