package com.lzqwn.mall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzqwn.mall.order.entity.OrderReturnReasonEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退货原因
 *
 * @author lzqwn
 * @email 286924558@qq.com
 * @date 2021-08-11 22:02:39
 */
@Mapper
public interface OrderReturnReasonDao extends BaseMapper<OrderReturnReasonEntity> {

}
