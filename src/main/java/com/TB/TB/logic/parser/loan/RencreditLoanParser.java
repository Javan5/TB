package com.TB.TB.logic.parser.loan;

import com.TB.TB.model.entity.loan.Loan;
import com.TB.TB.model.entity.loan.TypeLoan;
import com.TB.TB.repository.CardRepository;
import com.TB.TB.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
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
public class RencreditLoanParser {

	private final LoanRepository loanRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void ParserRenceditLoan() throws IOException {
		Document doc = Jsoup.connect("https://rencredit.ru/loans/").get();
		Elements elementsWithIconAndInfo = doc.getElementsByAttributeValue("class", "card-detail2__body");
		for (int i=0; i<elementsWithIconAndInfo.size(); i++) {

			Elements cost = elementsWithIconAndInfo.get(i).getElementsByAttributeValue("class", "card-detail2__picture").attr("class", "card-detail2__image");
			String icon = elementsWithIconAndInfo.get(i).getElementsByAttributeValue("class", "card-detail2__image").get(0).child(0).attr("srcset");

			String name = elementsWithIconAndInfo.get(i).getElementsByAttributeValue("class", "card-detail2__title").text();
			Elements elementsLoan = elementsWithIconAndInfo.get(i).getElementsByAttributeValue("class", "card-detail2__info-list");
			Elements elementsUrlGo = elementsWithIconAndInfo.get(i).getElementsByAttributeValue("class", "button-new button-new--brand button-new--mobile-medium button-new--mobile-only-full");
			Elements elementsUrl = elementsWithIconAndInfo.get(i).getElementsByAttributeValue("class", "button-new button-new--text-brand button-new--mobile-medium button-new--mobile-only-full");

			Loan loan = new Loan();
			loan.setName(name);
			System.out.println(name);
			String rate = elementsLoan.attr("class", "card-detail2__info-value").get(0).child(0).child(1).text();
			loan.setRate(logicParseRate(rate));
			String sum = elementsLoan.attr("class", "card-detail2__info-value").get(0).child(1).child(1).text();
			loan.setSum(logicParceSum(sum));
			String term = elementsLoan.attr("class", "card-detail2__info-value").get(0).child(2).child(1).text();
			logicParceTerm(loan, term);
			String valueUrlGo = elementsUrlGo.attr("href");
			String urlProduct = elementsUrl.attr("href");
			urlProduct = (urlProduct == null || urlProduct.length() == 0) ? null : urlProduct.substring(0, urlProduct.length() - 1);
			valueUrlGo = StringUtil.isBlank(valueUrlGo) ? valueUrlGo : valueUrlGo.substring(0, valueUrlGo.length() - 1);
			loan.setUrlGo(StringUtil.isBlank(valueUrlGo) ? urlProduct : valueUrlGo);
			loan.setUrlProduct(urlProduct);
			loan.setIcon("https://rencredit.ru" + icon);
			loan.setBank("Ренессанс кредит");
			loan.setTypeLoan(logicFindTypeLoan(name));
			loanRepository.save(loan);
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

	private static void logicParceTerm(Loan loan, String str) { //Метод получает строку вида "12-84 мес" и отдает отдельно 2 числа 12 и 84, сеттая их в объект
		String[] parts = str.split("[^0-9]");
		String result = "";
		loan.setTermMin(Integer.parseInt(parts[0]));
		loan.setTermMax(Integer.parseInt(parts[1]));
	}

	private static TypeLoan logicFindTypeLoan(String name) {
		if (name.contains("налич")) {
			return TypeLoan.CASH;
		} else if (name.contains("Рефен") || name.contains("рефен")) {
			return TypeLoan.REFINANCING;
		}
		return TypeLoan.OTHER;
	}
}
