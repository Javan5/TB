package com.TB.TB.model.mapper;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.entity.card.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CardMapper {

	CardDto toDto(Card card);

	Card toEntity(CardDto cardDto);

	void merge(@MappingTarget Card card, CardDto cardDto);
}
