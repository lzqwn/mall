package com.lzqwn.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.ware.entity.PurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:56:58
 */
public interface PurchaseService extends IService<PurchaseEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

