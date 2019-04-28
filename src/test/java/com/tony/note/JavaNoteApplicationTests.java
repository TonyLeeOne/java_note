package com.tony.note;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tony.note.entity.Note;
import com.tony.note.mapper.NoteMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaNoteApplicationTests {

    @Resource
    private NoteMapper noteMapper;

    @Test
    public void contextLoads() {
//        int i=noteMapper.insert(new Note().setCategory("JAVA").setContent("Test").setNoteName("Tony"));
//        Assert.assertTrue(i>0);

        List<Note> notes= noteMapper.selectList(new QueryWrapper<Note>().lambda().select(Note::getId,Note::getCategory,
                Note::getContent,Note::getCreator,Note::getCreateDate));
        System.out.println(notes.toString());

    }

}
