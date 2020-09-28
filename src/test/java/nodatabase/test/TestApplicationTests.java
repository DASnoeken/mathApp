package nodatabase.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import nodatabase.test.api.LinearAlgebraEndpoint;

@SpringBootTest
class TestApplicationTests {

	@Test
	void contextLoads() {
		LinearAlgebraEndpoint lae = new LinearAlgebraEndpoint();
		lae.addMatrix("1,2,3; 4,5,6; 7,8,9; 10,11,12");
		lae.addMatrix("2,4,6; 8,10,12; 14,16,18; 20,22,24");
		lae.addMatrix("17.0, 23.0; 38.0, 53.0; 59.0, 83.0;");
		lae.getMatrix(0).printElements();
		System.out.println();
		lae.getMatrix(1).printElements();
		System.out.println();
		System.out.println(lae.getMatrix(1).toHTMLString());
		System.out.println();
		System.out.println(lae.getMatrix(0).add(lae.getMatrix(1)).toString());
		System.out.println();
		System.out.println(lae.getMatrix(0).multiply(lae.getMatrix(2)));
		System.out.println(lae.getAllMatrixStrings());
		lae.clearMatrices();
	}

}
