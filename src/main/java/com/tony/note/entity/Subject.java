package com.tony.note.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jli2
 * @date 4/28/2019 10:39 AM
 **/
@TableName("SUBJECT")
@Data
@Accessors(chain = true)
public class Subject{

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String subName;
    private String description;
    private String image;
    @TableLogic(value = "1",delval = "2")
    private String isDelete;
    @TableField(fill = FieldFill.INSERT)
    private String createDate;
    @TableField(fill = FieldFill.INSERT)
    private String creator;
    @TableField(fill = FieldFill.UPDATE)
    private String modifier;
    @TableField(fill = FieldFill.UPDATE)
    private String modifyDate;

}
