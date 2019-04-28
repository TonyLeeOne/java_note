package com.tony.note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.note.entity.Info;
import com.tony.note.mapper.InfoMapper;
import com.tony.note.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author jli2
 * @date 4/9/2019 3:13 PM
 **/
@Slf4j
@Service
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {

    @Override
    public int star(String id) {
        Info info = getOne(new QueryWrapper<Info>().eq("nid",id));
        if (ObjectUtils.isEmpty(info)) {
            Info info1 = new Info();
            info1.setNid(id).setRecommend(1);
            save(info1);
            return info1.getRecommend();
        } else {
            info.setRecommend(info.getRecommend() + 1);
            updateById(info);
        }
        return info.getRecommend();
    }

    @Override
    public int unStar(String id) {
        Info info = getOne(new QueryWrapper<Info>().eq("nid",id));
        info.setRecommend(info.getRecommend()-1);
        updateById(info);
        return info.getRecommend();
    }

    @Override
    public boolean countNote(String id) {
        Info info = getOne(new QueryWrapper<Info>().eq("nid",id));
        if(ObjectUtils.isEmpty(info)){
            Info info1=new Info();
            info1.setCount(1).setNid(id);
            return save(info1);
        }
        info.setCount(info.getCount()+1);
        return updateById(info);
    }

    @Override
    public boolean countModify(String nid) {
        Info info = getOne(new QueryWrapper<Info>().eq("nid",nid));
        if(ObjectUtils.isEmpty(info)){
            Info info1=new Info();
            info1.setCount(1).setNid(nid);
            return save(info1);
        }
        info.setCount(info.getModify()+1);
        return updateById(info);
    }

    @Override
    public Info getInfo(String nid) {
        return getOne(new QueryWrapper<Info>().eq("nid",nid));
    }


}
