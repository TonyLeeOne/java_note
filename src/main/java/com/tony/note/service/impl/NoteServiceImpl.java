package com.tony.note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.note.controller.dto.IPageVo;
import com.tony.note.controller.dto.NoteVo;
import com.tony.note.entity.Note;
import com.tony.note.mapper.NoteMapper;
import com.tony.note.service.InfoService;
import com.tony.note.service.NoteService;
import com.tony.note.utils.BeanMapper;
import com.tony.note.utils.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date 4/4/2019 10:12 AM
 **/
@Slf4j
@Service
public class NoteServiceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {


    @Autowired
    private InfoService infoService;

    @Override
    public boolean saveNote(NoteVo noteVo, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Note note = BeanMapper.map(noteVo, Note.class);
        if (StringUtils.isEmpty(note.getId())) {
            note.setCreator(username);
            return save(note);
        }
        if (infoService.countModify(note.getId())) {
            note.setModifier(username);
            return updateById(note);
        }
        return false;
    }

    @Override
    public String syncNote(NoteVo noteVo, HttpSession session) {
        log.info("接收到的传值为:[{]]",noteVo.toString());
        String username = (String) session.getAttribute("username");
        Note note = BeanMapper.map(noteVo, Note.class);
        if (StringUtils.isEmpty(note.getId())) {
            note.setCreator(username);
            if (save(note)) {
                log.info("数据新增成功....");
                return note.getId();
            }
        } else {
            if (updateById(note)) {
                log.info("数据更新成功...");
                return note.getId();
            }
        }
        log.info("数据更新失败...");
        return "";
    }

    @Override
    public IPage<Note> getList(int pageNum, int size, String creator) {
        Page<Note> page = new Page<>(pageNum, size);
        return page(page, new QueryWrapper<Note>()
                .lambda()
                .select(Note::getId, Note::getCategory, Note::getNoteName, Note::getState, Note::getCreateDate, Note::getCreator, Note::getModifyDate)
                .eq(Note::getCreator, creator)
                .orderByDesc(Note::getState)
                .orderByDesc(Note::getCreateDate)
                .orderByDesc(Note::getModifyDate));
    }

    @Override
    public IPageVo<NoteVo> getShowList(int pageNum, int size) {
        Page<Note> page = new Page<>(pageNum, size);
        IPage<Note> noteIPage = page(page, new QueryWrapper<Note>()
                .lambda()
                .select(Note::getId, Note::getCategory, Note::getNoteName, Note::getCreateDate, Note::getCreator, Note::getModifyDate, Note::getImageUrl)
                .eq(Note::getState, 1)
                .orderByDesc(Note::getCreateDate)
                .orderByDesc(Note::getModifyDate));
        IPageVo<NoteVo> noteVoIPageVo = BeanMapper.page(noteIPage, Note.class, NoteVo.class);
        return noteVoIPageVo;
    }

    @Override
    public NoteVo getNote(String id) {
        Note note = getById(id);
        return BeanMapper.map(note, NoteVo.class);
    }

    @Override
    public Note editNote(String id) {
        return getById(id);
    }

    @Override
    public List<NoteVo> archive(String category) {
        List<Note> notes = list(new QueryWrapper<Note>().lambda()
                .select(Note::getId, Note::getNoteName, Note::getCreateDate)
                .eq(!StringUtils.isEmpty(category), Note::getCategory, category)
                .eq(Note::getState, 1)
                .orderByDesc(Note::getCreateDate)
        );
        if (!CollectionUtils.isEmpty(notes)) {
            return BeanMapper.mapList(notes, Note.class, NoteVo.class);
        }
        return null;
    }

    @Override
    public Map<String, List<NoteVo>> archiveNotes(String category) {
        List<NoteVo> noteVos = archive(category);
        return ListUtils.archives(noteVos);
    }

    @Override
    public IPageVo<NoteVo> search(int pageNum, int size, String content) {
        Page<Note> page = new Page<>(pageNum, size);
        IPage<Note> noteIPage = page(page, new QueryWrapper<Note>()
                .lambda()
                .select(Note::getId, Note::getCategory, Note::getNoteName, Note::getCreateDate, Note::getCreator, Note::getModifyDate, Note::getImageUrl)
                .eq(Note::getState, 1)
                .and(obj -> obj.like(Note::getCategory, content).or().like(Note::getNoteName, content))
                .orderByDesc(Note::getCreateDate)
                .orderByDesc(Note::getModifyDate));
        return BeanMapper.page(noteIPage, Note.class, NoteVo.class);
    }

    @Override
    public NoteVo getPub() {
        List<Note> notes = list(new QueryWrapper<Note>().lambda().eq(Note::getState, 4).orderByDesc(Note::getCreateDate));
        if (CollectionUtils.isEmpty(notes)) {
            return null;
        }
        return BeanMapper.map(notes.get(0), NoteVo.class);
    }

    @Override
    public List<Object> getCategories() {
        return listObjs(new QueryWrapper<Note>().lambda()
                .select(Note::getCategory)
                .ne(Note::getCategory, "公告")
                .eq(Note::getIsDelete,"1")
                .groupBy(Note::getCategory));
    }

    @Override
    public boolean deleteById(String id) {
        return removeById(id);
    }

    @Override
    public IPage<Note> search(NoteVo noteVo, int page, int size) {
        Note note = BeanMapper.map(noteVo, Note.class);
        if (ObjectUtils.isEmpty(note)) {
            return this.getList(page, size, "");
        }
        Page<Note> notePage = new Page<>(page, size);
        return page(notePage, new QueryWrapper<Note>()
                .lambda()
                .select(Note::getId, Note::getNoteName, Note::getCreator, Note::getCategory, Note::getCreateDate)
                .like(!StringUtils.isEmpty(note.getNoteName()), Note::getNoteName, note.getNoteName())
                .like(!StringUtils.isEmpty(note.getCategory()), Note::getCategory, note.getCategory())
                .eq(!StringUtils.isEmpty(note.getState()), Note::getState, note.getState())
                .orderByDesc(Note::getState)
                .orderByDesc(Note::getCreateDate)
                .orderByDesc(Note::getModifyDate));
    }

}
