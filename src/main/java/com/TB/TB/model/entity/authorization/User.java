package com.TB.TB.model.entity.authorization;

import com.TB.TB.model.entity.card.Card;
import com.TB.TB.model.entity.loan.Loan;
import com.TB.TB.model.entity.mortgage.Mortgage;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldNameConstants(innerTypeName = "UserFields")
@Table(name = "GFO_USER")
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	@Column(name = "USER_NAME")
	@Comment("Имя пользователя")
	private String name;

	@Column(name = "USER_PASSWORD")
	@Comment("Пароль пользователя")
	private String password;

	//@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@Column(name = "CARDS")
	//private List<Card> cardsProduct;

	@JoinColumn(name = "user_id")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	//@CollectionTable(name="loans_product", joinColumns=@JoinColumn(name="user_id"))
	//@Column(name = "LOANS")
	private List<UserLoan> loansProduct = new ArrayList<>();;

	//@OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@Column(name = "MORTGAGES")
	//private List<Mortgage> mortgageProduct;

}
