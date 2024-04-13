package com.datavistar.services;

import java.util.List;

import com.datavistar.entities.Content;

public interface IContentService {

	public List<Content> getAllContents();

	public Content getContentById(Integer id);

	public Content saveContent(Content content);

	public Content deleteContentById(Integer id);

	public List<Content> getContents(Integer page, Integer limit);

}
