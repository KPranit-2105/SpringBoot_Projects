package com.example.EnrollmentMicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EnrollmentMicroservice.Dto.EnrollementResponseDto;
import com.example.EnrollmentMicroservice.Dto.EnrollmentRequestDto;
import com.example.EnrollmentMicroservice.model.Enrollment;
import com.example.EnrollmentMicroservice.reopository.EnrollementRepository;
import com.example.EnrollmentMicroservice.service.EnrollmentService;
import com.example.EnrollmentMicroservice.service.EnrollmentServiceImpl;

import jakarta.validation.Valid;
import com.example.EnrollmentMicroservice.Dto.EnrollementResponseDto;
import com.example.EnrollmentMicroservice.Dto.EnrollmentRequestDto;
import com.example.EnrollmentMicroservice.model.Progress;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    // ----- CREATE -----
    @PostMapping
    public ResponseEntity<EnrollementResponseDto> create(@Valid @RequestBody EnrollmentRequestDto request) {
        EnrollementResponseDto dto = service.createEnrollment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    // ----- READ: ALL -----
    @GetMapping
    public ResponseEntity<List<EnrollementResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAllEnrollments());
    }

    // ----- READ: BY ENROLLMENT ID -----
    @GetMapping("/{id}")
    public ResponseEntity<EnrollementResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getByEnrollmentId(id));
    }

    // ----- READ: BY COURSE ID -----
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollementResponseDto>> getByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getByCourseId(courseId));
    }

    // ----- READ: BY USER ID -----
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EnrollementResponseDto>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    // ----- READ: BY USER + COURSE -----
    @GetMapping("/lookup")
    public ResponseEntity<EnrollementResponseDto> getByUserAndCourse(
            @RequestParam Long userId,
            @RequestParam Long courseId) {
        return ResponseEntity.ok(service.getByUserIdAndCourseId(userId, courseId));
    }

    // ----- STATUS -----
    @GetMapping("/{id}/status")
    public ResponseEntity<String> getStatus(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStatus(id));
    }

    // ----- PROGRESS -----
    @GetMapping("/{id}/progress")
    public ResponseEntity<Progress> getProgress(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProgress(id));
    }

    @GetMapping("/{id}/completion")
    public ResponseEntity<Integer> getCompletion(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCompletionPercentage(id));
    }

    // ----- UPDATE (full) -----
    @PutMapping("/{id}")
    public ResponseEntity<EnrollementResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody EnrollmentRequestDto request) {
        return ResponseEntity.ok(service.updateEnrollment(id, request));
    }

    // ----- UPDATE STATUS (partial) -----
    @PatchMapping("/{id}/status")
    public ResponseEntity<EnrollementResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    // ----- UPDATE PROGRESS (partial) -----
    @PatchMapping("/{id}/progress")
    public ResponseEntity<EnrollementResponseDto> updateProgress(
            @PathVariable Long id,
            @RequestParam Progress progress,
            @RequestParam(required = false) Integer completionPercentage) {
        return ResponseEntity.ok(service.updateProgress(id, progress, completionPercentage));
    }

    // ----- DELETE -----
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }

    // ----- LIST IDS -----
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> listIds() {
        return ResponseEntity.ok(service.listEnrollmentIds());
    }
}
