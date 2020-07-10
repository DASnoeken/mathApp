package nodatabase.test.domain;

import java.util.ArrayList;

public class Derivative {
	private String function;
	private ArrayList<String> terms;
	private StringBuilder derivative;
	private Matrix powerruleMatrix;
	
	public Derivative(String function){
		this.function=function;
		
	}
	
	public String derive() {
		breakIntoTerms();
		String result = powerRule();
		return result;
	}
	
	private String powerRule() {
		StringBuilder result = new StringBuilder();
		powerruleMatrix = new Matrix(terms.size(),terms.size());
		Matrix coefficients = new Matrix(terms.size(),1);
		int count=0;
		for(String term:terms) {
			String[] a_terms = term.split("x");
			String a_n;
			String p_n;
			if(!term.contains("x")) {
				a_n="0";
				p_n="0";
			}else if(a_terms.length==0) {
				a_n="1";
				if(a_terms.length>1) {
					p_n = a_terms[1].substring(1);
				}else {
					p_n = "1";
				}
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
		Matrix derivativeCoefficients = powerruleMatrix.multiply(coefficients);
		for(int i = 0; i<terms.size();i++) {
			if(derivativeCoefficients.getMatrix().get(i).get(0)!=0.0) {
				result.append(derivativeCoefficients.getMatrix().get(i).get(0));
				if(powerruleMatrix.getMatrix().get(i).get(i)-1.0>0.0) {
					if(powerruleMatrix.getMatrix().get(i).get(i)-1.0!=1.0)
						result.append("x^").append(powerruleMatrix.getMatrix().get(i).get(i)-1.0);
					else
						result.append("x");
					if(i<terms.size()-1)
						result.append(" + ");
				}
				
			}
		}
		return result.toString();
	}
	
	private void breakIntoTerms() {
		String[] terms_P = function.split("-");
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
			if(term.contains("+")) {
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
