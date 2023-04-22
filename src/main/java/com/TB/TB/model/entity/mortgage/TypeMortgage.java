package com.TB.TB.model.entity.mortgage;

import com.TB.TB.model.entity.news.TypeNews;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeMortgage {

	FAMILY("FAMILY"),

	NEW_BUILDING("NEWBUILDING"),

	RESELLERS("RESELLERS"),

	WAR("WAR"),

	PREFERENTIAL_ALL("PREFERENTIAL_ALL"),

	REF("REF"),

	REF_WAR("REF_WAR"),

	IT("IT"),

	FAR_EASTERN("FAR_EASTERN"),

	BUILDING("BUILDING"),

	REGION("REGION"),

	PRIVATE_HOUSE("PRIVATE_HOUSE");
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
