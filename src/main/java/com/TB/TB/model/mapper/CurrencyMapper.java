package com.TB.TB.model.mapper;

import com.TB.TB.model.dto.CurrencyDto;
import com.TB.TB.model.dto.NewsDto;
import com.TB.TB.model.entity.currency.Currency;
import com.TB.TB.model.entity.news.News;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {

	CurrencyDto toDto(Currency currency);

	Currency toEntity(CurrencyDto currencyDto);

	void merge(@MappingTarget Currency currency, CurrencyDto currencyDto);
}
