package nodatabase.test.domain;

public class Conversion {
	private double celcius;
	private double fahrenheit;
	private double kg;
	private double lbs;
	private double miles;
	private double km;
	private double inch;
	private double cm;
	
	public void inchToCm() {
		this.cm = this.inch*2.54;
	}
	public void cmToInch() {
		this.inch = this.cm/2.54;
	}
	public double getCm() {
		return cm;
	}
	public void setCm(double cm) {
		this.cm = cm;
	}
	public double getInch() {
		return inch;
	}
	public void setInch(double inch) {
		this.inch = inch;
	}
	public void kmToMiles() {
		this.miles = this.km/1.609344;
	}
	public void milesToKm() {
		this.km = this.miles*1.609344;
	}
	public double getMiles() {
		return miles;
	}
	public void setMiles(double miles) {
		this.miles = miles;
	}
	public double getKm() {
		return km;
	}
	public void setKm(double km) {
		this.km = km;
	}
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
