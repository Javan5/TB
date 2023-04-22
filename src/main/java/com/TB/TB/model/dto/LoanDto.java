package com.TB.TB.model.dto;

import com.TB.TB.model.entity.loan.TypeLoan;
import lombok.Data;

@Data
public class LoanDto {

	private String id;
	private String name;
	private String bank;
	private String icon;
	private String urlProduct;
	private String comment;
	private double rate;
	private int sum;
	private int termMin;
	private int termMax;
	private String urlGo;
	private TypeLoan typeLoan;
}
