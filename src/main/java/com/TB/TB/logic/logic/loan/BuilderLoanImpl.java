package com.TB.TB.logic.logic.loan;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.dto.LoanDto;
import com.TB.TB.model.entity.loan.TypeLoan;
import com.TB.TB.model.mapper.CardMapper;
import com.TB.TB.model.mapper.LoanMapper;
import com.TB.TB.repository.CardRepository;
import com.TB.TB.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuilderLoanImpl implements BuilderLoan {

	private final LoanRepository loanRepository;

	private final LoanMapper mapper;


	@Override
	public List<LoanDto> getCardByLimitAndPeriod(int summ, int period, TypeLoan typeLoan) {
		List<LoanDto> loans = loanRepository.findAll().stream()
				.filter(loan -> (loan.getTermMin() < period) && (loan.getTermMax() > period) && (loan.getSum() > summ) && (loan.getTypeLoan().equals(typeLoan)))
				.map(loan -> mapper.toDto(loan))
				.collect(Collectors.toList());
		return loans;
	}
}
