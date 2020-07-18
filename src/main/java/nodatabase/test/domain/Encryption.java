package nodatabase.test.domain;

import java.math.BigInteger;
import java.util.Random;

public class Encryption {
	private String input;
	private String output;
	
	public Encryption(String s) {this.input=s;encrypt();}
	
	public String getOutput() {
		return output;
	}
	
	private void encrypt() {
		output = new String();
		char[] c = this.input.toCharArray();
		int[] cints = new int[c.length];
		String s = new String();
		for(int i = 0;i<c.length;i++) {
			cints[i] = (int)(c[i]+101);
			s+=cints[i];
		}
		BigInteger inputInteger = new BigInteger(s);
		Random dC = new Random();
		BigInteger directionCoef = nextRandomBigInteger(new BigInteger(s));
		int sign = dC.nextInt();
		if(sign%2==1) {
			directionCoef = directionCoef.negate();
		}
		BigInteger[] outNumbers = {inputInteger.add(directionCoef),inputInteger.add(directionCoef.multiply(new BigInteger("2")))};
		for(int i = 0;i<2;i++) {
			output+=outNumbers[i]+";  ";
		}
	}
	
	private BigInteger nextRandomBigInteger(BigInteger n) {
	    Random rand = new Random();
	    BigInteger result = new BigInteger(n.bitLength(), rand);
	    while( result.compareTo(n) >= 0 ) {
	        result = new BigInteger(n.bitLength(), rand);
	    }
	    return result;
	}
}
