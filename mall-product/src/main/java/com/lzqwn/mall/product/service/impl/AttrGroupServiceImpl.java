package com.lzqwn.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.Query;
import com.lzqwn.mall.product.dao.AttrGroupDao;
import com.lzqwn.mall.product.entity.AttrGroupEntity;
import com.lzqwn.mall.product.service.AttrAttrgroupRelationService;
import com.lzqwn.mall.product.service.AttrGroupService;
import com.lzqwn.mall.product.service.AttrService;
import com.lzqwn.mall.product.vo.AttrGroupWithAttrsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrAttrgroupRelationService attrgroupRelationService;

    @Autowired
    private AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer catelogId) {
        IPage<AttrGroupEntity> page = null;
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<AttrGroupEntity>();
        String key = (String) params.get("key");
        if(StringUtils.isNotEmpty(key)){
            queryWrapper.and((wrapper)->wrapper.like("descript",key).or().like("attr_group_name",key));
        }
        if(catelogId==0){
            page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    queryWrapper);

        }
        else {
            queryWrapper.eq("catelog_id",catelogId);
            //1.分页信息,2查询信息
            page = this.page(new Query<AttrGroupEntity>().getPage(params),queryWrapper);
        }
        assert page != null;
        return new PageUtils(page);
    }

    /**
     * 根据分类id查询属性信息(分组包属性)
     * @author lzqwn
     * @param catId:
     * @return java.util.List<com.lzqwn.mall.product.vo.AttrGroupWithAttrsVo>
     */
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithattr(Long catId) {
        List<AttrGroupEntity> attrGroupEntityList = this.baseMapper.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catId));
        return attrGroupEntityList.stream().map(attrGroupEntity -> {
            AttrGroupWithAttrsVo vo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(attrGroupEntity,vo);
            vo.setAttrs(attrService.getRelationAttr(vo.getAttrGroupId()));
            return vo;
        }).collect(Collectors.toList());
    }
}