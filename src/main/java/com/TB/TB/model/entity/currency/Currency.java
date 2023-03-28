package com.TB.TB.model.entity.currency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
	private String code;

	@Column(name = "LETTER_CODE")
	private String letterCode;

	@Column(name = "UNITS")
	private int units;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EX_RATE")
	private double exchangeRate;

}
