package com.tony.note.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.note.controller.dto.IPageVo;
import com.tony.note.controller.dto.NoteVo;
import com.tony.note.entity.Note;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface NoteService extends IService<Note> {

    boolean saveNote(NoteVo note, HttpSession session);

    String syncNote(NoteVo note, HttpSession session);

    IPage<Note> getList(int page,int size,String creator);

    IPageVo<NoteVo> getShowList(int page, int size);

    NoteVo getNote(String id);

    boolean deleteById(String id);

    IPage<Note> search(NoteVo noteVo,int page,int size);

    Note editNote(String id);

    List<NoteVo> archive(String category);

    Map<String,List<NoteVo>> archiveNotes(String category);

    IPageVo<NoteVo> search(int pageNum,int size,String content);

    NoteVo getPub();

    List<Object> getCategories();

    boolean used(String category);

}
