package nodatabase.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Binomial;

@RestController
public class BinomialEndpoint {
	@GetMapping("/Binomial/get/{n}")
	public String getBinomial(@PathVariable int n) {
		Binomial b = new Binomial(n);
		if(n<=20) {
			b.solveEquationSmall();
		}else {
			b.solveEquation();
		}
		return b.getSolution();
	}
}
