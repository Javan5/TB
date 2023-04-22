package com.TB.TB.model.entity.loan;

import com.TB.TB.model.entity.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldNameConstants(innerTypeName = "LoanFields")
@Table(name = "GFO_LOAN")
public class Loan extends Product {

	@Column(name = "RATE")
	@Comment("Ставка по кредиту")
	private double rate;

	@Column(name = "SUM")
	@Comment("Максимальная сумма кредита")
	private int sum;

	@Column(name = "TERM_MIN")
	@Comment("Срок кредита минимальный")
	private int termMin;

	@Column(name = "TERM_MAX")
	@Comment("Срок кредита максимальный")
	private int termMax;

	@Column(name = "URL_GO")
	@Comment("Ссылка на оформление кредита")
	private String urlGo;

	@Column(name = "TYPE_LOAN")
	@Comment("Тип кредита")
	private TypeLoan typeLoan;
}
