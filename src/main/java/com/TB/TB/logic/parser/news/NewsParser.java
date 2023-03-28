package com.TB.TB.logic.parser.news;

import com.TB.TB.model.entity.dbinfo.DbInfo;
import com.TB.TB.model.entity.news.News;
import com.TB.TB.model.entity.news.TypeNews;
import com.TB.TB.repository.CurrencyRepository;
import com.TB.TB.repository.DbInfoRepository;
import com.TB.TB.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class NewsParser {

	private final NewsRepository newsRepository;
	private final DbInfoRepository dbInfoRepository;


	@EventListener(ApplicationReadyEvent.class)
	public void CurrencyParser() throws IOException {
		DbInfo dbInfo = new DbInfo();
		dbInfo.setNameBase("GFO_NEWS");
		dbInfo.setUrl("https://ria.ru");
		dbInfo.setNumber(
				fillNews("/economy/", TypeNews.ECONOMY) +
				fillNews("/politics/", TypeNews.POLICE) +
				fillNews("/society/", TypeNews.SOCIETY)
		);
		dbInfo.setDateUpdate(LocalDateTime.now());
		dbInfoRepository.save(dbInfo);
	}

	private int fillNews(String url, TypeNews typeNews) throws IOException {
		Document doc = Jsoup.connect("https://ria.ru" + url).get();
		Elements oneElements = doc.getElementsByAttributeValue("class", "list-item__content");
		for (int i =0; i < oneElements.size(); i++) {
			News news = new News();
			news.setIcon(oneElements.get(i).select("source").get(1).attr("srcset"));
			news.setText(oneElements.get(i).getElementsByAttributeValue("class", "list-item__title color-font-hover-only").text());
			news.setUrl(oneElements.get(i).getElementsByAttributeValue("class", "list-item__title color-font-hover-only").attr("href"));
			news.setCommentUrl(oneElements.get(i).select("img").attr("alt"));
			news.setTypeNews(typeNews);
			newsRepository.save(news);
		}
		return oneElements.size();
	}
}
