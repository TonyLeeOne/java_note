package com.tony.note.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tony.note.controller.dto.IPageVo;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;


import java.util.List;

/**
 * @author jli2
 * @date 1/28/2019 5:53 PM
 **/
public class BeanMapper {

    private static MapperFacade mapper;

    static {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }

    /**
     * 简单的复制出新类型对象.
     * <p>
     * 通过source.getClass() 获得源Class
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return mapper.map(source, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象.
     * <p>
     * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
     */
    public static <S, D> D map(S source, Type<S> sourceType, Type<D> destinationType) {
        return mapper.map(source, sourceType, destinationType);
    }

    /**
     * 简单的复制出新对象列表到ArrayList
     * <p>
     * 不建议使用mapper.mapAsList(Iterable<S>,Class<D>)接口, sourceClass需要反射，实在有点慢
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<S> sourceClass, Class<D> destinationClass) {
        return mapper.mapAsList(sourceList, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
    }

    /**
     * 极致性能的复制出新类型对象到ArrayList.
     * <p>
     * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Type<S> sourceType, Type<D> destinationType) {
        return mapper.mapAsList(sourceList, sourceType, destinationType);
    }


    /**
     * 简单复制出新对象列表到数组
     * <p>
     * 通过source.getComponentType() 获得源Class
     */
    public static <S, D> D[] mapArray(final D[] destination, final S[] source, final Class<D> destinationClass) {
        return mapper.mapAsArray(destination, source, destinationClass);
    }

    /**
     * 极致性能的复制出新类型对象到数组
     * <p>
     * 预先通过BeanMapper.getType() 静态获取并缓存Type类型，在此处传入
     */
    public static <S, D> D[] mapArray(D[] destination, S[] source, Type<S> sourceType, Type<D> destinationType) {
        return mapper.mapAsArray(destination, source, sourceType, destinationType);
    }

    /**
     * 预先获取orika转换所需要的Type，避免每次转换.
     */
    public static <E> Type<E> getType(final Class<E> rawType) {
        return TypeFactory.valueOf(rawType);
    }

    public static MapperFacade getMapper() {
        return mapper;
    }


    public static <S, D> IPageVo<D> page(IPage<S> iPage, Class<S> sourceClass, Class<D> destinationClass) {
        List<D> list = mapList(iPage.getRecords(), sourceClass, destinationClass);
        IPageVo<D> iPageVo = map(iPage, IPageVo.class);
        iPageVo.setPageCount(iPage.getPages());
        iPageVo.setPageSize(iPage.getSize());
        iPageVo.setRecords(list);
        iPageVo.setHasNext(iPageVo.getCurrent() < iPageVo.getPageCount());
        iPageVo.setHasPrev(iPageVo.getCurrent() > 1);
        return iPageVo;
    }


}
