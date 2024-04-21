package com.datavistar.services;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.datavistar.entities.Category;

public interface ICategoryService {

	public List<Category> getAllCategories();

	public Category getCategoryById(Integer id) throws BadRequestException;

	public Category saveCategory(Category category) throws BadRequestException;

	public Category deleteCategoryById(Integer id) throws BadRequestException;

	public List<Category> getCategories(Integer page, Integer limit);

	public Category updateCategory(Integer id, Category category) throws BadRequestException;
}
