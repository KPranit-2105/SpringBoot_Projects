package com.example.EnrollmentMicroservice.Dto;

import com.example.EnrollmentMicroservice.model.Enrollment;
import com.example.EnrollmentMicroservice.model.Progress;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.EnrollmentMicroservice.model.Enrollment;
import com.example.EnrollmentMicroservice.model.Progress;


public class EnrollementResponseDto {

    private Long id;
    private Long userId;
    private Long courseId;
    private String status;
    private Progress progress;
    private Integer completionPercentage;

    // Optionally include the entity for debug (avoid in production APIs)
    private Enrollment rawEntity; // Optional; you can remove this if not needed

    public EnrollementResponseDto() {}

    public EnrollementResponseDto(Long id, Long userId, Long courseId, String status, Progress progress, Integer completionPercentage) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
        this.progress = progress;
        this.completionPercentage = completionPercentage;
    }

    public EnrollementResponseDto(Long id, Long userId, Long courseId, String status, Progress progress, Integer completionPercentage, Enrollment rawEntity) {
        this(id, userId, courseId, status, progress, completionPercentage);
        this.rawEntity = rawEntity;
    }

    // ----- Getters/Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Progress getProgress() { return progress; }
    public void setProgress(Progress progress) { this.progress = progress; }

    public Integer getCompletionPercentage() { return completionPercentage; }
    public void setCompletionPercentage(Integer completionPercentage) { this.completionPercentage = completionPercentage; }

    public Enrollment getRawEntity() { return rawEntity; }
    public void setRawEntity(Enrollment rawEntity) { this.rawEntity = rawEntity; }
}
