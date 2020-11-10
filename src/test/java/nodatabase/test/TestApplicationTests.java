package nodatabase.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import app.math.api.FactorizeEndpoint;
import app.math.api.LinearAlgebraEndpoint;
import app.math.domain.Factorize;
import app.math.domain.Root;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		Root r = new Root("x^5-5x^3+4x",-3,3);
		ArrayList<BigDecimal> roots = r.getRoots();
		for(BigDecimal root:roots) {
			System.out.println("found root: "+root);
		}
	}
}
