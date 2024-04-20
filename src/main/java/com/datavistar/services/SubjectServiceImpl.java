package com.datavistar.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.datavistar.dao.SubjectRepository;
import com.datavistar.entities.Category;
import com.datavistar.entities.Subject;

@Service
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private ICategoryService categoryService;

	@Override
	public List<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}

	@Override
	public Subject getSubjectById(Integer id) {
		return subjectRepository.findById(id).orElse(null);
	}

	@Override
	public Subject saveSubject(Subject subject) throws BadRequestException {
		Integer catid = subject.getCategory().getId();
		Category category = categoryService.getCategoryById(catid);
		subject.setCategory(category);
		return subjectRepository.save(subject);
	}

	@Override
	public Subject deleteSubjectById(Integer id) {
		Subject subject = getSubjectById(id);
		subjectRepository.deleteById(id);
		return subject;
	}

	@Override
	public List<Subject> getSubjects(Integer page, Integer limit) {
		return subjectRepository.findAll(PageRequest.of(page, limit)).getContent();
	}

	@Override
	public Subject updateSubject(Integer id, Subject subject) throws BadRequestException {
		Subject existingSubject = getSubjectById(id);
		Integer catid = subject.getCategory().getId();
		Category category = categoryService.getCategoryById(catid);
		subject.setCategory(category);
		subject.setId(existingSubject.getId()); // Ensure the ID is set
		return subjectRepository.save(subject);
	}
}
