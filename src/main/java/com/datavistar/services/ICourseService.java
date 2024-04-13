package com.datavistar.services;

import java.util.List;

import com.datavistar.entities.Course;

public interface ICourseService {

	public List<Course> getAllCourses();

	public Course getCourseById(Integer id);

	public Course saveCourse(Course course);

	public Course deleteCourseById(Integer id);

	public List<Course> getCourses(Integer page, Integer limit);

	public Course updateCourse(Course course);

}
