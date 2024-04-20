package com.datavistar.services;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.datavistar.entities.Topic;

public interface ITopicService {

	public List<Topic> getAllTopics();

	public Topic getTopicById(Integer id) throws BadRequestException;

	public Topic saveTopic(Topic topic) throws BadRequestException;

	public Topic deleteTopicById(Integer id) throws BadRequestException;

	public List<Topic> getTopics(Integer page, Integer limit);

	public Topic updateTopic(Integer id, Topic topic) throws BadRequestException;

}
