package com.TB.TB.service.card.impl;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.entity.card.Card;
import com.TB.TB.model.mapper.CardMapper;
import com.TB.TB.repository.CardRepository;
import com.TB.TB.service.card.api.CardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

	private final CardRepository cardRepository;
	private final CardMapper mapper;


	@Override
	public String create(CardDto cardDto) {
		Card card = mapper.toEntity(cardDto);
		cardRepository.save(card);
		return card.getId();
	}

	@Override
	public void update(String id, CardDto cardDto) {
		Card card = getCard(id);
		mapper.merge(card, cardDto);
	}

	@Override
	public CardDto getById(String id) {
		return mapper.toDto(getCard(id));
	}

	@Override
	public List<CardDto> obtainAll() {
		return cardRepository.findAll().stream().map(p -> mapper.toDto(p)).toList();
	}

	@Override
	public void delete(String id) {
		Card toDeleate = getCard(id);
		cardRepository.delete(toDeleate);
	}

	@Override
	public List<CardDto> searchByName(String name) {
		List<CardDto> cards = obtainAll();
		return cards.stream().filter(cardDto -> cardDto.getName().contains(name)).toList();
	}

	private Card getCard(String cardId) {
		return cardRepository.findById(cardId)
				.orElseThrow(() -> new EntityNotFoundException(
					"Error"
				));
	}
}
