package app.math;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import app.math.api.ComplexEndpoint;
import app.math.domain.Polynomial;

@SpringBootTest
class MathApplicationTests {

	@Test
	void contextLoads() {
		Polynomial p = new Polynomial("x^2-x");
		System.out.println(p.integrate(BigDecimal.ZERO, BigDecimal.ONE));
		
	}
}
