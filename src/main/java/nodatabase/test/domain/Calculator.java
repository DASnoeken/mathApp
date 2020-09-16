package nodatabase.test.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
	private String sum;
	private double answer;
	
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public double getAnswer() {
		return answer;
	}
	
	public Calculator(String input) {
		this.sum = input;
		this.answer = 0.0;
		calculate();
	}
	public void calculate() {
		this.sum = this.sum.replace("\\s", "");	//remove spaces
		String[] terms = this.sum.split("\\+|\\-|\\*|\\/");	//contains numbers
		String[] ops = this.sum.split("\\d{1,}");			//contains operations
		boolean test = Arrays.stream(ops).anyMatch(""::equals);
		if(test) {	//Remove annoying empty strings
			ArrayList<String> help = new ArrayList<>();
			for(int i = 0;i<ops.length;i++) {
				if(!ops[i].equals(""))
					help.add(ops[i]);
			}
			ops = new String[help.size()];
			ops = help.toArray(ops);
		}
		for(String term:terms) {
			System.out.println(term);
		}
		for(String op:ops) {
			System.out.println(op);
		} //for testing purposes
		for(int i=0;i<ops.length;i++) {
			System.out.println(i +", "+ops.length);
			if(ops[i].equals("+")) {
				this.answer += add(Double.parseDouble(terms[i]),Double.parseDouble(terms[i+1]));
			}else if(ops[i].equals("-")) {
				this.answer += subtract(Double.parseDouble(terms[i]),Double.parseDouble(terms[i+1]));
			}else if(ops[i].equals("/")) {
				this.answer += divide(Double.parseDouble(terms[i]),Double.parseDouble(terms[i+1]));
			}else if(ops[i].equals("*")) {
				this.answer += multiply(Double.parseDouble(terms[i]),Double.parseDouble(terms[i+1]));
			}
		}
		System.out.println(this.answer);
	}
	private double multiply(double a,double b) {
		return a*b;
	}
	private double divide(double a,double b) {
		return a/b;
	}
	private double add(double a,double b) {
		return a+b;
	}
	private double subtract(double a,double b) {
		return a-b;
	}
}
