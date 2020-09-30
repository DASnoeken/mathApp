package nodatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.api.LinearAlgebraEndpoint;
import nodatabase.test.domain.Matrix;
import nodatabase.test.domain.MatrixDimensionException;
import nodatabase.test.domain.MatrixException;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		Matrix m = new Matrix(3,3);
		m.stringToMatrix("1,3,2;6,8,7;3,9,5");
		try {
			m.inverse();
		} catch (MatrixDimensionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
