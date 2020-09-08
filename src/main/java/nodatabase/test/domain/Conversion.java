package nodatabase.test.domain;

public class Conversion {
	private double celcius;
	private double fahrenheit;
	
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
