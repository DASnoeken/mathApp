package app.math.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Root {
	private String polynomial;
	private ArrayList<BigDecimal> coefficients;
	private ArrayList<BigInteger> powers;
	private ArrayList<BigDecimal> xgrid;
	private ArrayList<BigDecimal> yval;
	private BigDecimal x_min, x_max, x_maxOrig;
	private BigDecimal delta;
	private ArrayList<BigDecimal> roots;
	private static final BigDecimal thresh = new BigDecimal("1E-15");
	private int totalSignFlips;

	public Root(String inPol, long x_min, long x_max) {
		this.polynomial = inPol;
		if (this.polynomial.charAt(0) == '-') {
			this.polynomial = "0" + this.polynomial;
		}
		this.polynomial = this.polynomial.replace("-", "+-");
		this.powers = new ArrayList<BigInteger>();
		this.coefficients = new ArrayList<BigDecimal>();
		this.xgrid = new ArrayList<BigDecimal>();
		this.yval = new ArrayList<BigDecimal>();
		this.x_min = BigDecimal.valueOf(x_min);
		this.x_max = BigDecimal.valueOf(x_max);
		this.x_maxOrig = this.x_max;
		this.delta = BigDecimal.valueOf(0.5);
		this.roots = new ArrayList<BigDecimal>();
		splitToTerms();
		checkInterval();
		setGrid();
	}

	private void checkInterval() { // counts total number of sign flips in interval
		int sign = this.coefficients.get(0).multiply(x_min.pow(powers.get(0).intValue())).signum();
		int signflips = 0;
		double xmax = x_max.doubleValue();
		for (double bi = x_min.doubleValue(); bi < xmax; bi += 0.01) {
			double y = 0.0;
			for (int i = 0; i < this.powers.size(); i++) {
				y += this.coefficients.get(i).doubleValue() * Math.pow(bi, powers.get(i).doubleValue());
			}
			int sign2 = (int) Math.signum(y);
			if (sign != sign2) {
				sign = sign2;
				signflips++;
			}
		}
		this.totalSignFlips = signflips;
		// System.out.println("totalsf = "+totalSignFlips);
	}

	private void addToRoots(int i) {
		if (xgrid.get(i).abs().compareTo(thresh) <= 0) {
			xgrid.set(i, BigDecimal.ZERO);
		}
		roots.add(xgrid.get(i));
		if (roots.size() == this.totalSignFlips) {
			return;
		} else {
			this.x_min = roots.get(roots.size() - 1).add(new BigDecimal("0.1"));
			this.delta = new BigDecimal("0.5");
			this.x_max = this.x_maxOrig;
			this.xgrid.clear();
			this.yval.clear();
			setGrid();
		}
	}

	private void findSignFlips() {
		if (roots.size() == this.totalSignFlips) {
			return;
		}
		int sign = yval.get(0).signum();
		if (sign == 0) {
			addToRoots(0);
		}
		for (int i = 1; i < xgrid.size(); i++) {
			int sign2 = yval.get(i).signum();
			if (yval.get(i).abs().compareTo(thresh) < 0) {
				addToRoots(i);
			} else {
				if (sign2 == 0) {
					addToRoots(i);
				} else if (sign2 != sign) {
					this.delta = this.delta.divide(new BigDecimal("2"));
					this.x_min = xgrid.get(i - 1);
					this.x_max = xgrid.get(i);
					this.xgrid.clear();
					this.yval.clear();
					setGrid();
				}
			}
		}
	}

	private void calculateYvals() {
		if (roots.size() == this.totalSignFlips) {
			return;
		}
		for (BigDecimal xval : xgrid) {
			BigDecimal y = new BigDecimal("0");
			for (int i = 0; i < this.powers.size(); i++) {
				y = y.add(this.coefficients.get(i).multiply(xval.pow(powers.get(i).intValue())));
			}
			yval.add(y);
		}
		/*
		 * for(int i = 0;i<yval.size();i++) {
		 * System.out.println(xgrid.get(i)+", "+yval.get(i)); }
		 */
		findSignFlips();
	}

	private void setGrid() {
		if (roots.size() == this.totalSignFlips) {
			return;
		}
		xgrid.add(x_min);
		BigDecimal element = this.x_min;
		do {
			element = element.add(delta);
			xgrid.add(element);
		} while (element.compareTo(x_max) <= 0);
		calculateYvals();
	}

	private void splitToTerms() {
		String[] terms = this.polynomial.split("[\\+]");
		for (int i = 0; i < terms.length; i++) {
			if (!terms[i].contains("x")) {
				terms[i] = terms[i] + "x^0";
			} else if (!terms[i].contains("^") && terms[i].matches("\\d{1,}x")) {
				terms[i] = terms[i] + "^1";
			} else if (!terms[i].contains("^") && terms[i].matches("\\d{0}x")) {
				terms[i] = "1" + terms[i] + "^1";
			} else if (terms[i].charAt(0) != '-' && terms[i].contains("^") && !terms[i].matches("\\d{1,}x\\^\\d{1,}")) {
				terms[i] = "1" + terms[i];
			}
		}

		ArrayList<String> coefPow = new ArrayList<String>();
		for (String term : terms) {
			String sign = "";
			if (term.matches("-\\d{1,}x")) {
				sign = "-";
				term = sign + term.substring(1) + "^1";
			} else if (term.matches("-x")) {
				sign = "-1";
				term = sign + term.substring(1) + "^1";
			} else if (term.matches("-\\d{1,}x\\^\\d{1,}")) {
				sign = "-";
				term = sign + term.substring(1);
			} else if (term.matches("-x\\^\\d{1,}")) {
				sign = "-1";
				term = sign + term.substring(1);
			}
			coefPow.add(term.split("x\\^?")[0]);
			coefPow.add(term.split("x\\^?")[1]);
		}
		for (int i = 0; i < coefPow.size(); i++) {
			if (i % 2 == 0) {
				this.coefficients.add(new BigDecimal(coefPow.get(i)));
			} else {
				this.powers.add(new BigInteger(coefPow.get(i)));
			}
		}
	}

	public void roundRoots() {
		for (int i = 0; i < this.roots.size(); i++) {
			BigDecimal root = this.roots.get(i);
			String rootString = root.toString();
			for (int j = rootString.indexOf('.'); j < rootString.length(); j++) {
				if (j == -1) {
					break;
				}
				int count = 0;
				if (j > 0 && j<rootString.length() && (rootString.charAt(j-1) == '.'
						&& (rootString.charAt(j) == '9' || rootString.charAt(j) == '0'))) {
					while (j < rootString.length() && rootString.charAt(j) == '9') {
						j++;
						count++;
						if (count == 5) {
							rootString = rootString.substring(0, rootString.indexOf('.'));
							BigInteger intpart = new BigInteger(rootString).add(BigInteger.ONE);
							this.roots.set(i, new BigDecimal(intpart.toString()));
						}
					}
					while (j < rootString.length() && rootString.charAt(j) == '0') {
						j++;
						count++;
						if (count == 5) {
							rootString = rootString.substring(0, j - count);
							this.roots.set(i, new BigDecimal(rootString));
						}
					}
				}
			}
		}
	}

	public String getPolynomial() {
		return polynomial;
	}

	public ArrayList<BigDecimal> getRoots() {
		return roots;
	}

}
