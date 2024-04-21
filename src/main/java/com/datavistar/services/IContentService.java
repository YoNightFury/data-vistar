package com.datavistar.services;

import java.util.List;

import org.apache.coyote.BadRequestException;

import com.datavistar.entities.Content;

public interface IContentService {

	public List<Content> getAllContents();

	public Content getContentById(Integer id) throws BadRequestException;

	public Content saveContent(Content content) throws BadRequestException;

	public Content deleteContentById(Integer id) throws BadRequestException;

	public List<Content> getContents(Integer page, Integer limit);

	public Content updateContent(Integer id, Content content) throws BadRequestException;

}
