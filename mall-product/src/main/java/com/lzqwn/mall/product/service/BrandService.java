package com.lzqwn.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
public interface BrandService extends IService<BrandEntity> {
    PageUtils queryPage(Map<String, Object> params);

    void updateBrand(BrandEntity brand);
}

