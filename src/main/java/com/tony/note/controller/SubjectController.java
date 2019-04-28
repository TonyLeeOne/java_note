package com.tony.note.controller;

import com.tony.note.controller.dto.SubjectVo;
import com.tony.note.entity.Subject;
import com.tony.note.service.SubjectService;
import com.tony.note.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jli2
 * @date 4/28/2019 11:24 AM
 **/
@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    @ResponseBody
    private boolean add(@RequestBody SubjectVo subjectVo){
        Subject subject=BeanMapper.map(subjectVo,Subject.class);
        return subjectService.save(subject);
    }


}
