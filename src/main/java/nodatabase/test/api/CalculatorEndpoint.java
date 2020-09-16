package nodatabase.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Calculator;

@RestController
public class CalculatorEndpoint {
	private Calculator c;
	@PostMapping("/calculator/setCalculation")
	public void setCalc(@RequestBody String input) {
		this.c = new Calculator(input);
	}
	
	@GetMapping("/calculator/getAnswer")
	public String getAnswer() {
		return Double.toString(c.getAnswer());
	}
}
