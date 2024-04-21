package com.datavistar.services;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.datavistar.entities.Subject;

public interface ISubjectService {

	public List<Subject> getAllSubjects();

	public Subject getSubjectById(Integer id) throws BadRequestException;

	public Subject saveSubject(Subject subject) throws BadRequestException;

	public Subject deleteSubjectById(Integer id) throws BadRequestException;

	public List<Subject> getSubjects(Integer page, Integer limit);

	public Subject updateSubject(Integer id, Subject subject) throws BadRequestException;

}
