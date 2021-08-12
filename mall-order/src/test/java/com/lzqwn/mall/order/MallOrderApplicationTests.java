package com.lzqwn.mall.order;

import com.lzqwn.mall.order.entity.OrderEntity;
import com.lzqwn.mall.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MallOrderApplicationTests {


    @Resource
    private OrderService OrderService;

    @Test
    public void test() {
        OrderEntity saleAttrBySpuId = OrderService.getById(1L);
        System.out.println(saleAttrBySpuId);
    }

}
