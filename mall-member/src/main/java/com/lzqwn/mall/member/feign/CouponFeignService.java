package com.lzqwn.mall.member.feign;

import com.lzqwn.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//远程调用的服务名
@FeignClient("mall-coupon")
public interface CouponFeignService {

    @RequestMapping("/coupon/coupon/info/{id}")
    public R info(@PathVariable("id") Long id);

}
