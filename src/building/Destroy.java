package building;

import java.util.ArrayList;
import java.util.Random;

import land.Land;

public class Destroy {
	public static void destroy(Land land) {
		//create list of buildings need to be destroyed
		Building[][] newLand= land.getLand();
		ArrayList<Building> toDestroy=new ArrayList<Building>();
		for (int i=0;i<newLand.length;i++) {
			for(int j=0; j<newLand[i].length; j++){
				if (isToBeDestroyed(land, i, j)==true) {
					toDestroy.add(newLand[i][j]);
				}
			}
		}
		//now destroy them
		for (int i=0;i<newLand.length;i++) {
			for(int j=0; j<newLand[i].length; j++){
				if (toDestroy.contains(newLand[i][j])==true) {
					newLand[i][j]=null;
				}
			}
		}
	}

	//check if building is near a ranger station (to ensure it won't be destroyed)
	public static boolean isNearRanger(Land land, int row, int column) {
		Building[][] newLand= land.getLand();
		if(isOnEdge(newLand, row, column)==false) {
			for (int i=row-2;i<row+2;i++) {//2 rows above and 2 below
				for(int j=column-2; j<column+3; j++){//2 columns above and 2 below
					if (newLand[i][j] instanceof RangerStation){ //search for ranger
						return true;
					}
				}
			}
		} 
		return false;
	}
	
	//returns if building is near the edge of the matrix, is an add-on of isNearRanger
	public static boolean isOnEdge(Building[][] newLand, int row, int column) {
		int numRows= newLand.length-1;
		int numCols= newLand.length-1;
		if (row > numRows-2 || column > numCols-2) {
			return true;
		} else if (row <2 || column < 2) {
			return true;
		}
		return false;
	}

	//determines if building on a particular area is chosen to be destroyed
	public static boolean isToBeDestroyed(Land land, int row, int column) {
		Building[][] newLand=land.getLand();
		if (newLand[row][column] instanceof FishingHole) {
			return false;
		}
		if (newLand[row][column] != null && isNearRanger(land, row, column)==false) {
		Random rand = new Random();
		int destroyNum= rand.nextInt(20) + 1; //random integer between 1 and 20
			if (destroyNum==14) { //chose arbitrary number in the range
				return true;
			}
		}
		return false;
	}
	
}
