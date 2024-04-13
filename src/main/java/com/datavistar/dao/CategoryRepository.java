package com.datavistar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datavistar.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
