package com.TB.TB.service.mortgage.api;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.dto.MortgageDto;

import java.util.List;

public interface MortgageService {

	List<MortgageDto> obtainAll();
}
