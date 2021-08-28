package com.lzqwn.search.controller;

import com.lzqwn.common.es.SkuEsModel;
import com.lzqwn.common.exception.BizCodeEnum;
import com.lzqwn.common.utils.R;
import com.lzqwn.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 夏沫止水
 * @createTime: 2020-06-06 16:48
 **/


@Slf4j
@RequestMapping(value = "/search/save")
@RestController
public class ElasticSaveController {

    @Autowired
    private ProductSaveService productSaveService;


    /**
     * 上架商品
     * @param skuEsModels
     * @return
     */
    @PostMapping(value = "/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {

        boolean status=false;
        try {
            status = productSaveService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            //log.error("商品上架错误{}",e);

            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnum.PRODUCT_UP_EXCEPTION.getMessage());
        }

        if(status){
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnum.PRODUCT_UP_EXCEPTION.getMessage());
        }else {
            return R.ok();
        }

    }


}
