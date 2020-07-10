package nodatabase.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.domain.Matrix;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		Matrix m = new Matrix(2,2);
		m.setMatrixElement(0, 0, 1.0);
		m.setMatrixElement(0, 1, 2.0);
		m.setMatrixElement(1, 0, 3.0);
		m.setMatrixElement(1, 1, 4.0);
		m.printElements();
		
		Matrix m2 = new Matrix(2,2);
		m2.setMatrixElement(0, 0, 5.0);
		m2.setMatrixElement(0, 1, 6.0);
		m2.setMatrixElement(1, 0, 7.0);
		m2.setMatrixElement(1, 1, 8.0);
		m2.printElements();
		
		Matrix prod = m.multiply(m2);
		prod.printElements();
		
		Matrix sum = m.add(m2);
		sum.printElements();
	}

}
