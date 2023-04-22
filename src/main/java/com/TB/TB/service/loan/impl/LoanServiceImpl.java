package com.TB.TB.service.loan.impl;

import com.TB.TB.model.dto.LoanDto;
import com.TB.TB.model.mapper.CardMapper;
import com.TB.TB.model.mapper.LoanMapper;
import com.TB.TB.repository.CardRepository;
import com.TB.TB.repository.LoanRepository;
import com.TB.TB.service.loan.api.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

	private final LoanRepository loanRepository;
	private final LoanMapper mapper;

	@Override
	public List<LoanDto> obtainAll() {
		return loanRepository.findAll().stream().map(p -> mapper.toDto(p)).toList();
	}
}
