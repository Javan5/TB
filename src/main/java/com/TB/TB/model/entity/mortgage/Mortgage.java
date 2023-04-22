package com.TB.TB.model.entity.mortgage;

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
@FieldNameConstants(innerTypeName = "MortgageFields")
@Table(name = "GFO_MORTGAGE")
public class Mortgage extends Product {

	@Column(name = "SUM")
	@Comment("Максимальная сумма ипотеки")
	private int sum;

	@Column(name = "RATE")
	@Comment("Процентная ставка ипотеки")
	private double rate;

	@Column(name = "FIRST_PAYMENT")
	@Comment("Первоначальный взнос")
	private int firstPayment;

	@Column(name = "TYPE_MORTGAGE")
	@Comment("Тип ипотеки")
	private TypeMortgage typeMortgage;
}
