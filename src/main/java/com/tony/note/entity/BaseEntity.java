package com.tony.note.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author jli2
 * @date 4/3/2019 9:53 PM
 **/
@Data
public class BaseEntity {

    public String modifier;

    /**
     * 1为发布，2为草稿，默认为1
     */
    public String state;

    /**
     * 默认为1 删除为2
     */
    @TableLogic(value = "1",delval ="2")
    public String isDelete;

}
