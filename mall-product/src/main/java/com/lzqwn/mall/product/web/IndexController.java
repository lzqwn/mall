package com.lzqwn.mall.product.web;


import com.lzqwn.mall.product.entity.CategoryEntity;
import com.lzqwn.mall.product.service.CategoryService;
import com.lzqwn.mall.product.vo.Catelog2Vo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 夏沫止水
 * @createTime: 2020-06-08 14:14
 **/

@Controller
@RequestMapping("/product/web")
public class IndexController {

    @Resource
    private CategoryService categoryService;

    @GetMapping(value = {"/","index.html"})
    private String indexPage(Model model) {

        //1、查出所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();
        model.addAttribute("categories",categoryEntities);

        return "index";
    }


    //index/json/catalog.json
    @GetMapping(value = "/index/catalog.json")
    @ResponseBody
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        return categoryService.getCatalogJson();
    }


}
