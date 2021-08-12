package com.lzqwn.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 23:06:49
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

