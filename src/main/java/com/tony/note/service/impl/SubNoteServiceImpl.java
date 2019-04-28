package com.tony.note.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.note.entity.SubNote;
import com.tony.note.mapper.SubNoteMapper;
import com.tony.note.service.SubNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jli2
 * @date 4/28/2019 10:59 AM
 **/
@Service
@Slf4j
public class SubNoteServiceImpl extends ServiceImpl<SubNoteMapper,SubNote> implements SubNoteService {
}
