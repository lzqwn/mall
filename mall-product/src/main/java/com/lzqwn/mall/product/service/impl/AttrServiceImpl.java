package com.lzqwn.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.Query;
import com.lzqwn.mall.product.dao.AttrAttrgroupRelationDao;
import com.lzqwn.mall.product.dao.AttrDao;
import com.lzqwn.mall.product.dao.AttrGroupDao;
import com.lzqwn.mall.product.dao.CategoryDao;
import com.lzqwn.mall.product.entity.AttrAttrgroupRelationEntity;
import com.lzqwn.mall.product.entity.AttrEntity;
import com.lzqwn.mall.product.entity.AttrGroupEntity;
import com.lzqwn.mall.product.entity.CategoryEntity;
import com.lzqwn.mall.product.service.AttrAttrgroupRelationService;
import com.lzqwn.mall.product.service.AttrService;
import com.lzqwn.mall.product.service.CategoryService;
import com.lzqwn.mall.product.vo.AttrRespVo;
import com.lzqwn.mall.product.vo.AttrVo;
import com.sun.media.jfxmediaimpl.MediaDisposer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryService categoryService;
    /**
     * @description: 分页查询商品属性信息
     * @author liuyang
     * @date: 2021/8/19 18:06
     * @param params:
     * @param catelogId: 根据分类id查询
     * @return com.lzqwn.common.utils.PageUtils
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params,Long catelogId,String attrType) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>().eq("attr_type","base".equalsIgnoreCase(attrType)?1:0);
        if (catelogId != 0){
            queryWrapper.eq("catelog_id",catelogId);
        }
        String key = params.get("key").toString();
        if (StringUtils.isNotEmpty(key)){
            queryWrapper.and((wrapper)->{
                wrapper.like("attr_name",key).or().like("value_select",key);
            });
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), queryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> attrRespVoList = records.stream().map((attrEntity)->{
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity,attrRespVo);
            //获取属性与属性分组信息
            if ("base".equalsIgnoreCase(attrType)){
                AttrAttrgroupRelationEntity relation = attrAttrgroupRelationDao.selectOne(
                        new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
                if (relation != null && relation.getAttrGroupId() != null){
                    AttrGroupEntity attrGroup = attrGroupDao.selectById(relation.getAttrGroupId());
                    attrRespVo.setAttrGroupName(attrGroup!=null?attrGroup.getAttrGroupName():"");
                }
            }
            //获取分类信息
            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if (categoryEntity!=null)
            {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());
        pageUtils.setList(attrRespVoList);
        return pageUtils;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        return new PageUtils(page);
    }

    /**
     * @description: 保存商品属性,并保存商品分组信息
     * @author liuyang
     * @date: 2021/8/19 16:46
     * @param attr:
     * @return void
     */
    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
        this.save(attrEntity);
        AttrAttrgroupRelationEntity attrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
        attrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
        attrgroupRelationEntity.setAttrSort(0);
        attrAttrgroupRelationDao.insert(attrgroupRelationEntity);
    }

    /**
     * @description: 根据商品属性id查询商品属性id,并带出类别,分组信息
     * @author liuyang
     * @date: 2021/8/19 18:18
     * @param attrId:
     * @return com.lzqwn.mall.product.vo.AttrRespVo
     */
    @Override
    public AttrRespVo getAttrById(Long attrId) {
        AttrRespVo attrRespVo = new AttrRespVo();
        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity,attrRespVo);
        //获取属性与属性分组信息
        AttrAttrgroupRelationEntity relation = attrAttrgroupRelationDao.selectOne(
                new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
        if (relation != null && relation.getAttrGroupId() != null){
            AttrGroupEntity attrGroup = attrGroupDao.selectById(relation.getAttrGroupId());
            if (attrGroup!=null){
                attrRespVo.setAttrGroupName(attrGroup.getAttrGroupName());
                attrRespVo.setAttrGroupId(attrGroup.getAttrGroupId());
            }
        }
        //获取分类信息
        CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
        if (categoryEntity!=null)
        {
            attrRespVo.setCatelogName(categoryEntity.getName());
            //根据分类id获取完整分类id
            Long[] path = categoryService.getcatelogPath(categoryEntity.getCatId());
            attrRespVo.setCatelogPath(path);
        }
        return attrRespVo;
    }

    /**
     * @description: 修改商品属性
     * @author liuyang
     * @date: 2021/8/19 19:08
     * @param attr:
     * @return void
     */
    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
        //修改商品属性
        this.saveOrUpdate(attrEntity);
        //修改商品属性与分组关联信息
        Integer count = attrAttrgroupRelationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
        //判断之前是否有关联信息存在
        if (count>0 && attr.getAttrGroupId() != null){
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
            attrAttrgroupRelationDao.update(attrAttrgroupRelationEntity,new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",attrEntity.getAttrId()));
        }
        else if(attr.getAttrGroupId() != null){
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());
            relationEntity.setAttrSort(0);
            attrAttrgroupRelationDao.insert(relationEntity);
        }

    }
}