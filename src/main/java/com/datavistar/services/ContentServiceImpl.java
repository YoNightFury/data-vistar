package com.datavistar.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.datavistar.dao.ContentRepository;
import com.datavistar.entities.Content;
import com.datavistar.entities.Topic;

@Service
public class ContentServiceImpl implements IContentService {

	@Autowired
	private ContentRepository contentRepository;

	@Autowired
	private ITopicService topicService;

	@Override
	public List<Content> getAllContents() {
		return contentRepository.findAll();
	}

	@Override
	public Content getContentById(Integer id) throws BadRequestException {
		return contentRepository.findById(id).orElseThrow(() -> new BadRequestException("Invalid Content Id: " + id));
	}

	@Override
	public Content saveContent(Content content) throws BadRequestException {
		Topic topic = topicService.getTopicById(content.getTopic().getId());
		content.setTopic(topic);
		try {
		return contentRepository.save(content);
		}catch (DataIntegrityViolationException e) {
			throw new BadRequestException("Body and title cannot be blank!");
		}
	}

	@Override
	public Content deleteContentById(Integer id) throws BadRequestException {
		Content content = getContentById(id);
		contentRepository.deleteById(id);
		return content;
	}

	@Override
	public List<Content> getContents(Integer page, Integer limit) {
		return contentRepository.findAll(PageRequest.of(page, limit)).getContent();
	}

	@Override
	public Content updateContent(Integer id, Content content) throws BadRequestException {
		Content existingContent = getContentById(id);
		Topic topic = topicService.getTopicById(content.getTopic().getId());
		existingContent.setTopic(topic);
		existingContent.setBody(content.getBody()); 
		existingContent.setTitle(content.getTitle()); 
		return contentRepository.save(existingContent);

	}
}
