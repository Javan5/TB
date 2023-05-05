package com.TB.TB.model.entity.authorization;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldNameConstants(innerTypeName = "UserLoanFields")
@Table(name = "GFO_USER_LOAN")
public class UserLoan {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name = "LOAN_ID")
	private String loanId;


	public UserLoan(String loanId) {
		this.loanId = loanId;
	}
}
