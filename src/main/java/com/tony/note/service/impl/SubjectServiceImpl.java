package com.tony.note.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.note.entity.Subject;
import com.tony.note.mapper.SubjectMapper;
import com.tony.note.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jli2
 * @date 4/28/2019 10:58 AM
 **/
@Service
@Slf4j
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper,Subject> implements SubjectService {

}
