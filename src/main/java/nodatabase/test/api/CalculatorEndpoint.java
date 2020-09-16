package nodatabase.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Calculator;

@RestController
public class CalculatorEndpoint {
	@GetMapping("/calculator/{input}")
	public String getAnswer(@PathVariable String input) {
		Calculator c = new Calculator(input);
		
		return Double.toString(c.getAnswer());
	}
}
