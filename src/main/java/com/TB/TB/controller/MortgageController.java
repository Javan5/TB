package com.TB.TB.controller;

import com.TB.TB.logic.logic.mortgage.BuilderMortgage;
import com.TB.TB.model.dto.LoanDto;
import com.TB.TB.model.dto.MortgageDto;
import com.TB.TB.model.entity.loan.TypeLoan;
import com.TB.TB.model.entity.mortgage.TypeMortgage;
import com.TB.TB.service.mortgage.api.MortgageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/mortgage")
public class MortgageController {
	private final MortgageService mortgageService;
	private final BuilderMortgage builderMortgage;


	@GetMapping("/getall")
	public List<MortgageDto> obtainAll() {
		return mortgageService.obtainAll();
	}

	@GetMapping("/{sum}/{period}/{type}")
	public List<MortgageDto> getCardsByParam(@PathVariable int sum, @PathVariable int period, @PathVariable int type) {
		TypeMortgage typeMortgage = type != 99 ? TypeMortgage.values()[type] : null;
		return builderMortgage.getMortgageByLimitAndPeriod(sum, period, typeMortgage);
	}
}
