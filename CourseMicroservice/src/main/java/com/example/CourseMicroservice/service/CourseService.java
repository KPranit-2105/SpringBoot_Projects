package com.example.CourseMicroservice.service;

import java.util.List;
import java.util.Optional;

import com.example.CourseMicroservice.Dto.CourseRequestDto;
import com.example.CourseMicroservice.Dto.CourseResponseDto;
import jakarta.validation.Valid;

public interface CourseService {

    // ------- COURSE CRUD -------
    CourseResponseDto createCourse(@Valid CourseRequestDto request);
    CourseResponseDto updateCourse(Long courseId, @Valid CourseRequestDto request);
    void deleteCourse(Long courseId);

    CourseResponseDto getCourseById(Long courseId);
    CourseResponseDto getCourseByCode(String code);
    List<CourseResponseDto> getAllCourses();

    // ------- MODULE MANAGEMENT -------
    Module addModuleToCourse(Long courseId, Module module);
    Module updateModule(Long courseId, Long moduleId, Module updated);
    void removeModuleFromCourse(Long courseId, Long moduleId);

    List<com.example.CourseMicroservice.models.Module> listModules(Long courseId);
    Optional<com.example.CourseMicroservice.models.Module> getModuleById(Long courseId, Long moduleId);
    Optional<com.example.CourseMicroservice.models.Module> getModuleByCode(Long courseId, String moduleCode);
}

