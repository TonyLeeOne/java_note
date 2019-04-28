package com.tony.note.controller.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

/**
 * @author jli2
 * @date 4/3/2019 9:52 PM
 **/
@Data
@Accessors(chain = true)
@ToString
public class NoteVo implements Serializable {
    private static final long serialVersionUID = 5090935338381095674L;
    private String id;
    private String noteName;
    private String category;
    private String content;
    private String creator;
    private String state;
    private String imageUrl;
    private String createDate;
    private String modifyDate;
}
