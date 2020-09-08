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
		return Double.toString(conv.getFahrenheit()) + " <sup>o</sup>F";
	}
	
	@GetMapping("/Units/ftoc/{value}")
	public String getFtoC(@PathVariable String value) {
		Double fahrenheit = Double.valueOf(value);
		Conversion conv = new Conversion();
		conv.setFahrenheit(fahrenheit);
		conv.fToC();
		return Double.toString(conv.getCelcius()) + " <sup>o</sup>C";
	}
	
	@GetMapping("/Units/kgToLbs/{value}")
	public String getLbs(@PathVariable String value) {
		Conversion c = new Conversion();
		Double kg = Double.valueOf(value);
		c.setKg(kg);
		c.kgToLbs();
		return Double.toString(c.getLbs()) + " lbs";
	}
	
	@GetMapping("/Units/lbsToKg/{value}")
	public String getKg(@PathVariable String value) {
		Conversion c = new Conversion();
		Double lbs = Double.valueOf(value);
		c.setLbs(lbs);
		c.lbsToKg();
		return Double.toString(c.getKg()) + " kg";
	}
	
}
