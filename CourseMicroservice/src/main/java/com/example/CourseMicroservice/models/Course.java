package com.example.CourseMicroservice.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150, unique = true)
    private String code;  // e.g., "JAVA-101"

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    /**
     * Bidirectional OneToMany to Module.
     * - cascade ALL so creating/updating Course also persists Modules
     * - orphanRemoval true so removing module from list deletes it
     * - LAZY fetch to avoid loading modules unless needed
     */
    @OneToMany(
        mappedBy = "course",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @OrderBy("sequence ASC") // optional: keep modules ordered by sequence
    private List<Module> modules = new ArrayList<>();

    // ----- Constructors -----
    public Course() {}

    public Course(String code, String title, String description) {
        this.code = code;
        this.title = title;
        this.description = description;
    }

    // ----- Convenience helpers to keep both sides in sync -----
    public void addModule(Module module) {
        modules.add(module);
        module.setCourse(this);
    }

    public void removeModule(Module module) {
        modules.remove(module);
        module.setCourse(null);
    }

    // ----- Getters/Setters -----
    public Long getId() { return id; }

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

    public List<Module> getModules() { return modules; }
    public void setModules(List<Module> modules) { 
        this.modules.clear();
        if (modules != null) {
            modules.forEach(this::addModule);
        }
    }

    // ----- equals/hashCode based on business key 'code' (and id fallback) -----
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        // Prefer unique business key; fallback to id if both present
        return Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

}
