package com.tony.note.controller.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author jli2
 * @date 1/25/2019 4:22 PM
 **/
@Data
@ToString
public class IPageVo<T> implements Serializable {
    private static final long serialVersionUID = -5530881942915024775L;
    private long current;
    private long pageCount;
    private long total;
    private long pageSize;
    private boolean hasNext;
    private boolean hasPrev;
    private List<T> records;
}
