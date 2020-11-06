package nodatabase.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.api.FactorizeEndpoint;
import nodatabase.test.api.LinearAlgebraEndpoint;
import nodatabase.test.domain.Factorize;
import nodatabase.test.domain.Root;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		Root r = new Root("x^3-x",-2,2,1);
		ArrayList<BigDecimal> roots = r.getRoots();
		for(BigDecimal root:roots) {
			System.out.println(root);
		}
	}
}
