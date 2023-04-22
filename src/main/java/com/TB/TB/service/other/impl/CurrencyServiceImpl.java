package com.TB.TB.service.other.impl;

import com.TB.TB.model.dto.CurrencyDto;
import com.TB.TB.model.entity.currency.Currency;
import com.TB.TB.model.mapper.CurrencyMapper;
import com.TB.TB.repository.CurrencyRepository;
import com.TB.TB.service.other.api.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

	private final CurrencyRepository currencyRepository;
	private final CurrencyMapper currencyMapper;

	@Override
	public String getStrForFirstPage() {
		List<Currency> currencies = currencyRepository.findAll().stream().collect(Collectors.toList());
		StringBuilder currency = new StringBuilder();
		for (int i=10; i<15;i++) {
			currency.append(currencies.get(i).getLetterCode()).append(" ").append(currencies.get(i).getExchangeRate()).append(". ");
		}
		return currency.toString();
				//new StringBuilder(currencies.get(0).getName()).append(" курс ").append(currencies.get(0).getExchangeRate()).append(". ").append(currencies.get(1).getName()).append(" курс ").append(currencies.get(1).getExchangeRate()).toString();
	}

	@Override
	public List<CurrencyDto> getAllCurrency() {
		return currencyRepository.findAll().stream()
				.map(currency -> currencyMapper.toDto(currency))
				.collect(Collectors.toList());
	}
}
