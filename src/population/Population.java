package population;

import land.CityStatistics;
import land.Land;

public class Population {
	
	public int currentPopulation;
	
	public Population(int currentPopulation) {
		this.setPopulation(currentPopulation);
	}
	
	public void setPopulation(int population) {
		this.currentPopulation= population;
	}
	
	public int getPopulation() {
		return this.currentPopulation;
	}

	public void adjustPopulation(Land land, Population population) {
		//score based on CityStatistics
		double score= CityStatistics.getCityScore(land, population);
		//set equilibrium at ~.8
		double ratio= score/15.0;
		int newPopulation=population.currentPopulation;
		if (ratio < 0.3) {
			newPopulation=(int) Math.round(newPopulation*0.9);
		} else if (ratio >= 0.3 && ratio < 0.5) {
			newPopulation=(int) Math.round(newPopulation*0.95);
		} else if (ratio >= 0.5 && ratio < 0.8) {
			newPopulation=(int) Math.round(newPopulation*0.99);
		} else if (ratio >= 0.8 && ratio < 1) {
			newPopulation=(int) Math.round(newPopulation*1.03);
		} else {
			newPopulation=(int) Math.round(newPopulation*1.10);
		}
		setPopulation(newPopulation);
	}

}
