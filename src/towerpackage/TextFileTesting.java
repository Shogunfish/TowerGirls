package towerpackage;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileTesting {
	
public TextFileTesting(){
	}
	
public Princess1 readTextFile (String textLocation, String princessName) throws IOException {	
	ArrayList<String> princess = new ArrayList<String>();
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(textLocation));
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
		//Build princess from arraylist
		
		Color princessColor = Color.decode("#" + princess.get(1));
		Princess1 princessBuild = new Princess1(princess.get(0), "src/Girls/" + princess.get(0) +  ".png", princessColor, princess.get(2), princess.get(princess.size()-1));
		princessBuild.dowry1 = new Item(regexChecker(princess.get(15))[0].substring(0, regexChecker(princess.get(15))[0].length()-1), "src/Dowries/" + (regexChecker(princess.get(15))[0].substring(0, regexChecker(princess.get(15))[0].length()-1)) + ".png", regexChecker(princess.get(15))[1].substring(1, regexChecker(princess.get(15))[1].length()));
		princessBuild.dowry2 = new Item(regexChecker(princess.get(16))[0].substring(0, regexChecker(princess.get(16))[0].length()-1), "src/Dowries/" + (regexChecker(princess.get(16))[0].substring(0, regexChecker(princess.get(16))[0].length()-1)) + ".png", regexChecker(princess.get(16))[1].substring(1, regexChecker(princess.get(16))[1].length()));
		princessBuild.lustGift = new Item(regexChecker(princess.get(18))[0].substring(0, regexChecker(princess.get(18))[0].length()-1), "src/Lust items/" + (regexChecker(princess.get(18))[0].substring(0, regexChecker(princess.get(18))[0].length()-1)) + ".png", regexChecker(princess.get(18))[1].substring(1, regexChecker(princess.get(18))[1].length()));
		
		princessBuild.kinks = (new Preference[]{new Preference(princess.get(12),true), new Preference(princess.get(13),true)});
		princessBuild.turnoff = new Preference(princess.get(14),false);
		
		princessBuild.good = new String[]{princess.get(3),princess.get(4),princess.get(5)};
		princessBuild.bad = new String[]{princess.get(6),princess.get(7)};
		
		princessBuild.love = Integer.parseInt(princess.get(8));
		princessBuild.lust = Integer.parseInt(princess.get(9));
		princessBuild.wealth = Integer.parseInt(princess.get(10));
		princessBuild.power = Integer.parseInt(princess.get(11));
		
		return princessBuild;
	}

	String[] regexChecker(String str) {
		Pattern regex = Pattern.compile("^[^\\-]*");
	    Matcher regexMatcher = regex.matcher(str);
	    if (regexMatcher.find()) {
	    }
	    regex = Pattern.compile("-(.*)");
	    Matcher regexMatcher2 = regex.matcher(str);
	    if (regexMatcher2.find()) {
	    }
	    
	    String[] regexResult = new String[]{regexMatcher.group(0),regexMatcher2.group(1)};
		return regexResult;
	}


}