package nodatabase.test.domain;

import java.math.BigInteger;
import java.util.ArrayList;

public class Factorize {
	public Factorize(BigInteger bi) {
		this.number = bi;
		this.factors = new ArrayList<BigInteger>();
		this.powers = new ArrayList<BigInteger>();
	}

	public void factorizeNumber() {
		String numstring = this.number.toString();
		BigInteger localNumber = new BigInteger(numstring);
		BigInteger two = new BigInteger("2");
		BigInteger three = new BigInteger("3");
		BigInteger five = new BigInteger("5");
		long digSum = digitSum(numstring);
		if (digSum % 3 == 0) {
			powers.add(BigInteger.ONE);
			factors.add(three);
			localNumber = localNumber.divide(three);
			digSum = digitSum(localNumber.toString());
		}
		while (digSum % 3 == 0) {
			localNumber = localNumber.divide(three);
			powers.set(0, powers.get(0).add(BigInteger.ONE));
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
			powers.set(0, powers.get(0).add(BigInteger.ONE));
			localNumber = localNumber.divide(two);
			resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
			lastDigit = Math.abs(resultAndRemainder[1].intValue());
		}
		if (lastDigit % 5 == 0) {
			powers.add(BigInteger.ONE);
			factors.add(five);
			localNumber = localNumber.divide(five);
			resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
			lastDigit = Math.abs(resultAndRemainder[1].intValue());
		}
		while (lastDigit % 5 == 0) {
			powers.set(0, powers.get(0).add(BigInteger.ONE));
			localNumber = localNumber.divide(five);
			resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
			lastDigit = Math.abs(resultAndRemainder[1].intValue());
		}
		BigInteger limit = sqrt(localNumber);
		BigInteger[] remainder;

		// Working loop
		int count5 = 1;
		int count3 = 1;
		int count3Pattern = 1;
		for (BigInteger i = new BigInteger("7"); i.compareTo(limit) < 0; i = i.add(two)) {
			if (count5 == 5) { // Fastest way to skip i == multiple of 5
				count5 = 1;
				continue;
			} else if (check3Pattern(count3, count3Pattern)) {	//Fastest way to skip i == multiple of 3
				count3 = 1;
				count3Pattern = 1;
				continue;
			}
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
			count3Pattern++;
		}

		while (!localNumber.equals(BigInteger.ONE)) {
			localNumber = new BigInteger(this.number.toString());
			BigInteger totalFactor = BigInteger.ONE;
			int powindex = 0;
			for (BigInteger factor : factors) {
				totalFactor = totalFactor.multiply(factor).pow(powers.get(powindex).intValue());
				powindex++;
			}
			localNumber = localNumber.divide(totalFactor);
			factors.add(localNumber);
			powers.add(BigInteger.ONE);
		}
		if (!factors.contains(BigInteger.ONE)) {
			factors.add(BigInteger.ONE);
		}
		if (!factors.contains(this.number)) {
			factors.add(this.number);
		}
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

	private BigInteger sqrt(BigInteger x) {
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

	private boolean check3Pattern(int counter, int patternIndex) {
		if (counter == 5 && patternIndex == 1) {
			return true;
		} else if (counter == 2 && (patternIndex == 2 || patternIndex == 4)) {
			return true;
		} else if (counter == 3 && patternIndex == 3) {
			return true;
		} else {
			return false;
		}
	}

	private ArrayList<BigInteger> powers;
	private ArrayList<BigInteger> factors;
	private BigInteger number;
}
