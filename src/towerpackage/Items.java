package towerpackage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

	class Item {
		public String name;
		public String image;
		public String description;
		public boolean useable;
		public boolean removable;
		public static List<String> useableItems = Arrays.asList("Encrusted Chest", "Amethyst Gossamer");
		
		Item(String n, String i, String d) {
			name = n; 
			image = i; 
			description = d; 
			removable=true;
			if(useableItems.contains(n)) {
				useable=true;
			}
			else {
				useable=false;
			}
		}
		
	
		/*
		 * This blank method will eventually be used when effects are applied to characters at the end of the game, whenever an item 
		 * has an effect that applies to only certain princesses this will choose targets and apply them
		 */
		public void apply() {
			
		}
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

	class Character extends Item {
	  Character(String n, String i, Color c, String k, String d) 
	  {
		  super(n,i,d); 
		  col = c;
		  kingdom = k;
		  effects = new ArrayList<Effect>();
	  }
	  
	  public Color col;
	  public String kingdom;
	  
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

class Princess1 extends Character 
	{

	  
	public Item dowry1;
	public Item dowry2;
	public Item lustGift;
	  
		Princess1(String n, String i, Color princessColor, String k, String d) 
		{
		super(n, i, princessColor, k, d);
		}
	}

class Princess2 extends Character
	{
	public Totem totem;
	public Item lustGift;

	Princess2(String n, String i, Color c, String k, String d) 
	{
		super(n, i, c, k, d);
	}
	
	}

class Princess3 extends Character
	{
	
	public Character companion;
	public Effect wealthGift;
	public Effect powerGift;
	public Item lustGift;

	Princess3(String n, String i, Color c, String k, String d) 
	{
		super(n, i, c, k, d);
	}
	
	}

	//This class is the wagon, it has an array of objects, an arraylist of effects, and a name
class Wagon 
	{
		public String name;
		public Item[] spaces;
		public ArrayList<Effect> effects;
		public boolean huntress;
		
		public Wagon(int slots, String n)
		{ 
			spaces = new Item[slots+1];
			name = n;
			effects = new ArrayList<Effect>();
			huntress = false;
		}

		public boolean add(Item item)
		{
			
			//If you have added the Huntress and add another princess you may no longer remove Huntress
			if(huntress)
			{
				for(int i = 0; i<spaces.length-1; i++)
				{
					if(spaces[i].name.equals("Huntress Master"))
					{
						spaces[i].removable=false;
					}
				}
			}
			
			//If you are adding Huntress her effect activates
			if (item.name.equals("Huntress Master"))
			{
				huntress=true;
			}
			boolean succeed = false;
			for(int i = 0; i<= spaces.length-1; i++)
			{
				boolean isPrincess = (item instanceof Princess1) || (item instanceof Princess2) || (item instanceof Princess3);
				if(spaces[i]==null && (!isPrincess || i!=0))
				{
					spaces[i]=item;
					succeed=true;
					i=spaces.length;
				}
			}
			return succeed;
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
			for(int i=0; i<spaces.length; i++)
			{
				if(spaces[i] != null && spaces[i].removable && spaces[i].name.equals(name))
				{
					
					//if you are removing Huntress her effect deactivates
					if(name.equals("Huntress Master"))
					{
					huntress = false;
					}
					spaces[i]=null;
				}
			}
		}
		public int[] getStats()
		{
			int[] out = new int[]{0,0,0,0};
			for(Item i : spaces)
			{
				if(i!=null && i instanceof Character)
				{
					out[0] += ((Character)i).love;
					out[1] += ((Character)i).lust;
					out[2] += ((Character)i).wealth;
					out[3] += ((Character)i).power;
					
					
				}
			}
			return out;
		}
		
		public boolean contains(String s)
		{
			boolean b = false;
			for(Item i : spaces)
			{
				if (i!=null && i.name.equals(s)) b = true;
			}
			return b;
		}
	}

	//For things that don't take up space but have effects
class Effect 
	{
		public String name;
		public String image;
		public int[] cost;
		
		public Effect(String n, String i)
		{
			name = n;
			image = i;
			cost = new int[]{0,0,0,0};
		}
	}

class Totem extends Effect
{
	public String worship;
	public String renounce;
	public int whichEffect;

	public Totem(String n, String i, String w, String r) {
		super(n, i);
		worship = w;
		renounce = r;
		whichEffect = 0;
	}
	
	public String getEffect()
	{
		if(whichEffect== 0)
		{
			return (worship + " - " + renounce);
		}
		else if(whichEffect==1)
		{
			return worship;
		}
		else if(whichEffect==2)
		{
			return renounce;
		}
		else return "Something got fucked up";
	}
	
}
