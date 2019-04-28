package com.tony.note.controller;

import com.tony.note.configuration.GitHubProperties;
import com.tony.note.controller.dto.UserVo;
import com.tony.note.service.UserService;
import com.tony.note.service.impl.CaffeineService;
import com.tony.note.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    public String callback(String code, HttpSession session) {
        String res = HttpUtils.sendPost(TOKEN_CLIENT, properties, code);
        String userInfo = null;
        if (!StringUtils.isEmpty(res)) {
            String access_token = res.split("&")[0].split("=")[1];
            userInfo = HttpUtils.sendGet(GET_TOKEN + access_token);
            try {
                JSONObject jsonObject = new JSONObject(userInfo);
                String username = jsonObject.getString("login");
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
                e.printStackTrace();
            }
        }
        return "redirect:/note/list?pageNum=1&size=10";
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

}
