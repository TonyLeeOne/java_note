package com.tony.note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.note.controller.dto.CategoryVo;
import com.tony.note.entity.Category;
import com.tony.note.mapper.CategoryMapper;
import com.tony.note.service.CategoryService;
import com.tony.note.service.NoteService;
import com.tony.note.utils.BeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author jli2
 * @date 5/5/2019 11:19 AM
 **/
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {

    @Autowired
    private NoteService noteService;

    @Override
    public boolean exists(String name) {
        return count(new QueryWrapper<Category>().lambda().eq(Category::getName,name))>0;
    }

    @Override
    public CategoryVo get(String name) {
        Category category=getOne(new QueryWrapper<Category>().lambda().eq(Category::getName,name));
        if(ObjectUtils.isEmpty(category)){
            return null;
        }
         return BeanMapper.map(category,CategoryVo.class);
    }

    @Override
    public CategoryVo getByCid(String id) {
        Category category=getById(id);
        return BeanMapper.map(category,CategoryVo.class);
    }

    @Override
    public boolean used(String name) {
        return noteService.used(name);
    }

    @Override
    public List<Category> getList() {
        return list(new QueryWrapper<Category>().lambda().orderByDesc(Category::getName));
    }


}
