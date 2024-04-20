package com.datavistar.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.datavistar.dto.ContentDto;
import com.datavistar.dto.Response;
import com.datavistar.entities.Content;
import com.datavistar.entities.Topic;
import com.datavistar.services.IContentService;

@RestController
@RequestMapping("/api/content")
public class ContentController {

	@Autowired
	private IContentService contentService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllContents() {
		List<Content> contents = contentService.getAllContents();
		List<ContentDto> contentDtos = contents.stream().map(content -> new ContentDto(content.getTitle(),
				content.getBody(), content.getId(), content.getTopic().getId())).collect(Collectors.toList());
		return ResponseEntity.ok(contentDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getContentById(@PathVariable Integer id) throws BadRequestException {
		Content content = contentService.getContentById(id);
		return ResponseEntity
				.ok(new ContentDto(content.getTitle(), content.getBody(), content.getId(), content.getTopic().getId()));
	}

	@PostMapping
	public ResponseEntity<?> createContent(@RequestBody ContentDto contentDto) throws BadRequestException {
		if (contentDto == null)
			throw new BadRequestException("Empty Body");
		Content content = new Content();
		content.setTitle(contentDto.getTitle());
		content.setBody(contentDto.getBody());
		Topic topic = new Topic();
		topic.setId(contentDto.getTopicId());
		content.setTopic(topic);
		Content savedContent = contentService.saveContent(content);
		if (savedContent != null) {
			return ResponseEntity.ok(new Response(savedContent.getId().toString(), true));
		}
		throw new BadRequestException("Unable to create content");
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateContent(@PathVariable int id, @RequestBody ContentDto contentDto)
			throws BadRequestException {
		Content content = new Content();
		content.setTitle(contentDto.getTitle());
		content.setBody(contentDto.getBody());
		Topic topic = new Topic();
		topic.setId(contentDto.getTopicId());
		content.setTopic(topic);
		content = contentService.updateContent(id, content);
		if (content != null) {
			return ResponseEntity.ok(new Response(content.getId().toString(), true));
		}
		throw new BadRequestException("Unable to update content");
	}
}
