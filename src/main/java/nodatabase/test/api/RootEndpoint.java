package nodatabase.test.api;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Helper;
import nodatabase.test.domain.Root;

@RestController
public class RootEndpoint {
	@GetMapping("Root/getRoot/")
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
}
