package com.TB.TB.model.dto;

import lombok.Data;

@Data
public class CardDto {

	private String id;
	private String name;
	private String bank;
	private int grPeriod;
	private int limit;
	private double cashback;
}
