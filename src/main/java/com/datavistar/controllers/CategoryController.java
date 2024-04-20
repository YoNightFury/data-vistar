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

import com.datavistar.dto.CategoryDto;
import com.datavistar.dto.Response;
import com.datavistar.entities.Category;
import com.datavistar.services.ICategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		List<CategoryDto> catDtos = categories.stream()
				.map(category -> new CategoryDto(category.getName(), category.getId())).collect(Collectors.toList());
		return ResponseEntity.ok(catDtos);

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable Integer id) throws BadRequestException {
		Category cat = categoryService.getCategoryById(id);
		return ResponseEntity.ok(new CategoryDto(cat.getName(), cat.getId()));
	}

	@PostMapping
	public ResponseEntity<?> createCategory(@RequestBody Category category) throws BadRequestException {
		if (category == null)
			throw new BadRequestException("Empty Body");
		category.setId(null);
		Category cat = categoryService.saveCategory(category);
		if (cat != null) {
			return ResponseEntity.ok(new Response(cat.getId().toString(), true));
		}
		throw new BadRequestException("unable to create category");
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable int id, @RequestBody Category category)
			throws BadRequestException {

		category = categoryService.updateCategory(id, category);
		if (category != null) {
			return ResponseEntity.ok(new Response(category.getId().toString(), true));
		}
		throw new BadRequestException("unable to update category");
	}
}
