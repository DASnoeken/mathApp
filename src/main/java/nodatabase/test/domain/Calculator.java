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
		this.sum = input; 
		this.answer = 0.0;
		calculate();
	}
	public void calculate() {
		this.sum = this.sum.replace("\\s", "");					//remove spaces
		this.terms = this.sum.split("\\+|\\-");					//contains numbers
		this.ops = this.sum.split("[\\*\\/]?\\d{1,}[\\*\\/]?");					//contains operations
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
		for(int i=0;i<terms.length;i++) {
			Double finalValue = 1.0;
			if(!terms[i].contains("*") && !terms[i].contains("/")) {
				addsubTerms.add(Double.parseDouble(terms[i]));
			}
			String[] termsI = terms[i].split("\\*|\\/");
			finalValue*=Double.parseDouble(termsI[0]);
			String[] opsI = terms[i].split("\\d{1,}");
			boolean test2 = Arrays.stream(opsI).anyMatch(""::equals);
			if(test2) {	//Remove annoying empty strings
				ArrayList<String> help = new ArrayList<>();
				for(int q = 0;q<opsI.length;q++) {
					if(!opsI[q].equals(""))
						help.add(opsI[q]);
				}
				opsI = new String[help.size()];
				opsI = help.toArray(opsI);
			}
			for(int j=0;j<opsI.length;j++) {
				if(opsI[j].equals("*")) {
					finalValue*=Double.parseDouble(termsI[j+1]);
				}else if(opsI[j].equals("/")) {
					finalValue/=Double.parseDouble(termsI[j+1]);
				}
			}
			addsubTerms.add(finalValue);
		}
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
}
