package com.ncoding.backend.test.course.util.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({ "com.ncoding.backend.test.course.dao" })
public class MybatisConfig {

}
