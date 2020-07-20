package nodatabase.test.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Encryption {
	private String input;
	private String output;
	private int encryptionOrder;
	
	public Encryption(String s,int o) {this.encryptionOrder=o+1;this.input=s;encrypt();}
	
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
		ArrayList<Long> xar = randomizedXValues(this.encryptionOrder, -1*this.encryptionOrder*10, this.encryptionOrder*10);
		BigInteger inputInteger = new BigInteger(s);
		Random dC = new Random();
		ArrayList<BigInteger> a_Values = new ArrayList<>();
		a_Values.add(inputInteger);
		int sign;
		for(int i = 1; i <encryptionOrder;i++) {
			sign = (int) Math.pow(-1.0, dC.nextInt());
			a_Values.add(nextRandomBigInteger(new BigInteger(s)).multiply(new BigInteger(Integer.toString(sign))));
		}
		ArrayList<BigInteger> outNumbers = new ArrayList<BigInteger>();
		for(Long x_val:xar) {
			outNumbers.add(getNumber(a_Values,x_val));
		}
		output+="point: (xvalue, yvalue);<br>";
		for(int i = 0;i<outNumbers.size();i++) {
			output+=""+(i+1)+": ("+xar.get(i)+", " + outNumbers.get(i)+");<br>";
		}
	}
	
	private BigInteger getNumber(ArrayList<BigInteger> a_vals, Long x_val) {
		BigInteger y = new BigInteger("0");
		int p = 0;
		for(BigInteger a:a_vals) {
			y=y.add(a.multiply(BigInteger.valueOf((long) Math.pow((double)x_val,(double)p))));
			//System.out.println("p = "+p+ " a_n="+a);
			p++;
		}
		return y;
	}
	
	private ArrayList<Long> randomizedXValues(int numOfValues, int min, int max) {
		ArrayList<Long> ans = new ArrayList<Long>();
		for(int i=0;i<numOfValues;i++) {
			Long number = ThreadLocalRandom.current().nextLong(min,max);
			if(!ans.contains(number)&& !number.equals(0l))
				ans.add(number);
			else
				while(true) {
					number = ThreadLocalRandom.current().nextLong(min,max);
					if(!ans.contains(number)) {
						ans.add(number);
						break;
					}
				}
		}
		return ans;
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
