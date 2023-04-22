package com.TB.TB.logic.parser.mortgage;

import com.TB.TB.model.entity.mortgage.Mortgage;
import com.TB.TB.model.entity.mortgage.TypeMortgage;
import com.TB.TB.repository.MortgageRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class VtbMortgageParser {

	private final MortgageRepository mortgageRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void ParserVtbMortgage() throws IOException {
		Document doc = Jsoup.connect("https://www.vtb.ru/personal/ipoteka/").get();
		Elements elementsWithIconAndInfo = doc.getElementsByAttributeValue(
				"class", "groupstyles__Box-foundation-kit__sc-1gqk80i-0 eNVOHc card-mediumstyles__ParentGroupStyled-card-base__sc-senydt-1 gLdDOJ");
		Elements names = doc.getElementsByAttributeValue(
				"class", "typographystyles__Box-foundation-kit__sc-14qzghz-0 gGALTE"
		);
		Elements procendAndSum = doc.getElementsByAttributeValue(
				"class", "typographystyles__Box-foundation-kit__sc-14qzghz-0 jEFSaq numbersstyles__TypographyTitle-foundation-kit__sc-1xhbrzd-4 haHdlc"
		);

		Elements icons = doc.getElementsByAttributeValue(
				"class", "imagestyles__Picture-foundation-kit__sc-t12pg7-0 jJMRhY card-mediumstyles__CardImage-card-base__sc-senydt-5 jqtXxX"
		);

		Elements url = doc.getElementsByAttributeValue(
				"class", "buttonstyles__LinkBox-foundation-kit__sc-sa2uer-1 eRJSDA cardstyles__ButtonStyled-card-base__sc-zg4pyq-0 bGwUgm"
		);

		for (int i=0; i<procendAndSum.size()/2; i++) {
			Mortgage mortgage = new Mortgage();
			String name = names.get(i).text();
			mortgage.setName(name);
			String urlProduct = url.get(i).attr("href");
			mortgage.setUrlProduct("https://www.vtb.ru/" + urlProduct.substring(0, urlProduct.length() - 1));
			mortgage.setBank("ВТБ");
			mortgage.setRate(logicParseRate(procendAndSum.get(2*i).text()));
			mortgage.setSum((int) logicParseRate(procendAndSum.get(2*i+1).text()));
			mortgage.setIcon("https://www.vtb.ru/" + icons.get(i).child(0).attr("srcset").split(" ")[0]);
			mortgage.setTypeMortgage(fillTypeMortgage(name));
			mortgageRepository.save(mortgage);
			//System.out.println(names.get(i).text());
			//System.out.println(logicParseRate(procendAndSum.get(2*i).text()));
			//System.out.println(logicParseRate(procendAndSum.get(2*i+1).text()));
			//System.out.println(icons.get(i).child(0).attr("srcset").split(" ")[0]);
			//System.out.println(url.get(i).attr("href"));
		}

	}
	private static double logicParseRate(String str) {//Метод получает строку вида "От 4,5 %" и отдает значение 4.5
		Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
		Matcher matcher=pat.matcher(str);
		while (matcher.find()) {
			return Double.valueOf(matcher.group().replace(",", "."));
		};
		return 0;
	}

	private static int logicParceSum(String str) { //Метод получает строку вида "до 2 000 000 ₽" и отдает 2000000
		String[] parts = str.split("[^0-9]");
		String result = "";
		for (int i = 0; i < parts.length; i++) {
			if (!parts[i].equals("")) {
				result = result + parts[i];
			}
		}
		return Integer.parseInt(result);
	}

	private static TypeMortgage fillTypeMortgage(String name) {
		if (name.contains("сем")) {
			return TypeMortgage.FAMILY;
		} else if (name.contains("Новост")) {
			return TypeMortgage.NEW_BUILDING;
		} else if (name.contains("Вторич")) {
			return TypeMortgage.RESELLERS;
		} else if (name.contains("Вое") && name.contains("ипотека")) {
			return TypeMortgage.WAR;
		} else if (name.contains("Льгот")) {
			return TypeMortgage.PREFERENTIAL_ALL;
		} else if (name.contains("Реф") && name.contains("военн")) {
			return TypeMortgage.REF_WAR;
		} else if (name.contains("Реф")) {
			return TypeMortgage.REF;
		} else if (name.contains("IT")) {
			return TypeMortgage.IT;
		} else if (name.contains("Дальне")) {
			return TypeMortgage.FAR_EASTERN;
		} else if (name.contains("строительс")) {
			return TypeMortgage.BUILDING;
		} else if (name.contains("регион")) {
			return TypeMortgage.REGION;
		} else if (name.contains("дом")) {
			return TypeMortgage.PRIVATE_HOUSE;
		}
		return null;
	}
}
