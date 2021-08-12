package com.lzqwn.mall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzqwn.mall.order.entity.RefundInfoEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款信息
 *
 * @author lzqwn
 * @email 286924558@qq.com
 * @date 2021-08-11 22:02:39
 */
@Mapper
public interface RefundInfoDao extends BaseMapper<RefundInfoEntity> {

}
