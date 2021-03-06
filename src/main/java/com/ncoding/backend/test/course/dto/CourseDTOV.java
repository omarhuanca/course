package com.ncoding.backend.test.course.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ncoding.backend.test.course.core.Category;
import com.ncoding.backend.test.course.core.Course;

public class CourseDTOV {

    @NotNull(message = "The category is not valid.")
    private Category category;

    @Min(value = 0, message = "The state cannot be less than 0 o greather than 1.")
    @Max(1)
    private Integer status;

    @NotNull(message = "The price is not valid.")
    @Min(value = 0, message = "The price cannot be less than 0.")
    private Double price;

    @NotNull(message = "The title is not valid.")
    @Size(max = 30, message = "The title no should be greather than 30 characters.")
    @NotBlank(message = "The title is not valid.")
    private String title;

    @NotNull(message = "The description not valid.")
    @Size(max = 500, message = "The description should be greather than 500 characters.")
    @NotBlank(message = "The description is not valid.")
    private String description;

    public void copyCoreObject(Course object) {
        object.setCategory(category);
        object.setStatus(status);
        object.setPrice(price);
        object.setTitle(title);
        object.setDescription(description);
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
}
