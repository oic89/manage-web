package com.turing.pojo;

import java.util.List;

public class PageBean<T> {
    //总记录数
    private Integer totalCount;
    //当前页数据
    private List<T> rows;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
