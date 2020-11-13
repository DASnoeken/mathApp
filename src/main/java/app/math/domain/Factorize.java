package app.math.domain;

import java.math.BigInteger;
import java.util.ArrayList;

public class Factorize {
	public Factorize(BigInteger bi) {
		this.number = bi;
		this.prime = true;
	}

	public void factorizeNumber() {
		this.factors = new ArrayList<BigInteger>();
		this.powers = new ArrayList<BigInteger>();
		String numstring = this.number.toString();
		BigInteger localNumber = new BigInteger(numstring);
		String octalNum = toOctal(localNumber);
		long digSum7 = digitSum(octalNum); // The digit sum thing works for 7 in base 8
		if (digSum7 % 7 == 0) {
			powers.add(BigInteger.ONE);
			factors.add(seven);
			localNumber = localNumber.divide(seven);
			digSum7 = digitSum(toOctal(localNumber));
		}
		while (digSum7 % 7 == 0) {
			localNumber = localNumber.divide(seven);
			powers.set(powers.size() - 1, powers.get(powers.size() - 1).add(BigInteger.ONE));
			digSum7 = digitSum(toOctal(localNumber));
		}

		long digSum = digitSum(localNumber.toString());
		if (digSum % 3 == 0) {
			powers.add(BigInteger.ONE);
			factors.add(three);
			localNumber = localNumber.divide(three);
			digSum = digitSum(localNumber.toString());
		}
		while (digSum % 3 == 0) {
			localNumber = localNumber.divide(three);
			powers.set(powers.size() - 1, powers.get(powers.size() - 1).add(BigInteger.ONE));
			digSum = digitSum(localNumber.toString());
		}
		BigInteger[] resultAndRemainder;
		resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
		int lastDigit = Math.abs(resultAndRemainder[1].intValue());
		if (lastDigit % 2 == 0) {
			powers.add(BigInteger.ONE);
			factors.add(two);
			localNumber = localNumber.divide(two);
			resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
			lastDigit = Math.abs(resultAndRemainder[1].intValue());
		}
		while (lastDigit % 2 == 0) {
			powers.set(powers.size() - 1, powers.get(powers.size() - 1).add(BigInteger.ONE));
			localNumber = localNumber.divide(two);
			resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
			lastDigit = Math.abs(resultAndRemainder[1].intValue());
		}
		if (lastDigit == 5 || lastDigit == 0) {
			powers.add(BigInteger.ONE);
			factors.add(five);
			localNumber = localNumber.divide(five);
			resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
			lastDigit = Math.abs(resultAndRemainder[1].intValue());
		}
		while (lastDigit == 5 || lastDigit == 0) {
			powers.set(powers.size() - 1, powers.get(powers.size() - 1).add(BigInteger.ONE));
			localNumber = localNumber.divide(five);
			resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
			lastDigit = Math.abs(resultAndRemainder[1].intValue());
		}
		BigInteger limit = sqrt(localNumber);
		BigInteger[] remainder;
		// Working loop
		int count5 = 1;
		int count3 = 1;
		int[] Pattern3 = { 2, 5, 2, 3 };
		int count3index = 0;
		int count3Pattern = Pattern3[count3index];
		for (BigInteger i = seven; i.compareTo(limit) <= 0; i = i.add(two)) { // leave start at 7
			if (count5 == 5) { // Fastest way to skip i == multiple of 5
				count5 = 1;
				continue;
			} else if (count3 == count3Pattern) { // Fastest way to skip i == multiple of 3
				count3 = 1;
				count5++;
				if (count3index < Pattern3.length - 1)
					count3index++;
				else
					count3index = 0;
				count3Pattern = Pattern3[count3index];
				continue;
			} // Unfortunately I cannot repeat this trick for factors of 7, because this would
				// require me to find *THE* pattern in prime numbers.

			remainder = localNumber.divideAndRemainder(i);
			if (remainder[1].equals(BigInteger.ZERO)) {
				factors.add(i);
				powers.add(BigInteger.ONE);
				localNumber = remainder[0];
				remainder = localNumber.divideAndRemainder(i);
				while (remainder[1].equals(BigInteger.ZERO)) {
					powers.set(powers.size() - 1, powers.get(powers.size() - 1).add(BigInteger.ONE));
					localNumber = remainder[0];
					remainder = localNumber.divideAndRemainder(i);
				}
			}
			count5++;
			count3++;
			if(localNumber.equals(BigInteger.ONE)) {
				break;
			}
		}

		while (!localNumber.equals(BigInteger.ONE)) {
			localNumber = new BigInteger(this.number.toString());
			BigInteger totalFactor = BigInteger.ONE;
			BigInteger localFactor;
			int powindex = 0;
			for (BigInteger factor : factors) {
				localFactor = (factor).pow(powers.get(powindex).intValue());
				totalFactor = totalFactor.multiply(localFactor);
				powindex++;
			}
			localNumber = localNumber.divide(totalFactor);
			factors.add(localNumber);
			powers.add(BigInteger.ONE);
		}
		if (!factors.contains(BigInteger.ONE)) {
			factors.add(BigInteger.ONE);
			powers.add(BigInteger.ONE);
		}
		if (!factors.contains(this.number)) {
			factors.add(this.number);
			powers.add(BigInteger.ONE);
		}
		organize();
	}

