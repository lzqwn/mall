package com.lzqwn.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqwn.common.utils.PageUtils;
import com.lzqwn.common.utils.Query;
import com.lzqwn.mall.product.dao.AttrGroupDao;
import com.lzqwn.mall.product.entity.AttrGroupEntity;
import com.lzqwn.mall.product.service.AttrGroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

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
}