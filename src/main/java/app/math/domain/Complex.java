package app.math.domain;

public class Complex {
	private double real;
	private double imag;
	private double r;
	private double theta;
	private static final double thresh = 1e-10;

	public Complex(double re, double im) {
		this.real = re;
		this.imag = im;
		if(Math.abs(this.real)<thresh) {
			this.real=0;
		}
		if(Math.abs(this.imag)<thresh) {
			this.imag=0;
		}
	}
	
	public Complex() {
		this.real=0.0;
		this.imag=0.0;
	}

	public Complex(String s) {
		if (!s.matches("\\-?\\d{0,}\\.?\\d{0,}[+-]?\\d{0,}\\.?\\d{0,}i") && !s.matches("\\-?\\d{1,}\\.?\\d{0,}")) {
			throw new IllegalArgumentException("NaN");
		}
		if (s.matches("\\d{0,}\\.?\\d{0,}[+-]i")) {
			s = s.replace("i", "1i");
		}
		if (!s.contains("i")) {
			this.imag = 0.0;
			this.real = Double.parseDouble(s);
		} else if (!s.contains("+") && s.matches("\\-?\\d{0,}\\.?\\d{0,}i")) {
			this.real = 0.0;
			s = s.replace("i", "");
			this.imag = Double.parseDouble(s);
		} else if (!s.contains("+") && s.matches("\\-?\\d{0,}\\.?\\d{0,}")) {
			this.imag = 0.0;
			this.real = Double.parseDouble(s);
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
		this.theta = Math.acos(this.real/this.r);//Math.atan(this.imag / this.real);
	}

	public double abs() {
		return Math.sqrt(real * real + imag * imag);
	}

	public void toCartesian() {
		this.real = this.r * Math.cos(this.theta);
		this.imag = this.r * Math.sin(this.theta);
		if(Math.abs(this.real)<thresh) {
			this.real=0;
		}
		if(Math.abs(this.imag)<thresh) {
			this.imag=0;
		}
	}

	public void toCartesian(double r, double t) {
		this.real = r * Math.cos(t);
		this.imag = r * Math.sin(t);
		if(Math.abs(this.real)<thresh) {
			this.real=0;
		}
		if(Math.abs(this.imag)<thresh) {
			this.imag=0;
		}
	}

	public static Complex toCartesian(String s) {
		if (!s.matches("\\d{0,}\\.?\\d{0,}e\\^\\d{0,}\\.?\\d{0,}i")
				&& !s.matches("\\d{0,}\\.?\\d{0,}e\\^\\d{0,}\\.?\\d{0,}pii")) {
			throw new IllegalArgumentException("INPUT ERROR!");
		}
		String[] terms = s.split("e\\^");
		String rstr = terms[0];
		String powstr = terms[1];
		double r = Double.parseDouble(rstr);
		powstr=powstr.replaceAll("i", "");
		double pow = 1.0;
		if(powstr.contains("p")) {
			pow*=Math.PI;
			powstr=powstr.replaceAll("p", "");
		}
		if(!powstr.isEmpty()) {
			pow*=Double.parseDouble(powstr);
		}
		Complex c = new Complex();
		c.toCartesian(r,pow);
		return c;
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
			if (this.imag < -thresh)
				return new String(this.real + " - " + Math.abs(this.imag) + "i");
			else if (this.imag > thresh)
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
