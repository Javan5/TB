package com.TB.TB.logic.logic.mortgage;

import com.TB.TB.model.dto.LoanDto;
import com.TB.TB.model.dto.MortgageDto;
import com.TB.TB.model.entity.loan.TypeLoan;
import com.TB.TB.model.entity.mortgage.Mortgage;
import com.TB.TB.model.entity.mortgage.TypeMortgage;
import com.TB.TB.model.mapper.MortgageMapper;
import com.TB.TB.repository.MortgageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuilderMortgageImpl implements BuilderMortgage{

	private final MortgageRepository mortgageRepository;
	private final MortgageMapper mapper;

	@Override
	public List<MortgageDto> getMortgageByLimitAndPeriod(int summ, int period, TypeMortgage typeMortgage) {
		List<Mortgage> mortgages = mortgageRepository.findAll();
		if (typeMortgage != null) {
			return mortgages.stream()
					.filter(mortgage -> (mortgage.getSum() > (summ / 1000000)) && (mortgage.getTypeMortgage().equals(typeMortgage)))
					.map(mortgage -> mapper.toDto(mortgage))
					.collect(Collectors.toList());
		} else {
			return mortgages.stream()
					.filter(mortgage -> (mortgage.getSum() > (summ / 1000000)))
					.map(mortgage -> mapper.toDto(mortgage))
					.collect(Collectors.toList());
		}
	}
}
