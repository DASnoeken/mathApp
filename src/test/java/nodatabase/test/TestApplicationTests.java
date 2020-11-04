package nodatabase.test;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.api.FactorizeEndpoint;
import nodatabase.test.api.LinearAlgebraEndpoint;
import nodatabase.test.domain.Factorize;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		LinearAlgebraEndpoint lae = new LinearAlgebraEndpoint();
		lae.addMatrix("1,2,3");
		lae.addMatrix("2,1,6");
		lae.getCrossProduct(0, 1);
	}
}
