package com.tony.note.controller.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jli2
 * @date 4/28/2019 10:39 AM
 **/
@TableName("SUBJECT")
@Data
@Accessors(chain = true)
public class SubjectVo implements Serializable {
    private static final long serialVersionUID = -5680212134825115175L;
    private String subName;
    private String description;
    private String image;
    private String creator;
    private String modifier;
}
