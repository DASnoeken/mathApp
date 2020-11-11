package app.math.domain;

import java.math.BigDecimal;
import java.math.MathContext;

public class Complex {
	private BigDecimal real;
	private BigDecimal imag;
	private BigDecimal r;
	private BigDecimal theta;
	
	public Complex(BigDecimal re, BigDecimal im) {
		this.real = re;
		this.imag = im;
	}
	public void toPolar() {
		this.r=abs();
		this.theta = new BigDecimal(Math.atan(imag.divide(real).doubleValue()));
	}
	public BigDecimal abs() {
		BigDecimal ans = new BigDecimal("0");
		ans=ans.add(( real.multiply(real) ).add( imag.multiply(imag) ));
		ans=ans.sqrt(new MathContext(50));
		return ans;
	}
	public void toCartesian() {
		
	}
	public BigDecimal getReal() {
		return real;
	}

	public void setReal(BigDecimal real) {
		this.real = real;
	}

	public BigDecimal getImag() {
		return imag;
	}

	public void setImag(BigDecimal imag) {
		this.imag = imag;
	}

	public BigDecimal getR() {
		return r;
	}

	public void setR(BigDecimal r) {
		this.r = r;
	}

	public BigDecimal getTheta() {
		return theta;
	}

	public void setTheta(BigDecimal theta) {
		this.theta = theta;
	}
	
}
