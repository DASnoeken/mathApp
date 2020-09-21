package nodatabase.test.api;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Currency;

@RestController
public class WebPageEndpoint {
	@GetMapping("/web/test")
	public String webtest() throws IOException { 
		Document doc = Jsoup.connect("https://themoneyconverter.com/EUR/USD").get();
		String test = doc.title()+"<br><br><br>";
		Elements newsHeadlines = doc.select("#res1");
		for (Element headline : newsHeadlines) {
			  test+=headline + "<br>";
			}
		return test;
	}
	
	@GetMapping("/currency/EUR/USD/{input}")
	public double eurToUsd(@PathVariable String input) throws IOException {
		Currency c = new Currency();
		Document doc = Jsoup.connect("https://themoneyconverter.com/EUR/USD").get();
		String rate = "";
		Element conversionRate = doc.select("#res1").get(0);
		rate += conversionRate;
		double ans = c.convert(rate, Double.parseDouble(input));
		return ans;
	}
}
