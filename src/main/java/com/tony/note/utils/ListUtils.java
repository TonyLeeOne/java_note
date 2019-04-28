package com.tony.note.utils;

import com.tony.note.controller.dto.NoteVo;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author jli2
 * @date 4/10/2019 3:17 PM
 **/
public class ListUtils {

    public static Map<String, List<NoteVo>> archives(List<NoteVo> noteVos) {
        Map<String, List<NoteVo>> maps = new HashMap<>();
        Set<String> keys = new HashSet<>();
        if (!CollectionUtils.isEmpty(noteVos)) {
            noteVos.forEach(vo -> {
                keys.add(vo.getCreateDate().split(" ")[0]);
            });
            keys.forEach(key -> {
                List<NoteVo> noteVoList = new ArrayList<>();
                noteVos.forEach(vo -> {
                    if (vo.getCreateDate().startsWith(key)) {
                        noteVoList.add(vo);
                    }
                });
                maps.put(key, noteVoList);
            });
        }
        return maps;
    }
}
