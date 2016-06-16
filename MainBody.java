import java.awt.Color;
import java.util.ArrayList;

public class TowerGirls
{
	
	
}

class Item
{
	public String name;
	public String image;
	
	Item(String n, String i){name = n; image = i;}
}

class Preference
{
  public String name;
  public Boolean good;
  
  Preference(String n, Boolean k) {name = n; good = k;}
  
  public void corrupt() {good = !good;}
}

class Character extends Item
{
  Character(String n, String i, Color c, String k, String d) 
  {
	  super(n,i); 
	  col = c;
	  kingdom = k;
	  description = d;
	  effects = new ArrayList<Effect>();
	  }
  
  public Color col;
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

  Princess1(String n, String i, Color c, String k, String d) {
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
