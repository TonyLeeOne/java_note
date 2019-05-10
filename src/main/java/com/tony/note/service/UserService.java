package com.tony.note.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.note.controller.dto.UserVo;
import com.tony.note.entity.User;

public interface UserService extends IService<User> {

    boolean checkUserExist(String username);

    User checkUserExist(String username,String password);

    boolean saveUser(UserVo userVo);

    UserVo getByUsername(String username);

    boolean savePass(String password,String username);
}
