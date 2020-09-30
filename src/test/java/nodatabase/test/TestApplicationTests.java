package nodatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.api.LinearAlgebraEndpoint;
import nodatabase.test.domain.Matrix;
import nodatabase.test.domain.MatrixDimensionException;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		Matrix m = new Matrix(3,3);
		m.stringToMatrix("3,2,5;6,4,2;7,8,1");
		double det = 0.0;
		try {
			det = Matrix.determinant(m);
		} catch (MatrixDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(80, det);
	}

}
