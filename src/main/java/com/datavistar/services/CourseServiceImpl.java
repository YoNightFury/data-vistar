package com.datavistar.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.datavistar.dao.CourseRepository;
import com.datavistar.entities.Course;
import com.datavistar.entities.Subject;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ISubjectService subjectService;

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(Integer id) throws BadRequestException {
		return courseRepository.findById(id).orElseThrow(() -> new BadRequestException("Invalid Course Id: " + id));
	}

	@Override
	public Course saveCourse(Course course) throws BadRequestException {
		Subject subject = subjectService.getSubjectById(course.getSubject().getId());
		course.setSubject(subject);
		return courseRepository.save(course);
	}

	@Override
	public Course deleteCourseById(Integer id) throws BadRequestException {
		Course course = getCourseById(id);
		courseRepository.deleteById(id);
		return course;
	}

	@Override
	public List<Course> getCourses(Integer page, Integer limit) {
		return courseRepository.findAll(PageRequest.of(page, limit)).getContent();
	}

	@Override
	public Course updateCourse(Integer id, Course course) throws BadRequestException {
		Course existingCourse = getCourseById(id);
		Subject subject = subjectService.getSubjectById(course.getSubject().getId());
		existingCourse.setSubject(subject);
		existingCourse.setName(course.getName());
		return courseRepository.save(existingCourse);
	}
}
