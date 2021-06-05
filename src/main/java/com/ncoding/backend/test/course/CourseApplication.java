package com.ncoding.backend.test.course;

import javax.annotation.PostConstruct;

import com.ncoding.backend.test.course.util.AElog;
import com.ncoding.backend.test.course.util.AEutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(CourseApplication.class);

    @Autowired
    private AEutil util;

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    @PostConstruct
    public void runPostConstruct() {
      AElog.info1(logger, "The process runPostConstruct() was class"); 
    }

  @Override
  public void run(String... args) throws Exception {
    AElog.info1(logger, "The process run(String ...args) was class");
    util.loadData();
  }
}
