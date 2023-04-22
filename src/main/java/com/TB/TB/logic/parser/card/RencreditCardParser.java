
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

@RequiredArgsConstructor
@Component
public class RencreditCardParser {

	private final CardRepository cardRepository;
	private final DbInfoRepository dbInfoRepository;
	@EventListener(ApplicationReadyEvent.class)
	public void ParserRenceditCard() throws IOException {

		Document doc = Jsoup.connect("https://rencredit.ru/cards/").get();
		Elements oneElements = doc.getElementsByAttributeValue("class", "card-detail__content");
		Elements imageElements = doc.getElementsByAttributeValue("class", "card-detail__picture card-detail__picture--blue");
		//Elements url = doc.getElementsByAttributeValue("class", "list__item card-detail__action card-detail__action--link");
		Elements url = doc.getElementsByAttributeValue("class", "list__item card-detail__action card-detail__action--link");
		for ( int i=0; i<2; i++)  //Две первые кредитные карты ренкредита
		{
			Element oneElement = oneElements.get(i);
			Element imageElement = imageElements.get(i);

			String icon = imageElement.child(0).getElementsByAttribute("srcset").get(1).select("source").attr("srcset");

			Element zeroElement = oneElement.child(0);
			Element cardInfo = zeroElement.child(0).attr("class", "card-detail__heading");
			Elements cardName = cardInfo.select("h3");
			zeroElement = oneElement.child(1);
			cardInfo = zeroElement.child(0).attr("class", "card-detail__value card-detail__value--special");
			Element cardInfoz = cardInfo.child(0).child(1).child(1);
			Element cardInfozz = cardInfo.child(1).child(1).child(1);

			Card rencr = new Card(Integer.parseInt(cardInfoz.text().split(" ")[0]), Integer.parseInt(cardInfozz.text().replaceAll("\\s+","")), 0, null, null, null, 0);
			rencr.setName(cardName.text());
			rencr.setBank("Ренессанс кредит");
			rencr.setIcon("https://rencredit.ru" + icon);
			Element e = url.get(i);
			String ew = url.get(i).select("a").attr("href");
			//rencr.setUrlProduct("https://rencredit.ru" + url.get(i).attr("href"));
			String urlProduct = url.get(i).select("a").attr("href");
			urlProduct = (urlProduct == null || urlProduct.length() == 0) ? null : urlProduct.substring(0, urlProduct.length() - 1);

			rencr.setUrlProduct(urlProduct);
			cardRepository.save(rencr);
		}
		DbInfo dbInfo = new DbInfo();
		dbInfo.setNameBase("GFO_CARD");
		dbInfo.setDateUpdate(LocalDateTime.now());
		dbInfo.setUrl("https://rencredit.ru/cards/");
		dbInfo.setNumber(2);
		dbInfoRepository.save(dbInfo);
	}

}
