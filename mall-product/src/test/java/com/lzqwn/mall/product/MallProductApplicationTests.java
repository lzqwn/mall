package com.lzqwn.mall.product;

import com.lzqwn.mall.product.entity.UndoLogEntity;
import com.lzqwn.mall.product.service.UndoLogService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MallProductApplicationTests {

    @Resource
    UndoLogService logService;

    @Test
    void contextLoads() {
        UndoLogEntity byId = logService.getById(1L);
        byId.setRollbackInfo("12312312312".getBytes());
        logService.saveOrUpdate(byId);

        System.out.println("====================================");
        System.out.println(byId);
        System.out.println("====================================");
        System.out.println(new String(byId.getRollbackInfo()));

    }

}
