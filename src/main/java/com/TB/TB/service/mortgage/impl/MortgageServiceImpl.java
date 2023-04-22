package com.TB.TB.service.mortgage.impl;

import com.TB.TB.model.dto.MortgageDto;
import com.TB.TB.model.mapper.CardMapper;
import com.TB.TB.model.mapper.MortgageMapper;
import com.TB.TB.repository.CardRepository;
import com.TB.TB.repository.MortgageRepository;
import com.TB.TB.service.mortgage.api.MortgageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MortgageServiceImpl implements MortgageService {

	private final MortgageRepository mortgageRepository;
	private final MortgageMapper mapper;


	@Override
	public List<MortgageDto> obtainAll() {
		return mortgageRepository.findAll().stream().map(p -> mapper.toDto(p)).toList();
	}
}
