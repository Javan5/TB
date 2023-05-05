package com.TB.TB.logic.logic.authorization;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.dto.LoanDto;
import com.TB.TB.model.dto.LoginResponseDto;
import com.TB.TB.model.dto.MortgageDto;
import com.TB.TB.model.dto.UserDto;
import com.TB.TB.model.entity.authorization.User;
import com.TB.TB.model.entity.authorization.UserLoan;
import com.TB.TB.model.entity.loan.Loan;
import com.TB.TB.model.entity.product.Product;
import com.TB.TB.model.mapper.UserMapper;
import com.TB.TB.repository.CardRepository;
import com.TB.TB.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorizationManagerImpl implements AuthorizationManager{

	@Autowired
	private CustomAuthentivationManager customAuthentivationManager;

	private final UserRepository userRepository;

	private final UserMapper userMapper;


	@Override
	@Transactional
	public LoginResponseDto addNewUser(UserDto userDto) {
		User user = userMapper.toEntity(userDto);
		List<User> userDao = userRepository.findAll();

		boolean isOk = CollectionUtils.isEmpty(userDao) ||
				CollectionUtils.isEmpty(userDao.stream().filter(user1 -> user1.getName().equals(user.getName())).collect(Collectors.toList())) ?
				customAuthentivationManager.login(user) : false;
		if (isOk) {
			userRepository.save(user);
			return new LoginResponseDto(customAuthentivationManager.getUserName());
		} else {
			return new LoginResponseDto("Zareg");
		}
		//return isOk ? "IVAN" : "Зарегистрироваться";
	}

	@Override
	public String isAuthorized() {
		return customAuthentivationManager.isAuthorized() ? customAuthentivationManager.getUserName() : "NO";
	}

	@Override
	public List<CardDto> getAllCard() {
		return null;
	}

	@Override
	public List<UserLoan> getAllLoan() {
		User user = userRepository.findAll().stream()
				.filter(user1 -> user1.getId() == customAuthentivationManager.getUserId()).collect(Collectors.toList()).get(0);
		return user.getLoansProduct();
	}

	@Override
	public List<MortgageDto> getAllMortgage() {
		return null;
	}

	@Override
	@Transactional
	public boolean exit() {
		return customAuthentivationManager.exit();
	}

	@Override
	@Transactional
	public LoginResponseDto authorization(UserDto userDto) {
		User user = userMapper.toEntity(userDto);
		List<User> userDao = userRepository.findAll().stream().filter(user1 -> user1.getName().equals(user.getName())).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(userDao)) {
			return new LoginResponseDto("name");
		} else {
			return (userDao.get(0).getPassword().equals(user.getPassword()) && customAuthentivationManager.login(user)) ?
					new LoginResponseDto("OK") : new LoginResponseDto("password");
		}
	}

	@Override
	@Transactional
	public boolean addNewProductUser(Product product) {
		return userRepository.save(customAuthentivationManager.setProduct(product)) != null;

	}
}
