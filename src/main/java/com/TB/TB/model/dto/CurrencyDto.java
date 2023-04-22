package com.TB.TB.model.dto;

import lombok.Data;

@Data
public class CurrencyDto {

	private String code;
	private String letterCode;
	private int units;
	private String name;
	private double exchangeRate;
}
