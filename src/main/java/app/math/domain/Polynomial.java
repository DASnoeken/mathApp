package app.math.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Polynomial {
	private String polynomial;
	private Root root;
	private ArrayList<BigDecimal> xOfMinMax;
	
	public Polynomial(String input) {
		this.polynomial = input;
		xOfMinMax=new ArrayList<BigDecimal>();
	}
	public ArrayList<BigDecimal> getMinMax(long xmin, long xmax){
		Derivative d = new Derivative(this.polynomial);
		d.derive();
		Polynomial der = new Polynomial(d.getResult());
		der.setPolynomial(der.getPolynomial().replaceAll("\\s", ""));
		der.setPolynomial(der.getPolynomial().replaceAll("\\+\\-", "-"));
		System.out.println(der.getPolynomial());
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
	}
}
