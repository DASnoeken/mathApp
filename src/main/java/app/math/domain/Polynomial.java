package app.math.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Polynomial {
	private String polynomial;
	private Root root;
	private ArrayList<BigDecimal> xOfMinMax;
	private ArrayList<BigDecimal> coefficients;
	private ArrayList<BigInteger> powers;
	private Polynomial derivative;
	private ArrayList<BigDecimal> xgrid;
	private ArrayList<BigDecimal> ydata;
	
	public Polynomial(String input) {
		this.polynomial = input.trim().replaceAll("\\s", "");
		this.polynomial = this.polynomial.replace("-", "+-").replace("++", "+");
		this.xOfMinMax=new ArrayList<BigDecimal>();
		this.xgrid= new ArrayList<BigDecimal>();
		this.ydata = new ArrayList<BigDecimal>();
		this.powers = new ArrayList<BigInteger>();
		this.coefficients = new ArrayList<BigDecimal>();
		splitToTerms();
	}
	
	public void setDerivative() {
		Derivative der=new Derivative(this.polynomial);
		der.derive();
		this.derivative = new Polynomial(der.getResult());
		this.derivative.setPolynomial(Helper.integerPolynomial(der.getResult()));
	}
	public Polynomial getDerivative() {
		return this.derivative;
	}
	
	public ArrayList<BigDecimal> getMinMax(long xmin, long xmax){
		Derivative d = new Derivative(this.polynomial);
		d.derive();
		Polynomial der = new Polynomial(d.getResult());
		der.setPolynomial(der.getPolynomial().replaceAll("\\s", ""));
		der.setPolynomial(der.getPolynomial().replaceAll("\\+\\-", "-"));
		der.setPolynomial(Helper.integerPolynomial(der.getPolynomial()));
		Root derRoots = new Root(der.getPolynomial(),xmin,xmax);
		this.xOfMinMax = derRoots.getRoots();
		return xOfMinMax;
	}
	public Root getRoot() {
		return this.root;
	}
	public void setRoot(long xmin,long xmax) {
		this.root = new Root(this.polynomial,xmin,xmax);
	}
	public String getPolynomial() {
		return this.polynomial;
	}
	public void setPolynomial(String newPolynomial) {
		this.polynomial = newPolynomial;
		this.powers.clear();
		this.coefficients.clear();
		this.ydata.clear();
		this.xOfMinMax.clear();
		splitToTerms();
		setYdata();
	}

	public ArrayList<BigDecimal> getXgrid() {
		return xgrid;
	}

	public void setXgrid(BigDecimal xmin, BigDecimal xmax, BigDecimal delta) {
		this.xgrid.clear();
		this.xgrid.add(xmin);
		while(this.xgrid.get(xgrid.size()-1).compareTo(xmax) < 0) {
			this.xgrid.add(this.xgrid.get(xgrid.size()-1).add(delta));
		}
	}
	
	public void setYdata() {
		for (BigDecimal xval : xgrid) {
			BigDecimal y = new BigDecimal("0");
			for (int i = 0; i < this.powers.size(); i++) {
				y = y.add(this.coefficients.get(i).multiply(xval.pow(powers.get(i).intValue())));
			}
			ydata.add(y);
		}
	}

	public ArrayList<BigDecimal> getYdata() {
		return ydata;
	}
	
	public BigDecimal get(BigDecimal xvalue) {
		BigDecimal y = new BigDecimal("0");
		for (int i = 0; i < this.powers.size(); i++) {
			y = y.add(this.coefficients.get(i).multiply(xvalue.pow(powers.get(i).intValue())));
		}
		return y;
	}
	
	public void splitToTerms() {
		this.polynomial = this.polynomial.replaceAll("\\s", "");
		String[] terms = this.polynomial.split("[\\+]");
		for (int i = 0; i < terms.length; i++) {
			if (!terms[i].contains("x")) {
				terms[i] = terms[i] + "x^0";
			} else if (!terms[i].contains("^") && (terms[i].matches("\\d{1,}x") || terms[i].matches("\\d{1,}\\.\\d{1,}x")) ) {
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
				if(coefPow.get(i).contains(".")) {
					coefPow.set(i, coefPow.get(i).substring(0, coefPow.get(i).indexOf('.')));
				}
				this.powers.add(new BigInteger(coefPow.get(i)));
			}
		}
	}
}
