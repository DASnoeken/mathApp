package nodatabase.test.domain;

public class Conversion {
	private double celcius;
	private double fahrenheit;
	private double kg;
	private double lbs;
	
	public void kgToLbs() {
		this.lbs = this.kg / 0.4536;
	}
	public void lbsToKg() {
		this.kg = this.lbs*0.4536;
	}
	
	public double getKg() {
		return kg;
	}

	public void setKg(double kg) {
		this.kg = kg;
	}

	public double getLbs() {
		return lbs;
	}

	public void setLbs(double lbs) {
		this.lbs = lbs;
	}

	public void cToF() {
		this.fahrenheit = 1.8*celcius + 32.0;
	}
	
	public void fToC() {
		this.celcius = (this.fahrenheit - 32.0)/1.8;
	}
	
	public double getCelcius() {
		return celcius;
	}
	public void setCelcius(double celcius) {
		this.celcius = celcius;
	}
	public double getFahrenheit() {
		return fahrenheit;
	}
	public void setFahrenheit(double fahrenheit) {
		this.fahrenheit = fahrenheit;
	}
	
	
}
