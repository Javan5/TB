package com.TB.TB.logic.parser.card;

import com.TB.TB.model.entity.card.Card;
import com.TB.TB.model.entity.dbinfo.DbInfo;
import com.TB.TB.repository.CardRepository;
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
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
//@Component
public class AlfaCardParser {

	private final CardRepository cardRepository;
	private final DbInfoRepository dbInfoRepository;


	//@EventListener(ApplicationReadyEvent.class)
	public void ParserAlfaCard() throws IOException {
		Document doc = Jsoup.connect("https://alfabank.ru/get-money/credit-cards/").get();
		Elements image = doc.getElementsByAttributeValue("class", "c1m2");
		Elements oneElements = doc.getElementsByAttributeValue("class", "bfG2mw");
		Elements urlElem = doc.getElementsByAttributeValue("class", "a1cA g1cA c1cA");
		//System.out.println(oneElements.get(0).child(4));
		for (int i=0; i<oneElements.size(); i++) {
			//System.out.println(oneElements.get(i).child(0).attr("data-test-id", "text").child(0).text());
			//System.out.println(oneElements.get(i).child(0).attr("data-test-id", "text").child(1).text());
			//System.out.println(oneElements.get(i).child(2).attr("data-test-id", "text").child(0).text());
			//System.out.println(oneElements.get(i).child(2).attr("data-test-id", "text").child(1).text());
			//System.out.println(oneElements.get(i).child(4).attr("data-test-id", "text").child(0).text());
			//System.out.println(oneElements.get(i).child(4).attr("data-test-id", "text").child(1).text());
			//System.out.println(fillCard(oneElements.get(i)));
			//System.out.println(image.get(i).attr("alt"));
			//System.out.println(image.get(i).attr("src"));
			Card card = fillCard(oneElements.get(i));
			card.setBank("Alfa");
			card.setName(image.get(i).attr("alt"));
			String icon = image.get(i).attr("src");
			card.setIcon(icon.contains("alfabank.") ? icon : "https://alfabank.servicecdn.ru/site-upload/e8/f8/965/Red.png");
			//System.out.println(urlElem.get(i).attr("href"));
			//System.out.println();
			//System.out.println();
			//System.out.println();
			card.setUrlProduct("https://alfabank.ru" + urlElem.get(2*i+2).attr("href"));
			cardRepository.save(card);
		}
		DbInfo dbInfo = new DbInfo();
		dbInfo.setNameBase("GFO_CARD");
		dbInfo.setDateUpdate(LocalDateTime.now());
		dbInfo.setUrl("https://alfabank.ru/get-money/credit-cards/");
		dbInfo.setNumber(oneElements.size());
		dbInfoRepository.save(dbInfo);
	}

	private static Card fillCard(Element element) {
		Card card = new Card();
		fillParam(element.child(0).attr("data-test-id", "text").child(0).text(),
							element.child(0).attr("data-test-id", "text").child(1).text(),
							card);
		fillParam(element.child(2).attr("data-test-id", "text").child(0).text(),
							element.child(2).attr("data-test-id", "text").child(1).text(),
							card);
		fillParam(element.child(4).attr("data-test-id", "text").child(0).text(),
							element.child(4).attr("data-test-id", "text").child(1).text(),
							card);
		return card;
	}

	private static void fillParam(String text, String textPlus, Card card) {
		if (text.contains("Год") || text.contains("год")) {
			card.setGrPeriod(365);
		} else if (text.contains("дней")) {
			card.setGrPeriod(Integer.parseInt(getSumm(text)));
		} else if (text.contains("₽")) {
			int value = Integer.parseInt(getSumm(text));
			if (value > 5000) {
				card.setLimit(value);
			} else if (value < 10 && value != 0){
				card.setCashback(value);
				card.setCashbackComment(textPlus);
			} else {
				card.setBonus(text);
				card.setBonusComment(textPlus);
			}
		} else if (text.contains("%") && !text.contains("Год")) {
			try {
				//card.setCashback(Double.valueOf(text));
				Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
				Matcher matcher=pat.matcher(text);
				while (matcher.find()) {
					card.setCashback(Double.valueOf(matcher.group().replace(",", ".")));
				};
			} catch(NumberFormatException e){
			}
			card.setCashbackComment(textPlus);
		} else {
			card.setBonus(text);
			card.setBonusComment(textPlus);
		}
	}
	private static String getSumm(String summ) {
		String[] parts = summ.split("[^0-9]");
		String result = "";
		for (int i = 0; i < parts.length; i++) {
			if (!parts[i].equals("")) {
				result = result + parts[i];
			}
		}
		return result;
	}
}
