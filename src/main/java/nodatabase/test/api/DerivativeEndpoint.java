package nodatabase.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Derivative;

@RestController
public class DerivativeEndpoint {
	@GetMapping("/get/derivative/{function}")
	public String getDerivative(@PathVariable String function) {
		Derivative d = new Derivative(function);
		String derivative = new String();
		try {
			d.derive();
			d.texify();
			derivative = "Original: $$"+d.getFunction()+"$$ Derivative: $$"+d.getResult()+"$$";
		}catch(Exception e) {
			e.printStackTrace();
			derivative = "INPUT ERROR!";
		}
		return derivative;
	}
}
