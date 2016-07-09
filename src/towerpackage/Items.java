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
		
		Item(String n, String i, String d)
		{
			name = n; 
			image = i; 
			description = d; 
			removable=true;
			if(useableItems.contains(n))
			{
				useable=true;
			}
			else
			{
				useable=false;
			}
			
		}
		
		
		/*
		 * This method manages the activated abilities of certain items which must be useable during the picking stage
		 * 
		 * Currently it takes no inputs, but will eventually need to
		 */
		public void use(GameManager game)
		{
			Scanner reader = new Scanner(System.in);
			
			if(useable)
			{
				if(name.equals("Amethyst Gossamer"))
				{
					String target = reader.nextLine();
					
					for(Princess1 p : game.princesses1)
					{
						if(p.dowry1.name.equals(target))
						{
							game.wagon1.removeItem("Amethyst Gossamer");
							game.wagon1.add(p.dowry1);
						}
						else if(p.dowry2.name.equals(target))
						{
							game.wagon1.removeItem("Amethyst Gossamer");
							game.wagon1.add(p.dowry2);
						}
					}
					
					useable=false;
				}
				else if(name.equals("Encrusted Chest"))
				{
					String target = reader.nextLine();
					int dowry = reader.nextInt();
					Item replace = null;
					for(Item i : game.wagon1.spaces)
					{
						
						if(i != null && i.name.equals(target))
						{
							if(dowry==1) replace=((Princess1)i).dowry1;
							else if(dowry==2) replace=((Princess1)i).dowry2;
						}
					}
					
					if(replace != null && !game.wagon1.contains(replace.name))
					{
					game.wagon1.removeItem(target);
					game.wagon1.add(replace);
					}
					
				}
				else if(name.equals("Wizard Master"))
				{
					System.out.println(name);
				}
				else if(name.equals("Huntress Master"))
				{
					System.out.println(name);
				}
				else if(name.equals("Squire Courtier"))
				{
					System.out.println(name);
				}
			}
			

			removable = false;
		}
	
		/*
		 * This blank method will eventually be used when effects are applied to characters at the end of the game, whenever an item 
		 * has an effect that applies to only certain princesses this will choose targets and apply them
		 */
		public void apply()
		{
			
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

class Character extends Item
	{
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
	
	public Effect worship;
	public Effect renounce;
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
		
		public Wagon(int slots, String n)
		{ 
			spaces = new Item[slots+1];
			name = n;
			effects = new ArrayList<Effect>();
		}

		public boolean add(Item item)
		{
			boolean succeed = false;
			for(int i = 0; i<= spaces.length-1; i++)
			{
				if(spaces[i]==null && (!(item instanceof Princess1) || i!=0))
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
					spaces[i]=null;
				}
			}
		}
		
		public int getWealth()
		{
			int out=0;
			for(Item i : spaces)
			{
				if(i!=null && i instanceof Character)
				{
					out+= ((Character)i).wealth;
				}
			}
			return out;
		}
		
		public int getPower()
		{
			int out=0;
			for(Item i : spaces)
			{
				if(i!=null && i instanceof Character)
				{
					out+= ((Character)i).power;
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
		public String description;
	}

