package com.tony.note.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jli2
 * @date 4/9/2019 3:01 PM
 **/
@Data
@Accessors(chain = true)
@TableName("info")
public class Info implements Serializable {
    private static final long serialVersionUID = -1167988624885587314L;
    @TableId(type = IdType.UUID)
    private String id;
    private String nid;
    private int count;
    private int recommend;
    private int modify;

    @TableField(fill = FieldFill.INSERT)
    private String createDate;
    @TableField(fill = FieldFill.INSERT)
    private String creator;
    @TableField(fill = FieldFill.UPDATE)
    private String modifier;
    @TableField(fill = FieldFill.UPDATE)
    private String modifyDate;

}
