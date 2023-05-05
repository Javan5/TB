package com.TB.TB.logic.logic.card;

import com.TB.TB.model.dto.CardDto;

import java.util.List;

public interface BuilderCard {

	public List<CardDto> getCardByLimitAndPeriod(int limit, int period);

	boolean addCard(String id);


}
