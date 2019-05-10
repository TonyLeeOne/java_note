package com.tony.note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.note.controller.dto.UserVo;
import com.tony.note.entity.User;
import com.tony.note.mapper.UserMapper;
import com.tony.note.service.UserService;
import com.tony.note.utils.BeanMapper;
import com.tony.note.utils.EncryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author jli2
 * @date 4/11/2019 4:20 PM
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Override
    public boolean checkUserExist(String username) {
        return count(new QueryWrapper<User>().eq(!StringUtils.isEmpty(username),"username",username))>0;
    }

    @Override
    public User checkUserExist(String username, String password) {
        String pass=EncryptUtil.encode(password);
        return getOne(new QueryWrapper<User>().eq(!StringUtils.isEmpty(username),"username",username)
                .eq(!StringUtils.isEmpty(password),"password",pass));
    }

    @Override
    public boolean saveUser(UserVo userVo) {
        User user=BeanMapper.map(userVo,User.class);
        if(checkUserExist(user.getUsername())){
            return true;
        }
        return save(user);
    }

    @Override
    public UserVo getByUsername(String username) {
        User user= getOne(new QueryWrapper<User>()
                .lambda()
                .eq(User::getUsername,username));
        return BeanMapper.map(user,UserVo.class);
    }

    @Override
    public boolean savePass(String password,String username) {
        User user=getOne(new QueryWrapper<User>()
                .lambda()
                .eq(User::getUsername,username));
        user.setPassword(EncryptUtil.encode(password));
        return updateById(user);
    }
}
