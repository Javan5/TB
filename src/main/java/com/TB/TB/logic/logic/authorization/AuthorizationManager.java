package com.TB.TB.logic.logic.authorization;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.dto.LoanDto;
import com.TB.TB.model.dto.LoginResponseDto;
import com.TB.TB.model.dto.MortgageDto;
import com.TB.TB.model.dto.UserDto;
import com.TB.TB.model.entity.authorization.User;
import com.TB.TB.model.entity.authorization.UserLoan;
import com.TB.TB.model.entity.card.Card;
import com.TB.TB.model.entity.loan.Loan;
import com.TB.TB.model.entity.product.Product;

import java.util.List;

public interface AuthorizationManager {

	LoginResponseDto addNewUser(UserDto user);

	String isAuthorized();

	List<CardDto> getAllCard();

	List<UserLoan> getAllLoan();

	List<MortgageDto> getAllMortgage();

	boolean exit();

	LoginResponseDto authorization(UserDto userDto);

	boolean addNewProductUser(Product product);
}
