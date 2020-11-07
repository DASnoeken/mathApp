package nodatabase.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nodatabase.test.domain.Root;

@RestController
public class RootEndpoint {
	@GetMapping("Root/getRoot/")
	public String getRoot(@RequestParam String polynomial, @RequestParam long x_min, @RequestParam long x_max) {
		Root r = new Root(polynomial,x_min,x_max);
		
		return null;
	}
}
