package com.datavistar.services;

import java.util.List;

import com.datavistar.entities.Subject;

public interface ISubjectService {

	public List<Subject> getAllSubjects();

	public Subject getSubjectById(Integer id);

	public Subject saveSubject(Subject subject);

	public Subject deleteSubjectById(Integer id);

	public List<Subject> getSubjects(Integer page, Integer limit);

}
