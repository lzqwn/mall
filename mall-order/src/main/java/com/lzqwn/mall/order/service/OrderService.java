package com.lzqwn.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 11:31:47
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

