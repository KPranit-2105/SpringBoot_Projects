package com.example.CourseMicroservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(
    name = "modules",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_module_code_course", columnNames = {"code", "course_id"})
    }
)
public class Module implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Module code unique within a course (e.g., "M1", "INTRO", etc.)
    @Column(nullable = false, length = 100)
    private String code;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 4000)
    private String content; // optional: text/markdown/HTML

    @Column(nullable = false)
    private Integer sequence = 1; // ordering within the course

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    /**
     * Owning side of the relationship (has the foreign key).
     * The 'course_id' column references courses.id.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    // ----- Constructors -----
    public Module() {}

    public Module(String code, String title, String content, Integer sequence) {
        this.code = code;
        this.title = title;
        this.content = content;
        this.sequence = sequence;
    }

    // ----- Getters/Setters -----
    public Long getId() { return id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Integer getSequence() { return sequence; }
    public void setSequence(Integer sequence) { this.sequence = sequence; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    // ----- equals/hashCode based on unique pair (code + course) -----
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;
        Module module = (Module) o;
        return Objects.equals(code, module.code) &&
               Objects.equals(course != null ? course.getCode() : null,
                              module.course != null ? module.course.getCode() : null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, course != null ? course.getCode() : null);
    }
}

