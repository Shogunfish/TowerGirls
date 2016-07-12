package towerpackage;

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
	public ArrayList<Character> companionList;
		
	
	
	public GameManager()
	{
		pageNumber=1;
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
	
	public Wagon getWagon()
	{
		
		if(pageNumber==2)
		{
			return wagon2;
		}
		else if(pageNumber==3) 
		{
			return wagon3;
		} 
		else 
		{
			return wagon1;
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
	
}