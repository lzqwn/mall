package com.lzqwn.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.Query;
import com.lzqwn.mall.product.dao.CategoryDao;
import com.lzqwn.mall.product.entity.CategoryEntity;
import com.lzqwn.mall.product.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * @description: 树形显示所有分类
     * @author liuyang
     * @date: 2021/8/14 20:20

     * @return java.util.List<com.lzqwn.mall.product.entity.CategoryEntity>
     */
    @Override
    public List<CategoryEntity> treeShapeListAll() {
        //1.查询出所有分类
        List<CategoryEntity> categories = baseMapper.selectList(null);
        //2.树形显示分类
        return categories.stream().filter(category -> category.getParentCid() == 0)
                .peek(menu -> menu.setChildren(getChildren(menu, categories)))
                .sorted(Comparator.comparingInt(sorted -> (sorted.getSort() == null ? 0 : sorted.getSort())))
                .collect(Collectors.toList());
    }

    /**
     * @description: 根据递归解析数据为树形结构
     * @author liuyang
     * @date: 2021/8/14 21:30
     * @param category:
     * @param categories:
     * @return java.util.List<com.lzqwn.mall.product.entity.CategoryEntity>
     */
    private List<CategoryEntity> getChildren(CategoryEntity category,List<CategoryEntity> categories)
    {
        return categories.stream().filter(k->k.getParentCid().equals(category.getCatId()))
                .peek(y->y.setChildren(getChildren(y,categories)))
                .sorted(Comparator.comparingInt(sorted -> (sorted.getSort() == null ? 0 : sorted.getSort())))
                .collect(Collectors.toList());
    }
}