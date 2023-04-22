package com.TB.TB.model.entity.loan;

import com.TB.TB.model.entity.news.TypeNews;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeLoan {

	CASH("CASH"),

	REFINANCING("REFINANCING"),

	AUTO("AUTO"),

	HOUSE("HOUSE"),

	EDUCATION("EDUCATION"),

	OTHER("OTHER");
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
