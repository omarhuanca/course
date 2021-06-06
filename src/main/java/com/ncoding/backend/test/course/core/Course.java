package com.ncoding.backend.test.course.core;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Course {

    private Long uid;

    private Category category;

    private Integer status;

    private Double price;

    private String title;

    private String description;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;

    private List<Enroll> listEnroll;

    public Course() {
    }

    public Course(Category category, Integer status, Double price, String title, String description) {
        this.category = category;
        this.status = status;
        this.price = price;
        this.title = title;
        this.description = description;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<Enroll> getListEnroll() {
        return listEnroll;
    }

    public void setListEnroll(List<Enroll> listEnroll) {
        this.listEnroll = listEnroll;
    }

    public Boolean verifyStatusEnable(Integer otherStatus) {
        return status.equals(otherStatus);
    }

    @Override
    public boolean equals(Object obj) {
        boolean response = false;

        if (this == obj) {
            response = true;
        }

        if (obj instanceof Course) {
            Course other = (Course) obj;

            response = Objects.equals(this.uid, other.uid);
        }

        return response;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}
