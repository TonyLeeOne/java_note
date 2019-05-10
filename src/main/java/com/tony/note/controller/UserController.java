package com.tony.note.controller;

import com.tony.note.configuration.GitHubProperties;
import com.tony.note.controller.dto.UserVo;
import com.tony.note.entity.User;
import com.tony.note.service.UserService;
import com.tony.note.service.impl.CaffeineService;
import com.tony.note.utils.EncryptUtil;
import com.tony.note.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author jli2
 * @date 4/10/2019 5:50 PM
 **/
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private GitHubProperties properties;

    @Autowired
    private UserService userService;

    @Autowired
    private CaffeineService caffeineService;


    public static final String TOKEN_CLIENT = "https://github.com/login/oauth/access_token";
    public static final String GET_TOKEN = "https://api.github.com/user?access_token=";
    public static final String AUTHORIZE_URL = "https://github.com/login/oauth/authorize?client_id=";

    @GetMapping("/login")
    public String login() {
        String url = AUTHORIZE_URL + properties.getId();
        return "redirect:" + url;
    }

    @GetMapping("/callback")
    public String callback(String code, HttpSession session, ModelMap modelMap) {
        String res = HttpUtils.sendPost(TOKEN_CLIENT, properties, code);
        String userInfo = null;
        if (!StringUtils.isEmpty(res)) {
            String access_token = res.split("&")[0].split("=")[1];
            userInfo = HttpUtils.sendGet(GET_TOKEN + access_token);
            try {
                JSONObject jsonObject = new JSONObject(userInfo);
                String username = jsonObject.getString("login");
                if (userService.checkUserExist(username)) {
                    return "setFail";
                }
                String avatar_url = jsonObject.getString("avatar_url");
                String html_url = jsonObject.getString("html_url");
                UserVo userVo = new UserVo();
                userVo.setGitUrl(html_url).setAvatar(avatar_url).setUsername(username);
                userService.saveUser(userVo);
                session.setAttribute("username", username);
                session.setAttribute("avatar_url", avatar_url);
                session.setAttribute("html_url", html_url);
            } catch (JSONException e) {
                log.info("解析json异常....[{}]", e.getMessage());
            }
        }
        return "setSuccess";
    }


    @GetMapping("/logout")
    private String logout(HttpSession session) {
        session.removeAttribute("username");
        return "redirect:/note/show?pageNum=1&size=10";

    }

    @GetMapping
    @ResponseBody
    private UserVo getUserDetail(String username) {
        return caffeineService.getUser(username);
    }


    /**
     * 设置密码
     *
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/password")
    private String setPass(String password, HttpSession session) {
        boolean res;
        String username = (String) session.getAttribute("username");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "fail";
        }
        res = userService.savePass(password, username);
        if (res) {
            return "success";
        } else {
            return "fail";
        }
    }

    /**
     * 重置密码
     *
     * @param session
     * @return
     */
    @PostMapping("/password/reset")
    @ResponseBody
    private String setPass(@RequestBody Map<String, String> map, HttpSession session) {
        String newPass = map.get("newPass");
        String oldPass = map.get("oldPass");
        boolean res;
        String username = (String) session.getAttribute("username");
        if (StringUtils.isEmpty(oldPass) || StringUtils.isEmpty(newPass)||StringUtils.isEmpty(username)) {
            return "参数异常";
        }
        User user = userService.checkUserExist(username,oldPass);
        if(ObjectUtils.isEmpty(user)){
            return "原密码不正确";
        }
        user.setPassword(EncryptUtil.encode(newPass));
        res=userService.updateById(user);
        if(res){
            return "重置成功";
        }
        return "重置失败";
    }

    @PostMapping("/login")
    @ResponseBody
    private String login(@RequestBody UserVo userVo, HttpSession session) {
        if (StringUtils.isEmpty(userVo.getUsername()) || StringUtils.isEmpty(userVo.getPassword())) {
            return "登录失败，用户名或密码错误";
        }
        User user = userService.checkUserExist(userVo.getUsername(), userVo.getPassword());
        if (ObjectUtils.isEmpty(user)) {
            return "登录失败，用户名或密码错误";
        }
        session.setAttribute("username", user.getUsername());
        session.setAttribute("avatar_url", user.getAvatar());
        session.setAttribute("html_url", user.getGitUrl());
        return "";
    }

    @GetMapping("/signin")
    private String sign() {
        return "login";
    }

}
