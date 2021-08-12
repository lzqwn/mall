package com.lzqwn.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.member.entity.MemberCollectSpuEntity;

import java.util.Map;

/**
 * 会员收藏的商品
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 23:06:49
 */
public interface MemberCollectSpuService extends IService<MemberCollectSpuEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

