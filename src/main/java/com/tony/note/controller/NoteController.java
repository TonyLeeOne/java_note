package com.tony.note.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tony.note.constant.Constant;
import com.tony.note.controller.dto.IPageVo;
import com.tony.note.controller.dto.NoteVo;
import com.tony.note.entity.Info;
import com.tony.note.entity.Note;
import com.tony.note.service.InfoService;
import com.tony.note.service.NoteService;
import com.tony.note.service.impl.CaffeineService;
import com.tony.note.utils.BeanMapper;
import com.tony.note.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date 4/4/2019 10:36 AM
 **/
@Controller
@RequestMapping("/note")
@Slf4j
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private InfoService infoService;

    @Autowired
    private CaffeineService caffeineService;

    @PostMapping
    @ResponseBody
    private boolean save(@RequestBody NoteVo note, HttpSession session) {
        return noteService.saveNote(note,session);
    }

    @PostMapping("/sync")
    @ResponseBody
    private String sync(@RequestBody NoteVo note, HttpSession session) {
        return noteService.syncNote(note,session);
    }


    @GetMapping("/list")
    private String getList(int pageNum, int size, ModelMap modelMap,HttpSession session) {
        IPage<Note> noteIPage = noteService.getList(pageNum, size,(String)session.getAttribute("username"));
        IPageVo<NoteVo> noteIPageVo = BeanMapper.page(noteIPage, Note.class, NoteVo.class);
        modelMap.addAttribute("data", noteIPageVo);
        return "list";
    }

    @GetMapping("/show")
    private String getShowList(int pageNum, int size, ModelMap modelMap) {
        modelMap.addAttribute("data", caffeineService.getNoteList(pageNum, size));
        modelMap.addAttribute(Constant.ALL_CATEGORIES, caffeineService.getCategories());
        return "index";
    }

    @PostMapping("/more")
    @ResponseBody
    private IPageVo<NoteVo> getShowList(int pageNum, int size) {
        return caffeineService.getNoteList(pageNum, size);
    }

    @GetMapping
    private String getNote(String id, ModelMap modelMap) {
        Note note = noteService.editNote(id);
        NoteVo noteVo = BeanMapper.map(note, NoteVo.class);
        modelMap.addAttribute("note", noteVo);
        return "edit";
    }

    @GetMapping("/view")
    private String getNoteDetail(String id, ModelMap modelMap) {
        infoService.countNote(id);
        modelMap.addAttribute("note", caffeineService.getNote(id));
        return "view";
    }


    @GetMapping("/wr")
    private String getNote() {
        return "add";
    }

    @GetMapping("/search")
    private String search(String noteName, String category, String state, int pageNum, int size, ModelMap modelMap) {
        NoteVo noteVo = new NoteVo();
        noteVo.setNoteName(noteName).setCategory(category).setState(state);
        IPage<Note> noteIPage = noteService.search(noteVo, pageNum, size);
        IPageVo<NoteVo> noteVoIPageVo = BeanMapper.page(noteIPage, Note.class, NoteVo.class);
        modelMap.addAttribute("data", noteVoIPageVo);
        return "list";
    }

    @DeleteMapping
    @ResponseBody
    private boolean delNote(String id) {
        return noteService.deleteById(id);
    }


    @GetMapping("/archive")
    private String archive(String category, ModelMap modelMap) {
        Map<String, List<NoteVo>> res = caffeineService.getArchives(category);
        modelMap.addAttribute("data", res);
        return "archive";
    }

    @GetMapping("/find")
    private String find(int pageNum,int size,String content,ModelMap modelMap){
        modelMap.addAttribute("data",caffeineService.getSearchResult(pageNum,size,content));
        return "search";
    }

    @GetMapping("/find/more")
    @ResponseBody
    private IPageVo<NoteVo> load(int pageNum,int size,String content){
        return caffeineService.getSearchResult(pageNum,size,content);
    }

    @GetMapping("/publisher")
    private String getNoteDetail( ModelMap modelMap) {
        modelMap.addAttribute("note", caffeineService.getPub());
        return "publisher";
    }


    @RequestMapping("/test")
    @ResponseBody
    private String test(){
        return "Test Success";
    }
}
