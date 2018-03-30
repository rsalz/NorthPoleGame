package land;

import building.Building;
import population.Population;

public class CityStatistics {
	
	//Safety- ranger stations per area
	public static double getSafety(Land land){
		Building[][] matrix= land.getLand();
		int rangerStations=Building.getBuildingCount(matrix, "Ranger Station");
		int landSpace=(matrix[0].length)*(matrix[1].length);
		double space=(double)landSpace/25;
		double safetyMeasure=(double) rangerStations/space; // min 0.7
		return safetyMeasure;
	}
	//Industry- factories per capita
	public static double getIndustry(Land land, Population population){
		int numPeople=population.getPopulation();
		int toyFactories=Building.getBuildingCount(land.getLand(), "Toy Factory");
		int jacketFactories=Building.getBuildingCount(land.getLand(), "Jacket Factory");
		int allFactories=toyFactories+jacketFactories;
		double industry=(double) allFactories/numPeople;// min 0.02
		return industry;
		
	}
	//Leisure= skating rinks per capita
	public static double getLeisure(Land land, Population population) {
		int numPeople=population.getPopulation();
		int skatingRinks=Building.getBuildingCount(land.getLand(), "Skating Rink");
		double leisure=(double) skatingRinks/numPeople; //min 0.01
		return leisure;
	}
	//Utilities= fishing (fish supplies all needed food and supplies)
	public static double getUtilites(Land land, Population population) {
		int numPeople=population.getPopulation();
		int fishingHoles=Building.getBuildingCount(land.getLand(), "Fishing Hole");
		double utilities= (double) fishingHoles/numPeople;//min 0.05
		return utilities;
	}
	//Housing opportunity
	public static double getHousing(Land land, Population population) {
		int numPeople=population.getPopulation();
		int igloos=Building.getBuildingCount(land.getLand(), "Igloo");
		double housing= (double) igloos/numPeople;//should be minimum 0.2
		return housing;
	}
	
	public static double getCityScore(Land land, Population population) {
		//scores based on what i believed should be the minimum amount of each type of building per capita/land
		double safety=(getSafety(land)/0.7)*3.0;
		double industry=(getIndustry(land, population)/0.02)*1.0;
		double leisure=(getLeisure(land,population)/0.01)*2.0;
		double utilities=(getUtilites(land,population)/0.05)*4.0;
		double housing=(getHousing(land,population)/0.2)*5.0;
		double score=safety+industry+leisure+utilities+housing;
		return score; //if all proportions are right, will end up with 15. used for "adjustPopulation" in population class
	}
	
	//for the console version
	public static void printCityStats(Land land, Population population) {
		System.out.println("The city has a safety score of "+ getSafety(land)+" (Ranger stations/unit land)");
		System.out.println("The city has an industry score of "+ getIndustry(land,population)+" (factories per capita)");
		System.out.println("The city has a leisure score of "+ getLeisure(land, population)+" (rinks per capita)");
		System.out.println("The city has a utilities score of "+ getUtilites(land, population)+" (fishing holes per capita)");
		System.out.println("The city has a housing score of "+ getHousing(land, population)+" (igloos per capita)");
		System.out.println("Your total score is "+ getCityScore(land,population)+" out of 15.");
	}
	
	//for the GUI version
	public static String returnCityStats(Land land, Population population) {
		String sentence1 =("The city has a safety score of "+ getSafety(land)+" (Ranger stations/unit land). \n");
		String sentence2 =("The city has an industry score of "+ getIndustry(land,population)+" (factories per capita). \n");
		String sentence3=("The city has a leisure score of "+ getLeisure(land, population)+" (rinks per capita). \n");
		String sentence4=("The city has a utilities score of "+ getUtilites(land, population)+" (fishing holes per capita). \n");
		String sentence5= ("The city has a housing score of "+ getHousing(land, population)+" (igloos per capita). \n");
		String sentence6= ("Your total score is "+ getCityScore(land,population)+" out of 15.");
		return sentence1+ sentence2+ sentence3+sentence4+sentence5+sentence6;
	}
}
