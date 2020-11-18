package app.math.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.math.domain.Helper;
import app.math.domain.Polynomial;
import app.math.domain.Root;

@RestController
public class PolynomialEndpoint {
	@GetMapping("Polynomial/getRoot/")
	public String getRoot(@RequestParam String polynomial, @RequestParam double xmin, @RequestParam double xmax, @RequestParam boolean darkState) {
		Root r = new Root(polynomial,xmin,xmax);
		r.roundRoots();
		String ans = "For polynomial $$" + Helper.texify(polynomial) + "$$" + 
				"Found roots at: ";
		if(r.getRoots().size()>0) {
			for(BigDecimal root:r.getRoots()) {
				ans+="$$x = "+root+"$$<br>";
			}
		}else {
			ans+="<br> No real roots found!";
		}
		if(darkState) {
			ans+="<br><canvas id=\"polynomial-roots\" class=\"polynomialCanvas-dark\" width=\"500\" height=\"500\">\r\n" + 
				"        Your browser does not support the canvas element.\r\n" + 
				"    </canvas>";
		}else {
			ans+="<br><canvas id=\"polynomial-roots\" class=\"polynomialCanvas\" width=\"500\" height=\"500\">\r\n" + 
					"        Your browser does not support the canvas element.\r\n" + 
					"    </canvas>";
		}
		return ans;
	}
	
	@GetMapping("Polynomial/getMinMax/")
	public String getMinMax(@RequestParam String polynomial, @RequestParam double xmin, @RequestParam double xmax, @RequestParam boolean darkState) {
		String ans = "For polynomial $$" + Helper.texify(polynomial) + "$$";
		Polynomial p = new Polynomial(polynomial);
		ans+="Found minimum / maximum / plateau at: ";
		ArrayList<BigDecimal> minmaxpla = p.getMinMax(xmin, xmax);
		if(minmaxpla.size()>0) {
			for(BigDecimal mmp:minmaxpla) {
				ans+="$$x = "+mmp+"$$<br>";
			}
		}else {
			ans+="<br>No minimum / maximum / plateau found!";
		}
		if(darkState) {
			ans+="<br><canvas id=\"polynomial-minmax\" class=\"polynomialCanvas-dark\" width=\"500\" height=\"500\">\r\n" + 
				"        Your browser does not support the canvas element.\r\n" + 
				"    </canvas>";
		}else {
			ans+="<br><canvas id=\"polynomial-minmax\" class=\"polynomialCanvas\" width=\"500\" height=\"500\">\r\n" + 
					"        Your browser does not support the canvas element.\r\n" + 
					"    </canvas>";
		}
		return ans;
	}
	
	@GetMapping("/Polynomial/GetCoefficients")
	public ArrayList<BigDecimal> getCoefficients(@RequestParam String polynomial){
		Polynomial p = new Polynomial(polynomial);
		return p.getCoefficients();
	}
	
	@GetMapping("/Polynomial/GetPowers")
	public ArrayList<BigInteger> getPowers(@RequestParam String polynomial){
		Polynomial p = new Polynomial(polynomial);
		return p.getPowers();
	}
	
	@GetMapping("/Polynomial/GetIntegral")
	public String getIntegral(@RequestParam String polynomial,@RequestParam BigDecimal a, @RequestParam BigDecimal b) {
		Polynomial p = new Polynomial(polynomial);
		String ans = "$$\\int_{"+a+"}^{"+b+"}"+polynomial+"="+p.integrate(a, b).toString()+"$$";
		return ans;
	}
}
