package com.lzqwn.mall.product.controller;

import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.R;
import com.lzqwn.mall.product.entity.PmsSpuImagesEntity;
import com.lzqwn.mall.product.service.PmsSpuImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;


/**
 * spu图片
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:12:23
 */
@RestController
@RequestMapping("product/pmsspuimages")
public class PmsSpuImagesController {
    @Autowired
    private PmsSpuImagesService pmsSpuImagesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = pmsSpuImagesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        PmsSpuImagesEntity pmsSpuImages = pmsSpuImagesService.getById(id);

        return R.ok().put("pmsSpuImages", pmsSpuImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PmsSpuImagesEntity pmsSpuImages) {
        pmsSpuImagesService.save(pmsSpuImages);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PmsSpuImagesEntity pmsSpuImages) {
        pmsSpuImagesService.updateById(pmsSpuImages);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        pmsSpuImagesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
