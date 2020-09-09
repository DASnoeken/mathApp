package nodatabase.test.domain;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private long degree;
	private ArrayList<Long> coefficients;
	private ArrayList<String> polongs;
	private ArrayList<Long> xvalues;
	
	public Game(long d) {
		this.degree = d;
		this.coefficients = new ArrayList<>();
		this.polongs = new ArrayList<>();
	}
	public long getDegree() {
		return degree;
	}
	public void setDegree(long degree) {
		this.degree = degree;
	}
	public ArrayList<Long> getCoefficients() {
		return coefficients;
	}
	public void generateCoefficients() {
		this.coefficients = randomizedXValues(degree+1, -50, 50);
	}
	public void setPoints() {
		this.xvalues = randomizedXValues(degree+1,-50,50);
		for(int i = 0; i<degree+1;i++) {
			long yvalue=0;
			for(int j=0;j<degree+1;j++) {
				yvalue+=coefficients.get(j) * (long) Math.pow((double)xvalues.get(i),(double)j);
			}
			this.polongs.add("("+xvalues.get(i)+", "+yvalue+")");
		}
	}
	public ArrayList<String> getPoints() {
		return polongs;
	}
	private ArrayList<Long> randomizedXValues(long numOfValues, long min, long max) {
		ArrayList<Long> ans = new ArrayList<>();
		for(long i=0;i<numOfValues;i++) {
			Long number = ThreadLocalRandom.current().nextLong(min,max);
			if(!ans.contains(number)&& !number.equals(0l))
				ans.add(number);
			else
				while(true) {
					number = ThreadLocalRandom.current().nextLong(min,max);
					if(!ans.contains(number) && !number.equals(0l)) {
						ans.add(number);
						break;
					}
				}
		}
		return ans;
	}
}
