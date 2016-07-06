package towerpackage;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFileTesting {
	ArrayList<String> princess;
	Princess1 princessBuild;
	
public TextFileTesting(){
	}
	
public void readTextFile (String location, String princessName) throws IOException {
		princess = new ArrayList<String>();
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(location));
		String line = "";
		while ((line = reader.readLine()) != null) {
			if (line.equals("#" + princessName)) {
				princess.add(princessName);
				line = reader.readLine();
				while(line != null && !line.isEmpty() ) {
					princess.add(line);
					line = reader.readLine();
				}
				break;
			}
		}
		buildPrincess(princess);
	}
	
public void buildPrincess (ArrayList<String> princessObject) {

		Color princessColor = Color.decode("#" + princess.get(1));
		princessBuild = new Princess1(princess.get(0), "src/Girls/" + princess.get(0) +  ".png", princessColor, princess.get(2), princess.get(princess.size()-1));
		
		princessBuild.dowry1 = new Item(princess.get(15),princess.get(15));
		princessBuild.dowry2 = new Item(princess.get(16),princess.get(16));
		princessBuild.lustGift = new Item(princess.get(18),princess.get(18));
		
		princessBuild.kinks = (new Preference[]{new Preference(princess.get(12),true), new Preference(princess.get(13),true)});
		princessBuild.turnoff = new Preference(princess.get(14),false);
		
		princessBuild.good = new String[]{princess.get(3),princess.get(4),princess.get(5)};
		princessBuild.bad = new String[]{princess.get(6),princess.get(7)};
		
		princessBuild.love = Integer.parseInt(princess.get(8));
		princessBuild.lust = Integer.parseInt(princess.get(9));
		princessBuild.wealth = Integer.parseInt(princess.get(10));
		princessBuild.power = Integer.parseInt(princess.get(11));
	}


}