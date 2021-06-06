package com.ncoding.backend.test.course.service;

public enum ClassStatusEnum {

    ENABLE(1),
    DISABLE(0);

    private Integer code;

    ClassStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
