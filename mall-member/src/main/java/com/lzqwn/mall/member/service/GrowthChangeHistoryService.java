package com.lzqwn.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.member.entity.GrowthChangeHistoryEntity;

import java.util.Map;

/**
 * 成长值变化历史记录
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 23:06:49
 */
public interface GrowthChangeHistoryService extends IService<GrowthChangeHistoryEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

