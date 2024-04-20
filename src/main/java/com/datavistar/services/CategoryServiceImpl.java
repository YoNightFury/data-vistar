package com.datavistar.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.datavistar.dao.CategoryRepository;
import com.datavistar.entities.Category;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Integer id) throws BadRequestException {
		return categoryRepository.findById(id).orElseThrow(() -> new BadRequestException("Invalid Category Id: " + id));
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category deleteCategoryById(Integer id) throws BadRequestException {
		Category category = getCategoryById(id);
		if (category != null) {
			categoryRepository.deleteById(id);
		}
		return category;
	}

	@Override
	public List<Category> getCategories(Integer page, Integer limit) {
		return categoryRepository.findAll(PageRequest.of(page, limit)).getContent();
	}

	@Override
	public Category updateCategory(Integer id, Category category) throws BadRequestException {
		Category existingCategory = getCategoryById(id);
		category.setId(existingCategory.getId()); // Ensure the ID is set
		return categoryRepository.save(category);
	}
}
