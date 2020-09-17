package nodatabase.test.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
	private String sum;
	private double answer;
	private String errorMessage;
	private String[] ops;
	private String[] terms;
	private ArrayList<Double> addsubTerms;

	public String getSum() {
		return sum;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public double getAnswer() {
		return answer;
	}

	public Calculator(String input) {
		this.sum = input;
		this.errorMessage = "None";
		this.answer = 0.0;
		try {
			if (this.sum.contains("(") && (countCharOcc(this.sum, '(') == countCharOcc(this.sum, ')'))) {
				solveParenthesis();
			} else if((countCharOcc(this.sum, '(') != countCharOcc(this.sum, ')'))){
				throw new CalculatorSyntaxException("Syntax error! Parenthesis error!");
			}
			if (syntaxCheck()) {
				calculate();
			} else {
				throw new CalculatorSyntaxException("Syntax error!");
			}
		} catch (CalculatorSyntaxException cse) {
			this.errorMessage = cse.getMessage();
		} catch (Exception e) {
			this.errorMessage = "ERROR: " + e.getMessage();
			e.printStackTrace();
		}
	}
	
	public void solveParenthesis() throws CalculatorSyntaxException {
		int ind = this.sum.lastIndexOf('(');
		//find next close parenthesis
		int indP = -1;
		for(int i=ind+1;i<this.sum.length();i++) {
			if(this.sum.charAt(i) == ')') {
				indP=i;
				break;
			}
		}
		if(indP<0) {
			throw new CalculatorSyntaxException("Parenthesis Error!");
		}
		String subsum = this.sum.substring(ind+1,indP);
		//System.out.println(subsum+" = "+calculate(subsum));
		Double subAnswer = calculate(subsum);
		StringBuilder replacer = new StringBuilder(this.sum);
		replacer.replace(ind, indP+1, Double.toString(subAnswer));
		this.sum = replacer.toString();
		System.out.println(this.sum);
	}
	
	public void calculate() {
		this.sum = this.sum.replaceAll("\\s", ""); // remove spaces
		this.terms = this.sum.split("\\+|\\-"); // contains numbers
		this.ops = this.sum.split("[\\*\\/]?\\d{1,}[\\*\\/]?"); // contains operations
		this.addsubTerms = new ArrayList<Double>();
		boolean test = Arrays.stream(ops).anyMatch(""::equals);
		if (test) { // Remove annoying empty strings
			ArrayList<String> help = new ArrayList<>();
			for (int i = 0; i < ops.length; i++) {
				if (!ops[i].equals(""))
					help.add(ops[i]);
			}
			ops = new String[help.size()];
			ops = help.toArray(ops);
		}
		// multiply and divide first
		for (int i = 0; i < terms.length; i++) {
			if (!terms[i].contains("*") && !terms[i].contains("/")) {
				addsubTerms.add(Double.parseDouble(terms[i]));
				continue;
			}
			String[] termsI = terms[i].split("\\*|\\/");
			Double finalValue = Double.parseDouble(termsI[0]);
			String[] opsI = terms[i].split("\\d{1,}\\.?\\d{0,}");
			boolean test2 = Arrays.stream(opsI).anyMatch(""::equals);
			if (test2) { // Remove annoying empty strings
				ArrayList<String> help = new ArrayList<>();
				for (int q = 0; q < opsI.length; q++) {
					if (!opsI[q].equals(""))
						help.add(opsI[q]);
				}
				opsI = new String[help.size()];
				opsI = help.toArray(opsI);
			}
			for (int j = 0; j < opsI.length; j++) {
				if (opsI[j].equals("*")) {
					finalValue *= Double.parseDouble(termsI[j + 1]);
				} else if (opsI[j].equals("/")) {
					finalValue /= Double.parseDouble(termsI[j + 1]);
				}
			}
			addsubTerms.add(finalValue);
		}
		boolean test2 = Arrays.stream(ops).anyMatch("*"::equals);
		boolean test3 = Arrays.stream(ops).anyMatch("/"::equals);
		// remove * and / from ops
		if (test2 || test3) {
			ArrayList<String> help = new ArrayList<>();
			for (int i = 0; i < ops.length; i++) {
				if (!ops[i].equals("*") && !ops[i].equals("/"))
					help.add(ops[i]);
			}
			ops = new String[help.size()];
			ops = help.toArray(ops);
		}
		// add and subtract everything
		this.answer += addsubTerms.get(0);
		for (int i = 0; i < ops.length; i++) {
			if (ops[i].equals("+")) {
				this.answer += addsubTerms.get(i + 1);
			} else if (ops[i].equals("-")) {
				this.answer -= addsubTerms.get(i + 1);
			}
		}
		// System.out.println(this.answer);
	}
	
	public Double calculate(String subsum) {
		subsum = subsum.replace("\\s", ""); // remove spaces
		String[] terms = subsum.split("\\+|\\-"); // contains numbers
		String[] ops = subsum.split("[\\*\\/]?\\d{1,}[\\*\\/]?"); // contains operations
		ArrayList<Double> addsubTerms = new ArrayList<Double>();
		Double answer = 0.0;
		boolean test = Arrays.stream(ops).anyMatch(""::equals);
		if (test) { // Remove annoying empty strings
			ArrayList<String> help = new ArrayList<>();
			for (int i = 0; i < ops.length; i++) {
				if (!ops[i].equals(""))
					help.add(ops[i]);
			}
			ops = new String[help.size()];
			ops = help.toArray(ops);
		}
		// multiply and divide first
		for (int i = 0; i < terms.length; i++) {
			// Double finalValue = 1.0;
			if (!terms[i].contains("*") && !terms[i].contains("/")) {
				addsubTerms.add(Double.parseDouble(terms[i]));
				continue;
			}
			String[] termsI = terms[i].split("\\*|\\/");
			Double finalValue = Double.parseDouble(termsI[0]);
			String[] opsI = terms[i].split("\\d{1,}");
			boolean test2 = Arrays.stream(opsI).anyMatch(""::equals);
			if (test2) { // Remove annoying empty strings
				ArrayList<String> help = new ArrayList<>();
				for (int q = 0; q < opsI.length; q++) {
					if (!opsI[q].equals(""))
						help.add(opsI[q]);
				}
				opsI = new String[help.size()];
				opsI = help.toArray(opsI);
			}
			for (int j = 0; j < opsI.length; j++) {
				if (opsI[j].equals("*")) {
					finalValue *= Double.parseDouble(termsI[j + 1]);
				} else if (opsI[j].equals("/")) {
					finalValue /= Double.parseDouble(termsI[j + 1]);
				}
			}
			addsubTerms.add(finalValue);
		}
		boolean test2 = Arrays.stream(ops).anyMatch("*"::equals);
		boolean test3 = Arrays.stream(ops).anyMatch("/"::equals);
		// remove * and / from ops
		if (test2 || test3) {
			ArrayList<String> help = new ArrayList<>();
			for (int i = 0; i < ops.length; i++) {
				if (!ops[i].equals("*") && !ops[i].equals("/"))
					help.add(ops[i]);
			}
			ops = new String[help.size()];
			ops = help.toArray(ops);
		}
		// add and subtract everything
		answer += addsubTerms.get(0);
		for (int i = 0; i < ops.length; i++) {
			if (ops[i].equals("+")) {
				answer += addsubTerms.get(i + 1);
			} else if (ops[i].equals("-")) {
				answer -= addsubTerms.get(i + 1);
			}
		}
		return answer;
	}
	
	private boolean syntaxCheck() {
		if (this.sum.contains("**") || this.sum.contains("//") || this.sum.contains("++") || this.sum.contains("--")) {
			return false;
		}
		if (countCharOcc(this.sum, '(') != countCharOcc(this.sum, ')')) {
			return false;
		}
		return true;
	}

	private int countCharOcc(String s, char c) {
		char[] scarr = s.toCharArray();
		int ans = 0;
		for (int i = 0; i < scarr.length; i++) {
			if (scarr[i] == c) {
				ans++;
			}
		}
		return ans;
	}
}