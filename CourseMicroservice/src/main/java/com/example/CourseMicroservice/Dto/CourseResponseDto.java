package com.example.CourseMicroservice.Dto;

import java.time.Instant;

/**
 * Response payload for returning Course info.
 * Keeps it simple: scalar fields + moduleCount (no nested DTOs to honor "two classes").
 */

public class CourseResponseDto {

    private Long id;
    private String code;
    private String title;
    private String description;

    private Instant createdAt;
    private Instant updatedAt;

    private int moduleCount; // number of modules in this course

    public CourseResponseDto() {}

    public CourseResponseDto(Long id,
                             String code,
                             String title,
                             String description,
                             Instant createdAt,
                             Instant updatedAt,
                             int moduleCount) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.moduleCount = moduleCount;
    }

    // ----- Getters/Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    public int getModuleCount() { return moduleCount; }
    public void setModuleCount(int moduleCount) { this.moduleCount = moduleCount; }
}

