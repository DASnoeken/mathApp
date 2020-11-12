package app.math;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import app.math.api.ComplexEndpoint;

@SpringBootTest
class MathApplicationTests {

	@Test
	void contextLoads() {
		ComplexEndpoint ce = new ComplexEndpoint();
		ce.createComplex(1, 2);
		ce.multiply(0, "2-3i");
		System.out.println(ce.showAllNumbers());
	}
}
