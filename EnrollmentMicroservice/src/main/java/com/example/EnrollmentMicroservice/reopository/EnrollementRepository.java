package com.example.EnrollmentMicroservice.reopository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EnrollmentMicroservice.model.Enrollment;

@Repository
public interface EnrollementRepository extends JpaRepository <Enrollment , Long>{

	List<Enrollment> findByUserId(Long userId);

	Optional<Enrollment> findByUserIdAndCourseId(Long userId, Long courseId);

	List<Enrollment> findByCourseId(Long courseId);

}
