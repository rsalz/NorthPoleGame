package land;

import building.Building;
import building.FishingHole;
import building.Igloo;
import building.JacketFactory;
import building.RangerStation;
import building.SkatingRink;
import building.ToyFactory;

public class Land {
	Building[][] land;
	public Building[][] newVersion;
	static Building empty;
	
	public Land(Building[][] land) {
		this.land=land;
	}
	
	public void updateLand(Building[][] land) {
		this.land=land;
	}
	
	public Building[][] getLand() {
		return this.land;
	}
	
	public static void printLand(Building[][] land) {
		for (int i=0;i<land.length;i++) {//for each row
			for(int j=0; j<land[i].length; j++){
				System.out.print(appropriateType(land[i][j])+ " ");
			}
			System.out.println();
		}
	}
	
	public static String appropriateType(Building b) {
		if (b instanceof Igloo) {
			return "IG";
		}
		if (b instanceof JacketFactory) {
			return "JF";
		}
		if (b instanceof RangerStation) {
			return "RS";
		}
		if (b instanceof FishingHole) {
			return "FH";
		}
		if (b instanceof SkatingRink) {
			return "SR";
		}
		if (b instanceof ToyFactory) {
			return "TF";
		}
		return "__";
	}
	
}
