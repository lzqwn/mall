package com.lzqwn.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.product.entity.PmsBrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:12:23
 */
public interface PmsBrandService extends IService<PmsBrandEntity> {
    PageUtils queryPage(Map<String, Object> params);
}

