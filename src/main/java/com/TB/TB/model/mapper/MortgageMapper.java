package com.TB.TB.model.mapper;

import com.TB.TB.model.dto.MortgageDto;
import com.TB.TB.model.entity.mortgage.Mortgage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MortgageMapper {

	MortgageDto toDto(Mortgage mortgage);

	Mortgage toEntity(MortgageDto mortgageDto);

	void merge(@MappingTarget Mortgage mortgage, MortgageDto mortgageDto);
}
