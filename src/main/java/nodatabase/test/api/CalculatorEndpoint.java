package nodatabase.test.api;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Calculator;

@RestController
public class CalculatorEndpoint {
	private Calculator c;
	@PostMapping("/calculator/setCalculation/")
	@ResponseBody
	public void setCalc(@RequestBody String input,@RequestParam String trigState) {
		this.c = new Calculator(input,trigState);
	}
	
	@GetMapping("/calculator/getAnswer")
	public String getAnswer() {
		if(c.getErrorMessage().equals("None")) {
			String ans = c.getAnswer().toString();
			String strAns = new String();
			String tmp = ans.toString();
			for (int i = 1; i < tmp.length()+1; i++) {
				strAns += tmp.charAt(i-1);
				if (i % 80 == 0 && i != 0) {
					strAns += "<br>";
				}
			}
			return strAns;
		}else {
			return c.getErrorMessage();
		}
	}
	
	@GetMapping("/calculator/setTrigState/{value}")
	public void setTrigState(@PathVariable String value) {
		this.c.setTrigState(value);
	}
}
