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
		Calculator c2 = new Calculator("5*5-3/4");
		assertEquals(24.25,c2.getAnswer());
		Calculator c = new Calculator("5*5*5-3*3+3*4");
		assertEquals(128,c.getAnswer());
		Calculator c3 = new Calculator("1+1+1+1+1+1");
		assertEquals(6,c3.getAnswer());
		Calculator c4 = new Calculator("5*5-3");
		assertEquals(22,c4.getAnswer());
		Calculator c5 = new Calculator("5*5*5");
		assertEquals(125, c5.getAnswer());
		Calculator c6 = new Calculator("5*4*3*2*1");
		assertEquals(120,c6.getAnswer());
		Calculator c7 = new Calculator("5*4*3/2*1");
		assertEquals(30,c7.getAnswer());
		Calculator c8 = new Calculator("3*4+1+2*3");
		assertEquals(19, c8.getAnswer());
	}

}
