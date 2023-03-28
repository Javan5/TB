package com.TB.TB.model.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;

@Data
@FieldNameConstants(innerTypeName = "ProductFields")
@MappedSuperclass
public abstract class Product {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(name = "NAME", nullable = false)
	@Comment("Название продукта")
	protected String name;

	@Column(name = "BANK", nullable = false)
	@Comment("Название банка")
	protected String bank;

	@Column(name = "ICON", nullable = true)
	@Comment("Иконка продукта")
	protected String icon;

	@Column(name = "URL_PRODUCT", nullable = true)
	@Comment("Ссылка на продукт")
	protected String urlProduct;

	@Column(name = "COMMENT", nullable = true)
	@Comment("Комментарий продукта")
	protected String comment;
}
