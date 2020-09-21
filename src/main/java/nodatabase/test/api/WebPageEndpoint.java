package nodatabase.test.api;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Currency;

@RestController
public class WebPageEndpoint {	
	@GetMapping("/currency/{from}/{to}/{input}")
	public double convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable String input) throws IOException{
		Currency c = new Currency();
		String url = "https://themoneyconverter.com/"+from+"/"+to;
		Document doc = Jsoup.connect(url).get();
		String rate = "";
		Element conversionRate = doc.select("#res1").get(0);
		rate += conversionRate;
		double ans = c.convert(rate, Double.parseDouble(input));
		return ans;
	}
}
