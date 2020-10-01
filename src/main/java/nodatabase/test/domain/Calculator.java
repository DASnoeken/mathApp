package nodatabase.test.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculator {
	private String sum;
	private BigDecimal answer;
	private String errorMessage;
	private String[] ops;
	private String[] terms;
	private ArrayList<BigDecimal> addsubTerms;
	private String trigState;
	private MathContext mc;

	public String getTrigState() {
		return trigState;
	}

	public void setTrigState(String trigState) {
		this.trigState = trigState;
	}

	public String getSum() {
		return sum;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public BigDecimal getAnswer() {
		BigDecimal thresh = new BigDecimal("1E-10");
		if (this.answer.compareTo(thresh) == -1 && this.answer.compareTo(thresh.multiply(new BigDecimal("-1"))) == 1) {
			return BigDecimal.ZERO;
		} else {
			return answer;
		}
	}

	public Calculator(String input, String trigStateInput) {
		this.trigState = trigStateInput;
		this.sum = input;
		this.mc = new MathContext(50);
		this.errorMessage = "None";
		this.answer = BigDecimal.ZERO;
		try {
			if ((countCharOcc(this.sum, '(') != countCharOcc(this.sum, ')'))) {
				throw new CalculatorSyntaxException("Syntax error! Parenthesis error!");
			}
			while (this.sum.contains("(") && (countCharOcc(this.sum, '(') == countCharOcc(this.sum, ')'))) {
				solveParenthesis();
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
		// find next close parenthesis
		int indP = -1;
		for (int i = ind + 1; i < this.sum.length(); i++) {
			if (this.sum.charAt(i) == ')') {
				indP = i;
				break;
			}
		}
		if (indP < 0) {
			throw new CalculatorSyntaxException("Parenthesis Error!");
		}
		String subsum = this.sum.substring(ind + 1, indP);
		BigDecimal subAnswer = calculate(subsum);
		StringBuilder replacer = new StringBuilder(this.sum);
		replacer.replace(ind, indP + 1, subAnswer.toString());
		this.sum = replacer.toString();
	}

	public void calculate() {
		this.sum = this.sum.replaceAll("\\s", ""); // remove spaces
		this.terms = this.sum.split("(\\+|\\-)"); // contains numbers
		this.ops = this.sum.split(
				"\\({0,}([\\*\\/\\^]?\\d{1,}[\\*\\/\\^]?|[a-z]{1,}\\{\\d{1,}\\.?\\d{0,}\\}|e|pi|phi|\\d{0}(?!\\-)\\d{1,})\\){0,}");
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(this.terms));
		list.remove("");
		if (this.terms.length != list.size()) {
			this.terms = list.toArray(new String[0]);
		}
		this.addsubTerms = new ArrayList<BigDecimal>();
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
			if (!terms[i].contains("*") && !terms[i].contains("/") && !terms[i].contains("^")
					&& !checkSpecialFunction(terms[i]) && !checkSpecialNumber(terms[i])) {
				// addsubTerms.add(Double.parseDouble(terms[i]));
				addsubTerms.add(new BigDecimal(terms[i]));
				continue;
			}
			String[] termsI = terms[i].split("\\*|\\/|\\^");
			String[] opsI = terms[i].split("\\d{1,}\\.?\\d{0,}|[a-z]{1,}\\{\\d{1,}\\}|pi|e");
			for (int j = 0; j < termsI.length; j++) {
				if (termsI[j].contains("neg")) {
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(-1 * (Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].equalsIgnoreCase("pi")) {
					termsI[j] = new BigDecimal(Math.PI).toString();
				}
				if (termsI[j].equalsIgnoreCase("e")) {
					termsI[j] = new BigDecimal(Math.E).toString();
				}
				if (termsI[j].equalsIgnoreCase("phi")) { // Golden Ratio
					termsI[j] = new BigDecimal(0.5 + Math.sqrt(5.0) / 2.0).toString();
				}
				if (termsI[j].contains("exp")) {
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(Math.exp(Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].contains("sqrt")) { // square roots
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1); // contains number we want sqrt of
					termsI[j] = new BigDecimal(Math.sqrt(Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].contains("cbrt")) { // cube roots
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(Math.cbrt(Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].contains("sin") && !termsI[j].contains("sinh")) { // trigonometry
					if (this.trigState.equals("radians")) {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.sin(Double.valueOf(termsIp[1]))).toString();
					} else {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.sin(Double.valueOf(termsIp[1]) * Math.PI / 180.0)).toString();
					}
				}
				if (termsI[j].contains("cos") && !termsI[j].contains("cosh")) {
					if (this.trigState.equals("radians")) {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.cos(Double.valueOf(termsIp[1]))).toString();
					} else {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.cos(Double.valueOf(termsIp[1]) * Math.PI / 180.0)).toString();
					}
				}
				if (termsI[j].contains("tan") && !termsI[j].contains("tanh")) {
					if (this.trigState.equals("radians")) {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.tan(Double.valueOf(termsIp[1]))).toString();
					} else {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.tan(Double.valueOf(termsIp[1]) * Math.PI / 180.0)).toString();
					}
				}
				if (termsI[j].contains("sinh")) { // hyperbolic functions
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(Math.sinh(Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].contains("cosh")) {
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(Math.cosh(Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].contains("tanh")) {
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(Math.tanh(Double.valueOf(termsIp[1]))).toString();
				}
			}
			BigDecimal finalValue = new BigDecimal(termsI[0]);// Double.parseDouble(termsI[0]);
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
				if (opsI[j].equals("^")) {
					double power = Double.parseDouble(termsI[j + 1]);
					if ((int) power == power) {
						for (int k = 1; k < power; k++) {
							finalValue = finalValue.multiply(new BigDecimal(termsI[j]));// Double.parseDouble(termsI[j]);
						}
					} else {
						finalValue = finalValue.pow((int) power)
								.multiply(new BigDecimal(Math.pow(Double.valueOf(finalValue.toString()), power)));// Math.pow(Double.parseDouble(termsI[j]),
																													// power);
					}
				} else if (opsI[j].equals("*")) {
					finalValue = finalValue.multiply(new BigDecimal(termsI[j + 1]));// Double.parseDouble(termsI[j +
																					// 1]);
				} else if (opsI[j].equals("/")) {
					finalValue = finalValue.divide(new BigDecimal(termsI[j + 1]), this.mc);// Double.parseDouble(termsI[j
																							// +
																							// 1]);
				}
			}
			addsubTerms.add(finalValue);
		}
		boolean test2 = Arrays.stream(ops).anyMatch("*"::equals);
		boolean test3 = Arrays.stream(ops).anyMatch("/"::equals);
		boolean test4 = Arrays.stream(ops).anyMatch("."::equals);
		boolean test5 = Arrays.stream(ops).anyMatch("^"::equals);
		// remove *, . and / from ops
		if (test2 || test3 || test4 || test5) {
			ArrayList<String> help = new ArrayList<>();
			for (int i = 0; i < ops.length; i++) {
				if (!ops[i].equals("*") && !ops[i].equals("/") && !ops[i].equals(".") && !ops[i].equals("^"))
					help.add(ops[i]);
			}
			ops = new String[help.size()];
			ops = help.toArray(ops);
		}
		// add and subtract everything
		if (ops.length == addsubTerms.size() - 1) {
			this.answer = this.answer.add(addsubTerms.get(0));
			for (int i = 0; i < ops.length; i++) {
				if (ops[i].equals("+")) {
					this.answer = this.answer.add(addsubTerms.get(i + 1));
				} else if (ops[i].equals("-")) {
					this.answer = this.answer.subtract(addsubTerms.get(i + 1));
				}
			}
		} else {
			for (int i = 0; i < ops.length; i++) {
				if (ops[i].equals("+")) {
					this.answer = this.answer.add(addsubTerms.get(i));
				} else if (ops[i].equals("-")) {
					this.answer = this.answer.subtract(addsubTerms.get(i));
				}
			}
		}
	}

	public BigDecimal calculate(String subsum) {
		subsum = subsum.replace("\\s", ""); // remove spaces
		if (subsum.charAt(0) == '-') {
			subsum = "0" + subsum;
		}
		this.terms = subsum.split("(\\+|\\-)(?!\\d{0}(\\+|\\-))"); // contains numbers
		this.ops = subsum.split(
				"\\({0,}([\\*\\/\\^]?\\d{1,}[\\*\\/\\^]?|[a-z]{1,}\\{\\d{1,}\\.?\\d{0,}\\}|e|pi|\\d{0}(?!\\-)\\d{1,})\\){0,}");

		ArrayList<String> list = new ArrayList<String>(Arrays.asList(this.terms));
		list.remove("");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains("("))
				list.set(i, list.get(i).replace("(", ""));
			if (list.get(i).contains(")"))
				list.set(i, list.get(i).replace(")", ""));
		}
		this.terms = list.toArray(new String[0]);
		ArrayList<BigDecimal> addsubTerms = new ArrayList<BigDecimal>();
		BigDecimal answer = BigDecimal.ZERO;
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
			if (!terms[i].contains("*") && !terms[i].contains("/") && !terms[i].contains("^")
					&& !checkSpecialFunction(terms[i]) && !checkSpecialNumber(terms[i])) {
				addsubTerms.add(new BigDecimal(terms[i]));
				continue;
			}
			String[] termsI = terms[i].split("\\*|\\/|\\^");
			String[] opsI = terms[i].split("\\d{1,}\\.?\\d{0,}|[a-z]{1,}\\{\\d{1,}\\}|pi|e");
			for (int j = 0; j < termsI.length; j++) {
				if (termsI[j].contains("neg")) {
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(-1 * (Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].equalsIgnoreCase("pi")) {
					termsI[j] = new BigDecimal(Math.PI).toString();
				}
				if (termsI[j].equalsIgnoreCase("e")) {
					termsI[j] = new BigDecimal(Math.E).toString();
				}
				if (termsI[j].equalsIgnoreCase("phi")) { // Golden Ratio
					termsI[j] = new BigDecimal(0.5 + Math.sqrt(5.0) / 2.0).toString();
				}
				if (termsI[j].contains("exp")) {
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(Math.exp(Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].contains("sqrt")) { // square roots
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1); // contains number we want sqrt of
					termsI[j] = new BigDecimal(Math.sqrt(Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].contains("cbrt")) { // cube roots
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(Math.cbrt(Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].contains("sin") && !termsI[j].contains("sinh")) { // trigonometry
					if (this.trigState.equals("radians")) {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.sin(Double.valueOf(termsIp[1]))).toString();
					} else {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.sin(Double.valueOf(termsIp[1]) * Math.PI / 180.0)).toString();
					}
				}
				if (termsI[j].contains("cos") && !termsI[j].contains("cosh")) {
					if (this.trigState.equals("radians")) {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.cos(Double.valueOf(termsIp[1]))).toString();
					} else {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.cos(Double.valueOf(termsIp[1]) * Math.PI / 180.0)).toString();
					}
				}
				if (termsI[j].contains("tan") && !termsI[j].contains("tanh")) {
					if (this.trigState.equals("radians")) {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.tan(Double.valueOf(termsIp[1]))).toString();
					} else {
						String[] termsIp = termsI[j].split("\\{");
						termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
						termsI[j] = new BigDecimal(Math.tan(Double.valueOf(termsIp[1]) * Math.PI / 180.0)).toString();
					}
				}
				if (termsI[j].contains("sinh")) { // hyperbolic functions
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(Math.sinh(Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].contains("cosh")) {
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(Math.cosh(Double.valueOf(termsIp[1]))).toString();
				}
				if (termsI[j].contains("tanh")) {
					String[] termsIp = termsI[j].split("\\{");
					termsIp[1] = termsIp[1].substring(0, termsIp[1].length() - 1);
					termsI[j] = new BigDecimal(Math.tanh(Double.valueOf(termsIp[1]))).toString();
				}
			}
			BigDecimal finalValue = new BigDecimal(termsI[0]);// Double.parseDouble(termsI[0]);
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
				if (opsI[j].equals("^")) {
					double power = Double.parseDouble(termsI[j + 1]);
					if ((int) power == power) {
						for (int k = 1; k < power; k++) {
							finalValue = finalValue.multiply(new BigDecimal(termsI[j]));// Double.parseDouble(termsI[j]);
						}
					} else {
						finalValue = finalValue.pow((int) power)
								.multiply(new BigDecimal(Math.pow(Double.valueOf(finalValue.toString()), power)));// Math.pow(Double.parseDouble(termsI[j]),
																													// power);
					}
				} else if (opsI[j].equals("*")) {
					finalValue = finalValue.multiply(new BigDecimal(termsI[j + 1]));// Double.parseDouble(termsI[j +
																					// 1]);
				} else if (opsI[j].equals("/")) {
					finalValue = finalValue.divide(new BigDecimal(termsI[j + 1]), this.mc);// Double.parseDouble(termsI[j
																							// +
																							// 1]);
				}
			}
			addsubTerms.add(finalValue);
		}
		boolean test2 = Arrays.stream(ops).anyMatch("*"::equals);
		boolean test3 = Arrays.stream(ops).anyMatch("/"::equals);
		boolean test4 = Arrays.stream(ops).anyMatch("."::equals);
		boolean test5 = Arrays.stream(ops).anyMatch("^"::equals);
		// remove *, . and / from ops
		if (test2 || test3 || test4 || test5) {
			ArrayList<String> help = new ArrayList<>();
			for (int i = 0; i < ops.length; i++) {
				if (!ops[i].equals("*") && !ops[i].equals("/") && !ops[i].equals(".") && !ops[i].equals("^"))
					help.add(ops[i]);
			}
			ops = new String[help.size()];
			ops = help.toArray(ops);
		}
		// add and subtract everything
		answer = answer.add(addsubTerms.get(0));
		for (int i = 0; i < ops.length; i++) {
			if (ops[i].equals("+")) {
				answer = answer.add(addsubTerms.get(i + 1));
			} else if (ops[i].equals("-")) {
				answer = answer.subtract(addsubTerms.get(i + 1));
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

	private boolean checkSpecialFunction(String input) {
		if (input.contains("sqrt") || input.contains("sin") || input.contains("cos") || input.contains("tan")
				|| input.contains("cbrt") || input.contains("sinh") || input.contains("cosh") || input.contains("tanh")
				|| input.contains("exp") || input.contains("neg")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkSpecialNumber(String input) {
		if (input.equalsIgnoreCase("pi") || input.equalsIgnoreCase("e") || input.equalsIgnoreCase("phi")) {
			return true;
		} else {
			return false;
		}
	}
}
