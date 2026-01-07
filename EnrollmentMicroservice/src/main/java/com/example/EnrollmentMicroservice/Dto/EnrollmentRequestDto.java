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
import com.example.EnrollmentMicroservice.model.Progress;
import jakarta.validation.constraints.*;


public class EnrollmentRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long courseId;

    @Size(max = 50)
    private String status; // Optional in request; can be set later

    private Progress progress; // Optional

    @Min(0)
    @Max(100)
    private Integer completionPercentage; // Optional

    public EnrollmentRequestDto() {}

    public EnrollmentRequestDto(Long userId, Long courseId, String status, Progress progress, Integer completionPercentage) {
        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
        this.progress = progress;
        this.completionPercentage = completionPercentage;
    }

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
}
