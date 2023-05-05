package com.TB.TB.logic.logic.loan;

import com.TB.TB.model.dto.LoanDto;
import com.TB.TB.model.entity.loan.TypeLoan;

import java.util.List;

public interface BuilderLoan {
	List<LoanDto> getCardByLimitAndPeriod(int summ, int period, TypeLoan typeLoan);

	boolean addNewLoanUser(String id);

	List<LoanDto> getLoanForUser();
}
