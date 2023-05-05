package com.TB.TB.controller;

import com.TB.TB.logic.logic.card.BuilderCard;
import com.TB.TB.model.dto.CardDto;
import com.TB.TB.service.card.api.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product/card")
public class CardController {
	private final CardService cardService;
	private final BuilderCard builderCard;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =
			MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public String create(@Validated @RequestBody CardDto dto) {
		return cardService.create(dto);
	}

	@GetMapping("/getall")
	public List<CardDto> obtainAll() {
		return cardService.obtainAll();
	}

	@GetMapping("/{cardId}")
	public CardDto getById(@PathVariable String cardId) {
		return cardService.getById(cardId);
	}

	@PatchMapping("/{cardId}")
	public void update(@PathVariable String cardId, @RequestBody CardDto dto) {
		cardService.update(cardId, dto);
	}

	@DeleteMapping("/{cardId}")
	public void delete(@PathVariable String cardId) {
		cardService.delete(cardId);
	}

	@GetMapping("/search-by-name")
	public List<CardDto> searchByName(@RequestParam String name){
		return cardService.searchByName(name);
	}

	@GetMapping("/{limit}/{period}")
	public List<CardDto> getCardsByParam(@PathVariable int limit, @PathVariable int period) {
		return builderCard.getCardByLimitAndPeriod(limit, period);
	}

	@GetMapping("/addcard/{cardId}")
	public boolean addCardToListUser(@PathVariable String cardId) {
		return false;
	}

}
