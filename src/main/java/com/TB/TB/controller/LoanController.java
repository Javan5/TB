package com.TB.TB.controller;

import com.TB.TB.logic.logic.card.BuilderCard;
import com.TB.TB.logic.logic.loan.BuilderLoan;
import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.dto.LoanDto;
import com.TB.TB.model.entity.loan.TypeLoan;
import com.TB.TB.service.card.api.CardService;
import com.TB.TB.service.loan.api.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/loan")
public class LoanController {

	private final LoanService loanService;
	private final BuilderLoan builderLoan;


	@GetMapping("/getall")
	public List<LoanDto> obtainAll() {
		return loanService.obtainAll();
	}

	@GetMapping("/{sum}/{period}/{type}")
	public List<LoanDto> getCardsByParam(@PathVariable int sum, @PathVariable int period, @PathVariable int type) {
		return builderLoan.getCardByLimitAndPeriod(sum, period, TypeLoan.values()[type]);
	}
}
