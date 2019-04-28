package com.tony.note.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author jli2
 * @date 4/11/2019 1:21 PM
 **/
@WebFilter(urlPatterns = "/note/**")
@Slf4j
@Data
public class AuthFilter implements Filter {

    private static final String[] EXCLUDE_URLS = {"/list","/wr"};

    boolean PASS;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        PASS = false;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        authorizedMethods(request);
        if (PASS) {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            if (username!=null) {
                log.info("请求的url[{}],请求方式[{}],用户名[{}]",request.getRequestURL(), request.getMethod(),username);
                filterChain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/user/login").forward(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }

    private void authorizedMethods(HttpServletRequest request) {
        Arrays.asList(EXCLUDE_URLS).stream().forEach(str -> {
            if (request.getRequestURL().indexOf(str) != -1) {
                PASS = true;
            }
        });
    }


}
