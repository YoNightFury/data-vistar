package com.datavistar.services;

import java.util.List;

import com.datavistar.entities.Topic;

public interface ITopicService {

	public List<Topic> getAllTopics();

	public Topic getTopicById(Integer id);

	public Topic saveTopic(Topic topic);

	public Topic deleteTopicById(Integer id);

	public List<Topic> getTopics(Integer page, Integer limit);

}
