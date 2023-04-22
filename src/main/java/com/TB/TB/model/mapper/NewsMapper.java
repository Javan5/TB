package com.TB.TB.model.mapper;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.dto.NewsDto;
import com.TB.TB.model.entity.card.Card;
import com.TB.TB.model.entity.news.News;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NewsMapper {

	NewsDto toDto(News news);

	News toEntity(NewsDto newsDto);

	void merge(@MappingTarget News news, NewsDto newsDto);
}
