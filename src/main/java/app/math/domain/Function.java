package app.math.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Function {
	private String function;
	private Derivative derivative;
	private ArrayList<String> terms;
	private ArrayList<String> polynomialTerms;
	private ArrayList<String> otherTerms;
	private ArrayList<BigDecimal> xgrid;
	private ArrayList<BigDecimal> y;
	private String trigState;		//"radians" or "degrees"

	public Function(String input, String trigS) {
		this.function = input;
		this.trigState = trigS;
		this.derivative = new Derivative(this.function);
		this.derivative.derive();
		this.terms = this.derivative.getTerms();
		this.polynomialTerms = this.derivative.getPolynomialTerms();
		this.otherTerms = new ArrayList<String>();
		for (String t : terms) {
			if (!this.polynomialTerms.contains(t)) {
				this.otherTerms.add(t);
			}
		}
		this.xgrid = new ArrayList<BigDecimal>();
		this.y = new ArrayList<BigDecimal>();
	}

	public void setFunction(String function, String trigS) {
		this.terms.clear();
		this.trigState = trigS;
		this.function = function;
		this.derivative = new Derivative(this.function);
		this.derivative.derive();
		this.terms = this.derivative.getTerms();
		this.otherTerms.clear();
		for (String t : terms) {
			if (!this.polynomialTerms.contains(t)) {
				this.otherTerms.add(t);
			}
		}
		this.y.clear();
		this.xgrid.clear();
		this.polynomialTerms = this.derivative.getPolynomialTerms();
	}

	public BigDecimal integrate(BigDecimal a, BigDecimal b) {
		BigDecimal ans = new BigDecimal("0");
		for (String s : polynomialTerms) {
			Polynomial p = new Polynomial(s);
			ans = ans.add(p.integrate(a, b));
		}

		return ans;
	}
	
	

	public ArrayList<BigDecimal> getY() {
		return y;
	}

	public void setY() {
		
	}

	public ArrayList<BigDecimal> getXgrid() {
		return xgrid;
	}

	public void setXgrid(BigDecimal xmin, BigDecimal xmax, BigDecimal delta) {
		this.xgrid.add(xmin);
		while (this.xgrid.get(this.xgrid.size() - 1).compareTo(xmax) < 0) {
			this.xgrid.add(this.xgrid.get(this.xgrid.size() - 1).add(delta));
		}
	}

	public String getFunction() {
		return function;
	}

	public Derivative getDerivative() {
		return derivative;
	}

	public ArrayList<String> getTerms() {
		return terms;
	}

	public ArrayList<String> getPolynomialTerms() {
		return polynomialTerms;
	}

	public ArrayList<String> getOtherTerms() {
		return otherTerms;
	}

	public String getTrigState() {
		return trigState;
	}

	public void setTrigState(String trigState) {
		if(!trigState.equals(this.trigState)) {
			this.trigState = trigState;
			setFunction(this.function, trigState);
		}
	}

	
}
