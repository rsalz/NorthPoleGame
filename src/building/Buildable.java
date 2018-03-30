package building;

import budget.Budget;

public interface Buildable {
	public void build(Building[][] newLand, Building b, int row, int column, Budget budget);
	public boolean isEmptyLand(Building[][] newLand, Building b, int row, int column);
}
