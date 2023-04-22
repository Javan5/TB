package com.TB.TB.logic.logic.card;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.mapper.CardMapper;
import com.TB.TB.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuilderCardImpl implements BuilderCard{

	private final CardRepository cardRepository;

	private final CardMapper mapper;


	@Override
	public List<CardDto> getCardByLimitAndPeriod(int limit, int period) {
		List<CardDto> cards = cardRepository.findAll().stream()
				.filter(card -> (card.getGrPeriod() / 30) >= period)
				.filter(card -> card.getLimit() >= limit)
				.map(card -> mapper.toDto(card))
				.collect(Collectors.toList());
		return cards;
	}
}
