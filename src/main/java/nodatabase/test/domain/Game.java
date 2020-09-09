package nodatabase.test.domain;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private int degree;
	private ArrayList<Integer> coefficients;
	private String[] points;
	private ArrayList<Integer> xvalues;
	
	public Game(int d) {
		this.degree = d;
		this.coefficients = new ArrayList<>();
		this.points = new String[degree+1];
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public ArrayList<Integer> getCoefficients() {
		return coefficients;
	}
	public void generateCoefficients() {
		this.coefficients = randomizedXValues(degree+1, -50, 50);
	}
	public void setPoints() {
		this.xvalues = randomizedXValues(degree+1,-50,50);
		for(int i = 0; i<degree+1;i++) {
			int yvalue=0;
			for(int j=0;j<degree+1;j++) {
				yvalue+=coefficients.get(j) *(int) Math.pow((double)xvalues.get(i),(double)j);
			}
			this.points[i] = "("+xvalues.get(i)+", "+yvalue+")";
		}
	}
	public String[] getPoints() {
		return points;
	}
	private ArrayList<Integer> randomizedXValues(int numOfValues, int min, int max) {
		ArrayList<Integer> ans = new ArrayList<>();
		for(int i=0;i<numOfValues;i++) {
			Integer number = (int) ThreadLocalRandom.current().nextLong(min,max);
			if(!ans.contains(number)&& !number.equals(0))
				ans.add(number);
			else
				while(true) {
					number = (int) ThreadLocalRandom.current().nextLong(min,max);
					if(!ans.contains(number) && !number.equals(0)) {
						ans.add(number);
						break;
					}
				}
		}
		return ans;
	}
}
