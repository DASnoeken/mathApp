package app.math.domain;

import java.util.ArrayList;

public class Function {
	private String function;
	private Derivative derivative;
	private ArrayList<String> terms;
	
	public Function(String input) {
		this.function = input;
		this.derivative = new Derivative(this.function);
		this.derivative.derive();
		this.terms = this.derivative.getTerms();
	}

	public void setFunction(String function) {
		this.terms.clear();
		this.function = function;
		this.derivative = new Derivative(this.function);
		this.derivative.derive();
		this.terms = this.derivative.getTerms();
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
	
	
}
