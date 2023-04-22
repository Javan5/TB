package com.TB.TB.service.other.api;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.dto.NewsDto;

import java.util.List;

public interface NewsService {

	String create(NewsDto newsDto);

	void update(String id, NewsDto newsDto);

	NewsDto getById(String id);

	List<NewsDto> obtainAll();

	void delete(String id);

	List<NewsDto> searchByName(String name);
}
