package com.tony.note.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.sql.Date;

/**
 * @author jli2
 * @date 4/3/2019 9:52 PM
 **/
@Data
@TableName("note")
@Accessors(chain = true)
public class Note extends BaseEntity {
    @TableId(type = IdType.UUID)
    private String id;
    private String noteName;
    private String category;
    private String content;
    private String imageUrl;
    @TableField(fill = FieldFill.INSERT)
    private String createDate;
    private String creator;
    @TableField(fill = FieldFill.UPDATE)
    private String modifyDate;
}
