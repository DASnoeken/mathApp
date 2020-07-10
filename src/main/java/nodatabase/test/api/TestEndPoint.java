package nodatabase.test.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndPoint {
	@GetMapping("/test/{num}")
	public int getTest(@PathVariable int num) {
		return num;
	}
	
	@GetMapping("/sum/{begin}/{end}")
	public long getSum(@PathVariable long begin, @PathVariable long end) {
		long ans=(begin+end)*(end-begin+1)/2;
		return ans;
	}
	
	@GetMapping("/factorial/{num}")
	public String factorial(@PathVariable String num) {
		BigInteger ans = new BigInteger("1");
		BigInteger initNum = new BigInteger(num);
		for(BigInteger i = BigInteger.valueOf(1l); i.compareTo(initNum)==-1 || i.compareTo(initNum)==0; i=i.add(BigInteger.valueOf(1l))) {
			ans=ans.multiply(i);
		}
		return ans.toString();
	}
	
	@GetMapping("/series/geometric/{num}")
	public String geometricSeries(@PathVariable double num) {
		if(abs(num)>=1) {
			return "Diverges";
		}else {
			double ans = 1/(1-num);
			return Double.toString(ans);
		}
	}
	
	@GetMapping("/getNumber/e/{n}")
	public String gete(@PathVariable int n) {
		if(n==0) {
			return "ILLEGAL VALUE!";
		}else if(n<0) {
			n=abs(n);
		}
		BigDecimal largeE = new BigDecimal(1);
		BigDecimal largeE2 = new BigDecimal(1).divide(BigDecimal.valueOf(n),MathContext.DECIMAL128);
		BigDecimal largeE3 = largeE.add(largeE2).pow(n);
		String ans = largeE3.toString();
		if(ans.length()>200)
			ans = ans.substring(0, 200);
		return ans;
	}
	private int abs(int num) {
		if(num<0)
			num=-num;
		return num;
	}
	private double abs(double num) {
		if(num<0)
			num=-num;
		return num;
	}
}
