package com.TB.TB.model.dto;

import lombok.Data;

@Data
public class MortgageDto {
	private String id;
	private String name;
	private String bank;
	private String icon;
	private String urlProduct;
	private String comment;
	private int sum;
	private double rate;
	private int firstPayment;
}
