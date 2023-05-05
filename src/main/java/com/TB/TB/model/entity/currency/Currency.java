package com.TB.TB.model.entity.currency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GFO_CURRENCY")
public class Currency {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "CODE")
	@Comment("Цифровой код")
	private String code;

	@Column(name = "LETTER_CODE")
	@Comment("Буквенный код")
	private String letterCode;

	@Column(name = "UNITS")
	@Comment("Число единиц")
	private int units;

	@Column(name = "NAME")
	@Comment("Название валюты")
	private String name;

	@Column(name = "EX_RATE")
	@Comment("Курс")
	private double exchangeRate;

}
