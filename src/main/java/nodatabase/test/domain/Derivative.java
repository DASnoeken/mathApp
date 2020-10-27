package nodatabase.test.domain;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Derivative {
	private String function;
	private ArrayList<String> terms;
	private ArrayList<String> specialTerms;
	private ArrayList<String> polynomialTerms;
	private ArrayList<String> exponentialTerms;
	private ArrayList<String> productRuleTerms;
	private ArrayList<String> chainRuleTerms;
	private ArrayList<String> logTerms;
	private StringBuilder derivative;
	private MatrixD powerruleMatrix;
	
	public Derivative(String function){
		function = function.replaceAll("\\s", "");
		function = function.replaceAll("u", "x");
		this.function=function;
		specialTerms=new ArrayList<String>();
		polynomialTerms = new ArrayList<String>();
		productRuleTerms = new ArrayList<String>();
		exponentialTerms = new ArrayList<String>();
		logTerms = new ArrayList<String>();
		chainRuleTerms = new ArrayList<String>();
	}
	
	private void specializeTerms() {
		for(String term:terms) {
			if(term.matches("[-]?\\d{0,}x\\^[-]?\\d{1,}") || term.matches("[-]?\\d{0,}x")) {
				polynomialTerms.add(term);
			}else if(term.matches("[-]?\\d{1,}\\^x")) {
				exponentialTerms.add(term);
			}else if(term.matches("[-]?(log)[_]\\d{1,}[(]x[)]")) {
				logTerms.add(term);
			}
			else {
				specialTerms.add(term);
			}
			if(term.contains("*")) {
				productRuleTerms.add(term);
			}else if(term.contains("{") && term.contains("}")) {
				chainRuleTerms.add(term);
			}
		}
	}
	
	public String derive() {
		breakIntoTerms();
		for(int index=0;index<terms.size();index++) {
			String term = terms.get(index);
			if(term.charAt(0)=='*') {
				terms.remove(index);
			}
		}
		specializeTerms();
		String result = new String();
		try {
			result = powerRule();
		} catch (MatrixDimensionException e) {
			e.printStackTrace();
		}
		if(result.length()>0) {
			result += " + ";
		}
		result += special();
		result += exponentialNonE();
		result += logarithmicNonE();
		result += productRule();
		result += chainRule();
		result = result.trim();
		if(result.charAt(result.length()-1) == '+') {
			result = result.substring(0, result.length()-1);
		}
		return result;
	}
	
	private String chainRule() {
		String ans = new String();
		for(String term:chainRuleTerms) {
			String extractedTerm=new String();
			Pattern pattern = Pattern.compile("\\{.*\\}");
			Matcher matcher = pattern.matcher(term);
			String outerTerm = term.replaceAll("\\{.*\\}", "u");
			Derivative d_outer = new Derivative(outerTerm);
			String outerDerivative = d_outer.derive();
			outerDerivative = outerDerivative.replaceAll("\\(x\\)","(u)");
			do {
				if (matcher.find()){
					extractedTerm = matcher.group(0).substring(1, matcher.group(0).length()-1);
					outerDerivative = outerDerivative.replaceAll("\\(u\\)", "({"+extractedTerm+"})");
					outerDerivative.trim();
					ans+=outerDerivative+" * ";
				}	
				try {
					Derivative d = new Derivative(extractedTerm);
					ans+="("+d.derive().trim()+")";
				}catch(Exception e) {
					e.printStackTrace();
				}
				matcher = pattern.matcher(extractedTerm);
			}while(extractedTerm.contains("{") && extractedTerm.contains("}"));
			ans+="+";
		}
		return ans;
	}
	
	private String chainRule(String term) {
		String ans = new String();
		String extractedTerm=new String();
		Pattern pattern = Pattern.compile("\\{.*\\}");
		Matcher matcher = pattern.matcher(term);
		String outerTerm = term.replaceAll("\\{.*\\}", "u");
		Derivative d_outer = new Derivative(outerTerm);
		String outerDerivative = d_outer.derive();
		outerDerivative = outerDerivative.replaceAll("\\(x\\)","(u)");
		do {
			if (matcher.find()){
				extractedTerm = matcher.group(0).substring(1, matcher.group(0).length()-1);
				outerDerivative = outerDerivative.replaceAll("\\(u\\)", "({"+extractedTerm+"})");
				ans+=outerDerivative+"*";
			}	
			try {
				Derivative d = new Derivative(extractedTerm);
				ans+=d.derive();
			}catch(Exception e) {
				e.printStackTrace();
			}
			matcher = pattern.matcher(extractedTerm);
		}while(extractedTerm.contains("{") && extractedTerm.contains("}"));
		return ans;
	}
	
	private String productRule() {
		String ans = new String();
		for(String term:productRuleTerms) {
			String[] tmp = term.split("[*]");
			for(int i =0;i<tmp.length;i++) {
				if(!tmp[i].contains("x")) {		//numbers have derivative 0
					continue;
				}
				if(!tmp[i].matches("\\d{1,}"))
					ans+=singleTerm(tmp[i])+"*";
				else
					continue;
				for(int j = 0;j<tmp.length;j++) {
					if(j==i) {
						continue;
					}
					ans+=tmp[j]+"*";
				}
				if(ans.length()>=1 && ans.charAt(ans.length()-1)=='*') {
					ans = ans.substring(0, ans.length()-1);
				}
				ans+=" + ";
			}
		}
		if(ans.length()>=1 && ans.charAt(ans.length()-1)=='*') {
			ans = ans.substring(0, ans.length()-1);
		}
		return ans;
	}
	
	private String singleTerm(String term) {
		String ans = new String();
		if(term.matches("[-]?\\d{0,}x\\^[-]?\\d{1,}") || term.matches("[-]?\\d{0,}x")) {
			ans+=powerRule(term);
		}else if(term.matches("[-]?\\d{1,}\\^x")) {
			ans+=exponentialNonE(term);
		}else if(term.matches("[-]?(log)[_]\\d{1,}[(]x[)]")){
			ans+=logarithmicNonE(term);
		}else if(term.contains("{") && term.contains("}")){
			ans+=chainRule(term);
		}else {
			switch(term) {
			case "ln(x)": {ans+="1/(x)";break;}
			case "e^x": {ans+="e^(x)";break;}
			case "exp(x)": {ans+="exp(x)";break;}
			case "sin(x)": {ans+="cos(x)";break;}
			case "cos(x)": {ans+="-sin(x)";break;}
			case "-sin(x)": {ans+="-cos(x)";break;}
			case "-cos(x)": {ans+="sin(x)";break;}
			case "tan(x)": {ans+="sec(x)*sec(x)";break;}
			case "sec(x)": {ans+="tan(x)*sec(x)";break;}
			case "csc(x)": {ans+="-cot(x)*csc(x) + ";break;}
			case "cot(x)": {ans+="-csc(x)*csc(x) = ";break;}
			case "x^x": {ans+="x^x*(ln(x)+1)";break;}
			}
		}
		return ans;
	}
	
	private String logarithmicNonE(String term) {
		String ans = new String();
		String[] tmp = term.split("[_]");
		tmp = tmp[1].split("[(]");
		String base = tmp[0];
		ans="1/(x*ln("+base+"))";
		return ans;
	}
	
	private String logarithmicNonE() {
		String ans = new String();
		for(String term:logTerms) {
			String[] tmp = term.split("[_]");
			tmp = tmp[1].split("[(]");
			String base = tmp[0];
			ans="1/(x*ln("+base+"))";
		}
		return ans;
	}
	
	private String exponentialNonE(String term) {
		String ans = new String();
		String[] tmp = term.split("\\^");
		String base = tmp[0];
		ans = term+"*ln("+base+")";
		return ans;
	}
	
	private String exponentialNonE() {
		String ans = new String();
		for(String term:exponentialTerms) {
			String[] tmp = term.split("\\^");
			String base = tmp[0];
			ans = term+"*ln("+base+") + ";
		}
		return ans;
	}
	
	private String special() {
		String ans = new String();
		for(String term:specialTerms) {
			switch(term) {
			case "ln(x)": {ans+="1/(x) + ";break;}
			case "e^x": {ans+="e^(x) + ";break;}
			case "exp(x)": {ans+="exp(x) + ";break;}
			case "sin(x)": {ans+="cos(x) + ";break;}
			case "cos(x)": {ans+="-sin(x) + ";break;}
			case "-sin(x)": {ans+="-cos(x) + ";break;}
			case "-cos(x)": {ans+="sin(x) + ";break;}
			case "tan(x)": {ans+="sec(x)*sec(x) + ";break;}
			case "sec(x)": {ans+="tan(x)*sec(x) + ";break;}
			case "csc(x)": {ans+="-cot(x)*csc(x) + ";break;}
			case "cot(x)": {ans+="-csc(x)*csc(x) = ";break;}
			case "x^x": {ans+="x^x*(ln(x)+1)";break;}
			}
		}
		return ans;
	}
	
	private String powerRule(String term) {
		String ans = new String();
		if(term.contains("^")) {
			String[] coef_pow = term.split("x\\^");
			double coef;
			double pow;
			if(coef_pow[0].equals("")) {
				pow = Double.parseDouble(coef_pow[1]);
				coef = 1.0;
			}else {
				coef = Double.parseDouble(coef_pow[0]);
				pow = Double.parseDouble(coef_pow[1]);
			}
			double newCoef = coef*pow;
			double newPow = pow - 1.0;
			ans+=newCoef+"x^"+newPow;
		}else {
			String[] coef_pow = term.split("x");
			if(coef_pow.length>0)
				ans+=coef_pow[0];
			else
				ans+="1";
		}
		return ans;
	}
	
	private String powerRule() throws MatrixDimensionException {
		StringBuilder result = new StringBuilder();
		powerruleMatrix = new MatrixD(polynomialTerms.size(),polynomialTerms.size());
		MatrixD coefficients = new MatrixD(polynomialTerms.size(),1);
		int count=0;
		for(String term:polynomialTerms) {
			String[] a_terms = term.split("x");
			String a_n;
			String p_n;
			if(!term.contains("x")) {
				a_n="0";
				p_n="0";
			}else if(a_terms.length==0) {
				a_n = "1";
				p_n = "1";
			}else{
				a_n = a_terms[0];
				if(a_terms.length>1) {
					p_n = a_terms[1].substring(1);
				}else {
					p_n = "1";
				}
				
			}
			if(a_n.length()==0 || a_n.equals("-")) {
				a_n+="1";
			}
			powerruleMatrix.setMatrixElement(count,count, Double.parseDouble(p_n));
			coefficients.setMatrixElement(count, 0, Double.parseDouble(a_n));
			count++;
		}
		MatrixD derivativeCoefficients = powerruleMatrix.multiply(coefficients);
		for(int i = 0; i<polynomialTerms.size();i++) {
			if(derivativeCoefficients.getMatrix().get(i).get(0)!=0.0) {
				result.append(derivativeCoefficients.getMatrix().get(i).get(0));
				if(powerruleMatrix.getMatrix().get(i).get(i)-1.0!=0.0) {
					if(powerruleMatrix.getMatrix().get(i).get(i)-1.0!=1.0)
						result.append("x^").append(powerruleMatrix.getMatrix().get(i).get(i)-1.0);
					else
						result.append("x");
					if(i<polynomialTerms.size()-1)
						result.append(" + ");
				}
			}
		}
		return result.toString();
	}
	
	private void breakIntoTerms() {
		String[] terms_P = function.split("(?<!\\^|\\{)-");
		if (terms_P[0].equals("")) {
			for(int i = 0;i<terms_P.length-1;i++) {
				terms_P[i]=terms_P[i+1];
			}
			terms_P[terms_P.length-1] = "0";
		}
		if(function.charAt(0)=='-') {
			for(int i=0;i<terms_P.length;i++) {
				terms_P[i]="-"+terms_P[i];
			}
		}else {
			for(int i=1;i<terms_P.length;i++) {
				terms_P[i]="-"+terms_P[i];
			}
		}
		terms = new ArrayList<String>();
		String[] termF;
		for(String term:terms_P) {
			if(term.contains("+") && !term.matches(".*\\{.*[\\+-].*\\}.*")) {
				termF = term.split("[+]");
				for(int i=0;i<termF.length;i++) {
					if(!term.equals("0") && !term.equals("-0"))
						terms.add(termF[i]);
				}
				termF=null;
			}else {
				if(!term.equals("0") && !term.equals("-0"))
					terms.add(term);
			}
			
		}
	}
	
	public String getFunction() {
		return function;
	}
	
	public void setFunction(String function) {
		this.function = function;
	}
	
	public StringBuilder getDerivative() {
		return derivative;
	}
}
