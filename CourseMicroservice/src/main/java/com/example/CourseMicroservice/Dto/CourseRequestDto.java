package com.example.CourseMicroservice.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request payload for creating/updating a Course.
 */

public class CourseRequestDto {

    @NotBlank
    @Size(max = 150)
    private String code; // e.g., "JAVA-101"

    @NotBlank
    @Size(max = 255)
    private String title;

    @Size(max = 2000)
    private String description;

    public CourseRequestDto() {}

    public CourseRequestDto(String code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

    // ----- Getters/Setters -----
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}


