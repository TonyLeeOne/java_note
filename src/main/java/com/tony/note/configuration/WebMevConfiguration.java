package com.tony.note.configuration;

import com.tony.note.constant.Constant;
import com.tony.note.utils.DirectoryUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author jli2
 * @date 4/3/2019 10:16 AM
 **/
@Configuration
public class WebMevConfiguration implements WebMvcConfigurer {

    @Value("${upload.directory}")
    private String path;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String filePath = DirectoryUtils.getFileDirectory().concat(path);
        if(Constant.WINDOWS.equals(File.separator)){
            registry.addResourceHandler("/image/**").addResourceLocations("file:/" + filePath);
        }
        if(Constant.LINUX.equals(File.separator)){
            registry.addResourceHandler("/image/**").addResourceLocations("file:" + filePath);
        }

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/note/show?pageNum=1&size=10");
    }


}
