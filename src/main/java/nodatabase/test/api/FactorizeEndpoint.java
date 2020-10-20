package nodatabase.test.api;

import java.math.BigInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Factorize;

@RestController
public class FactorizeEndpoint {
	@GetMapping("/Factorize/getPrimeFactorization/{num}")
	public String getPrimeFactorization(@PathVariable String num) {
		BigInteger bi = new BigInteger(num);
		Factorize f = new Factorize(bi);
		f.factorizeNumber();
		String ans = new String();
		if (f.getFactors().size() > 2) {
			ans += "Prime factors are: ";
			for (int i = 0; i < f.getFactors().size()-3; i++) {
				if(f.getPowers().get(i).equals(BigInteger.ONE))
					ans += f.getFactors().get(i) + ", ";
				else
					ans += f.getFactors().get(i) + "^" + f.getPowers().get(i) + ", ";
			}
			if(f.getPowers().get(f.getFactors().size()-3).equals(BigInteger.ONE))
				ans += f.getFactors().get(f.getFactors().size()-3);
			else
				ans += f.getFactors().get(f.getFactors().size()-3) + "^" + f.getPowers().get(f.getFactors().size()-3);
			
		} else {
			return "Number is prime";
		}
		return ans;
	}
}
