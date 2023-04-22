package com.TB.TB.service.other.impl;

import com.TB.TB.model.dto.NewsDto;
import com.TB.TB.model.mapper.NewsMapper;
import com.TB.TB.repository.NewsRepository;
import com.TB.TB.service.other.api.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

	private final NewsRepository newsRepository;
	private final NewsMapper mapper;

	@Override
	public String create(NewsDto newsDto) {
		return null;
	}

	@Override
	public void update(String id, NewsDto newsDto) {

	}

	@Override
	public NewsDto getById(String id) {
		return null;
	}

	@Override
	public List<NewsDto> obtainAll() {
		return newsRepository.findAll().stream()
				.map(news -> mapper.toDto(news))
				.collect(Collectors.toList());
	}

	@Override
	public void delete(String id) {

	}

	@Override
	public List<NewsDto> searchByName(String name) {
		return null;
	}
}
