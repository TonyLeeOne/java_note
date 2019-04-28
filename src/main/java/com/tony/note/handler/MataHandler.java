package com.tony.note.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tony.note.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

/**
 * @author jli2
 * @date 4/4/2019 9:44 AM
 **/
@Component
@Slf4j
public class MataHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("starting insert fill...");
        this.setFieldValByName("createDate",DateUtils.SIMPLE_DATE_FORMAT.format(new Date()), metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("starting update fill...");
        this.setFieldValByName("modifyDate", DateUtils.SIMPLE_DATE_FORMAT.format(new Date()), metaObject);
    }
}
