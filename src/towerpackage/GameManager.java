package towerpackage;

import java.awt.Component;
import java.util.ArrayList;

public class GameManager {
	
	public Wagon wagon1;
	public ArrayList<Princess1> princesses1;
	
	public Wagon wagon2;
	public ArrayList<Princess1> princesses2;
	
	public int pageNumber;
	
	public Wagon wagon3;
	public ArrayList<Princess2> princesses3;
	
	public Wagon wagon4;
	public ArrayList<Princess3> princesses4;
	public ArrayList<Factionless> companionList;
	
	
	public boolean huntress;
	
		
	
	
	public GameManager()
	{
		pageNumber=1;
		huntress=false;
	}
	
	public ArrayList<Princess1> getPrincess1List()
	{
		
		if(pageNumber==2)
		{
			return princesses2;
		}
		else
		{
			return princesses1;
		}
	}
	
	public ArrayList<Princess2> getPrincess2List()
	{
		return princesses3;
	}
	
	public ArrayList<Princess3> getPrincess3List() {
		// TODO Auto-generated method stub
		return princesses4;
	}
	
	public Wagon getWagon()
	{
		
		if(pageNumber==1)
		{
			return wagon1;
		}
		else if(pageNumber==2)
		{
			return wagon2;
		}
		else if(pageNumber==3) 
		{
			return wagon3;
		} 
		else 
		{
			return wagon4;
		}
	}
	
	public int[] getStats()
	{
		int[] out = new int[4];
		
		for(int i = 0; i<=3; i++)
		{
			out[i] = wagon1.getStats()[i] + wagon2.getStats()[i] + wagon3.getStats()[i] + wagon4.getStats()[i];
		}
		return out;
	}

	public boolean wealthGreaterThanPower()
	{
		int[] stats = getStats();
		return (stats[2]>stats[3]);
	}
	
	public boolean powerGreaterThanWealth()
	{
		int[] stats = getStats();
		return (stats[2]<stats[3]);
	}
	
	//HELPER METHODS TO AID IN IMPLEMENTATION OF FACTIONLESS COMPANION ABILITIES AND OTHER SUCH THINGS
	
	public ArrayList<Item> getChosenLustGifts()
	{
		ArrayList<Item> out = new ArrayList<Item>();
		
		for(Item i : wagon1.spaces)
		{
			if(i != null && i instanceof Princess) out.add(((Princess)i).lustGift);
		}
		for(Item i : wagon2.spaces)
		{
			if(i != null && i instanceof Princess) out.add(((Princess)i).lustGift);
		}
		for(Item i : wagon3.spaces)
		{
			if(i != null && i instanceof Princess) out.add(((Princess)i).lustGift);
		}
		for(Item i : wagon4.spaces)
		{
			if(i != null && i instanceof Princess) out.add(((Princess)i).lustGift);
		}
		
		return out;
	}
	
	public ArrayList<Item> getUnChosenLustGifts(ArrayList<Item> chosen)
	{
		ArrayList<Item> out = new ArrayList<Item>();
		
		for(Princess1 i : princesses1)
		{
			if(!i.name.equals("Template")) out.add(i.lustGift);
		}
		for(Princess1 i : princesses2)
		{
			if(!i.name.equals("Template"))out.add(i.lustGift);
		}
		for(Princess2 i : princesses3)
		{
			if(!i.name.equals("Example"))out.add(i.lustGift);
		}
		for(Princess3 i : princesses4)
		{
			if(!i.name.equals("Default"))out.add(i.lustGift);
		}
		for(Item i : chosen)
		{
			out.remove(i);
		}
		return out;
		
	}

	public ArrayList<Item> getUnChosenLustGifts()
	{
		return getUnChosenLustGifts(getChosenLustGifts());
	}
	
	public ArrayList<Item> getChosenDowries()
	{
		ArrayList<Item> out = new ArrayList<Item>();
		for(Item i : wagon1.spaces)
		{
			if(i != null && !(i instanceof Character)) out.add(i);
		}
		for(Item i : wagon2.spaces)
		{
			if(i != null && !(i instanceof Character)) out.add(i);
		}
		for(Item i : wagon4.spaces)
		{
			if(i != null && !(i instanceof Character)) out.add(i);
		}
		return out;
	}
	
