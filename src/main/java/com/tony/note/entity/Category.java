package com.tony.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jli2
 * @date 5/5/2019 11:13 AM
 **/
@TableName("category")
@Data
@Accessors(chain = true)
public class Category {
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String name;
    private String image;
    @TableLogic(value = "1",delval = "2")
    private String isDelete;
    private String description;
}
