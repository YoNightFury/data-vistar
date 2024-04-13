package com.datavistar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datavistar.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}
