package com.datavistar.services;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.datavistar.entities.Course;

public interface ICourseService {

	public List<Course> getAllCourses();

	public Course getCourseById(Integer id) throws BadRequestException;

	public Course saveCourse(Course course) throws BadRequestException;

	public Course deleteCourseById(Integer id) throws BadRequestException;

	public List<Course> getCourses(Integer page, Integer limit);

	public Course updateCourse(Integer id, Course course) throws BadRequestException;

}
