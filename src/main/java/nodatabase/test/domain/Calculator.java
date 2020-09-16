package nodatabase.test.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
	private String sum;
	private double answer;
	private String[] ops;
	private String[] terms;
	private ArrayList<Double> addsubTerms;
	
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
		this.sum = "1*1-1+"+input+"*1"; //Shut up! This works (for now)!
		this.answer = 0.0;
		calculate();
	}
	public void calculate() {
		this.sum = this.sum.replace("\\s", "");					//remove spaces
		this.terms = this.sum.split("\\+|\\-|\\*|\\/");			//contains numbers
		this.ops = this.sum.split("\\d{1,}");					//contains operations
		this.addsubTerms = new ArrayList<Double>();
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
		//multiply and divide first
		for(int i=0;i<ops.length;i++){
			if(ops[i].equals("*")) {
				addsubTerms.add(multiply(Double.parseDouble(terms[i]),Double.parseDouble(terms[i+1])));
			}else if(ops[i].equals("/")) {
				try {
					addsubTerms.add(divide(Double.parseDouble(terms[i]),Double.parseDouble(terms[i+1])));
				}catch(ArithmeticException ae) {
					this.answer = Double.NaN;
					return;
				}
			}else if(((i==ops.length-1) && ops[i].equals("+")) || ops[i].equals("+")&&!(ops[i+1].equals("*")||ops[i+1].equals("/"))) {
				addsubTerms.add(Double.parseDouble(terms[i]));
			}else if(((i==ops.length-1) && ops[i].equals("-")) || ops[i].equals("-")&&!(ops[i+1].equals("*")||ops[i+1].equals("/"))) {
				addsubTerms.add(Double.parseDouble(terms[i]));
			}
		}
		/*System.out.println("addsubTerms:");
		for(Double d:addsubTerms) {
			System.out.println(d);
		}*/
		boolean test2 = Arrays.stream(ops).anyMatch("*"::equals);
		boolean test3 = Arrays.stream(ops).anyMatch("/"::equals);
		//remove * and / from ops
		if(test2||test3){
			ArrayList<String> help = new ArrayList<>();
			for(int i = 0;i<ops.length;i++) {
				if(!ops[i].equals("*") && !ops[i].equals("/"))
					help.add(ops[i]);
			}
			ops = new String[help.size()];
			ops = help.toArray(ops);
		}
		//add and subtract everything
		this.answer+=addsubTerms.get(0);
		for(int i=0;i<ops.length;i++) {
			if(ops[i].equals("+")) {
				this.answer+=addsubTerms.get(i+1);
			}else if(ops[i].equals("-")) {
				this.answer-=addsubTerms.get(i+1);
			}
		}
		//System.out.println(this.answer);
	}
	

	private double multiply(double a,double b) {
		return a*b;
	}

	private double divide(double a,double b) {
		if(b==0.0) {
			throw new ArithmeticException();
		}
		return a/b;
	}
	
	@SuppressWarnings("unused")
	private double add(double a,double b) {
		return a+b;
	}
	
	@SuppressWarnings("unused")
	private double subtract(double a,double b) {
		return a-b;
	}
}
