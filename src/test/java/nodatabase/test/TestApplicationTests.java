package nodatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

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
		double value = 300052.000000009;
		System.out.println(BigDecimal.valueOf(value).scale());
		if(BigDecimal.valueOf(value).scale() > 5) {
			Double tmp = value;
			String tmpString = Double.toString(tmp);
			if(tmpString.matches("\\d{0,}\\.\\d{0,3}0{3,}\\d{0,}")) {
				System.out.println("jojo");
				tmpString = tmpString.replaceAll("0{3}[1-9]{0,}$", "");
			}
			value = Double.valueOf(tmpString);
		}
		System.out.println(value);
		
		
	}

}
