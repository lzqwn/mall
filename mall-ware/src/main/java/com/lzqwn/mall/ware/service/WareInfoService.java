package com.lzqwn.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.ware.entity.WareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:56:58
 */
public interface WareInfoService extends IService<WareInfoEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

