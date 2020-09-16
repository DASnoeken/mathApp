package nodatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.domain.Calculator;
import nodatabase.test.domain.Encryption;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		Calculator c = new Calculator("5*5-3*3+3*4");
		assertEquals(28,c.getAnswer());
		Calculator c2 = new Calculator("5*5-3/4");
		assertEquals(24.25,c2.getAnswer());
		Calculator c3 = new Calculator("1+1+1+1+1+1");
		assertEquals(6,c3.getAnswer());
		Calculator c4 = new Calculator("5*5-3");
		assertEquals(22,c4.getAnswer());
	}

}
