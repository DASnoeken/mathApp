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
		String num = "5623";
		Factorize f = new Factorize(new BigInteger(num));
		f.factorizeNumber();
		for(BigInteger bi:f.getFactors()) {
			System.out.println(bi);
		}
		FactorizeEndpoint fe = new FactorizeEndpoint();
		System.out.println(fe.getPrimeFactorization(num));
	}
}
