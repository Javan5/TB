package com.TB.TB.logic.parser.currency;

import com.TB.TB.model.entity.card.Card;
import com.TB.TB.model.entity.currency.Currency;
import com.TB.TB.model.entity.dbinfo.DbInfo;
import com.TB.TB.repository.CardRepository;
import com.TB.TB.repository.CurrencyRepository;
import com.TB.TB.repository.DbInfoRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class CurrencyParser {

	private final CurrencyRepository currencyRepository;
	private final DbInfoRepository dbInfoRepository;


	@EventListener(ApplicationReadyEvent.class)
	public void CurrencyParser() throws IOException {
		Document doc = Jsoup.connect("https://www.cbr.ru/currency_base/daily/").get();
		Elements oneElements = doc.getElementsByTag("tr");
		for (int i = 1; i < oneElements.size(); i++)  //Две первые кредитные карты ренкредита
		{
			Element oneElement = oneElements.get(i);
			currencyRepository.save(fillCurrency(oneElement));
		}
		DbInfo dbInfo = new DbInfo();
		dbInfo.setNameBase("GFO_CURRENCY");
		dbInfo.setDateUpdate(LocalDateTime.now());
		dbInfo.setUrl("https://www.cbr.ru/currency_base/daily/");
		dbInfo.setNumber(oneElements.size());
		dbInfoRepository.save(dbInfo);
	}

	private Currency fillCurrency(Element element) {
		Currency currency = new Currency();
		currency.setCode(element.child(0).text());
		currency.setLetterCode(element.child(1).text());
		currency.setUnits(Integer.parseInt(element.child(2).text()));
		currency.setName(element.child(3).text());
		currency.setExchangeRate(Double.parseDouble(element.child(4).text().replace(",", ".")));
		return currency;
	}
}
