package com.lzqwn.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.member.entity.MemberReceiveAddressEntity;

import java.util.Map;

/**
 * 会员收货地址
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 23:06:49
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

