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
		public static final List<String> useableItems = Arrays.asList("Encrusted Chest", "Amethyst Gossamer", "Freyda, Ancient Dragon of Ice", "Huntress Master","Wizard Master","Squire Courtier","Rabbit Princess","Imp Princess","Physicker Master","Funk Master");
		
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
		
	}

	class Preference {
	  public String name;
	  public Boolean good;
	  
	  Preference(String n, Boolean k) {name = n; good = k;}
	  
	  public void corrupt() {good = !good;}
	  
	  public Boolean likesIt() {
		  return good;
	  }
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
	  
	  public void corrupt() {turnoff.corrupt();}
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

		public boolean addItem(Item item)
		{
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
		public void addEffect(Effect effect)
		{
			effects.add(effect);
		}
		
		public void removeEffect(String name)
		{
			for(int i = 0; i < effects.size(); i++)
			{
				if(effects.get(i).name.equals(name))
				{
					effects.remove(i);
				}
			}
		}
		
		public void removeItem(String name)
		{
			for(int i=0; i<spaces.length; i++)
			{
				if(spaces[i] != null && spaces[i].removable && spaces[i].name.equals(name))
				{
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
			for(Effect e : effects)
			{
				if(e instanceof Totem)
				{
				for(int j = 0; j<4; j++)
				{
					out[j] += ((Totem)e).cost[j];
				}
				}
			}
			
			return out;
		}
		
		public boolean contains(String s)
		{
			boolean b = false;
			for(Item i : spaces)
			{
				boolean isLustItem = (i!=null) && ((i instanceof Princess1 && ((Princess1)i).lustGift.name.equals(s)) || (i instanceof Princess2 && ((Princess2)i).lustGift.name.equals(s)) || (i instanceof Princess3 && ((Princess3)i).lustGift.name.equals(s)));
				if(i!=null && i.name.equals(s)) b = true;
				if(isLustItem) b=true;
			}
			for(Effect e : effects) {
				if (e!=null && e.name.equals(s)) b = true;
			}
			
			
			return b;
		}

		public boolean hasWorship() {
			// TODO Auto-generated method stub
			boolean out = false;
			
			for(Effect e : effects)
			{
				if(e!=null && e instanceof Totem && ((Totem)e).whichEffect == 1)
				{
					out = true;
				}
			}
			return out;
		}
	}

	//For things that don't take up space but have effects
class Effect extends Item
	{
		Effect(String n, String i) {
			super(n, i, null);
			// TODO Auto-generated constructor stub
		}
	}

class Totem extends Effect
{
	public String worship;
	public String renounce;
	public int whichEffect;
	public int[] cost;

	public Totem(String n, String i, String w, String r) {
		super(n, i);
		cost = new int[] {0,0,0,0};
		worship = w;
		renounce = r;
		whichEffect = 0;
		description = null;
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
