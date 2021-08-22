package com.lzqwn.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.Query;
import com.lzqwn.mall.product.dao.CategoryDao;
import com.lzqwn.mall.product.entity.CategoryEntity;
import com.lzqwn.mall.product.service.CategoryBrandRelationService;
import com.lzqwn.mall.product.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 树形显示所有分类
     *
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
     * 根据递归解析数据为树形结构
     *
     * @param category:
     * @param categories:
     * @return java.util.List<com.lzqwn.mall.product.entity.CategoryEntity>
     */
    private List<CategoryEntity> getChildren(CategoryEntity category, List<CategoryEntity> categories) {
        return categories.stream().filter(k -> k.getParentCid().equals(category.getCatId()))
                .peek(y -> y.setChildren(getChildren(y, categories)))
                .sorted(Comparator.comparingInt(sorted -> (sorted.getSort() == null ? 0 : sorted.getSort())))
                .collect(Collectors.toList());
    }

    /**
     * 逻辑删除分类信息
     *
     * @param asList:
     * @return void
     */
    @Override
    public void removelogicByIds(List<Long> asList) {
        baseMapper.deleteBatchIds(asList);
    }

    /**
     * 根据分类id查询出所有父类分类集合(包含自身)
     */
    @Override
    public Long[] getcatelogPath(Long catelogId) {
        List<Long> catelogPath = findCatelogPath(catelogId, new ArrayList<>());
        Collections.reverse(catelogPath);
        return catelogPath.toArray(new Long[catelogPath.size()]);
    }

    /**
     * 递归遍历父级类别
     */
    private List<Long> findCatelogPath(Long catelogId, List<Long> catelogPath) {
        CategoryEntity category = this.getById(catelogId);
        catelogPath.add(category.getCatId());
        if (category.getParentCid() != 0) {
            findCatelogPath(category.getParentCid(), catelogPath);
        }
        return catelogPath;
    }

    @Override
    public void updateCategory(CategoryEntity category) {
        if (StringUtils.isNotBlank(category.getName())) {
            categoryBrandRelationService.updateCategoryName(category.getCatId(), category.getName());
        }
        this.updateById(category);
    }
}