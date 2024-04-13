package com.datavistar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datavistar.entities.Topic;

public interface ContentRepository extends JpaRepository<Topic, Integer> {

}
