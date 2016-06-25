package testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFileTesting {
	static ArrayList<String> princess;
	Princess1 kobold;
	
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
		//for(int i=0; i<princess.size(); i++) {
			//System.out.println("'" + princess.get(i) + "'");
		//}
		buildPrincess(princess);
	}
	
	public void buildPrincess (ArrayList<String> princessObject) {

		String princessColor = princess.get(1);
		kobold = new Princess1(princess.get(0), "src/Girls/" + princess.get(0) +  ".png", princessColor, princess.get(2), princess.get(princess.size()-1));
		
		Item dowry1 = new Item(princess.get(15),princess.get(15));
		Item dowry2 = new Item(princess.get(16),princess.get(16));
		Item lustGift = new Item(princess.get(18),princess.get(18));
		kobold.setGifts(dowry1, dowry2, lustGift);
		
		Preference kink1 = new Preference(princess.get(12),true);
		Preference kink2 = new Preference(princess.get(13),true);
		Preference kink3 = new Preference(princess.get(14),false);
		kobold.setKinks(kink1, kink2, kink3);
		
		kobold.setAttr(princess.get(3),princess.get(4),princess.get(5),princess.get(6),princess.get(7));
		
		kobold.setStats(Integer.parseInt(princess.get(8)), Integer.parseInt(princess.get(9)), Integer.parseInt(princess.get(10)), Integer.parseInt(princess.get(11)));
	}


	public Princess1 givePrincess() {
		return kobold;
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
  Character(String n, String i, String c, String k, String d) 
  {
	  super(n,i); 
	  col = c;
	  kingdom = k;
	  description = d;
	  effects = new ArrayList<Effect>();
  }
  
  public String col;
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
  
  public String getColor()
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

  Princess1(String n, String i, String c, String k, String d) {
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
}