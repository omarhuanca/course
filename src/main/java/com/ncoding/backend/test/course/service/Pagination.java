package com.ncoding.backend.test.course.service;

public enum Pagination {

    DEFAULT_PAGESIZE(10), MAX_PAGE_SIZE(1000), DEFAULT_PAGE(0);

    private Integer code;

    Pagination(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
