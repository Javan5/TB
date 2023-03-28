package com.TB.TB.logic.parser.card;

import com.TB.TB.model.entity.card.Card;
import com.TB.TB.model.entity.dbinfo.DbInfo;
import com.TB.TB.repository.CardRepository;
import com.TB.TB.repository.DbInfoRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class TinkoffCardParser {

	private final CardRepository cardRepository;
	private final DbInfoRepository dbInfoRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void ParserTinkoffCard() throws IOException {
		Document doc = Jsoup.connect("https://www.tinkoff.ru/cards/credit-cards/").get();
		int size = doc.getElementsByAttributeValue("class","abssHJA39 ab1W755ag fbssHJA39 sbssHJA39").size();
		//System.out.println(oneElements.get(0).child(0));
		for (int i=0; i<size; i++) {

			//System.out.println(Arrays.stream(doc.getElementsByAttributeValue("class", "ebK0GOCio gbK0GOCio").get(i).select("source").attr("srcset").split(" ")).findFirst());
			//System.out.println(doc.getElementsByAttributeValue("class", "ab80Jv2wt bb80Jv2wt fb80Jv2wt").get(2*i+1).select("a").attr("href"));
			//System.out.println(doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag fbssHJA39 sbssHJA39").get(i).text());//
			//System.out.println(doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag lbssHJA39 sbssHJA39").get(i).text());
			//System.out.println(doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag gbssHJA39 sbssHJA39").get(3*i).text());
			//System.out.println(doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag gbssHJA39 sbssHJA39").get(1+3*i).text());
			//System.out.println(doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag gbssHJA39 sbssHJA39").get(2+3*i).text());

			Card card = new Card();
			card.setBank("Tinkoff");
			card.setIcon(Arrays.stream(doc.getElementsByAttributeValue("class", "ebK0GOCio gbK0GOCio").get(i).select("source").attr("srcset").split(" ")).findFirst().get());
			card.setUrlProduct("https://www.tinkoff.ru/" + doc.getElementsByAttributeValue("class", "ab80Jv2wt bb80Jv2wt fb80Jv2wt").get(2*i+1).select("a").attr("href"));
			card.setName(doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag fbssHJA39 sbssHJA39").get(i).text());
			//card.setComment(doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag lbssHJA39 sbssHJA39").get(i).text());
			fillCashback(card,
									 doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag gbssHJA39 sbssHJA39").get(1+3*i).text(),
									 doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag lbssHJA39 sbssHJA39").get(i).text(),
									 doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag gbssHJA39 sbssHJA39").get(3*i).text());

			card.setMaintenance(Integer.parseInt(getSumm(doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag gbssHJA39 sbssHJA39").get(2+3*i).text())));
			cardRepository.save(card);
		}
		DbInfo dbInfo = new DbInfo();
		dbInfo.setNameBase("GFO_CARD");
		dbInfo.setDateUpdate(LocalDateTime.now());
		dbInfo.setUrl("https://www.tinkoff.ru/cards/credit-cards/");
		dbInfo.setNumber(2);
		dbInfoRepository.save(dbInfo);
	}

	private static void fillCashback(Card card, String cashback, String cashbackComment, String limit) {
		if (cashback.contains("%")) {
			card.setCashback(Double.parseDouble(getSumm(cashback)));
			card.setCashbackComment(cashbackComment);
			card.setLimit(Integer.parseInt(getSumm(limit)));
		} else if (!cashback.contains("₽")){
			card.setBonus(cashback);
			card.setBonusComment(cashbackComment);
			card.setLimit(Integer.parseInt(getSumm(limit)));
		} else {
			card.setLimit(Integer.parseInt(getSumm(cashback)));
			card.setBonus(limit);
			card.setBonusComment(cashbackComment);
		}
		//card.setLimit(Integer.parseInt(getSumm(doc.getElementsByAttributeValue("class", "abssHJA39 ab1W755ag gbssHJA39 sbssHJA39").get(3*i).text())));

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
