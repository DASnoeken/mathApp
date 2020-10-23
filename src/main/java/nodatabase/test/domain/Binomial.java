package nodatabase.test.domain;

public class Binomial {
	public Binomial(int n) {
		this.power = n;
		this.solution = new String("$");
	}

	public void solveEquation() {
		this.solution += "(x + y)^" + this.power + " = ";
		int powerx = 0;
		int powery = this.power;
		for (int k = 0; k <= this.power; k++) {
			powerx = this.power - k;
			powery = k;
			double nk = noverk(this.power,k);
			if (powerx != 0 && powery != 0 && powerx != 1 && powery != 1) {
				if(nk!=1.0) {
					this.solution += nk + "x^" + powerx + "y^" + powery + " + ";
				}else {
					this.solution += "x^" + powerx + "y^" + powery + " + ";
				}
			} else if (powery == 0) {
				if(nk!=1.0) {
					this.solution += nk + "x^" + powerx + " + ";
				}else {
					this.solution += "x^" + powerx + " + ";
				}
			} else if(powerx == 0) {
				if(nk!=1.0) {
					this.solution += nk + "y^" + powery;
				}else {
					this.solution += "y^" + powery;
				}
			} else if(powery == 1 && powerx == 1) {
				if(nk!=1.0) {
					this.solution += nk + "x" + "y" + " + ";
				}else {
					this.solution += "x" + "y" + " + ";
				}
			} else if(powery == 1) {
				if(nk!=1.0) {
					this.solution += nk + "x^" + powerx + "y" + " + ";
				}else {
					this.solution += "x^" + powerx + "y" + " + ";
				}
			}else if(powerx == 1) {
				if(nk!=1.0) {
					this.solution += nk + "x" + "y^" + powery + " + ";
				}else {
					this.solution += "x" + "y^" + powery + " + ";
				}
			}
		}
		this.solution += "$";
	}

	public double noverk(int n, int k) {
		if (k < 0) {
			return 0.0;
		} else if (k > n) {
			return 0.0;
		}
		double ans = factorial(n) / (factorial(k) * factorial(n - k));
		return ans;
	}

	private long factorial(int n) {
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
