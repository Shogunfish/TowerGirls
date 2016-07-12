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
	
public Princess1 readPrincess1 (String textLocation, String princessName) throws IOException {	
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
		
		Color princessColor = Color.decode("#" + princess.get(1));
		Princess1 princessBuild = new Princess1(princess.get(0), "src/Girls/" + princess.get(0) +  ".png", princessColor, princess.get(2), princess.get(princess.size()-1));
		princessBuild.dowry1 = new Item(hyphenRegex(princess.get(15))[0], "src/Dowries/" + hyphenRegex(princess.get(15))[0] + ".png", hyphenRegex(princess.get(15))[1]);
		princessBuild.dowry2 = new Item(hyphenRegex(princess.get(16))[0], "src/Dowries/" + hyphenRegex(princess.get(16))[0] + ".png", hyphenRegex(princess.get(16))[1]);
		princessBuild.lustGift = new Item(hyphenRegex(princess.get(17))[0], "src/Lust items/" + hyphenRegex(princess.get(17))[0] + ".png", hyphenRegex(princess.get(17))[1]);
		
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

public Princess2 readPrincess2 (String textLocation, String princessName) throws IOException {	
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
		
		
		Color princessColor = Color.decode("#" + princess.get(1));
		Princess2 princessBuild = new Princess2(princess.get(0), "src/Girls/" + princess.get(0) +  ".png", princessColor, princess.get(2), princess.get(princess.size()-1));
		
		princessBuild.totem = new Totem(princess.get(15), "src/Totems/" + commaRegex(princess.get(15))[0] +  ".png", princess.get(16), princess.get(17));
		princessBuild.lustGift = new Item(hyphenRegex(princess.get(18))[0], "src/Lust items/" + hyphenRegex(princess.get(18))[0] + ".png", hyphenRegex(princess.get(18))[1]);
		
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

	String[] hyphenRegex(String str) {
		Pattern regex = Pattern.compile("^.*?(?= - )");
	    Matcher regexMatcher = regex.matcher(str);
	    if (regexMatcher.find()) {
	    }
	    regex = Pattern.compile(" - (.*)");
	    Matcher regexMatcher2 = regex.matcher(str);
	    if (regexMatcher2.find()) {
	    }
	    String[] regexResult = new String[]{regexMatcher.group(0),regexMatcher2.group(1)};
		return regexResult;
	}
	
	String[] commaRegex(String str) {
		Pattern regex = Pattern.compile("^(.*),");
	    Matcher regexMatcher = regex.matcher(str);
	    if (regexMatcher.find()) {
	    }
	    regex = Pattern.compile(", (.*)");
	    Matcher regexMatcher2 = regex.matcher(str);
	    if (regexMatcher2.find()) {
	    }
	    String[] regexResult = new String[]{regexMatcher.group(1),regexMatcher2.group(1)};
		return regexResult;
	}


}