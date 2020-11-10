package app.math.api;

import java.math.BigInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndPoint {

	@GetMapping("/sum/{begin}/{end}")
	public String getSum(@PathVariable String begin, @PathVariable String end) {
		BigInteger ans;
		BigInteger Begin = new BigInteger(begin);
		BigInteger End = new BigInteger(end);
		ans = (Begin.add(End)).multiply((End.subtract(Begin).add(new BigInteger("1")))).divide(new BigInteger("2"));
		String strAns = new String();
		String tmp = ans.toString();
		for (int i = 1; i < tmp.length()+1; i++) {
			strAns += tmp.charAt(i-1);
			if (i % 80 == 0 && i != 0) {
				strAns += "<br>";
			}
		}
		return strAns;
	}

	@GetMapping("/factorial/{num}")
	public String factorial(@PathVariable String num) {
		if (num.contains("-")) {
			return "No Negative Numbers Please";
		} else if (num.contains(".")) {
			return "Only integers!";
		}
		BigInteger ans = BigInteger.ONE;
		BigInteger initNum = new BigInteger(num);
		for (BigInteger i = BigInteger.ONE; i.compareTo(initNum) == -1
				|| i.compareTo(initNum) == 0; i = i.add(BigInteger.ONE)) {
			ans = ans.multiply(i);
		}
		String strAns = new String();
		String tmp = ans.toString();
		for (int i = 1; i < tmp.length()+1; i++) {
			strAns += tmp.charAt(i-1);
			if (i % 78 == 0 && i != 0) {
				strAns += "<br>";
			}
		}
		return strAns;
	}

	@GetMapping("/series/geometric/{num}")
	public String geometricSeries(@PathVariable double num) {
		if (abs(num) >= 1) {
			return "Diverges";
		} else {
			double ans = 1 / (1 - num);
			return Double.toString(ans);
		}
	}

	/*@GetMapping("/getNumber/e/{np}") tried this, but is very slow
	public String gete(@PathVariable String np) {
		BigInteger n = new BigInteger(np);
		if (n.equals(BigInteger.ZERO)) {
			return "ILLEGAL VALUE!";
		} else if (n.compareTo(BigInteger.ZERO) < 0) {
			n = n.abs();
		}
		BigDecimal largeE = new BigDecimal("1.0");
		BigDecimal largeE2 = BigDecimal.ONE.divide(new BigDecimal(n));
		BigDecimal largeE3 = largeE.add(largeE2);
		largeE3 = power(largeE3,n);
		String ans = largeE3.toString();
		if (ans.length() > 200)
			ans = ans.substring(0, 200);
		return ans;
	}
	private BigDecimal power(BigDecimal bd, BigInteger bi) {
		BigDecimal ans = BigDecimal.ONE;
		for(BigInteger i = BigInteger.ZERO;i.compareTo(bi)<0;i=i.add(BigInteger.ONE)) {
			ans = ans.multiply(bd);
		}
		return ans;
	}
	*/
	
	@GetMapping("/getNumber/e/{n}")
	public String gete(@PathVariable long n) {
		if (n == 0) {
			return "ILLEGAL VALUE!";
		} else if (n < 0) {
			n = abs(n);
		}
		if (n > 100000000000l) {
			return "Value too large";
		}
		double largeE = 1.0;
		double largeE2 = 1.0 / ((double) n);
		double largeE3 = (largeE + largeE2);
		largeE3 = Math.pow(largeE3, (double) n);
		String ans = Double.toString(largeE3);
		if (ans.length() > 200)
			ans = ans.substring(0, 200);
		return ans;
	}

	private long abs(long num) {
		if (num < 0)
			num = -num;
		return num;
	}

	private double abs(double num) {
		if (num < 0)
			num = -num;
		return num;
	}
}
