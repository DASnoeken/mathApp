package nodatabase.test.api;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Root;

@RestController
public class RootEndpoint {
	@GetMapping("Root/getRoot/")
	public String getRoot(@RequestParam String polynomial, @RequestParam long xmin, @RequestParam long xmax) {
		System.out.println(polynomial);
		Root r = new Root(polynomial,xmin,xmax);
		String ans = new String("Found roots at: ");
		for(BigDecimal root:r.getRoots()) {
			ans+="$$x = "+root+"$$<br>";
		}
		return ans;
	}
}
