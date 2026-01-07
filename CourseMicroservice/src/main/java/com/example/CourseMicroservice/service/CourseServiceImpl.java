package com.example.CourseMicroservice.service;

import com.example.CourseMicroservice.Dto.CourseRequestDto;
import com.example.CourseMicroservice.Dto.CourseResponseDto;
import com.example.CourseMicroservice.exceptions.CourseNotFoundException;
import com.example.CourseMicroservice.models.Course;
import com.example.CourseMicroservice.models.Module;
import com.example.CourseMicroservice.repository.CourseRepository;
import com.example.CourseMicroservice.repository.ModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;

    public CourseServiceImpl(CourseRepository courseRepository, ModuleRepository moduleRepository) {
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
    }

    // ==================== CREATE ====================
    public CourseResponseDto createCourse(CourseRequestDto request) {
        Course course = new Course();
        course.setCode(request.getCode());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setCreatedAt(Instant.now());
        course.setUpdatedAt(Instant.now());

        // If modules are ever pre-attached externally, ensure both sides are synced:
        if (course.getModules() != null) {
            course.getModules().forEach(m -> m.setCourse(course));
        }

        Course saved = courseRepository.save(course);
        return toDto(saved);
    }

    // ==================== UPDATE ====================
    public CourseResponseDto updateCourse(Long courseId, CourseRequestDto request) {
        Course existing = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

        // Update only provided scalar fields
        if (request.getCode() != null && !request.getCode().isBlank()) {
            existing.setCode(request.getCode());
        }
        if (request.getTitle() != null) {
            existing.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            existing.setDescription(request.getDescription());
        }

        existing.setUpdatedAt(Instant.now());
        Course updated = courseRepository.save(existing);
        return toDto(updated);
    }

    // ==================== READ ====================
    @Override
    @Transactional(readOnly = true)
    public CourseResponseDto getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));
        return toDto(course);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponseDto getCourseByCode(String code) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with code: " + code));
        return toDto(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ==================== DELETE ====================
    @Override
    public void deleteCourse(Long courseId) {
        Course existing = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));
        courseRepository.delete(existing); // orphanRemoval on Course will delete modules
    }

    // ==================== MODULE HELPERS (optional) ====================
    // If you later decide to handle modules through this service,
    // you can add methods that still return CourseResponseDto after mutation
    // to keep API consistent with "two DTOs" only.

    // ==================== MAPPING ====================
    /** Map Entity -> Response DTO */
    private CourseResponseDto toDto(Course c) {
        int moduleCount = (c.getModules() == null) ? 0 : c.getModules().size();
        return new CourseResponseDto(
                c.getId(),
                c.getCode(),
                c.getTitle(),
                c.getDescription(),
                c.getCreatedAt(),
                c.getUpdatedAt(),
                moduleCount
        );
    }

	@Override
	public java.lang.Module addModuleToCourse(Long courseId, java.lang.Module module) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public java.lang.Module updateModule(Long courseId, Long moduleId, java.lang.Module updated) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeModuleFromCourse(Long courseId, Long moduleId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Module> listModules(Long courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Module> getModuleById(Long courseId, Long moduleId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Module> getModuleByCode(Long courseId, String moduleCode) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}