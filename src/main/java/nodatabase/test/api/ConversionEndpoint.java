package nodatabase.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Conversion;

@RestController
public class ConversionEndpoint {
	
	@GetMapping("/Units/ctof/{value}")
	public String getCtoF(@PathVariable String value) {
		Double celcius = Double.valueOf(value);
		Conversion conv = new Conversion();
		conv.setCelcius(celcius);
		conv.cToF();
		return Double.toString(conv.getFahrenheit());
	}
	
	@GetMapping("/Units/ftoc/{value}")
	public String getFtoC(@PathVariable String value) {
		Double fahrenheit = Double.valueOf(value);
		Conversion conv = new Conversion();
		conv.setFahrenheit(fahrenheit);
		conv.fToC();
		return Double.toString(conv.getCelcius());
	}
	
}
