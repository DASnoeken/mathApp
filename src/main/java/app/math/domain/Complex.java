package app.math.domain;

public class Complex {
	private double real;
	private double imag;
	private double r;
	private double theta;
	
	public Complex(double re, double im) {
		this.real = re;
		this.imag = im;
	}
	public void toPolar() {
		this.r=abs();
		this.theta = Math.atan(imag/real);
	}
	public double abs() {
		return Math.sqrt(real*real+imag*imag);
	}
	public void toCartesian() {
		this.real=this.r*Math.cos(this.theta);
		this.imag=this.r*Math.sin(this.theta);
	}
	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImag() {
		return imag;
	}

	public void setImag(double imag) {
		this.imag = imag;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}
	
}
