package nodatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.api.LinearAlgebraEndpoint;
import nodatabase.test.domain.Matrix;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		Matrix m = new Matrix(3,4);
		m.stringToMatrix("3,2,5,15;6,4,2,23;7,8,1,98");
		//m.printElements();
		System.out.println();
		m=m.rref();
		System.out.println();
		//m.printElements();
	}

}
