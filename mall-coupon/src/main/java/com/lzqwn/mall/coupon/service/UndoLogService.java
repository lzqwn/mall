package com.lzqwn.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.coupon.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:44:06
 */
public interface UndoLogService extends IService<UndoLogEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

