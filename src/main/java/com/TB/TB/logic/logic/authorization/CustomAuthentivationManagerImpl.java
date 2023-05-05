package com.TB.TB.logic.logic.authorization;

import com.TB.TB.model.entity.authorization.User;
import com.TB.TB.model.entity.authorization.UserLoan;
import com.TB.TB.model.entity.card.Card;
import com.TB.TB.model.entity.loan.Loan;
import com.TB.TB.model.entity.mortgage.Mortgage;
import com.TB.TB.model.entity.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomAuthentivationManagerImpl implements CustomAuthentivationManager {

	private User user;


	@Override
	public boolean isAuthorized() {
		return user != null;
	}

	@Override
	public boolean exit() {
		this.user = null;
		return user == null;
	}

	@Override
	public boolean login(User user) {
		this.user = user;
		return user != null;
	}

	@Override
	public User setProduct(Product product) {
		if (product instanceof Card) {


		} else if (product instanceof Loan) {
			List<UserLoan> loans = user.getLoansProduct();
			if (CollectionUtils.isEmpty(loans)) {
				loans =  new ArrayList<>(Arrays.asList(new UserLoan(product.getId())));
			} else {
				loans.add(new UserLoan(product.getId()));
			}
			user.setLoansProduct(loans);
			return user;
		} else if (product instanceof Mortgage) {

		}
		return user;
	}

	@Override
	public int getUserId() {
		return user.getId();
	}

	@Override
	public String getUserName() {
		return user.getName();
	}
}
