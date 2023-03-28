package com.TB.TB.model.entity.news;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeNews {

	ECONOMY("ECM"),

	POLICE("PLC"),

	SCIENCE("SCI"),

	SOCIETY("SOC");
	private final String code;

	public static TypeNews getByCode(String code) throws IllegalAccessException {
		for (TypeNews enumValue : TypeNews.values()) {
			if (enumValue.getCode().equals(code)) return enumValue;
		}
		throw new IllegalAccessException("There is no Color enum value with code " + code);
	}

	@Override
	public String toString() {
		return super.toString() + "(" + code + ")";
	}
}
