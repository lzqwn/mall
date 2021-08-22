package com.lzqwn.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.mall.ware.entity.PurchaseEntity;
import com.lzqwn.mall.ware.vo.MergeVo;
import com.lzqwn.mall.ware.vo.PurchaseDoneVo;

import java.util.List;
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

    void done(PurchaseDoneVo doneVo);

    void received(List<Long> ids);

    void mergePurchase(MergeVo mergeVo);

    PageUtils queryPageUnreceive(Map<String, Object> params);
}

