package com.datavistar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datavistar.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
