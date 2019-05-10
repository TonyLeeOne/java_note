package com.tony.note.controller.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jli2
 * @date 4/11/2019 12:13 PM
 **/
@Data
@Accessors(chain = true)
public class UserVo implements Serializable {
    private static final long serialVersionUID = -745940208570536008L;
    private String id;
    private String username;
    private String avatar;
    private String state;
    private String gitUrl;
    private String password;
}