	public ArrayList<BigInteger> getFactors() {
		return factors;
	}

	public BigInteger getNumber() {
		return number;
	}

	public ArrayList<BigInteger> getPowers() {
		return powers;
	}

	private BigInteger sqrt(BigInteger x) { // https://stackoverflow.com/questions/4407839/how-can-i-find-the-square-root-of-a-java-biginteger/
											// Thanks to: Edward Falk. I needed this because Heroku needs to update its
											// Java version.
		BigInteger div = BigInteger.ZERO.setBit(x.bitLength() / 2);
		BigInteger div2 = div;
		// Loop until we hit the same value twice in a row, or wind
		// up alternating.
		for (;;) {
			BigInteger y = div.add(x.divide(div)).shiftRight(1);
			if (y.equals(div) || y.equals(div2))
				return y;
			div2 = div;
			div = y;
		}
	}

	private long digitSum(String s) {
		long ans = 0;
		for (int i = 0; i < s.length(); i++) {
			ans += Long.parseLong(Character.toString(s.charAt(i)));
		}
		return ans;
	}

	private String toOctal(BigInteger b) {
		return b.toString(8);
	}

	private void organize() {		//Just to help the answer look nicer.
		ArrayList<BigInteger> tmp = new ArrayList<BigInteger>();
		ArrayList<BigInteger> tmp2 = new ArrayList<BigInteger>();
		int index2 = this.factors.indexOf(two);
		int index3 = this.factors.indexOf(three);
		int index5 = this.factors.indexOf(five);
		int index7 = this.factors.indexOf(seven);
		if (index2 != -1) {
			tmp.add(this.factors.get(index2));
			tmp2.add(this.powers.get(index2));
		}
		if (index3 != -1) {
			tmp.add(this.factors.get(index3));
			tmp2.add(this.powers.get(index3));
		}
		if (index5 != -1) {
			tmp.add(this.factors.get(index5));
			tmp2.add(this.powers.get(index5));
		}
		if (index7 != -1) {
			tmp.add(this.factors.get(index7));
			tmp2.add(this.powers.get(index7));
		}
		for (int i = 0; i < this.factors.size(); i++) {
			if (this.factors.get(i).equals(two) || this.factors.get(i).equals(three) || this.factors.get(i).equals(five)
					|| this.factors.get(i).equals(seven)) {
				continue;
			}
			tmp.add(this.factors.get(i));
			tmp2.add(this.powers.get(i));
		}
		this.factors = tmp;
		this.powers = tmp2;
	}
	
	public void checkPrime() {
		String numstring = this.number.toString();
		BigInteger localNumber = new BigInteger(numstring);
		String octalNum = toOctal(localNumber);
		long digSum7 = digitSum(octalNum); // The digit sum thing works for 7 in base 8
		if (digSum7 % 7 == 0 && !localNumber.equals(seven)) {
			this.prime=false;
			return;
		}
		long digSum = digitSum(localNumber.toString());
		if (digSum % 3 == 0 && !localNumber.equals(three)) {
			this.prime=false;
			return;
		}
		BigInteger[] resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
		int lastDigit = Math.abs(resultAndRemainder[1].intValue());
		if (lastDigit % 2 == 0 && !localNumber.equals(two)) {
			this.prime=false;
			return;
		}
		if ((lastDigit == 5 || lastDigit == 0) && !localNumber.equals(five)) {
			this.prime=false;
			return;
		}
		BigInteger limit = sqrt(localNumber);
		BigInteger[] remainder;
		
		int count5 = 1;
		int count3 = 1;
		int[] Pattern3 = { 2, 5, 2, 3 };
		int count3index = 0;
		int count3Pattern = Pattern3[count3index];
		for (BigInteger i = seven; i.compareTo(limit) <= 0; i = i.add(two)) { // leave start at 7
			if (count5 == 5) { // Fastest way to skip i == multiple of 5
				count5 = 1;
				continue;
			} else if (count3 == count3Pattern) { // Fastest way to skip i == multiple of 3
				count3 = 1;
				count5++;
				if (count3index < Pattern3.length - 1)
					count3index++;
				else
					count3index = 0;
				count3Pattern = Pattern3[count3index];
				continue;
			} // Unfortunately I cannot repeat this trick for factors of 7, because this would
				// require me to find *THE* pattern in prime numbers.

			remainder = localNumber.divideAndRemainder(i);
			if (remainder[1].equals(BigInteger.ZERO)) {
				this.prime=false;
				return;
			}
			count5++;
			count3++;
		}
	}

	public boolean isPrime() {
		return prime;
	}

	private ArrayList<BigInteger> powers;
	private ArrayList<BigInteger> factors;
	private final BigInteger two = new BigInteger("2");
	private final BigInteger three = new BigInteger("3");
	private final BigInteger five = new BigInteger("5");
	private final BigInteger seven = new BigInteger("7");
	private boolean prime;
	private BigInteger number;
}
