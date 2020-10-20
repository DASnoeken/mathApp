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
		BigInteger localNumber = new BigInteger(this.number.toString());
		// BigInteger t = limit.divideAndRemainder(new BigInteger("3"))[1];
		BigInteger[] resultAndRemainder;
		resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
		int lastDigit = Math.abs(resultAndRemainder[1].intValue());
		if(lastDigit%2 ==0) {
			powers.add(BigInteger.ONE);
			factors.add(BigInteger.TWO);
			localNumber = localNumber.divide(BigInteger.TWO);
			resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
			lastDigit = Math.abs(resultAndRemainder[1].intValue());
		}
		while (lastDigit % 2 == 0) {
			powers.set(0,powers.get(0).add(BigInteger.ONE));
			localNumber = localNumber.divide(BigInteger.TWO);
			resultAndRemainder = localNumber.divideAndRemainder(BigInteger.TEN);
			lastDigit = Math.abs(resultAndRemainder[1].intValue());
		}
		BigInteger limit = localNumber.sqrt();
		BigInteger[] remainder;
		for (BigInteger i = new BigInteger("3"); i.compareTo(limit) < 0; i = i.add(BigInteger.TWO)) {
			remainder = localNumber.divideAndRemainder(i);
			if (remainder[1].equals(BigInteger.ZERO)) {
				factors.add(i);
				powers.add(BigInteger.ONE);
				localNumber = remainder[0];
				remainder = localNumber.divideAndRemainder(i);
				while(remainder[1].equals(BigInteger.ZERO)) {
					powers.set(powers.size()-1, powers.get(powers.size()-1).add(BigInteger.ONE));
					localNumber = remainder[0];
					remainder = localNumber.divideAndRemainder(i);
				}
			}
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
		if(!factors.contains(BigInteger.ONE)) {
			factors.add(BigInteger.ONE);
		}
		if(!factors.contains(this.number)) {
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

	private ArrayList<BigInteger> powers;
	private ArrayList<BigInteger> factors;
	private BigInteger number;
}
