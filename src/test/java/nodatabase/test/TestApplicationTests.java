package nodatabase.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.domain.Derivative;
import nodatabase.test.domain.Matrix;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		Derivative d = new Derivative("tan({x^2})");

	}

}
