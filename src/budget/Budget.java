package budget;

import java.util.ArrayList;

import building.Building;
import land.Land;

public class Budget {

	int currentAmount; //this will be the current amount in the budget
	
	public Budget(int currentAmount) {
		this.setCurrentAmount(currentAmount);
	}
	
	public void setCurrentAmount(int amount) {
		this.currentAmount=amount;
	}
	
	public int getCurrentAmount() {
		return this.currentAmount;
	}
	
	public int getIncome(Land newLand) { //retrieve tax income from all the buildings
		Building[][] land= newLand.getLand(); //see current matrix
		ArrayList<Building> incomeBuilding= new ArrayList<>();//to avoid recount
		int income=0;
		for (int i=0;i<land.length;i++) {
			for(int j=0; j<land[i].length; j++){
				if (land[i][j]!= null && !incomeBuilding.contains(land[i][j])) {
					incomeBuilding.add(land[i][j]); //make sure not to recount
					income=income+land[i][j].getTaxIncome();
				}
			}
		}
		return income;
	}
	
	public void addIncome(Land land) { //change the budget with additions
		int income=getIncome(land);
		int budget= getCurrentAmount();
		budget=budget+income;
		setCurrentAmount(budget);
	}
	
	public int getExpenses(Land newLand) {//retrieve expenses from all the buildings
		Building[][] land= newLand.getLand();
		ArrayList<Building> expenseBuilding= new ArrayList<>();
		int expenses=0;
		for (int i=0;i<land.length;i++) {
			for(int j=0; j<land[i].length; j++){
				if (land[i][j]!= null && !expenseBuilding.contains(land[i][j])) {
					expenseBuilding.add(land[i][j]);
					expenses=expenses+land[i][j].getUpkeep();
				}
			}
		}
		return expenses;
	}
	
	public void subtractExpenses(Land land) {// change the budget with subtractions
		int expenses=getExpenses(land);
		int budget= getCurrentAmount();
		budget=budget-expenses;
		setCurrentAmount(budget);
	}
	
	public void printBudgetStats(Land land) {
		System.out.println("Your current budget is: "+ getCurrentAmount());
		System.out.println("Your current income is: "+ getIncome(land));
		System.out.println("Your current expenditures are: "+ getExpenses(land));
	}
}
