package com.datavistar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datavistar.entities.Content;

public interface ContentRepository extends JpaRepository<Content, Integer> {

}
