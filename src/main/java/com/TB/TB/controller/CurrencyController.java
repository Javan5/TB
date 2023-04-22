package com.TB.TB.controller;

import com.TB.TB.model.dto.CurrencyDto;
import com.TB.TB.service.other.api.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {

	private final CurrencyService currencyService;

	@GetMapping("/getstr")
	public String obtainAll() {
		return currencyService.getStrForFirstPage();
	}

	@GetMapping("/getall")
	public List<CurrencyDto> getAllCurrency() {
		return currencyService.getAllCurrency();
	}

}
