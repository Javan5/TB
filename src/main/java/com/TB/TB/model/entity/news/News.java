package com.TB.TB.model.entity.news;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GFO_NEWS")
public class News {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ICON_NEWS")
	@Comment("Иконка новости")
	private String icon;

	@Column(name = "TEXT_NEWS")
	@Comment("Текст новости")
	private String text;

	@Column(name = "URL_NEWS")
	@Comment("Ссылка на новость")
	private String url;

	@Column(name = "COMMENT_URL_NEWS")
	@Comment("Комментарий картинки")
	private String commentUrl;

	@Column(name = "TYPE_NEWS")
	@Comment("Тип новости")
	private TypeNews typeNews;
}
