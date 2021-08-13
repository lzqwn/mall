package com.lzqwn.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.member.entity.UndoLogEntity;

import java.util.Map;

/**
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 23:06:50
 */
public interface UndoLogService extends IService<UndoLogEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

