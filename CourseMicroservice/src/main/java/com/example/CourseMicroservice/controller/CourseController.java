package com.example.CourseMicroservice.controller;

import com.example.CourseMicroservice.Dto.CourseRequestDto;
import com.example.CourseMicroservice.Dto.CourseResponseDto;

import com.example.CourseMicroservice.service.CourseService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // ---------- CREATE ----------
    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseRequestDto request) {
        CourseResponseDto created = courseService.createCourse(request);
        // Location header: /api/courses/{id}
        URI location = URI.create(String.format("/api/courses/%d", created.getId()));
        return ResponseEntity.created(location).body(created);
    }

    // ---------- UPDATE ----------
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponseDto> updateCourse(
            @PathVariable Long courseId,
            @Valid @RequestBody CourseRequestDto request) {
        CourseResponseDto updated = courseService.updateCourse(courseId, request);
        return ResponseEntity.ok(updated);
    }

    // ---------- READ: BY ID ----------
    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long courseId) {
        CourseResponseDto dto = courseService.getCourseById(courseId);
        return ResponseEntity.ok(dto);
    }

    // ---------- READ: BY CODE ----------
    @GetMapping("/code/{code}")
    public ResponseEntity<CourseResponseDto> getCourseByCode(@PathVariable String code) {
        CourseResponseDto dto = courseService.getCourseByCode(code);
        return ResponseEntity.ok(dto);
    }

    // ---------- READ: ALL ----------
    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        List<CourseResponseDto> list = courseService.getAllCourses();
        return ResponseEntity.ok(list);
    }

    // ---------- DELETE ----------
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    // ---------- Optional: Simple health endpoint ----------
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Course API is up");
    }
}

