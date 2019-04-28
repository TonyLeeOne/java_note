package com.tony.note.controller;

import com.tony.note.controller.dto.InfoVo;
import com.tony.note.entity.Info;
import com.tony.note.service.InfoService;
import com.tony.note.utils.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jli2
 * @date 4/9/2019 3:36 PM
 **/
@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/star")
    private int star(String id){
        return infoService.star(id);
    }

    @GetMapping("/unStar")
    private int unStar(String id){
        return infoService.unStar(id);
    }

    @GetMapping
    private InfoVo getDetail(String nid){
        Info info=infoService.getInfo(nid);
        return BeanMapper.map(info,InfoVo.class);
    }

}
