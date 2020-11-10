package app.math.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.math.domain.Conversion;

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
	
	@GetMapping("/Units/milesToKm/{value}")
	public String getKm(@PathVariable String value) {
		Conversion c = new Conversion();
		Double miles = Double.valueOf(value);
		c.setMiles(miles);
		c.milesToKm();
		return Double.toString(c.getKm()) + " km";
	}
	
	@GetMapping("/Units/kmToMiles/{value}")
	public String getMiles(@PathVariable String value) {
		Conversion c = new Conversion();
		Double km = Double.valueOf(value);
		c.setKm(km);
		c.kmToMiles();
		return Double.toString(c.getMiles()) + " miles";
	}
	
	@GetMapping("/Units/cmToInch/{value}")
	public String getFeetInch(@PathVariable String value) {
		Conversion c = new Conversion();
		Double cm = Double.valueOf(value);
		c.setCm(cm);
		c.cmToInch();
		int feet = (int) c.getInch()/ 12;
		String inches = Double.toString(c.getInch()%12);
		String ans = feet + "' " + inches + "''";
		return ans;
	}
	
	@GetMapping("/Units/inchToCm/{foot}/{inch}")
	public String getCm(@PathVariable String foot, @PathVariable String inch) {
		Conversion c = new Conversion();
		Double inches = Double.valueOf(inch);
		Double feet = Double.valueOf(foot);
		Double inchesFromFeet = feet * 12;
		inches = inches + inchesFromFeet;
		c.setInch(inches);
		c.inchToCm();
		return Double.toString(c.getCm());
	}
}
