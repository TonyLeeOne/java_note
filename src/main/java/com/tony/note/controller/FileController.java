package com.tony.note.controller;

import com.tony.note.utils.DirectoryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author jli2
 * @date 4/3/2019 9:39 AM
 **/

@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {

    @Value("${upload.directory}")
    private String UPLOAD_DIRECTORY;

    @RequestMapping("/upload")
    public Map<String,Object> fileUpload(@RequestParam(value = "editormd-image-file", required = false)MultipartFile file, HttpServletRequest request){
        Map<String,Object> resultMap=new HashMap<>(4);
        String fileName=file.getOriginalFilename();
        String newName=System.currentTimeMillis()+(int)Math.random()*100+fileName.substring(fileName.indexOf("."),fileName.length());
            try {
                byte[] bytes=file.getBytes();
                String filePath =DirectoryUtils.getFileDirectory().concat(UPLOAD_DIRECTORY);
                Path path=Paths.get(filePath+newName);
                Files.write(path,bytes);
                resultMap.put("success",1);
                resultMap.put("message","upload success");
                resultMap.put("url","/image/"+newName);
            } catch (IOException e) {
                log.info("文件上传失败");
                resultMap.put("success",0);
                resultMap.put("message","upload failed");
                e.printStackTrace();
            }
            return resultMap;
    }
}
