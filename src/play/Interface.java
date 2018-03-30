package play;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import budget.Budget;
import building.Building;
import building.Destroy;
import building.FishingHole;
import building.Igloo;
import building.JacketFactory;
import building.RangerStation;
import building.SkatingRink;
import building.ToyFactory;
import land.CityStatistics;
import land.Land;
import population.Metrics;
import population.Population;

public class Interface extends JFrame implements ActionListener {
	JPanel panel = new JPanel();
	private final String CBUILD= "build";
	private final String CBUDGET= "budget";
	private final String POP_STATS= "pop stats";
	private final String CITY_STATS= "city stats";
	private final String END_TURN= "end turn";
	JTextField rowtb= new JTextField("Row");
	JTextField coltb= new JTextField("Column");
	Map<JButton, String> buildings= new HashMap<>();
	Building[][] newLand = new Building[10][10];
	Land newLand2= new Land(newLand);
	Budget budget= new Budget(4500000);
	Population population= new Population(100);
	int currentMonth=1;
	 Building toBuild=null;
	
	
	public Interface() {
		JFrame frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("North Pole");
		

		panel.setLayout(new GridLayout(10,10));
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(500,500));
		for (int i=0;i<newLand2.getLand().length;i++) {//for each row
			for(int j=0; j<newLand2.getLand()[i].length; j++){
				JButton building= new JButton(Land.appropriateType(newLand2.getLand()[i][j])+ " ");
				String buildingInfo= Building.getBuildingStats(newLand2.getLand(), i, j);
				buildings.put(building, buildingInfo);
				panel.add(building);
			}
		}
		List<JButton> keys = new ArrayList<JButton>(buildings.keySet());
		for (int i = 0; i < keys.size(); i++) {
		    JButton buildingButton = keys.get(i);
		    buildingButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	JOptionPane.showMessageDialog(null, buildings.get(buildingButton), "Building info", JOptionPane.PLAIN_MESSAGE);
					//panel.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		        }});
		}
		
		JPanel gameChoices= new JPanel();
		gameChoices.setBackground(Color.white);
		//gameChoices.setPreferredSize(new Dimension(400, 500));
		String[] types= new String[]{ "Choose type", "Igloo","Ranger Station", "Fishing Hole", "Skating Rink", "Jacket Factory", "Toy Factory"	};
		JComboBox<String> buildType= new JComboBox<String>(types);
		buildType.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 JComboBox<String> buildType = (JComboBox<String>)e.getSource();
				 String typeToBuild= (String)buildType.getSelectedItem();
				 switch(typeToBuild) {
					case "Igloo":
						toBuild=new Igloo(currentMonth);
						break;
					case "Fishing Hole":
						toBuild=new FishingHole(currentMonth);
						break;
					case "Skating Rink":
						toBuild=new SkatingRink(currentMonth);
						break;
					case "Ranger Station":
						toBuild= new RangerStation(currentMonth);
						break;
					case "Jacket Factory":
						toBuild= new JacketFactory(currentMonth);
						break;
					case "Toy Factory":
						toBuild= new ToyFactory(currentMonth);
						break;
					}
	         }
	         });
		rowtb.addActionListener(this);
		coltb.addActionListener(this);
		JButton build=new JButton("Build");
		build.setActionCommand(CBUILD);
		gameChoices.add(rowtb);
		gameChoices.add(coltb);
		gameChoices.add(buildType);
		gameChoices.add(build);
		
		JPanel everythingElse= new JPanel();
		everythingElse.setBackground(Color.white);
		everythingElse.setLayout(new BoxLayout(everythingElse, BoxLayout.Y_AXIS));
		JButton getBudget= new JButton("See budget");
		getBudget.setActionCommand(CBUDGET);
		JButton getCityStats= new JButton("See city statistics");
		getCityStats.setActionCommand(CITY_STATS);
		JButton getPopStats= new JButton("See population statistics");
		getPopStats.setActionCommand(POP_STATS);
		JButton endTurn= new JButton("End turn");
		endTurn.setActionCommand(END_TURN);
		build.addActionListener(this);
		getBudget.addActionListener(this);
		getCityStats.addActionListener(this);
		getPopStats.addActionListener(this);
		endTurn.addActionListener(this);
		everythingElse.add(getBudget);
		everythingElse.add(getCityStats);
		everythingElse.add(getPopStats);
		everythingElse.add(endTurn);

		frame.getContentPane().add(gameChoices, BorderLayout.NORTH);
		frame.getContentPane().add(everythingElse, BorderLayout.SOUTH);
		
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		String action = ev.getActionCommand(); 
		 switch (action) {
		 case CBUILD:
			 try {
				 int rowtb1= Integer.parseInt(rowtb.getText());
				 int coltb1= Integer.parseInt(coltb.getText());
				 toBuild.build(newLand, toBuild, rowtb1, coltb1, budget);
				 newLand2.updateLand(newLand);
				 for (int i=0;i<newLand2.getLand().length;i++) {//for each row
						for(int j=0; j<newLand2.getLand()[i].length; j++){
							JButton building= new JButton(Land.appropriateType(newLand2.getLand()[i][j])+ " ");
							String buildingInfo= Building.getBuildingStats(newLand2.getLand(), i, j);
							buildings.put(building, buildingInfo);
							panel.add(building);
						}
					}
			 } catch (IllegalArgumentException iae) {
				 
			 }
			 break;
		 case CBUDGET:
			 JOptionPane.showMessageDialog(null, "Your current budget is " + budget.getCurrentAmount() + ". Your current income is: "+ budget.getIncome(newLand2) + ". Your current expenditures are: "+ budget.getExpenses(newLand2), "Budget", JOptionPane.PLAIN_MESSAGE);
			 break;
		 case CITY_STATS:
			 JOptionPane.showMessageDialog(null, CityStatistics.returnCityStats(newLand2, population), "City statistics", JOptionPane.PLAIN_MESSAGE);
			 break;
		 case POP_STATS:
			 JOptionPane.showMessageDialog(null, Metrics.popMet(newLand2, population), "Population statistics", JOptionPane.PLAIN_MESSAGE);
			 break;
		 case END_TURN:
				currentMonth=currentMonth+1;
				population.adjustPopulation(newLand2, population);
				Destroy.destroy(newLand2);
				budget.addIncome(newLand2);
				budget.subtractExpenses(newLand2);
		 }

	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			   public void run() {
			    new Interface();
			   }
		});
	}
}
