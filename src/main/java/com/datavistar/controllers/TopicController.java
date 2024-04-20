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

import com.datavistar.dto.Response;
import com.datavistar.dto.TopicDto;
import com.datavistar.entities.Course;
import com.datavistar.entities.Topic;
import com.datavistar.services.ITopicService;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Autowired
    private ITopicService topicService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        List<TopicDto> topicDtos = topics.stream()
                .map(topic -> new TopicDto(topic.getName(), topic.getId(), topic.getCourse().getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(topicDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicById(@PathVariable Integer id) throws BadRequestException {
        Topic topic = topicService.getTopicById(id);
        return ResponseEntity.ok(new TopicDto(topic.getName(), topic.getId(), topic.getCourse().getId()));
    }

    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody TopicDto topicDto) throws BadRequestException {
        if (topicDto == null)
            throw new BadRequestException("Empty Body");
        Topic topic = new Topic();
        topic.setName(topicDto.getName());
        Course course = new Course();
        course.setId(topicDto.getCourseId());
        topic.setCourse(course);
        Topic savedTopic = topicService.saveTopic(topic);
        if (savedTopic != null) {
            return ResponseEntity.ok(new Response(savedTopic.getId().toString(), true));
        }
        throw new BadRequestException("Unable to create topic");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable int id, @RequestBody TopicDto topicDto)
            throws BadRequestException {
        Topic topic = new Topic();
        topic.setName(topicDto.getName());
        Course course = new Course();
        course.setId(topicDto.getCourseId());
        topic.setCourse(course);
        topic = topicService.updateTopic(id, topic);
        if (topic != null) {
            return ResponseEntity.ok(new Response(topic.getId().toString(), true));
        }
        throw new BadRequestException("Unable to update topic");
    }
}
