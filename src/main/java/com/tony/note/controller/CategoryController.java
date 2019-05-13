package com.tony.note.controller;

import com.tony.note.constant.Constant;
import com.tony.note.controller.dto.CategoryVo;
import com.tony.note.entity.Category;
import com.tony.note.service.CategoryService;
import com.tony.note.service.impl.CaffeineService;
import com.tony.note.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author jli2
 * @date 5/5/2019 11:39 AM
 **/
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CaffeineService caffeineService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    private String getCategories(ModelMap modelMap, HttpSession session){
        String username= (String) session.getAttribute("username");
        if(!StringUtils.isEmpty(username)&&username.equals("TonyLeeOne")){
            modelMap.addAttribute(Constant.ALL_CATEGORIES,categoryService.getList());
        }else{
            modelMap.addAttribute(Constant.ALL_CATEGORIES,caffeineService.getAllCategories());
        }
        return "category";
    }

    @PostMapping
    @ResponseBody
    private String add(@RequestBody CategoryVo vo){
        Category category=BeanMapper.map(vo,Category.class);
        if(StringUtils.isEmpty(category.getId())) {
            if (categoryService.exists(category.getName()))
                return "你添加的标签已存在，不可重复 ";
        }else {
            return String.valueOf(categoryService.updateById(category));
        }
        return String.valueOf(categoryService.save(category));
    }

    @DeleteMapping
    @ResponseBody
    private String delete(String id){
        Category category=categoryService.getById(id);
        if(categoryService.used(category.getName()))
            return "当前标签已被占用，不可删除";

        return String.valueOf(categoryService.removeById(id));
    }

    @PutMapping
    @ResponseBody
    private String update(@RequestBody CategoryVo categoryVo){
        Category category=BeanMapper.map(categoryVo,Category.class);
        return String.valueOf(categoryService.updateById(category));
    }


    @GetMapping("/detail")
    @ResponseBody
    private CategoryVo get(String id){
        return categoryService.getByCid(id);
    }
}
