package nodatabase.test.domain;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Encryption {
	private String input;
	private String output;
	private int encryptionOrder;
	
	public Encryption(String s) {this.input = s;decrypt();}	//Decryption
	public Encryption(String s,int o) {this.encryptionOrder=o+1;this.input=s;encrypt();}
	
	public String getOutput() {
		return output;
	}
	
	private void decrypt() {
		output = new String();
		char[] c = this.input.toCharArray();
		if(c.length%3!=0) {
			output += "ERROR! INVALID NUMBER OF DIGITS!";
			return;
		}
		int[] cints = new int[c.length/3];
		int j=0;
		char[] outArray = new char[cints.length];
		for(int i = 0;i<c.length;i=i+3) {
			cints[j]=Integer.parseInt(Character.toString(c[i])+Character.toString(c[i+1])+Character.toString(c[i+2]));
			cints[j]-=(101+j);
			outArray[j]=(char)cints[j];
			j++;
		}
		for(char cc : outArray) {
			output+=cc;
		}
	}
	
	private void encrypt() {
		output = new String();
		char[] c = this.input.toCharArray();
		int[] cints = new int[c.length];
		String s = new String();
		for(int i = 0;i<c.length;i++) {
			cints[i] = (int)(c[i]+101)+i;
			s+=cints[i];
		}
		int xBound;
		if(this.encryptionOrder<100) {
			xBound=100;
		}else {
			xBound=2*this.encryptionOrder;
		}
		ArrayList<Long> xar = randomizedXValues(this.encryptionOrder, -1*xBound, xBound);
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
		output+="<br>Printed on single line:<br>";
		for(int i = 0;i<outNumbers.size();i++) {
			if(i<outNumbers.size()-1)
				output+="("+xar.get(i)+", "+outNumbers.get(i)+"), ";
			else
				output+="("+xar.get(i)+", "+outNumbers.get(i)+")";
		}
		output+="<br><br>Scilab input:<br>x=[";
		for(int i = 0; i<outNumbers.size();i++) {
			if(i<outNumbers.size()-1) {
				output+=""+xar.get(i)+",";
			}else {
				output+=xar.get(i)+"];";
			}
		}
		output+="<br>y=[";
		for(int i = 0; i<outNumbers.size();i++) {
			if(i<outNumbers.size()-1) {
				output+=""+outNumbers.get(i)+",";
			}else {
				output+=outNumbers.get(i)+"];";
			}
		}

		output+="<br><br>Python Script:<br># import sympy  <br>" + 
				"from sympy import * <br>" + 
				"M = Matrix([";
		for(int i = 0;i<outNumbers.size();i++) {
			if(i<outNumbers.size()-1) {
				output+="[1,";
				for(int j=1;j<encryptionOrder;j++) {
					output+="("+xar.get(i)+")**"+j;
					if(j<encryptionOrder-1)
						output+=",";
					else
						output+=","+outNumbers.get(i)+"],";
				}
				
			}else {
				output+="[1,";
				for(int j=1;j<encryptionOrder;j++) {
					output+="("+xar.get(i)+")**"+j;
					if(j<encryptionOrder-1)
						output+=",";
					else
						output+=","+outNumbers.get(i)+"]])";
				}
			}
		}
		output+="<br>print(\"Matrix : {} \".format(M))<br>";
		output+="# Use sympy.rref() method  <br>" + 
				"M_rref = M.rref()<br>";
		output+="print(\"The Row echelon form of matrix M and the pivot columns : {}\".format(M_rref))<br>";
		output+="print(\"Your number of interest should be : {}\".format(M_rref[0]["+(encryptionOrder)+"]))";
	}
	
	private BigInteger getNumber(ArrayList<BigInteger> a_vals, Long x_val) {
		BigInteger y = new BigInteger("0");
		int p = 0;
		for(BigInteger a:a_vals) {
			y=y.add(a.multiply(BigInteger.valueOf((long) Math.pow((double)x_val,(double)p))));
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
