package com.TB.TB.model.dto;

import com.TB.TB.model.entity.news.TypeNews;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Data
public class NewsDto {

	private String icon;
	private String text;
	private String url;
	private String commentUrlIcon;
	private TypeNews typeNews;
}
