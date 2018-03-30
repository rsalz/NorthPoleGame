package play;

import land.Land;
import building.Building;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import budget.Budget;
import building.FishingHole;
import building.Igloo;
import building.JacketFactory;
import building.RangerStation;
import building.SkatingRink;
import building.ToyFactory;
import building.Destroy;
import population.Metrics;
import population.Population;
import land.CityStatistics;

public class Turn {
	public static void main(String[] args) {
		try
		{
		BufferedReader br1 = new BufferedReader (new InputStreamReader(System.in));
		System.out.println("Welcome to the North Pole.");
		System.out.println("Santa needs some help making a city to produce his toys.");
		System.out.println("How big is your land?");
		System.out.println("Rows:");
		String srowz= br1.readLine();
		while (srowz.trim().length() ==0) {
			System.out.println("Input was empty. Try again.");
			System.out.println("Rows:");
			srowz= br1.readLine();
		}
		int rowz= Integer.parseInt(srowz);
		System.out.println("Columns:");
		String scolumnz= br1.readLine();
		while (scolumnz.trim().length() ==0) {
			System.out.println("Input was empty. Try again.");
			System.out.println("Rows:");
			scolumnz= br1.readLine();
		}
		int columnz= Integer.parseInt(scolumnz);
		if (rowz <1 || columnz<1) {//throw exception if invalid land size
			throw new NullPointerException();
		}
		Building[][] newLand = new Building[rowz][columnz];//initialize matrix
		Land newLand2= new Land(newLand);//initialize land
		Budget budget= new Budget(4500000); //initialize budget
		Population population= new Population(100); // initialize population
		int currentMonth=1; //initialize month
		int choiceNum=0;
		while (choiceNum!=7) {
			Land.printLand(newLand);
			System.out.println("Please choose from the following options:");
			System.out.println("1. Build");
			System.out.println("2. See city statistics");
			System.out.println("3. See population statistics");
			System.out.println("4. See budget");
			System.out.println("5. See specific building");
			System.out.println("6. End turn");
			System.out.println("7. Exit game");
			String choice= br1.readLine();
			while (choice.trim().length() ==0) {//redo until valid choice is chosen
				System.out.println("Invalid choice: try again:");
				choice= br1.readLine();
			}
			choiceNum= Integer.parseInt(choice);
			while (choiceNum <1 || choiceNum >7) {//redo until valid choice is chosen
				System.out.println("Invalid choice: try again:");
				choice= br1.readLine();
				choiceNum=Integer.parseInt(choice);
			}
			switch (choiceNum) {
			case 1: //choice 1= build a building
				System.out.println("Enter the number corresponding to the building you would like to build:");
				System.out.println("1. Igloo");
				System.out.println("2. Fishing hole");
				System.out.println("3. Ice skating rink");
				System.out.println("4. Ranger Station");
				System.out.println("5. Jacket Factory");
				System.out.println("6. Toy Factory");
				String nextchoice= br1.readLine();
				while (nextchoice.trim().length() ==0) {
					System.out.println("Invalid choice: try again:");
					nextchoice= br1.readLine();
				}
				int subchoiceNum=Integer.parseInt(nextchoice);
				while (subchoiceNum <1 || subchoiceNum >6) {//redo until valid choice is chosen
					System.out.println("Invalid choice: try again:");
					nextchoice= br1.readLine();
					subchoiceNum=Integer.parseInt(nextchoice);
				}
				Building toBuild=null; //which building to build based on input
				switch(subchoiceNum) {
					case 1:
						toBuild=new Igloo(currentMonth);
						break;
					case 2:
						toBuild=new FishingHole(currentMonth);
						break;
					case 3:
						toBuild=new SkatingRink(currentMonth);
						break;
					case 4:
						toBuild= new RangerStation(currentMonth);
						break;
					case 5:
						toBuild= new JacketFactory(currentMonth);
						break;
					case 6:
						toBuild= new ToyFactory(currentMonth);
					}
				if (toBuild.cost > budget.getCurrentAmount()) {//check if there is enough in the budget
					System.out.println("You do not have enough in your budget to build that.");
				} else {
				System.out.println("Where would you like to build it?");
				System.out.println("Keep in mind that you must enter the position of the upper left corner of the building");
				System.out.println("Row:");
				String rowEntered= br1.readLine();
				while (rowEntered.trim().length() ==0) {//redo until valid choice is chosen
					System.out.println("Invalid choice: try again:");
					rowEntered= br1.readLine();
				}
				int row= Integer.parseInt(rowEntered);
				System.out.println("Column:");
				String columnEntered= br1.readLine();
				while (columnEntered.trim().length() ==0) {//redo until valid choice is chosen
					System.out.println("Invalid choice: try again:");
					columnEntered= br1.readLine();
				}
				int column= Integer.parseInt(columnEntered);
				while (row>rowz || column>columnz || columnEntered.trim().length() ==0) {
					System.out.println("Chosen position is not within the land. Please try again:");
					System.out.println("Row:");
					rowEntered= br1.readLine();
					row=Integer.parseInt(rowEntered);
					System.out.println("Column:");
					columnEntered= br1.readLine();
					column= Integer.parseInt(columnEntered);
				}
				//make sure chosen building will fit to avoid IndexOutOfBoundsException
				if (toBuild instanceof JacketFactory || toBuild instanceof ToyFactory) {
					if (row==rowz || column==columnz) {
						System.out.println("The type of building you chose will not fit there.");
					} else if (toBuild.isEmptyLand(newLand, toBuild, row-1, column-1)==true) {
						toBuild.build(newLand, toBuild, row-1, column-1, budget); //build the building on the matrix
						newLand2.updateLand(newLand); //update the matrix in the land object
						System.out.println("Success.");
					} else {
						System.out.println("There is something already there");
						subchoiceNum=1;
					}
				} else if (toBuild instanceof RangerStation) {
					if (column==columnz) {
						System.out.println("The type of building you chose will not fit there.");
					} else if (toBuild.isEmptyLand(newLand, toBuild, row-1, column-1)==true) {
						toBuild.build(newLand, toBuild, row-1, column-1, budget); //build the building on the matrix
						newLand2.updateLand(newLand); //update the matrix in the land object
						System.out.println("Success.");
					} else {
						System.out.println("There is something already there");
						subchoiceNum=1;
					}
				} else {
					if (toBuild.isEmptyLand(newLand, toBuild, row-1, column-1)==true) {
						toBuild.build(newLand, toBuild, row-1, column-1, budget); //build the building on the matrix
						newLand2.updateLand(newLand); //update the matrix in the land object
						System.out.println("Success.");
					} else {
						System.out.println("There is something already there");
					}
				}
				}
				//back to menu
				break;
			case 2://option 2= see city statistics
				CityStatistics.printCityStats(newLand2, population);
				break;
			case 3://option 3 see population statistics
				System.out.println(Metrics.popMet(newLand2, population));
				//back to menu
				break;
			case 4://option 4 see budget
				budget.printBudgetStats(newLand2);
				//back to menu
				break;
			case 5://option 5 see individual building info
				System.out.println("Please enter the position of the building");
				System.out.println("Row:");
				String rowEntered2=br1.readLine();
				int row2=Integer.parseInt(rowEntered2);
				System.out.println("Column:");
				String columnEntered2=br1.readLine();
				int column2=Integer.parseInt(columnEntered2);
				row2=row2-1;
				column2=column2-1;
				String buildingToInspect=Building.getBuildingStats(newLand2.getLand(), row2, column2);
				System.out.println(buildingToInspect);
				//back to menu
				break;
			case 6: //option 6 next turn
				//+1 month
				currentMonth=currentMonth+1;
				//+/- people
				population.adjustPopulation(newLand2, population);
				//- buildings
				Destroy.destroy(newLand2);
				//+/- budget
				budget.addIncome(newLand2);
				budget.subtractExpenses(newLand2);
				//restart menu
				break;
			case 7: //option 7 end game
				System.out.println("Game ended.");
				break;
			}
		}
	}
	catch (IOException ioe){
		ioe.printStackTrace();
	} catch (NumberFormatException nfe) {
		System.err.println("Must input a valid number.");
	} catch (IllegalArgumentException iae) {
		System.err.println("Invalid.");
	} catch (NullPointerException npe) {
		System.err.println("Invalid choice.");
	}
	}
}
