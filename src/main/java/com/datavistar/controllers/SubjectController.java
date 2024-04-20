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

import com.datavistar.dto.Response;
import com.datavistar.dto.SubjectDto;
import com.datavistar.entities.Category;
import com.datavistar.entities.Subject;
import com.datavistar.services.ISubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        List<SubjectDto> subjectDtos = subjects.stream()
                .map(subject -> new SubjectDto(subject.getName(), subject.getId(), subject.getCategory().getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(subjectDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable Integer id) {
        Subject subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(new SubjectDto(subject.getName(), subject.getId(), subject.getCategory().getId()));
    }

    @PostMapping
    public ResponseEntity<?> createSubject(@RequestBody SubjectDto subjectDto) throws BadRequestException {
        if (subjectDto == null)
            throw new BadRequestException("Empty Body");
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        Category category = new Category();
        category.setId(subjectDto.getCategoryId());
        subject.setCategory(category);
        Subject savedSubject = subjectService.saveSubject(subject);
        if (savedSubject != null) {
            return ResponseEntity.ok(new Response(savedSubject.getId().toString(), true));
        }
        throw new BadRequestException("Unable to create subject");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable int id, @RequestBody SubjectDto subjectDto)
            throws BadRequestException {
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        Category category = new Category();
        category.setId(subjectDto.getCategoryId());
        subject.setCategory(category);
        subject = subjectService.updateSubject(id, subject);
        if (subject != null) {
            return ResponseEntity.ok(new Response(subject.getId().toString(), true));
        }
        throw new BadRequestException("Unable to update subject");
    }
}
