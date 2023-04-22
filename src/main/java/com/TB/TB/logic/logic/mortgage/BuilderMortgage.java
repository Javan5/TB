package com.TB.TB.logic.logic.mortgage;

import com.TB.TB.model.dto.MortgageDto;
import com.TB.TB.model.entity.mortgage.TypeMortgage;

import java.util.List;

public interface BuilderMortgage {
	List<MortgageDto> getMortgageByLimitAndPeriod(int summ, int period, TypeMortgage typeMortgage);
}
