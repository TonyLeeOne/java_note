package com.tony.note.service.impl;

import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tony.note.constant.Constant;
import com.tony.note.controller.dto.IPageVo;
import com.tony.note.controller.dto.NoteVo;
import com.tony.note.controller.dto.UserVo;
import com.tony.note.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date 4/10/2019 11:11 AM
 **/
@Service
@Slf4j
public class CaffeineService {

    @Autowired
    private LoadingCache cache;

    public IPageVo<NoteVo> getNoteList(int pageNum, int size) {
        return (IPageVo<NoteVo>) cache.get(Constant.SHOW_NOTES + pageNum + Constant.SPLITTER + size);
    }

    public NoteVo getNote(String id) {
        return (NoteVo) cache.get(Constant.GET_NOTE.concat(id));
    }

    public Map<String, List<NoteVo>> getArchives(String category) {
        return (Map<String, List<NoteVo>>) cache.get(Constant.ARCHIVES.concat(category));
    }

    public UserVo getUser(String username) {
        return (UserVo) cache.get(Constant.USER.concat(username));
    }

    public IPageVo<NoteVo> getSearchResult(int pageNum, int size, String content) {
        return (IPageVo<NoteVo>) cache.get(Constant.SEARCH + content + Constant.UNDERLINE + pageNum +
                Constant.UNDERLINE + size);
    }

    public NoteVo getPub(){
        return (NoteVo) cache.get(Constant.PUB);
    }

    public List<Object> getCategories(){
        return (List<Object>) cache.get(Constant.ALL_CATEGORIES);
    }

    public List<Category> getAllCategories(){
        return (List<Category>) cache.get(Constant.CATEGORIES);
    }


}
