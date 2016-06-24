package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.awt.Color;

public class TextFileReader {
	static ArrayList<String> princess = new ArrayList<String>();
	public static void main (String[] args) throws IOException {
		doIt();
	}
	
	public static void doIt() throws IOException {
		readTextFile("src/Text files/Princesses.txt", "Kobold");
	}
	
	public static void readTextFile (String location, String princessName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(location));
		String line = "";
		while ((line = reader.readLine()) != null) {
			if (line.equals("#" + princessName)) {
				princess.add(princessName);
				while(!line.equals("") && (line = reader.readLine()) != null) {
					princess.add(line);
				}
			}
		}
		
		buildPrincess(princess);
	}
	
	public static void buildPrincess (ArrayList princessObject) {

		int princessColor = Integer.parseInt(princess.get(2));
		Princess1 kobold = new Princess1(princess.get(0), princess.get(1), princessColor, princess.get(3), princess.get(princess.size()-1));
		
		Item dowry1 = new Item(princess.get(16),princess.get(16));
		Item dowry2 = new Item(princess.get(17),princess.get(17));
		Item lustGift = new Item(princess.get(19),princess.get(19));
		kobold.setGifts(dowry1, dowry2, lustGift);
		
		Preference kink1 = new Preference(princess.get(13),true);
		Preference kink2 = new Preference(princess.get(14),true);
		Preference kink3 = new Preference(princess.get(15),false);
		kobold.setKinks(kink1, kink2, kink3);
		
		kobold.setAttr(princess.get(4),princess.get(5),princess.get(6),princess.get(7),princess.get(8));
		
		kobold.setStats(Integer.parseInt(princess.get(9)), Integer.parseInt(princess.get(10)), Integer.parseInt(princess.get(11)), Integer.parseInt(princess.get(12)));
		
		PrincessCard frame = new PrincessCard(kobold);
		frame.setVisible(true);
	}
}

class Item
{
	public String name;
	public String image;
	
	Item(String n, String i){name = n; image = i;}

	  public String getName() {
		  return name;
	  }
	  
	  public String getImage() {
		  return image;
	  }
}

class Preference
{
  public String name;
  public Boolean good;
  
  Preference(String n, Boolean k) {name = n; good = k;}
  
  public void corrupt() {good = !good;}
  
  public String getName() {
	  return name;
  }
  
  public Boolean likesIt() {
	  return good;
  }
}

class Character extends Item
{
  Character(String n, String i, int c, String k, String d) 
  {
	  super(n,i); 
	  col = c;
	  kingdom = k;
	  description = d;
	  effects = new ArrayList<Effect>();
  }
  
  public int col;
  public String kingdom;
  public String description;
  
  public int love;
  public int lust;
  public int wealth;
  public int power;
  
  public String[] good;
  public String[] bad;
  
  public Preference[] kinks;
  public Preference turnoff;

  public ArrayList<Effect> effects;
  
  public void setStats(int lo, int lu, int we, int po)
  {
	  love = lo;
	  lust = lu;
	  wealth = we;
	  power = po;
  }
  
  public String[] getTitle() {
	  String[] out = new String[]{kingdom,description};
	  return out;
  }
  
  public int getColor()
  {
	  return col;
  }
  
  public int[] getStats()
  {
	  int[] out = new int[]{love, lust, wealth, power};
	  return out;
  }
  
  public void setAttr(String g1, String g2, String g3, String b1, String b2)
  {
	  good = new String[]{g1,g2,g3};
	  bad = new String[]{b1,b2};
  }
  
  public String[] getGood() {return good;}
  public String[] getBad() {return bad;}
  
  public void setKinks(Preference k1, Preference k2, Preference t1)
  {
	  kinks = new Preference[]{k1,k2};
	  turnoff = t1;
  }
  public Preference[] getKinks() {return kinks;}
  public Preference getTurnOff() {return turnoff;}
  public void corrupt() {turnoff.corrupt();}
}



class Princess1 extends Character 
{

  Princess1(String n, String i, int c, String k, String d) {
		super(n, i, c, k, d);
	}
  public Item dowry1;
  public Item dowry2;
  public Item lustGift;
  
  public void setGifts(Item d1, Item d2, Item lg)
  {
	  dowry1=d1;
	  dowry2=d2;
	  lustGift=lg;
  }
  
  public Item[] getGifts(){
  return new Item[]{dowry1,dowry2,lustGift};
  }
}
class Effect
{
	public String image;
	public String description;
}
class Power
{
	public String name;
	
	Power(String n){ name = n;}
}