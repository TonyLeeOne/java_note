package com.tony.note.configuration;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tony.note.entity.Category;
import com.tony.note.service.CategoryService;
import com.tony.note.service.NoteService;
import com.tony.note.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static com.tony.note.constant.Constant.*;

/**
 * @author jli2
 * @date 4/10/2019 10:54 AM
 **/
@Configuration
@Slf4j
public class CaffeineConfiguration {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Bean("noteCache")
    public LoadingCache<String, Object> noteCache() {
        LoadingCache<String, Object> noteCache = Caffeine.newBuilder()
                .refreshAfterWrite(30, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build(key -> getCacheValueFromDB(key));
        return noteCache;
    }

    private Object getCacheValueFromDB(String key) {
        if (key.startsWith(SHOW_NOTES)) {
            String[] keys = key.split("_");
            String[] params = keys[1].split("-");
            return noteService.getShowList(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
        }
        if (key.startsWith(GET_NOTE)) {
            String[] keys = key.split("_");
            return noteService.getNote(keys[1]);
        }

        if(key.startsWith(ARCHIVES)){
            String[] keys= key.split("_");
            String category="";
            if(keys.length==2){
                category=keys[1];
            }
            return noteService.archiveNotes(category);
        }
        if(key.startsWith(USER)){
            String username= key.split("_")[1];
            return userService.getByUsername(username);
        }

        if(key.startsWith(PUB)){
            return noteService.getPub();
        }

        if(key.equals(ALL_CATEGORIES)){
            return categoryService.getList();
        }

        if(key.startsWith(SEARCH)){
            String[] res=key.split("_");
            return noteService.search(Integer.parseInt(res[2]),Integer.parseInt(res[3]),res[1]);
        }

        return null;
    }
}
