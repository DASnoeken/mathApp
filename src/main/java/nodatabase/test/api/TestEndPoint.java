package nodatabase.test.api;

import java.math.BigInteger;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndPoint {
	
	@GetMapping("/sum/{begin}/{end}")
	public BigInteger getSum(@PathVariable String begin, @PathVariable String end) {
		BigInteger ans;
		if(Long.parseLong(end)>1000000000) {
			BigInteger Begin = new BigInteger(begin);
			BigInteger End = new BigInteger(end);
			ans=(Begin.add(End)).multiply((End.subtract(Begin).add(new BigInteger("1")))).divide(new BigInteger("2"));
		}else {
			long beginl = Long.parseLong(begin);
			long endl = Long.parseLong(end);
			long answer = (beginl+endl)*(endl-beginl+1)/2;
			ans = BigInteger.valueOf(answer);
		}
		return ans;
	}
	
	@GetMapping("/factorial/{num}")
	public String factorial(@PathVariable String num) {
		if(num.contains("-")) {
			return "No Negative Numbers Please";
		}
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
	public String gete(@PathVariable long n) {
		if(n==0) {
			return "ILLEGAL VALUE!";
		}else if(n<0) {
			n=abs(n);
		}
		/*BigDecimal largeE = new BigDecimal(1);
		BigDecimal largeE2 = new BigDecimal(1).divide(BigDecimal.valueOf(n),MathContext.DECIMAL128);
		BigDecimal largeE3 = largeE.add(largeE2);
		largeE3 = largeE3.pow(n);*/
		if(n>100000000000l) {
			return "Value too large";
		}
		double largeE = 1.0;
		double largeE2 = 1.0/((double) n);
		double largeE3 = (largeE + largeE2);
		largeE3 = Math.pow(largeE3, (double) n);
		String ans = Double.toString(largeE3);
		if(ans.length()>200)
			ans = ans.substring(0, 200);
		return ans;
	}
	private long abs(long num) {
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
