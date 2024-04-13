package com.datavistar.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "content")
public class Content extends BaseEntity {

	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	@Basic(fetch = FetchType.LAZY)
	private String body;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "topicid", nullable = false)
	private Topic topic;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Content [title=" + title + ", topicid=" + topic.getId() + "]";
	}

}
