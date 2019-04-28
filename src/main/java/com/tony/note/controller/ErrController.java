package com.tony.note.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jli2
 * @date 4/12/2019 5:59 PM
 **/
@Controller
public class ErrController implements ErrorController {

    public static final String ERROR_PATH="/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public String errorHandler(HttpServletRequest request, HttpServletResponse response){
        int status=response.getStatus();
        switch (status){
            case  403:
                return "403";
            case 404:
                return "404";
            case 500:
                return "500";
        }
        return "";
    }
}
