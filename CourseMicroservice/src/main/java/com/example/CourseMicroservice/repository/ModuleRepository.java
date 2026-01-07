package com.example.CourseMicroservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CourseMicroservice.models.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByCourseId(Long courseId);
    List<Module> findByCourseCodeOrderBySequenceAsc(String courseCode);
    Optional<Module> findByCourseCodeAndCode(String courseCode, String moduleCode);
}
