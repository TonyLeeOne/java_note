package com.tony.note.controller.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jli2
 * @date 5/5/2019 11:13 AM
 **/
@Data
@Accessors(chain = true)
public class CategoryVo implements Serializable {
    private static final long serialVersionUID = -6875490085317420669L;
    private String id;
    private String name;
}
