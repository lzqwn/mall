package com.lzqwn.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.product.entity.SpuInfoEntity;
import com.lzqwn.mall.product.vo.SpuInfoVo;
import com.lzqwn.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {
    PageUtils queryPage(Map<String, Object> params);

    void savesupInfo(SpuSaveVo vo);

    SpuInfoVo getSpuInfoBySkuId(Long skuId);

    PageUtils queryPageByCondtion(Map<String, Object> params);

    void up(Long spuId);
}

