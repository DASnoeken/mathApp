package nodatabase.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.domain.Encryption;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		Encryption e = new Encryption("198199200201202203204205206207208");
		
	}

}
