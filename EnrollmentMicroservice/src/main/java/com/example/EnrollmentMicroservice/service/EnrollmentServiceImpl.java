package com.example.EnrollmentMicroservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.EnrollmentMicroservice.Dto.EnrollementResponseDto;
import com.example.EnrollmentMicroservice.Dto.EnrollmentRequestDto;
import com.example.EnrollmentMicroservice.exceptions.EnrollmentNotFoundException;
import com.example.EnrollmentMicroservice.model.Enrollment;
import com.example.EnrollmentMicroservice.model.Progress;
import com.example.EnrollmentMicroservice.reopository.EnrollementRepository;

@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollementRepository enrollmentRepository;

    public EnrollmentServiceImpl(EnrollementRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // ---------- CREATE ----------
    @Override
    public EnrollementResponseDto createEnrollment(EnrollmentRequestDto requestDto) {
        Enrollment e = new Enrollment();
        e.setCourseId(requestDto.getCourseId());
        e.setUserId(requestDto.getUserId());
        e.setStatus(requestDto.getStatus());
        e.setProgress(requestDto.getProgress());
        e.setCompletionPercentage(requestDto.getCompletionPercentage());

        Enrollment saved = enrollmentRepository.save(e);
        return toDto(saved);
    }

    // ---------- READ: ALL ----------
    @Override
    public List<EnrollementResponseDto> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ---------- READ: BY ENROLLMENT ID ----------
    @Override
    public EnrollementResponseDto getByEnrollmentId(Long id) {
        Enrollment e = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        return toDto(e);
    }

    // ---------- READ: BY COURSE ID ----------
    @Override
    public List<EnrollementResponseDto> getByCourseId(Long courseId) {
        List<Enrollment> list = enrollmentRepository.findByCourseId(courseId);
        if (list.isEmpty()) {
            throw new EnrollmentNotFoundException("No enrollments found for courseId: " + courseId);
        }
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    // ---------- READ: BY USER ID ----------
    @Override
    public List<EnrollementResponseDto> getByUserId(Long userId) {
        List<Enrollment> list = enrollmentRepository.findByUserId(userId);
        if (list.isEmpty()) {
            throw new EnrollmentNotFoundException("No enrollments found for userId: " + userId);
        }
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    // ---------- READ: BY USER + COURSE ----------
    @Override
    public EnrollementResponseDto getByUserIdAndCourseId(Long userId, Long courseId) {
        Enrollment e = enrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new EnrollmentNotFoundException(
                        "Enrollment not found for userId: " + userId + ", courseId: " + courseId));
        return toDto(e);
    }

    // ---------- STATUS ----------
    @Override
    public String getStatus(Long enrollmentId) {
        Enrollment e = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + enrollmentId));
        return e.getStatus();
    }

    @Override
    public String getStatusByUserAndCourse(Long userId, Long courseId) {
        Enrollment e = enrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new EnrollmentNotFoundException(
                        "Enrollment not found for userId: " + userId + ", courseId: " + courseId));
        return e.getStatus();
    }

    // ---------- PROGRESS ----------
    @Override
    public Progress getProgress(Long enrollmentId) {
        Enrollment e = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + enrollmentId));
        return e.getProgress();
    }

    @Override
    public Integer getCompletionPercentage(Long enrollmentId) {
        Enrollment e = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + enrollmentId));
        return e.getCompletionPercentage();
    }

    // ---------- UPDATE ----------
    @Override
    public EnrollementResponseDto updateEnrollment(Long id, EnrollmentRequestDto requestDto) {
        Enrollment e = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));

        e.setCourseId(requestDto.getCourseId());
        e.setUserId(requestDto.getUserId());
        e.setStatus(requestDto.getStatus());
        e.setProgress(requestDto.getProgress());
        // Clamp completion 0..100 for safety if provided
        Integer cp = requestDto.getCompletionPercentage();
        e.setCompletionPercentage(cp == null ? null : Math.max(0, Math.min(100, cp)));

        Enrollment updated = enrollmentRepository.save(e);
        return toDto(updated);
    }

    @Override
    public EnrollementResponseDto updateStatus(Long id, String status) {
        Enrollment e = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        e.setStatus(status);
        return toDto(enrollmentRepository.save(e));
    }

    @Override
    public EnrollementResponseDto updateProgress(Long id, Progress progress, Integer completionPercentage) {
        Enrollment e = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        e.setProgress(progress);
        if (completionPercentage != null) {
            e.setCompletionPercentage(Math.max(0, Math.min(100, completionPercentage)));
        }
        return toDto(enrollmentRepository.save(e));
    }

    // ---------- DELETE ----------
    @Override
    public void deleteEnrollment(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException("Enrollment not found with id: " + id);
        }
        enrollmentRepository.deleteById(id);
    }

    // ---------- LIST IDS ----------
    @Override
    public List<Long> listEnrollmentIds() {
        return enrollmentRepository.findAll()
                .stream()
                .map(Enrollment::getId)
                .collect(Collectors.toList());
    }

    // ---------- Helper ----------
    private EnrollementResponseDto toDto(Enrollment e) {
        return new EnrollementResponseDto(
            e.getId(),
            e.getUserId(),
            e.getCourseId(),
            e.getStatus(),
            e.getProgress(),
            e.getCompletionPercentage()
        );
    }
}
