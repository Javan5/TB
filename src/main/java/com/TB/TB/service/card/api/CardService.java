package com.TB.TB.service.card.api;

import com.TB.TB.model.dto.CardDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CardService {

	String create(CardDto cardDto);

	void update(String id, CardDto cardDto);

	CardDto getById(String id);

	List<CardDto> obtainAll();

	void delete(String id);

	List<CardDto> searchByName(String name);
}
