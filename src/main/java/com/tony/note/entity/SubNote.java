package com.tony.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jli2
 * @date 4/28/2019 10:44 AM
 **/
@TableName("R_SUB_NOTE")
@Data
@Accessors(chain = true)
public class SubNote {
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    private String subId;
    private String noteId;
    @TableLogic(value = "1",delval = "2")
    private String isDelete;


}