	public ArrayList<Item> getUnChosenDowries(ArrayList<Item> chosen)
	{
		ArrayList<Item> out = new ArrayList<Item>();
		for(Princess1 i : princesses1)
		{
			if(!i.name.equals("Template")){
			out.add(i.dowry1);
			out.add(i.dowry2);}
		}
		for(Princess1 i : princesses2)
		{
			if(!i.name.equals("Template")){
			out.add(i.dowry1);
			out.add(i.dowry2);}
		}
		for(Item i : chosen)
		{
			out.remove(i);
		}
		return out;
	}
	
	public ArrayList<Item> getUnChosenDowries()
	{
		return getUnChosenDowries(getChosenDowries());
	}
	
	public ArrayList<Character> getChosenCharacters()
	{
		ArrayList<Character> out = new ArrayList<Character>();
		
		for(Item i : wagon1.spaces)
		{
			if(i != null && i instanceof Character) out.add((Character)i);
		}
		for(Item i : wagon2.spaces)
		{
			if(i != null && i instanceof Character) out.add((Character)i);
		}
		for(Item i : wagon3.spaces)
		{
			if(i != null && i instanceof Character) out.add((Character)i);
		}
		for(Item i : wagon4.spaces)
		{
			if(i != null && i instanceof Character) out.add((Character)i);
		}
		
		return out;
	}
	
	public ArrayList<Character> getUnChosenCharacters(ArrayList<Character> chosen)
	{
		ArrayList<Character> out = getAllCharacters();
		for(Character c : chosen)
		{
			out.remove(c);
		}
		return out;
	}
	
	
	
	public ArrayList<Character> getUnChosenCharacters()
	{
		return getUnChosenCharacters(getChosenCharacters());
	}

	
	public void replaceLustGift(String name, Item gift) {
		for(int i = 0; i < wagon1.spaces.length; i++){
		Item temp = wagon1.spaces[i];
		if(temp!=null && temp instanceof Princess) {
			Princess temp2 = (Princess)temp;
			if(temp2.lustGift.name.equals(name)) temp2.lustGift=gift;
			}
		
		}
		for(int i = 0; i < wagon2.spaces.length; i++){
			Item temp = wagon2.spaces[i];
			if(temp!=null && temp instanceof Princess) {
				Princess temp2 = (Princess)temp;
				if(temp2.lustGift.name.equals(name)) temp2.lustGift=gift;
				}
			
			}
		for(int i = 0; i < wagon3.spaces.length; i++){
			Item temp = wagon3.spaces[i];
			if(temp!=null && temp instanceof Princess) {
				Princess temp2 = (Princess)temp;
				if(temp2.lustGift.name.equals(name)) temp2.lustGift=gift;
				}
			
			}
		for(int i = 0; i < wagon4.spaces.length; i++){
			Item temp = wagon4.spaces[i];
			if(temp!=null && temp instanceof Princess) {
				Princess temp2 = (Princess)temp;
				if(temp2.lustGift.name.equals(name)) temp2.lustGift=gift;
				}
			
			}
	}

	public void replaceDowry(String name, Item gift) {
		for(int i = 0; i < wagon1.spaces.length; i++)
		{
			if(wagon1.spaces[i]!=null && wagon1.spaces[i].name.equals(name))
			{
				wagon1.spaces[i]=gift;
			}
		}
		for(int i = 0; i < wagon2.spaces.length; i++)
		{
			if(wagon2.spaces[i]!=null && wagon2.spaces[i].name.equals(name))
			{
				wagon2.spaces[i]=gift;
			}
		}
		for(int i = 0; i < wagon3.spaces.length; i++)
		{
			if(wagon3.spaces[i]!=null && wagon3.spaces[i].name.equals(name))
			{
				wagon3.spaces[i]=gift;
			}
		}
		for(int i = 0; i < wagon4.spaces.length; i++)
		{
			if(wagon4.spaces[i]!=null && wagon4.spaces[i].name.equals(name))
			{
				wagon4.spaces[i]=gift;
			}
		}
	
	}

	public ArrayList<Character> getAllCharacters() {
		ArrayList<Character> out = new ArrayList<Character>();
		for(Princess1 c : princesses1)
		{
			if(!c.name.equals("Template"))out.add(c);
		}
		for(Princess1 c : princesses2)
		{
			if(!c.name.equals("Template"))out.add(c);
		}
		for(Princess2 c : princesses3)
		{
			if(!c.name.equals("Example"))out.add(c);
		}
		for(Princess3 c : princesses4)
		{
			if(!c.name.equals("Default"))out.add(c);
		}
		for(Character c : companionList)
		{
			out.add(c);
		}
		return out;
	}
}