package com.lzqwn.mall.member.controller;

import com.lzqwn.common.utils.R;
import com.lzqwn.mall.member.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("text")
public class textController {

    @Value("${member.name}")
    private String name;

    @Value("${member.age}")
    private int age;

    @Autowired
    private CouponFeignService couponFeignService;

    /**
     * 测试feign
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        System.out.println("===============================================");
        R info = couponFeignService.info(1L);
        return R.ok().put("coupon", info).put("text", "123");
    }

    /**
     * 测试config配置中心
     */
    @RequestMapping("/printname")
    public R printname() {

        return R.ok().put("name", name).put("age", age);
    }

}
