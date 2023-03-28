package com.TB.TB.model.entity.card;

import com.TB.TB.model.entity.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldNameConstants(innerTypeName = "CardFields")
@Table(name = "GFO_CARD")
public class Card extends Product {

	@Column(name = "GRPERIOD")
	@Comment("Льготный период")
	private int grPeriod;

	@Column(name = "LIMITS")
	@Comment("Кредитный лимит")
	private int limit;

	@Column(name = "CASHBACK")
	@Comment("Кэшбек в %")
	private double cashback;

	@Column(name = "CASHBACK_COMMENT")
	@Comment("Комментарий к кэшбеку ")
	private String cashbackComment;

	@Column(name = "BONUS")
	@Comment("Бонус")
	private String bonus;

	@Column(name = "BONUS_COMMENT")
	@Comment("Комментарий к бонусу")
	private String bonusComment;

	@Column(name = "MAINTENANCE")
	@Comment("Обслуживание карты")
	private int maintenance;
}
