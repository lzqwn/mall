package com.lzqwn.mall.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.R;
import com.lzqwn.mall.product.entity.AttrGroupEntity;
import com.lzqwn.mall.product.entity.CategoryBrandRelationEntity;
import com.lzqwn.mall.product.service.AttrGroupService;
import com.lzqwn.mall.product.service.CategoryBrandRelationService;
import com.lzqwn.mall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 属性分组
 *
 * @author lzqwn
 * @email 2869245558@qq.com
 * @date 2021-08-12 22:38:51
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;



    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params,@PathVariable Integer catelogId) {

        PageUtils page = attrGroupService.queryPage(params,catelogId);
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        attrGroup.setCatelogPath(categoryService.getcatelogPath(attrGroup.getCatelogId()));
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
