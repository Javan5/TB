package com.TB.TB.service.other.api;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.dto.CurrencyDto;

import java.util.List;

public interface CurrencyService {

	String getStrForFirstPage();

	List<CurrencyDto> getAllCurrency();
}
