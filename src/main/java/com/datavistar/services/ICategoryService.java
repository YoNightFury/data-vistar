package com.datavistar.services;

import java.util.List;

import com.datavistar.entities.Category;

public interface ICategoryService {

	public List<Category> getAllCategories();

	public Category getCategoryById(Integer id);

	public Category saveCategory(Category category);

	public Category deleteCategoryById(Integer id);

	public List<Category> getCategories(Integer page, Integer limit);

	public Category updateCategory(Category category);
}
