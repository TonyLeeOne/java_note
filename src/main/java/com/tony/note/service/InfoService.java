package com.tony.note.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.note.controller.dto.NoteVo;
import com.tony.note.entity.Info;
import com.tony.note.entity.Note;

public interface InfoService extends IService<Info> {

    int star(String nid);
    int unStar(String nid);
    boolean countNote(String nid);
    boolean countModify(String nid);
    Info getInfo(String nid);

}
