package population;

import building.Building;
import land.CityStatistics;
import land.Land;

public class Metrics {
	
	public int totalScore;
	
	public static String hunger(Land land, Population population) {
		//number fishing holes vs population size
		//5 fishing holes per hundred people
		double utilitiesScore=CityStatistics.getUtilites(land, population);
		double hunger= (utilitiesScore/0.05)*5.0;
		//System.out.println("Hunger metric:");
		if (hunger< 3) {
			return "The citizens are really hungry.";
		} else if (hunger >=3 && hunger <5) {
			return "The citizens are somewhat hungry.";
		} else {
			return "Your citizens are well fed.";
		}
	}
	
	public static String fear(Land land) {
		//number Ranger stations vs land area
		//1 ranger station per 25 units of land
		double safetyScore= CityStatistics.getSafety(land);
		double fear= (safetyScore/0.7)*5.0;
		//System.out.println("Fear metric:");
		if (fear< 2.5) {
			return "Your citizens are very afraid.";
		} else if (fear >=2.5 && fear <4.5) {
			return "Your citizens are afraid.";
		} else {
			return "Your citizens feel safe.";
		}
	}
	
	public static String unemployment(Land land, Population population) {
		//factories vs population size
		//2 factories per 100 people
		double industry= CityStatistics.getIndustry(land, population);
		double unemployment= (industry/0.02)*5.0;
		//System.out.println("Unemployment metric:");
		if (unemployment< 2) {
			return "Many citizens are unemployed.";
		} else if (unemployment >=2 && unemployment <4) {
			return "Some citizens are unemployed.";
		} else {
			return "Your citizens are employed.";
		}
	}
	
	public static String boredom(Land land, Population population) {
		//rinks vs population size
		//1 rink per 100 people
		double leisure= CityStatistics.getLeisure(land, population);
		double boredom= (leisure/0.01)*5.0;
		//System.out.println("Boredom metric:");
		if (boredom< 2.5) {
			return "The citizens are really bored.";
		} else if (boredom >=2.5 && boredom <4.5) {
			return "The citizens are somewhat bored.";
		} else {
			return "Your citizens are entertained.";
		}
	}
	
	public static String cold(Land land, Population population) {
		//jacket factories vs population size
		//1 jacket factory per 100 people
		int numPeople=population.getPopulation();
		Building[][] matrix= land.getLand();
		int jackets=Building.getBuildingCount(matrix, "Jacket Factory");
		double cold= ((double) jackets/numPeople)/0.01;
		cold=cold*5.0;
		//System.out.println("Cold metric:");
		if (cold< 2.5) {
			return "The citizens are really cold.";
		} else if (cold >=2.5 && cold <4.5) {
			return "The citizens are somewhat cold.";
		} else {
			return "Your citizens are warm.";
		}
	}
	
	public static String popMet(Land land, Population population) {
		String sentence1= hunger(land, population);
		String sentence2= fear(land);
		String sentence3= cold(land, population);
		String sentence4= boredom(land, population);
		String sentence5= unemployment(land, population);
		String sentence6= "The current population is "+ population.getPopulation() + ". \n";
		return sentence6+sentence1+ "\n" +sentence2+"\n"+sentence3+"\n"+sentence4+"\n"+sentence5;
	}
	
}
