package towerpackage;
import java.awt.Color;
import java.util.ArrayList;

class Item {
		public String name;
		public String image;
		
		Item(String n, String i){name = n; image = i;}
	}

class Preference {
	  public String name;
	  public Boolean good;
	  
	  Preference(String n, Boolean k) {name = n; good = k;}
	  
	//  public void corrupt() {good = !good;}
	  
	//  public Boolean likesIt() {
//		  return good;
	//  }
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
	  
	//  public void corrupt() {turnoff.corrupt();}
	}

	class Princess1 extends Character {

	  
	  public Item dowry1;
	  public Item dowry2;
	  public Item lustGift;
	  
	  Princess1(String n, String i, Color princessColor, String k, String d) {
			super(n, i, princessColor, k, d);
	}}

	//This class is the wagon, it has an array of objects, an arraylist of effects, and a name
class Wagon 
	{
		public String name;
		public Item[] spaces;
		public ArrayList<Effect> effects;
		
		public Wagon(int slots, String n)
		{ 
			spaces = new Item[slots];
			name = n;
			effects = new ArrayList<Effect>();
		}

		public void add(Item item)
		{
			for(int i = 0; i<= spaces.length; i++)
			{
				if(spaces[i]==null)
				{
					spaces[i]=item;
					i=spaces.length;
				}
			}
		}
		public void add(Effect effect)
		{
			effects.add(effect);
		}
		
		public void removeEffect(String name)
		{
			for(Effect effect : effects)
			{
				if(effect.name.equals(name))
				{
					effect=null;
				}
			}
		}
		
		public void removeItem(String name)
		{
			for(Item item : spaces)
			{
				if(item.name.equals(name))
				{
					item=null;
				}
			}
		}
	}

	//For things that don't take up space but have effects
class Effect 
	{
		public String name;
		public String image;
		public String description;
	}
