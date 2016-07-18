package towerpackage;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileReader {
	
public TextFileReader(){
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
		princessBuild.dowry1 = new Item(hyphenRegex(princess.get(15))[0], "src/Dowries/" + hyphenRegex(princess.get(15))[0] + ".png", hyphenRegex(princess.get(15))[1],Integer.parseInt(hyphenRegex(princess.get(15))[2]));
		princessBuild.dowry2 = new Item(hyphenRegex(princess.get(16))[0], "src/Dowries/" + hyphenRegex(princess.get(16))[0] + ".png", hyphenRegex(princess.get(16))[1],Integer.parseInt(hyphenRegex(princess.get(16))[2]));
		princessBuild.lustGift = new Item(hyphenRegex(princess.get(17))[0], "src/Lust items/" + hyphenRegex(princess.get(17))[0] + ".png", hyphenRegex(princess.get(17))[1],Integer.parseInt(hyphenRegex(princess.get(17))[2]));
		
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
		
		princessBuild.totem = new Totem(princess.get(15), "src/Totems/" + commaRegex(princess.get(15))[0] +  ".png", hyphenRegex(princess.get(16))[0], hyphenRegex(princess.get(17))[0],Integer.parseInt(hyphenRegex(princess.get(16))[1]),Integer.parseInt(hyphenRegex(princess.get(17))[1]));
		princessBuild.lustGift = new Item(hyphenRegex(princess.get(18))[0], "src/Lust items/" + hyphenRegex(princess.get(18))[0] + ".png", hyphenRegex(princess.get(18))[1],Integer.parseInt(hyphenRegex(princess.get(18))[2]));
		
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

public Princess3 readPrincess3 (String textLocation, String princessName) throws IOException {	
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
		Princess3 princessBuild = new Princess3(princess.get(0), "src/Girls/" + princess.get(0) +  ".png", princessColor, princess.get(2), princess.get(princess.size()-1));

		princessBuild.companion = new Character(hyphenRegex(princess.get(15))[0], "src/Companions/" + hyphenRegex(princess.get(15))[0] + ".png", princessColor, princess.get(2), hyphenRegex(princess.get(15))[1]);
		princessBuild.companion.kinks = new Preference[]{new Preference(hyphenRegex(princess.get(15))[2],true),new Preference(hyphenRegex(princess.get(15))[3],true)};
		princessBuild.companion.turnoff = new Preference(hyphenRegex(princess.get(15))[4],false);
		
		princessBuild.wealthGift = new Effect(princess.get(0) + " Wealth", "src/Effect icons/" + princess.get(0) +  ".png", Integer.parseInt(hyphenRegex(princess.get(16))[1]));
		princessBuild.wealthGift.description=hyphenRegex(princess.get(16))[0];
				
		princessBuild.powerGift = new Effect(princess.get(0) + " Power", "src/Effect icons/" + princess.get(0) +  ".png",Integer.parseInt(hyphenRegex(princess.get(17))[1]));
		princessBuild.powerGift.description=hyphenRegex(princess.get(17))[0];
		
		princessBuild.lustGift = new Item(hyphenRegex(princess.get(18))[0], "src/Lust items/" + hyphenRegex(princess.get(18))[0] + ".png",hyphenRegex(princess.get(18))[1] ,Integer.parseInt(hyphenRegex(princess.get(18))[2]));
		
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

public Factionless readFactionless(String textLocation, String companionName) throws IOException
{
	ArrayList<String> companion = new ArrayList<String>();
	@SuppressWarnings("resource")
	BufferedReader reader = new BufferedReader(new FileReader(textLocation));
	String line = "";
	while ((line = reader.readLine()) != null) {
		if (line.equals("#" + companionName)) {
			companion.add(companionName);
			line = reader.readLine();
			while(line != null && !line.isEmpty() ) {
				companion.add(line);
				line = reader.readLine();
			}
			break;
		}
	}
	
	Color companionColor = Color.decode("#" + companion.get(1));
	
	Factionless companionBuild = new Factionless(companion.get(0), "src/Companions/" + companion.get(0) +  ".png", companionColor, companion.get(2), companion.get(companion.size()-1));
	
	companionBuild.type = companion.get(0).split(" ")[1];
	
	companionBuild.kinks = (new Preference[]{new Preference(companion.get(12),true), new Preference(companion.get(13),true)});
	companionBuild.turnoff = new Preference(companion.get(14),false);
	
	companionBuild.good = new String[]{companion.get(3),companion.get(4),companion.get(5)};
	companionBuild.bad = new String[]{companion.get(6),companion.get(7)};
	
	companionBuild.love = Integer.parseInt(companion.get(8));
	companionBuild.lust = Integer.parseInt(companion.get(9));
	companionBuild.wealth = Integer.parseInt(companion.get(10));
	companionBuild.power = Integer.parseInt(companion.get(11));

	if(!companion.get(15).equals("Nothing")){
	companionBuild.effect1 = new Effect(hyphenRegex(companion.get(15))[0], "src/Effect Icons/" + companion.get(0) +  ".png", Integer.parseInt(hyphenRegex(companion.get(15))[2]));
	companionBuild.effect1.description=hyphenRegex(companion.get(15))[1];
	}
	if(!companion.get(16).equals("Nothing")){
	companionBuild.effect2 = new Effect(hyphenRegex(companion.get(16))[0], "src/Effect Icons/" + companion.get(0) +  ".png",Integer.parseInt(hyphenRegex(companion.get(16))[2]));
	companionBuild.effect2.description=hyphenRegex(companion.get(16))[1];
	}
	companionBuild.kingdomMod = new Effect(hyphenRegex(companion.get(17))[0], "src/Effect Icons/" + companion.get(0) +  ".png",Integer.parseInt(hyphenRegex(companion.get(17))[2]));
	companionBuild.kingdomMod.description=hyphenRegex(companion.get(17))[1];
	
	return companionBuild;
	
}

	String[] hyphenRegex(String str) {
//		Pattern regex = Pattern.compile("^.*?(?= - )");
//	    Matcher regexMatcher = regex.matcher(str);
//	    if (regexMatcher.find()) {
//	    }
//	    regex = Pattern.compile(" - (.*)");
//	    Matcher regexMatcher2 = regex.matcher(str);
//	    if (regexMatcher2.find()) {
//	    }
//	    String[] regexResult = new String[]{regexMatcher.group(0),regexMatcher2.group(1)};
		
		return str.split(" - ");
		
		
		//return regexResult;
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