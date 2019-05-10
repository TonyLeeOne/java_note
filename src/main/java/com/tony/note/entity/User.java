package com.tony.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jli2
 * @date 4/11/2019 4:16 PM
 **/
@Data
@TableName("user")
public class User {
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String username;
    private String avatar;
    private String state;
    private String gitUrl;
    private String password;
}
