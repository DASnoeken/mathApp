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
		LinearAlgebraEndpoint lae = new LinearAlgebraEndpoint();
		lae.addMatrix("1,77,5929,456533,28336469843682946767622927812837181147274107593648231414240931644147610718669975713910152064993746351682; 1,-187,34969,-6539203,-408735462557927650339400560330659144340016392982577536241338445928381240821190816416592077233705170823926; 1,130,16900,2197000,136668114539641278629106114138225262067202482378369379411481087861061406576458969648275435558113374153419; 1,160,25600,4096000,254945761247487018912237740721991634311257072292837498926860370957629645522137375270502659371694705375489;");
		System.out.println();
		lae.getMatrix(0).ref().printElements();
		System.out.println();
		lae.getMatrix(0).rref().printElements();
		System.out.println();
	}

}
