package com.tony.note.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author jli2
 * @date 4/15/2019 3:10 PM
 **/
public class DirectoryUtils {


    public static String getFileDirectory(){
        return System.getProperty("user.home").replaceAll("\\\\","/");
    }

}
