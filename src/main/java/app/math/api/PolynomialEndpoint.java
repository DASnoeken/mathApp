package app.math.api;

import java.math.BigDecimal;
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
	public String getRoot(@RequestParam String polynomial, @RequestParam long xmin, @RequestParam long xmax) {
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
		return ans;
	}
	
	@GetMapping("Polynomial/getMinMax/")
	public String getMinMax(@RequestParam String polynomial, @RequestParam long xmin, @RequestParam long xmax) {
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
		return ans;
	}
}
