package com.datavistar.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datavistar.dto.CourseDto;
import com.datavistar.dto.Response;
import com.datavistar.entities.Course;
import com.datavistar.entities.Subject;
import com.datavistar.services.ICourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        List<CourseDto> courseDtos = courses.stream()
                .map(course -> new CourseDto(course.getName(), course.getId(), course.getSubject().getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(courseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Integer id) throws BadRequestException {
        Course course = courseService.getCourseById(id);
        return ResponseEntity
                .ok(new CourseDto(course.getName(), course.getId(), course.getSubject().getId()));
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto) throws BadRequestException {
        if (courseDto == null)
            throw new BadRequestException("Empty Body");
        Course course = new Course();
        course.setName(courseDto.getName());
        Subject subject = new Subject();
        subject.setId(courseDto.getSubjectId());
        course.setSubject(subject);
        Course savedCourse = courseService.saveCourse(course);
        if (savedCourse != null) {
            return ResponseEntity.ok(new Response(savedCourse.getId().toString(), true));
        }
        throw new BadRequestException("Unable to create course");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @RequestBody CourseDto courseDto)
            throws BadRequestException {
        Course course = new Course();
        course.setName(courseDto.getName());
        Subject subject = new Subject();
        subject.setId(courseDto.getSubjectId());
        course.setSubject(subject);
        course = courseService.updateCourse(id, course);
        if (course != null) {
            return ResponseEntity.ok(new Response(course.getId().toString(), true));
        }
        throw new BadRequestException("Unable to update course");
    }
}
