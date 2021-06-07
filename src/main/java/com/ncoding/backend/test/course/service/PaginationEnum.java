package com.ncoding.backend.test.course.service;

public enum PaginationEnum {

    DEFAULT_PAGE_SIZE(10), MAX_PAGE_SIZE(1000), DEFAULT_PAGE(0);

    private Integer code;

    PaginationEnum(Integer code) {
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
}
