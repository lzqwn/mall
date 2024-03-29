package com.lzqwn.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.product.entity.SpuImagesEntity;

import java.util.List;
import java.util.Map;

/**
 * spu图片
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {
    PageUtils queryPage(Map<String, Object> params);

    void saveImages(Long id, List<String> images);
}

