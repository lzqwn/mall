package com.lzqwn.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.Query;
import com.lzqwn.mall.coupon.dao.MemberPriceDao;
import com.lzqwn.mall.coupon.entity.MemberPriceEntity;
import com.lzqwn.mall.coupon.service.MemberPriceService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;


@Service("memberPriceService")
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceDao, MemberPriceEntity> implements MemberPriceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<MemberPriceEntity> queryWrapper = new QueryWrapper<>();

        String key = (String) params.get("key");

        if (!StringUtils.isEmpty(key)) {
            queryWrapper.eq("id",key).or().eq("sku_id",key).or().eq("member_level_id",key);
        }

        IPage<MemberPriceEntity> page = this.page(
                new Query<MemberPriceEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}