package com.TB.TB.model.dto;

import lombok.Data;

@Data
public class CardDto {

	private String id;
	private String name;
	private String bank;
	private String icon;
	private String urlProduct;
	private String comment;
	private int grPeriod;
	private int limit;
	private double cashback;
	private String cashbackComment;
	private String bonus;
	private String bonusComment;
	private int maintenance;
}
