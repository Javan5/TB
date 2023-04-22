package com.TB.TB.model.mapper;

import com.TB.TB.model.dto.LoanDto;
import com.TB.TB.model.entity.loan.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LoanMapper {
	LoanDto toDto(Loan loan);

	Loan toEntity(LoanDto loanDto);

	void merge(@MappingTarget Loan loan, LoanDto loanDto);
}
