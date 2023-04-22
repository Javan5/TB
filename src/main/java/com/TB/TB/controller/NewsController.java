package com.TB.TB.controller;

import com.TB.TB.model.dto.CardDto;
import com.TB.TB.model.dto.NewsDto;
import com.TB.TB.service.card.api.CardService;
import com.TB.TB.service.other.api.NewsService;
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
@RequestMapping("/news")
public class NewsController {
	private final NewsService newsService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =
			MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public String create(@Validated @RequestBody NewsDto dto) {
		return newsService.create(dto);
	}

	@GetMapping("/getall")
	public List<NewsDto> obtainAll() {
		return newsService.obtainAll();
	}

	@GetMapping("/{newsid}")
	public NewsDto getById(@PathVariable String newsid) {
		return newsService.getById(newsid);
	}

	@PatchMapping("/{newsid}")
	public void update(@PathVariable String newsid, @RequestBody NewsDto dto) {
		newsService.update(newsid, dto);
	}

	@DeleteMapping("/{newsid}")
	public void delete(@PathVariable String newsid) {
		newsService.delete(newsid);
	}

	@GetMapping("/search-by-name")
	public List<NewsDto> searchByName(@RequestParam String name){
		return newsService.searchByName(name);
	}
}
