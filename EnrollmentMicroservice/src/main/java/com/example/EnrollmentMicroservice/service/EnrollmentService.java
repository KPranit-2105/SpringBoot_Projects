package com.example.EnrollmentMicroservice.service;

import java.util.List;

import com.example.EnrollmentMicroservice.Dto.EnrollementResponseDto;
import com.example.EnrollmentMicroservice.Dto.EnrollmentRequestDto;
import com.example.EnrollmentMicroservice.model.Progress;
import java.util.List;

import com.example.EnrollmentMicroservice.Dto.EnrollementResponseDto;
import com.example.EnrollmentMicroservice.Dto.EnrollmentRequestDto;
import com.example.EnrollmentMicroservice.model.Progress;

public interface EnrollmentService {

    // CREATE
    EnrollementResponseDto createEnrollment(EnrollmentRequestDto requestDto);

    // READ
    List<EnrollementResponseDto> getAllEnrollments();
    EnrollementResponseDto getByEnrollmentId(Long id);
    List<EnrollementResponseDto> getByCourseId(Long courseId);
    List<EnrollementResponseDto> getByUserId(Long userId);
    EnrollementResponseDto getByUserIdAndCourseId(Long userId, Long courseId);

    // STATUS
    String getStatus(Long enrollmentId);
    String getStatusByUserAndCourse(Long userId, Long courseId);

    // PROGRESS
    Progress getProgress(Long enrollmentId);
    Integer getCompletionPercentage(Long enrollmentId);

    // UPDATE
    EnrollementResponseDto updateEnrollment(Long id, EnrollmentRequestDto requestDto);
    EnrollementResponseDto updateStatus(Long id, String status);
    EnrollementResponseDto updateProgress(Long id, Progress progress, Integer completionPercentage);

    // DELETE
    void deleteEnrollment(Long id);

    // LIST IDS
    List<Long> listEnrollmentIds();
}

