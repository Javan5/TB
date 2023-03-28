package com.TB.TB.model.entity.dbinfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GFO_INFO_UPDATE")
public class DbInfo {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME_BASE")
	private String nameBase;

	@Column(name = "DATE_UPDATE")
	private LocalDateTime dateUpdate;

	@Column(name = "URL")
	private String url;

	@Column(name = "NUMBER")
	private int number;
}
