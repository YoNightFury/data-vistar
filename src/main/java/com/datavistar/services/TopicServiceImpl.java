package com.datavistar.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.datavistar.dao.TopicRepository;
import com.datavistar.entities.Course;
import com.datavistar.entities.Topic;

@Service
public class TopicServiceImpl implements ITopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private ICourseService courseService;

	@Override
	public List<Topic> getAllTopics() {
		return topicRepository.findAll();
	}

	@Override
	public Topic getTopicById(Integer id) throws BadRequestException {
		return topicRepository.findById(id).orElseThrow(() -> new BadRequestException("Invalid Topic Id: " + id));
	}

	@Override
	public Topic saveTopic(Topic topic) throws BadRequestException {
		Course course = courseService.getCourseById(topic.getCourse().getId());
		topic.setCourse(course);
		return topicRepository.save(topic);
	}

	@Override
	public Topic deleteTopicById(Integer id) throws BadRequestException {
		Topic topic = getTopicById(id);
		topicRepository.deleteById(id);
		return topic;
	}

	@Override
	public List<Topic> getTopics(Integer page, Integer limit) {
		return topicRepository.findAll(PageRequest.of(page, limit)).getContent();
	}

	@Override
	public Topic updateTopic(Integer id, Topic topic) throws BadRequestException {
		Topic existingTopic = getTopicById(id);
		Course course = courseService.getCourseById(topic.getCourse().getId());

		topic.setCourse(course);
		topic.setId(existingTopic.getId()); // Ensure the ID is set
		return topicRepository.save(topic);
	}
}
