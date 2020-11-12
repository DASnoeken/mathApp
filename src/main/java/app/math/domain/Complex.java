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

	public Complex(String s) {
		if (!s.matches("\\-?\\d{0,}\\.?\\d{0,}[+-]?\\d{0,}\\.?\\d{0,}i") && !s.matches("\\-?\\d{1,}\\.?\\d{0,}")) {
			throw new IllegalArgumentException("NaN");
		}
		if (!s.contains("i")) {
			this.imag = 0.0;
			this.real = Double.parseDouble(s);
		} else if (!s.contains("+") && s.matches("\\-?\\d{0,}\\.?\\d{0,}i")) {
				this.real = 0.0;
				s = s.replace("i", "");
				this.imag = Double.parseDouble(s);
		}else if(!s.contains("+") && s.matches("\\-?\\d{0,}\\.?\\d{0,}")) {
				this.imag=0.0;
				this.real=Double.parseDouble(s);
		} else if (s.contains("+")) {
			String[] terms = s.split("[+]");
			terms[1] = terms[1].replace("i", "");
			this.real = Double.parseDouble(terms[0]);
			this.imag = Double.parseDouble(terms[1]);
		} else if (s.contains("-")) {
			String[] terms = s.split("[-]");
			terms[1] = terms[1].replace("i", "");
			this.real = Double.parseDouble(terms[0]);
			this.imag = -Double.parseDouble(terms[1]);
		} else {
			System.out.println("ERROR!");
		}
	}

	public void toPolar() {
		this.r = abs();
		this.theta = Math.atan(imag / real);
	}

	public double abs() {
		return Math.sqrt(real * real + imag * imag);
	}

	public void toCartesian() {
		this.real = this.r * Math.cos(this.theta);
		this.imag = this.r * Math.sin(this.theta);
	}

	public Complex conj() {
		return new Complex(this.real, -this.imag);
	}

	public Complex pow(int p) {
		Complex ans = new Complex(this.real, this.imag);
		for (int i = 0; i < p; i++) {
			ans = ans.multiply(ans);
		}
		return ans;
	}

	public Complex multiply(long l) {
		return new Complex(this.real * l, this.imag * l);
	}

	public Complex multiply(double d) {
		return new Complex(this.real * d, this.imag * d);
	}

	public Complex multiply(Complex other) {
		return new Complex(this.real * other.real - this.imag * other.imag,
				this.real * other.imag + this.imag * other.real);
	}

	public Complex add(Complex other) {
		return new Complex(this.real + other.real, this.imag + other.imag);
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

	public String toString() {
		if (this.real != 0) {
			if (this.imag < 0)
				return new String(this.real + " - " + Math.abs(this.imag) + "i");
			else if (this.imag > 0)
				return new String(this.real + " + " + this.imag + "i");
			else
				return new String("" + this.real);
		} else {
			if (this.imag != 0)
				return new String(this.imag + "i");
			else
				return "0";
		}
	}

	public String toPolarString() {
		toPolar();
		if (this.r != (int) this.r && this.theta != 0) {
			return "\\sqrt{" + (int) (this.real * this.real + this.imag * this.imag) + "}e^{" + this.theta / Math.PI
					+ "\\pi i}";
		} else if (this.r == (int) this.r && this.theta != 0) {
			return this.r + "e^{" + this.theta / Math.PI + "\\pi i}";
		} else {
			return "" + this.r;
		}
	}

	public void printNumber() {
		if (this.imag < 0)
			System.out.println(this.real + " - " + Math.abs(this.imag) + "i");
		else
			System.out.println(this.real + " + " + this.imag + "i");
	}
}
