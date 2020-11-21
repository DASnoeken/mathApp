package app.math;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import app.math.api.ComplexEndpoint;
import app.math.domain.Function;
import app.math.domain.Polynomial;

@SpringBootTest
class MathApplicationTests {

	@Test
	void contextLoads() {
		Function f = new Function("x*e^(x^2)", "degrees");
		f.setXgrid(new BigDecimal("-1"), new BigDecimal("1"), new BigDecimal("0.0001"));
		f.setY();
		ArrayList<BigDecimal> x = f.getXgrid();
		ArrayList<BigDecimal> y = f.getY();
		System.out.println("integrate = " + f.integrate(new BigDecimal("-1"), BigDecimal.ONE));
	}
}
