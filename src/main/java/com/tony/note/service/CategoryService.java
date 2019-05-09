package com.tony.note.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.note.entity.Category;

import java.util.List;

/**
 * @author jli2
 * @date 5/5/2019 11:18 AM
 **/
public interface CategoryService extends IService<Category> {

    boolean exists(String name);

    boolean used(String name);

    List<Category> getList();
}
