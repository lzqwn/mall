package com.lzqwn.mall.product.feign;

import org.springframework.cloud.openfeign.FeignClient;


/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 夏沫止水
 * @createTime: 2020-06-06 17:12
 **/

@FeignClient("gulimall-search")
public interface SearchFeignService {

   /* @PostMapping(value = "/search/save/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
*/
}
