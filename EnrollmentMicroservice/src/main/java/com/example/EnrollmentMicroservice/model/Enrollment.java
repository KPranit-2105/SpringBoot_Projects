package com.example.EnrollmentMicroservice.model;

import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "enrollments")
public class Enrollment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long courseId;

    @Column(length = 50)
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Progress progress; // e.g., NOT_STARTED, IN_PROGRESS, COMPLETED

    @Column
    private Integer completionPercentage; // 0..100

    // ----- Constructors -----
    public Enrollment() {}

    public Enrollment(Long id, Long userId, Long courseId, String status, Progress progress, Integer completionPercentage) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
        this.progress = progress;
        this.completionPercentage = completionPercentage;
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
}

