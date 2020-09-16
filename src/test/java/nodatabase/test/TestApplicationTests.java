package nodatabase.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.domain.Calculator;
import nodatabase.test.domain.Encryption;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		Calculator c = new Calculator("16+27*38/49-50");
		
	}

}
