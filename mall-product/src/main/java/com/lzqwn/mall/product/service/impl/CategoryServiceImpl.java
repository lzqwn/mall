package com.lzqwn.mall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.Query;
import com.lzqwn.mall.product.dao.CategoryDao;
import com.lzqwn.mall.product.entity.CategoryEntity;
import com.lzqwn.mall.product.service.CategoryBrandRelationService;
import com.lzqwn.mall.product.service.CategoryService;
import com.lzqwn.mall.product.vo.Catelog2Vo;
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

    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        System.out.println("查询了数据库");

        //将数据库的多次查询变为一次
        List<CategoryEntity> selectList = this.baseMapper.selectList(null);

        //1、查出所有分类
        //1、1）查出所有一级分类
        List<CategoryEntity> level1Categorys = getParent_cid(selectList, 0L);

        //封装数据
        Map<String, List<Catelog2Vo>> parentCid = level1Categorys.stream()
                .collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            //1、每一个的一级分类,查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = getParent_cid(selectList, v.getCatId());

            //2、封装上面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName().toString());

                    //1、找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = getParent_cid(selectList, l2.getCatId());

                    if (level3Catelog != null) {
                        List<Catelog2Vo.Category3Vo> category3Vos = level3Catelog.stream().map(l3 -> {
                            //2、封装成指定格式
                            Catelog2Vo.Category3Vo category3Vo = new Catelog2Vo.Category3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());

                            return category3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(category3Vos);
                    }

                    return catelog2Vo;
                }).collect(Collectors.toList());
            }

            return catelog2Vos;
        }));

        return parentCid;
    }

    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        System.out.println("getLevel1Categorys........");
        long l = System.currentTimeMillis();
        List<CategoryEntity> categoryEntities = this.baseMapper.selectList(
                new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        System.out.println("消耗时间："+ (System.currentTimeMillis() - l));
        return categoryEntities;
    }

    private List<CategoryEntity> getParent_cid(List<CategoryEntity> selectList,Long parentCid) {
        return selectList.stream().filter(item -> item.getParentCid().equals(parentCid))
                .collect(Collectors.toList());
    }
}