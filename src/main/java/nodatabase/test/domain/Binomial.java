package nodatabase.test.domain;

import java.math.BigInteger;

public class Binomial {
	public Binomial(int n) {
		this.power = n;
		this.solution = new String("$");
	}

	public void solveEquation() {
		this.solution += "(x + y)^{" + this.power + "} = ";
		int powerx = 0;
		int powery = this.power;
		for (int k = 0; k <= this.power; k++) {
			powerx = this.power - k;
			powery = k;
			BigInteger nk = noverk(this.power, k);
			if (powerx != 0 && powery != 0 && powerx != 1 && powery != 1) {
				if (!nk.equals(BigInteger.ONE)) {
					this.solution += nk + "x^" + "{" + powerx + "}" + "y^" + "{" + powery + "}" + " + ";
				} else {
					this.solution += "x^" + "{" + powerx + "}" + "y^" + "{" + powery + "}" + " + ";
				}
			} else if (powery == 0) {
				if (!nk.equals(BigInteger.ONE)) {
					this.solution += nk + "x^" + "{" + powerx + "}" + " + ";
				} else {
					this.solution += "x^" + "{" + powerx + "}" + " + ";
				}
			} else if (powerx == 0) {
				if (!nk.equals(BigInteger.ONE)) {
					this.solution += nk + "y^" + "{" + powery + "}";
				} else {
					this.solution += "y^" + "{" + powery + "}";
				}
			} else if (powery == 1 && powerx == 1) {
				if (!nk.equals(BigInteger.ONE)) {
					this.solution += nk + "x" + "y" + " + ";
				} else {
					this.solution += "x" + "y" + " + ";
				}
			} else if (powery == 1) {
				if (!nk.equals(BigInteger.ONE)) {
					this.solution += nk + "x^" + "{" + powerx + "}" + "y" + " + ";
				} else {
					this.solution += "x^" + "{" + powerx + "}" + "y" + " + ";
				}
			} else if (powerx == 1) {
				if (!nk.equals(BigInteger.ONE)) {
					this.solution += nk + "x" + "y^" + "{" + powery + "}" + " + ";
				} else {
					this.solution += "x" + "y^" + "{" + powery + "}" + " + ";
				}
			}
		}
		this.solution += "$";
	}

	public BigInteger noverk(long n, long k) {
		if (k < 0) {
			return BigInteger.ZERO;
		} else if (k > n) {
			return BigInteger.ZERO;
		}
		BigInteger ans = factorial(n).divide((factorial(k).multiply(factorial(n - k))));
		return ans;
	}

	private BigInteger factorial(long n) {
		BigInteger ans = BigInteger.ONE;
		if (n == 0) {
			return ans;
		} else if (n < 0) {
			throw new IllegalArgumentException("Factorial only allowed for positive integers!");
		}
		BigInteger initNum = BigInteger.valueOf(n);
		for (BigInteger i = initNum; i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(BigInteger.ONE)) {
			ans = ans.multiply(i);
		}
		return ans;
	}
	
	//small numbers
	public void solveEquationSmall() {
		this.solution += "(x + y)^{" + this.power + "} = ";
		int powerx = 0;
		int powery = this.power;
		for (int k = 0; k <= this.power; k++) {
			powerx = this.power - k;
			powery = k;
			long nk = noverk2(this.power,k);
			if (powerx != 0 && powery != 0 && powerx != 1 && powery != 1) {
				if(nk!=1.0) {
					this.solution += nk + "x^" + "{" + powerx + "}" + "y^" + "{" + powery + "}" + " + ";
				}else {
					this.solution += "x^" + "{" + powerx + "}" + "y^" + "{" + powery + "}" + " + ";
				}
			} else if (powery == 0) {
				if(nk!=1.0) {
					this.solution += nk + "x^" + "{" + powerx + "}" + " + ";
				}else {
					this.solution += "x^" + "{" + powerx + "}" + " + ";
				}
			} else if(powerx == 0) {
				if(nk!=1.0) {
					this.solution += nk + "y^" + "{" + powery + "}";
				}else {
					this.solution += "y^" + "{" + powery + "}";
				}
			} else if(powery == 1 && powerx == 1) {
				if(nk!=1.0) {
					this.solution += nk + "x" + "y" + " + ";
				}else {
					this.solution += "x" + "y" + " + ";
				}
			} else if(powery == 1) {
				if(nk!=1.0) {
					this.solution += nk + "x^" + "{" + powerx + "}" + "y" + " + ";
				}else {
					this.solution += "x^" + "{" + powerx + "}" + "y" + " + ";
				}
			}else if(powerx == 1) {
				if(nk!=1.0) {
					this.solution += nk + "x" + "y^" + "{" + powery + "}" + " + ";
				}else {
					this.solution += "x" + "y^" + "{" + powery + "}" + " + ";
				}
			}
		}
		this.solution += "$";
	}

	public long noverk2(int n, int k) {
		if (k < 0) {
			return 0;
		} else if (k > n) {
			return 0;
		}
		long ans = factorial2(n) / (factorial2(k) * factorial2(n - k));
		return ans;
	}

	private long factorial2(int n) {
		long ans = 1l;
		if (n == 0) {
			return ans;
		} else if (n < 0) {
			throw new IllegalArgumentException("Factorial only allowed for positive integers!");
		}
		for (int i = n; i > 0; i--) {
			ans = i * ans;
		}
		return ans;
	}


	public int getPower() {
		return power;
	}

	public String getSolution() {
		return solution;
	}

	private int power;
	private String solution;
}
