package app.math.domain;

public class Currency {
	public Currency() {}
	public double convert(String conversion,double input) {
		String a = clearString(conversion);
		a=a.substring(a.indexOf('=')).trim();
		a=a.replaceAll("[^\\d^\\.]", "");
		double conversionFactor = Double.parseDouble(a);
		double returnValue = input*conversionFactor;
		returnValue = Math.round(returnValue*100.0)/100.0;
		return returnValue;
	}
	private String clearString(String input) {
		int bracket2 = input.indexOf('>');
		input = input.substring(bracket2+1);
		int bracket1 = input.indexOf('<');
		input = input.substring(0, bracket1);
		return input;
	}
}
