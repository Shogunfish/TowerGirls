package towerpackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

//¯\_(:))_/¯
//
//,|
/// ;
///  \
//: ,'(
//|( `.\
//: \  `\       \.
//\ `.         | `.
//\  `-._     ;   \
//\     ``-.'.. _ `._
// `. `-.            ```-...__
//  .'`.        --..          ``-..____
//,'.-'`,_-._            ((((   <o.   ,'
//     `' `-.)``-._-...__````  ____.-'
//  SSt    ,'    _,'.--,---------'
//     _.-' _..-'   ),'
//    ``--''        `


public class SimpleFrameTest extends JFrame {
	
	JScrollPane scroll;
	JScrollPane scroll2;
	JPanel princessPane;
	PrincessCard princessCard;
	JPanel infoPane;
	JPanel wagonPane1;
	JPanel wagonPane2;
	JPanel wagonPane3;
	JPanel wagonPane4;
	JPanel statPane;
	JPanel itemChoosePane;

	//CONSTRUCTION//
	
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				

				//Make Wagon
				GameManager game = new GameManager();
				game.princesses1 = new ArrayList<Princess1>();
				game.princesses2 = new ArrayList<Princess1>();
				game.princesses3 = new ArrayList<Princess2>();
				game.princesses4 = new ArrayList<Princess3>();
				game.companionList = new ArrayList<Factionless>();
				game.wagon1 = new Wagon(4, "Wally");
				game.wagon2 = new Wagon(4, "Michael");
				game.wagon3 = new Wagon(4, "Ganondorf");
				game.wagon4 = new Wagon(8, "Toby");
				
				//Populate princess arrays
				TextFileReader test = new TextFileReader();
				String[] princessNames = new String[]{"Kobold","Human","Insect","Skeleton","Slime","Mermaid","Knight","Harpy","Boy","Orc","Dwarf","Amazon","Ghost","Golem","Succubus","Goblin","Drider","Mimic","Dragon","Template"};
				for(String s : princessNames) {
					try {
						game.princesses1.add(test.readPrincess1("src/Text Files/Princesses.txt",s));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				princessNames = new String[]{"Merrow","Demon","Gnome","Serpent","Kaiju","Lampad","Orphan","Squid","Shark","Book","Shadow","Gargoyle","Candy","Lion","Frankenstein","Alien","Elephant","P'orc","Smuggler","Wild","Wyvern","Template"};
				for(String s : princessNames) {
					try {
						game.princesses2.add(test.readPrincess1("src/Text Files/Princesses.txt",s));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				princessNames = new String[]{"Dawn Elf", "Assassin", "Nomad", "Ninja", "Ogre", "Zombie", "Plant", "Witch", "Fairy", "Centaur", "Gorgon", "Frog", "Angel", "Monkey", "Cat", "Arcane", "Armor", "Crest", "Merchant", "Ice Dragon", "Pirate", "Example"};
				for(String s : princessNames) {
					try {
						game.princesses3.add(test.readPrincess2("src/Text Files/Princesses.txt",s));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				princessNames = new String[]{"Holstaur", "Mouse", "Rabbit", "Dusk Elf", "Vampire", "Dog", "Djinn", "Triclops", "Raider", "Eldritch", "Lamia", "Imp", "Moth", "Sphinx", "Yeti", "Sword", "Jester", "Faun", "Crystal", "Toxic Dragon", "Paladin", "Default"};
				for(String s : princessNames) {
					try {
						game.princesses4.add(test.readPrincess3("src/Text Files/Princesses.txt",s));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				String[] companionNames = new String[]{"Paladin General", "Barbarian General", "Incubus General", "Border General", "Jadeite Beast", "Lazulite Beast", "Sinhalite Beast", "Citrine Beast", "Physicker Master", "Wizard Master", "Huntress Master", "Funk Master", "Noble Courtier", "Squire Courtier", "Friar Courtier", "Monk Courtier", "Bard Courtier"};
				for(String s : companionNames) {
					try {
						game.companionList.add(test.readFactionless("src/Text Files/Factionless.txt",s));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				
				//Put out frame
				try {
					SimpleFrameTest frame = new SimpleFrameTest(game);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param game
	 */
	SimpleFrameTest(GameManager game) {
		
		//Set Frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, (950+360+180), 550);
		JPanel framePane = new JPanel();
		framePane.setLayout(null);
		setContentPane(framePane);
		
		princessPane = new JPanel();
		princessPane.setLayout(new WrapLayout());
		
		princessPane(game);
		
		JScrollPane scroll = new JScrollPane(princessPane);
		scroll.setSize(new Dimension(320,490));
		scroll.setBorder(BorderFactory.createLineBorder(Color.RED));
		scroll.setLocation(10,10);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		add(scroll);
		
		infoPane = new JPanel();
		infoPane.setLayout(new WrapLayout());
		
		scroll2 = new JScrollPane(infoPane);
		scroll2.setSize(new Dimension(400,490));
		scroll2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scroll2.setLocation(340,10);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2.getVerticalScrollBar().setUnitIncrement(16);
		add(scroll2);
		
		changeInfoPane(game.getPrincess1List().get(game.getPrincess1List().size()-1), infoPane, game);
		
		itemChoosePane = new JPanel();
		itemChoosePane.setSize(new Dimension(400,280));
		itemChoosePane.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		itemChoosePane.setLocation(340,220);

		wagonPane1 = new JPanel();
		wagonPane1.setSize(new Dimension(170,360));
		wagonPane1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		wagonPane1.setLocation(750,10);
		
		wagonPane2 = new JPanel();
		wagonPane2.setSize(new Dimension(170,360));
		wagonPane2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		wagonPane2.setLocation(930,10);
		
		wagonPane3 = new JPanel();
		wagonPane3.setSize(new Dimension(170,360));
		wagonPane3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		wagonPane3.setLocation(930+180,10);
		
		wagonPane4 = new JPanel();
		wagonPane4.setSize(new Dimension(170,360));
		wagonPane4.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		wagonPane4.setLocation(930+180+180,10);
		
		wagonPane(game);
		
		statPane = new JPanel();
		statPane.setSize(new Dimension(170,120));
		statPane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		statPane.setLocation(750,380);
		statPane.setLayout(new MigLayout("", "[][]", "[][][][]"));
		
		statPane(game);
		
		add(statPane);
	}
	
	/**
	 * Build the left third: princessPane
	 * @param game
	 */
	void princessPane(GameManager game) {
		
		if(game.pageNumber < 3) {
			for(int i=0; i<game.getPrincess1List().size(); i++) 
			{
				if(!Arrays.asList(game.getWagon().spaces).contains(game.getPrincess1List().get(i))) 
				{
					JLabel temp = new JLabel();
					temp.setName(game.getPrincess1List().get(i).name); 
					paintImage(game.getPrincess1List().get(i),temp);
					addObjectClick(game.getPrincess1List().get(i), temp, game);
					princessPane.add(temp);
				}
			}
		} else if(game.pageNumber==3) {
			for(int i=0; i<game.getPrincess2List().size(); i++) 
			{
				if(!Arrays.asList(game.getWagon().spaces).contains(game.getPrincess2List().get(i))) 
				{
					JLabel temp = new JLabel();
					temp.setName(game.getPrincess2List().get(i).name);
					paintImage(game.getPrincess2List().get(i),temp);
					addObjectClick(game.getPrincess2List().get(i), temp, game);
					princessPane.add(temp);
				}
			}
		} else if(game.pageNumber==4)
		{
			for(int i=0; i<game.getPrincess3List().size(); i++) 
			{
				if(!Arrays.asList(game.getWagon().spaces).contains(game.getPrincess3List().get(i))) 
				{
					JLabel temp = new JLabel();
					temp.setName(game.getPrincess3List().get(i).name);
					paintImage(game.getPrincess3List().get(i),temp);
					addObjectClick(game.getPrincess3List().get(i), temp, game);
					princessPane.add(temp);
				}
			}
			for(int i=0; i<game.companionList.size(); i++) 
			{
				if(!Arrays.asList(game.getWagon().spaces).contains(game.companionList.get(i))) 
				{
					JLabel temp = new JLabel();
					temp.setName(game.companionList.get(i).name);
					paintImage(game.companionList.get(i),temp);
					addObjectClick(game.companionList.get(i), temp, game);
					princessPane.add(temp);
				}
			}
		}
	}

	/**
	 * Change the middle third: infoPane
	 * @param i
	 * @param comp
	 * @param game
	 */
	void changeInfoPane(Item clicked, Component comp, GameManager game) {
		
		infoPane.removeAll();
		princessCard = new PrincessCard(clicked, game);
		infoPane.add(princessCard.provideInput());
		infoPane.validate();
		infoPane.repaint();
	}
	
	/**
	 * Build the right third: wagonPane
	 * @param game
	 */
	void wagonPane(GameManager game) {
		for(int i=0; i<game.wagon1.spaces.length; i++) {
			JLabel temp = new JLabel();
			if(game.wagon1.spaces[i] != null) {
				temp.setName(game.wagon1.spaces[i].name);
				wagonPane1.add(temp);
				paintImage(game.wagon1.spaces[i], temp);
				addObjectClick(game.wagon1.spaces[i], temp, game);
			}
		}
		for(Effect e : game.wagon1.effects) {
			JLabel temp = new JLabel();
			temp.setName(e.name);
			wagonPane1.add(temp);
			paintImage(e, temp);
			addObjectClick(e, temp, game);
		}
		add(wagonPane1);
		
		for(int i=0; i<game.wagon2.spaces.length; i++) {
			JLabel temp = new JLabel();
			if(game.wagon2.spaces[i] != null) {
				temp.setName(game.wagon2.spaces[i].name);
				wagonPane2.add(temp);
				paintImage(game.wagon2.spaces[i], temp);
				addObjectClick(game.wagon2.spaces[i], temp, game);
			}
		}
		for(Effect e : game.wagon2.effects) {
			JLabel temp = new JLabel();
			temp.setName(e.name);
			wagonPane2.add(temp);
			paintImage(e, temp);
			addObjectClick(e, temp, game);
		}
		add(wagonPane2);
		
		for(int i=0; i<game.wagon3.spaces.length; i++) {
			JLabel temp = new JLabel();
			if(game.wagon3.spaces[i] != null) {
				temp.setName(game.wagon3.spaces[i].name);
				wagonPane3.add(temp);
				paintImage(game.wagon3.spaces[i], temp);
				addObjectClick(game.wagon3.spaces[i], temp, game);
			}
		}
		for(Effect e : game.wagon3.effects) {
			JLabel temp = new JLabel();
			temp.setName(e.name);
			wagonPane3.add(temp);
			paintImage(e, temp);
			addObjectClick(e, temp, game);
		}
		add(wagonPane3);
		
		for(int i=0; i<game.wagon4.spaces.length; i++) {
			JLabel temp = new JLabel();
			if(game.wagon4.spaces[i] != null) {
				temp.setName(game.wagon4.spaces[i].name);
				wagonPane4.add(temp);
				paintImage(game.wagon4.spaces[i], temp);
				addObjectClick(game.wagon4.spaces[i], temp, game);
			}
		}
		for(Effect e : game.wagon4.effects) {
			JLabel temp = new JLabel();
			temp.setName(e.name);
			wagonPane4.add(temp);
			paintImage(e, temp);
			addObjectClick(e, temp, game);
		}
		add(wagonPane4);
		
		
	}
	
	/**
	 * Build the bottom right pane: statPane
	 * @param game
	 */
	void statPane(GameManager game) {
		//Grab fonts (should this be done every time? Ideally not)
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				Font blockFont = grabFonts("src/Fonts/04B_19__.ttf");
				ge.registerFont(blockFont);
				Font mainFont = grabFonts("src/Fonts/BebasNeue Bold.ttf");
				ge.registerFont(mainFont);
		
		String[] icons = new String[]{"Wealth","Power"};
		for(int i=0; i<icons.length; i++) {
			JLabel temp = new JLabel();
			statPane.add(temp, "cell 0 " + (2 + i));
			paintImage("src/Stat icons/" + icons[i] + ".png", temp);
		}
		
			JLabel tempNum = new JLabel();
			tempNum.setText(Integer.toString(game.getStats()[2]));
			statPane.add(tempNum, "cell 1 " + (2));
			
			tempNum = new JLabel();
			tempNum.setText(Integer.toString(game.getStats()[3]));
			statPane.add(tempNum, "cell 1 " + (3));
		
		
		int count = 0;
		for(int i=1; i<game.getWagon().spaces.length; i++) {
		    if(game.getWagon().spaces[i] != null) {
		        count++;
		    }
		}
		JLabel title = new JLabel("<html>Current space: </html>");
		title.setFont(mainFont.deriveFont(16f));
		statPane.add(title, "cell 0 0 3 2");
		JLabel spaces = new JLabel(count + "/" + (game.getWagon().spaces.length-2));
		spaces.setFont(blockFont.deriveFont(16f));
		statPane.add(spaces, "cell 3 0 0 2");
		
		JButton next = new JButton();
		next.setText("Next Page");
		next.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				game.pageNumber++;
				if(game.pageNumber<3)
				{
					updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
				}
				else if(game.pageNumber==3)
				{
					updateFrame(game, game.princesses3.get(game.princesses3.size()-1));
				}
				else if(game.pageNumber==4)
				{
					updateFrame(game, game.princesses4.get(game.princesses4.size()-1));
				}
				else if(game.pageNumber==5)
				{
					ArrayList<Character> rescued = new ArrayList<Character>();
					rescued.add(game.player);
					rescued.addAll(game.getChosenCharacters());
					ArrayList<Effect> effects = game.getAllEffects();
					ArrayList<Effect> temp = new ArrayList<Effect>();
					for(Item i : game.getChosenDowries()) effects.add(i.makeEffect());
					for(Item i : game.getChosenLustGifts()) effects.add(i.makeEffect());
					
					finalPage(rescued, effects, 0, game);
				}
			}
		});
		statPane.add(next, "cell 1 4 0 2");
	}

	void finalPage(ArrayList<Character> rescued, ArrayList<Effect> effects, int index, GameManager game)
	{
		Effect applying = effects.get(index);
		if(applying.getType()==Effect.EFFECT_DO_NOTHING)
		{
			if(index<effects.size()-1)
				{
				finalPage(rescued,effects,index+1,game);
				}
			else 
				{
				System.out.println("YOU FUCKIN DID IT BABYCAKES");
				}
		}
		else if(applying.getType()==Effect.EFFECT_PLAYER)
		{
			game.player.effects.add(applying);
			if(index<effects.size()-1)
			{
			finalPage(rescued,effects,index+1,game);
			}
		else 
			{
			System.out.println("YOU FUCKIN DID IT BABYCAKES");
			}
		}
		else if(applying.getType()==Effect.EFFECT_KINGDOM)
		{
			game.kingdom.add(applying);
			if(index<effects.size()-1)
			{
			finalPage(rescued,effects,index+1,game);
			}
		else 
			{
			System.out.println("YOU FUCKIN DID IT BABYCAKES");
			}
		}
		else if(applying.getType()==Effect.EFFECT_STASH)
		{
			game.stash.add(applying);
			if(index<effects.size()-1)
			{
			finalPage(rescued,effects,index+1,game);
			}
		else 
			{
			System.out.println("YOU FUCKIN DID IT BABYCAKES");
			}
		}
		else
		{
		princessPane.removeAll();
		changeInfoPane(applying, null, game);
		JButton butt = new JButton("Apply");
		butt.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(index<effects.size()-1)
				{
					boolean canApply = true;
				for(int i = 0; i < rescued.size(); i++)
				{
					Character temp = rescued.get(i);
					if(temp.selected && canApply)temp.effects.add(effects.get(index));
					if(applying.type==Effect.EFFECT_SINGLE_CHARACTER || applying.type==Effect.EFFECT_SINGLE_NON_PLAYER_CHARACTER)canApply=false;
					temp.selected=false;
				}
				
				finalPage(rescued, effects, index+1, game);
				}
				else
				{
					System.out.println("YOU FUCKIN DID IT BABYCAKES");
				}
			}});
		infoPaneAdd(butt);
		
		for(int i = 0; i < rescued.size(); i++)
		{
			if(i!=0 || applying.getType() != Effect.EFFECT_MULTIPLE_NON_PLAYER_CHARACTERS || applying.getType() != Effect.EFFECT_SINGLE_NON_PLAYER_CHARACTER){
			Character current = rescued.get(i);
			JLabel temp = new JLabel();
			paintImage(current,temp);
			temp.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me){
					if(current.selected)
					{
						current.selected=false;
						game.numSelected++;
						temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					}
					else 
					{
						current.selected = true;
						game.numSelected--;
						temp.setBorder(null);
					}
				}
			});
			princessPane.add(temp);
			}
		}
		
		princessPane.revalidate();
		princessPane.repaint();
		}
		/*
		 * 
		 * The plan here is pretty simple, First, we will evaluate whether the player has gotten any special bonuses like
		 * Primeval princess, dragon queen, etc. and if they have we will add them
		 * 
		 * Then we will go through all the effects and for each one load
		 * a page which lets you select some number of characters depending on some variable of the effect which tells how
		 * many targets it can have
		 * 
		 * Then it will go through the list of all the selected characters and add the effect to each chosen character
		 * 
		 * Then, after all that's done it will call a method that will simply display all your shit.
		 */
	}
	
	///CLICK LISTENER//

	void addObjectClick(Item clickedItem, Component comp, GameManager game) {
		comp.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent me) {

					if(clickedItem instanceof Princess1) {
						Princess1 p = (Princess1)clickedItem;
						//Princess1
						//If this princess is in the wagon
						if(game.wagon1.contains(clickedItem.name) || game.wagon2.contains(clickedItem.name)) {
							//Add remove button
							JButton temp = new JButton("Remove");
							if(!p.removable || !p.dowry1.removable || !p.dowry2.removable) temp.setEnabled(false);
							temp.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
										game.getWagon().removeItem(p.name);
										game.getWagon().removeItem(p.dowry1.name);
										game.getWagon().removeItem(p.dowry2.name);
										changeInfoPane(p, comp, game);
										updateFrameDefault(game);
										//updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
									
								}
							});
							changeInfoPane(clickedItem, comp, game);
							if(!game.getWagon().contains(p.dowry1.name)) princessCard.removeDowries(1);
							if(!game.getWagon().contains(p.dowry2.name)) princessCard.removeDowries(2);
							
							if((game.pageNumber>2) || (game.wagon1.contains(p.name)&&game.pageNumber==2) || (game.wagon2.contains(p.name)&&game.pageNumber==1))temp.setEnabled(false);
							
							infoPaneAdd(temp);
						} else {
							//If this princess is not in the wagon
							JButton temp = new JButton("Add");
							temp.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									game.getWagon().addItem(p);
									if(princessCard.getChosen()[0]) game.getWagon().addItem(p.dowry1);
									if(princessCard.getChosen()[1]) game.getWagon().addItem(p.dowry2);
									changeInfoPane(p, comp, game);
									updateFrameDefault(game);
									//updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
								}
							});							
							changeInfoPane(clickedItem, comp, game);
							if(!p.name.equals("Template")) infoPane.add(temp);
							infoPane.revalidate();
							infoPane.repaint();
							//Add add button
						}
						//Show princess
					} else if(clickedItem instanceof Princess2) {
						Princess2 p = (Princess2)clickedItem;
						//Princess1
						//If this princess is in the wagon
						if(game.wagon3.contains(clickedItem.name)) {
							//Add remove button
							JButton temp = new JButton("Remove");
							if(!p.removable || !p.totem.removable) temp.setEnabled(false);
							temp.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
										game.getWagon().removeItem(p.name);
										p.totem.whichEffect = 0;
										p.totem.cost = new int[]{0,0,0,0};
										game.getWagon().removeEffect(p.totem.name);
										changeInfoPane(p, comp, game);
										updateFrameDefault(game);
										//updateFrame(game, game.princesses2.get(game.princesses2.size()-1));
								}
							});
							if(game.pageNumber!=3)temp.setEnabled(false);
							changeInfoPane(clickedItem, comp, game);
							infoPaneAdd(temp);
						} else {
							//If this princess is not in the wagon
							JButton temp = new JButton("Add");
							temp.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									game.getWagon().addItem(p);
									if(princessCard.getChosen()[0] && !game.getWagon().hasWorship()) p.totem.whichEffect = 1;
									else p.totem.whichEffect = 2;
									if(!game.getWagon().contains(p.totem.name)) game.getWagon().addEffect(p.totem);
									changeInfoPane(p, comp, game);
									updateFrameDefault(game);
									//updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
								}
							});
							if(game.pageNumber!=3)temp.setEnabled(false);
							changeInfoPane(clickedItem, comp, game);
							if(!p.name.equals("Example")) infoPane.add(temp);
							//Add add button
							
							//Add buy buttons
							temp = new JButton("Buy Totem: Wealth");
							temp.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									//set totem cost
									p.totem.cost = new int[] {0,0,-3,0};
									
									//set which effect
									if(princessCard.getChosen()[0]) p.totem.whichEffect = 1;
									else p.totem.whichEffect = 2;
									
									//add totem
									game.getWagon().addEffect(p.totem);
									updateFrameDefault(game);
									//updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
								}
							});
							if(game.pageNumber!=3)temp.setEnabled(false);
							infoPane.add(temp);
							if(game.getStats()[2] < 3 || game.getWagon().contains(p.totem.name)) temp.setEnabled(false);
							
							temp = new JButton("Buy Totem: Power");
							temp.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									//set totem cost
									p.totem.cost = new int[] {0,0,0,-3};
									
									//set which effect
									if(princessCard.getChosen()[0]) p.totem.whichEffect = 1;
									else p.totem.whichEffect = 2;
									
									//add totem
									game.getWagon().addEffect(p.totem);
									updateFrameDefault(game);
									//updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
								}
							});
							if(game.getStats()[3] < 3 || game.getWagon().contains(p.totem.name)) temp.setEnabled(false);
							if(game.pageNumber!=3)temp.setEnabled(false);
							infoPaneAdd(temp);
						}
						//Draw princess
					} else if (clickedItem instanceof Princess3){
							Princess3 p = (Princess3)clickedItem;
							
							if(game.getWagon().contains(clickedItem.name))
							{
								JButton temp = new JButton("Remove");
								if(!p.removable) temp.setEnabled(false);
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
											game.getWagon().removeItem(p.name);
											game.getWagon().removeItem(p.companion.name);
											game.getWagon().removeEffect(p.wealthGift.name);
											game.getWagon().removeEffect(p.powerGift.name);
											changeInfoPane(p, comp, game);
											updateFrameDefault(game);
											//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
									}
								});
								if(game.pageNumber!=4)temp.setEnabled(false);
								changeInfoPane(clickedItem, comp, game);
								infoPaneAdd(temp);
							} 
							else
							{
								JButton temp = new JButton("Add");
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
										
										if((!game.huntress && game.wealthGreaterThanPower()) || (game.huntress && game.powerGreaterThanWealth())) game.getWagon().addEffect(p.wealthGift);
									    else if ((!game.huntress && game.powerGreaterThanWealth()) || (game.huntress && game.wealthGreaterThanPower())) game.getWagon().addEffect(p.powerGift);
									    else 
									    {
									    	if(princessCard.getChosen()[0]) game.getWagon().addEffect(p.wealthGift);
									    	else game.getWagon().addEffect(p.powerGift);
									    }
										game.getWagon().addItem(p);
										boolean succeed = true;
										if(!game.getWagon().contains(p.companion.name)){
										succeed = game.getWagon().addItem(p.companion);}
										
										if(!succeed) {
											game.getWagon().removeItem(p.name);
											game.getWagon().removeItem(p.wealthGift.name);
											game.getWagon().removeEffect(p.powerGift.name);
										}
										
										changeInfoPane(p, comp, game);
										updateFrameDefault(game);
										//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
									
									
									}
								});
								if(game.pageNumber!=4)temp.setEnabled(false);
								changeInfoPane(clickedItem, comp, game);
								if(!p.name.equals("Default")) infoPane.add(temp);
								//Add add button
							}
							
					} else if (clickedItem instanceof Factionless){
						Factionless f = (Factionless)clickedItem;
						changeInfoPane(clickedItem, comp, game);
						
						//Princess1
						//If this princess is in the wagon
						if(game.getWagon().contains(clickedItem.name)) {
							//Add remove button
							JButton temp = new JButton("Remove");
							if(!f.removable || (f.effect1 != null && !f.effect1.removable) || (f.effect2 != null && !f.effect2.removable)) temp.setEnabled(false);
							temp.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
										game.getWagon().removeItem(f.name);
										if(f.effect1!=null)
										{
										game.getWagon().removeEffect(f.effect1.name);
										}
										if(f.effect2!=null)
										{
										game.getWagon().removeEffect(f.effect2.name);
										}
										game.getWagon().removeEffect(f.kingdomMod.name);
										changeInfoPane(f, comp, game);
										updateFrameDefault(game);
										//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
									
								}
							});
							if(game.pageNumber!=4)temp.setEnabled(false);
							changeInfoPane(clickedItem, comp, game);
							infoPaneAdd(temp);
						} else {
							//If this princess is not in the wagon
							JButton temp = new JButton("Add");
							temp.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									boolean succeed = game.getWagon().addItem(f);
									if(succeed){
										if(f.effect1!=null)
										{
										game.getWagon().addEffect(f.effect1);
										}
										if(f.effect2!=null)
										{
										game.getWagon().addEffect(f.effect2);
										}
										game.getWagon().addEffect(f.kingdomMod);
									}
									changeInfoPane(f, comp, game);
									updateFrameDefault(game);
									//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
								}
							});
							changeInfoPane(clickedItem, comp, game);
							int count = 0;
							for(Item i : game.getWagon().spaces)
							{
								if((i instanceof Factionless) && ((Factionless)i).type.equals(f.type)) count++;
							}
							if(count>=2)
							{
								temp.setText("Too many " + f.type + "s");
								temp.setEnabled(false);
							}
							if(game.pageNumber!=4)temp.setEnabled(false);
							infoPaneAdd(temp);
							//Add add button
						}
						
						
						
					} else if(clickedItem instanceof Character){	
					
					Character c = (Character)clickedItem;
					updateFrame(game, c);
						
					} else if(clickedItem instanceof Totem) {
							Totem t = (Totem)clickedItem;
							//Show Totem
							updateFrame(game, clickedItem);
							//if totem has a nonzero cost, it's been bought already
							if(t.name.equals("Freyda, Dragon Priestess of Ice")) {
								JButton temp = new JButton("Use");
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
										
										//mess with panes
										scroll2.setSize(new Dimension(400,200));
										
										for(int i=infoPane.getComponents().length-1; i>0; i--) {
											infoPane.remove(i);
										}
										
									 add(itemChoosePane);
									 itemChoosePane.revalidate();
										
											//slap totems onto pane
											for(int q=0; q<game.getPrincess2List().size()-1; q++) {
												if(!game.getWagon().contains(game.getPrincess2List().get(q).totem.name)) {
													JLabel temp = new JLabel();
													paintImage(game.getPrincess2List().get(q).totem, temp);
													Totem totem = game.getPrincess2List().get(q).totem;
													temp.addMouseListener(new MouseAdapter() {
														@Override
														public void mouseClicked(MouseEvent me) {
														//add replace button
														JButton replace = new JButton("Worship");
														replace.addMouseListener(new MouseAdapter() {
															@Override
															public void mouseClicked(MouseEvent me) {
																//Set unusable
																clickedItem.useable = false;
																//Set non-removable (now Ice Dragon can't leave)
																clickedItem.removable = false;
																//Set totem to worship
																totem.whichEffect = 1;
																//add new
																game.getWagon().addEffect(totem);
																
																scroll2.setSize(new Dimension(380,490));
																remove(itemChoosePane);
																
																updateFrameDefault(game);
																//updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
															}
														});
														changeInfoPane(totem,temp,game);
														infoPaneAdd(replace);
														}
														});
													itemChoosePane.add(temp);
												}
												if(!game.getWagon().contains(game.getPrincess2List().get(q).lustGift.name)) {
													JLabel temp = new JLabel();
													paintImage(game.getPrincess2List().get(q).lustGift, temp);
													Item lustGift = game.getPrincess2List().get(q).lustGift;
													temp.addMouseListener(new MouseAdapter() {
														@Override
														public void mouseClicked(MouseEvent me) {
														//add replace button
														JButton replace = new JButton("Steal");
														replace.addMouseListener(new MouseAdapter() {
															@Override
															public void mouseClicked(MouseEvent me) {
																//Set unusable
																clickedItem.useable = false;
																//Set non-removable (now Ice Dragon can't leave)
																clickedItem.removable = false;
																//add new
																game.getWagon().addItem(lustGift);
																
																scroll2.setSize(new Dimension(380,490));
																remove(itemChoosePane);
																updateFrameDefault(game);
																//updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
															}
														});
														changeInfoPane(lustGift,temp,game);
														infoPaneAdd(replace);
														}
														});
													itemChoosePane.add(temp);
												}
		
											clickedItem.useable=false;
											
											infoPane.revalidate();
											infoPane.repaint();
											
										}
									}
								});
								if(!clickedItem.useable) temp.setEnabled(false);
								infoPaneAdd(temp);
							}
							if(Arrays.equals(t.cost, new int[]{0,0,-1,0}) || Arrays.equals(t.cost, new int[]{0,0,0,-1})) {
								//undo buyout button
								JButton temp = new JButton("Undo Buyout");
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
										t.cost = new int[]{0,0,0,0};
										t.whichEffect = 2;
										
										updateFrameDefault(game);
										//updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
									}
								});
								if(!t.removable) temp.setEnabled(false);
								if(game.pageNumber!=3)temp.setEnabled(false);
								infoPaneAdd(temp);
							} else if(Arrays.equals(t.cost, new int[]{0,0,-3,0}) || Arrays.equals(t.cost, new int[]{0,0,0,-3})) {
								//remove button
								JButton temp = new JButton("Remove");
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
										t.whichEffect = 0;
										t.cost = new int[]{0,0,0,0};
										game.getWagon().removeEffect(t.name);
										
										updateFrameDefault(game);
										//updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
									}
								});
								if(game.pageNumber!=3)temp.setEnabled(false);
								infoPaneAdd(temp);
							} else if(Arrays.equals(t.cost, new int[]{0,0,0,0}) && t.whichEffect == 2) {
								//buyout
								JButton temp = new JButton("Buy out: Wealth");
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
										t.cost = new int[]{0,0,-1,0};
										t.whichEffect = 1;
										
										updateFrameDefault(game);
										//updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
									}
								});
								infoPane.add(temp);
								if(game.pageNumber!=3) temp.setEnabled(false);
								
								if(game.getWagon().getStats()[2] < 1) temp.setEnabled(false);
								
								temp = new JButton("Buy out: Power");
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
										t.cost = new int[]{0,0,0,-1};
										t.whichEffect = 1;

										updateFrameDefault(game);
										//updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
									}
								});
								if(game.getWagon().getStats()[3] < 1) temp.setEnabled(false);
								
								infoPaneAdd(temp);
								if(game.pageNumber!=3) temp.setEnabled(false);
							}
						}
						else if(clickedItem instanceof Effect)
						{
							Effect e = (Effect)clickedItem;
							
							updateFrame(game, e);
							if(e.name.equals("Rabbit Wealth"))
							{
								JButton temp = new JButton("Use");
								temp.addActionListener(new ActionListener()
								{

									@Override
									public void actionPerformed(ActionEvent arg0) 
									{
										scroll2.setSize(new Dimension(400,200));
										
										for(int i=infoPane.getComponents().length-1; i>0; i--) {
											infoPane.remove(i);
										}
										
										add(itemChoosePane);
										itemChoosePane.revalidate();
										
										for(int i = 0; i < game.wagon1.spaces.length; i++)
										{
											if(game.wagon1.spaces[i] != null && game.wagon1.spaces[i] instanceof Princess1)
											{
												JLabel temp = new JLabel();
												paintImage(game.wagon1.spaces[i], temp);
												Princess1 p = (Princess1)game.wagon1.spaces[i];
												temp.addMouseListener(new MouseAdapter(){
													public void mouseClicked(MouseEvent me)
													{
														JLabel dow = new JLabel();
		
														if(!game.wagon1.contains(p.dowry1.name) && !game.wagon2.contains(p.dowry1.name)) {
														paintImage(p.dowry1, dow);
														dow.addMouseListener(new MouseAdapter() {
															@Override
															public void mouseClicked(MouseEvent me) {
																//add replace button
																JButton replace = new JButton("Add");
																replace.addMouseListener(new MouseAdapter() {
																	@Override
																	public void mouseClicked(MouseEvent me) {
																		
																		clickedItem.useable = false;
																		//Set non-removable (now Rabbit can't leave)
																		clickedItem.removable = false;
																		//add
																		game.getWagon().addItem(p.dowry1);
																		
																		scroll2.setSize(new Dimension(380,490));
																		remove(itemChoosePane);
																		updateFrameDefault(game);
																		//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
																	}
																});	
																
																
																changeInfoPane(p.dowry1, replace, game);
																infoPaneAdd(replace);
															}
														});
														}
														infoPane.removeAll();
														infoPane.add(dow);
														
														dow = new JLabel();
														if(!game.wagon1.contains(p.dowry2.name) && !game.wagon2.contains(p.dowry2.name)) {
														paintImage(p.dowry2, dow);
														dow.addMouseListener(new MouseAdapter() {
															@Override
															public void mouseClicked(MouseEvent me) {
																//add replace button
																JButton replace = new JButton("Add");
																replace.addMouseListener(new MouseAdapter() {
																	@Override
																	public void mouseClicked(MouseEvent me) {
																		
																		clickedItem.useable = false;
																		//Set non-removable (now Rabbit can't leave)
																		
																		clickedItem.removable = false;
																		//add new
																		game.getWagon().addItem(p.dowry2);
																		
																		scroll2.setSize(new Dimension(380,490));
																		remove(itemChoosePane);
																		updateFrameDefault(game);
																		//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
																	}
																});	
															
																changeInfoPane(p.dowry2, replace, game);
																infoPaneAdd(replace);
															}
														});
														}
														infoPaneAdd(dow);
													}
												});
												
												itemChoosePane.add(temp);
											}
										}
										for(int i = 0; i < game.wagon2.spaces.length; i++)
										{
											if(game.wagon2.spaces[i] != null && game.wagon2.spaces[i] instanceof Princess1)
											{
												JLabel temp = new JLabel();
												paintImage(game.wagon2.spaces[i], temp);
												Princess1 p = (Princess1)game.wagon2.spaces[i];
												temp.addMouseListener(new MouseAdapter(){
													public void mouseClicked(MouseEvent me)
													{
														JLabel dow = new JLabel();
		
														if(!game.wagon1.contains(p.dowry1.name) && !game.wagon2.contains(p.dowry1.name)) {
														paintImage(p.dowry1, dow);
														dow.addMouseListener(new MouseAdapter() {
															@Override
															public void mouseClicked(MouseEvent me) {
																//add replace button
																JButton replace = new JButton("Add");
																replace.addMouseListener(new MouseAdapter() {
																	@Override
																	public void mouseClicked(MouseEvent me) {
																		
																		clickedItem.useable = false;
																		//Set non-removable (now Rabbit can't leave)
																		clickedItem.removable = false;
																		//add
																		game.getWagon().addItem(p.dowry1);
																		
																		scroll2.setSize(new Dimension(380,490));
																		remove(itemChoosePane);
																		updateFrameDefault(game);
																		//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
																	}
																});	
																
																
																changeInfoPane(p.dowry1, replace, game);
																infoPaneAdd(replace);
															}
														});
														}
														infoPane.removeAll();
														infoPane.add(dow);
														
														dow = new JLabel();
														if(!game.wagon1.contains(p.dowry2.name) && !game.wagon2.contains(p.dowry2.name)) {
														paintImage(p.dowry2, dow);
														dow.addMouseListener(new MouseAdapter() {
															@Override
															public void mouseClicked(MouseEvent me) {
																//add replace button
																JButton replace = new JButton("Add");
																replace.addMouseListener(new MouseAdapter() {
																	@Override
																	public void mouseClicked(MouseEvent me) {
																		
																		clickedItem.useable = false;
																		//Set non-removable (now Rabbit can't leave)
																		
																		clickedItem.removable = false;
																		//add new
																		game.getWagon().addItem(p.dowry2);
																		
																		scroll2.setSize(new Dimension(380,490));
																		remove(itemChoosePane);
																		updateFrameDefault(game);
																		//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
																	}
																});	
															
																changeInfoPane(p.dowry2, replace, game);
																infoPaneAdd(replace);
															}
														});
														}
														infoPaneAdd(dow);
													}
												});
												
												itemChoosePane.add(temp);
											}
										}
										
										
									}
								});
							
								infoPaneAdd(temp);
								if(!clickedItem.useable)temp.setEnabled(false);
								
							}
							else if(e.name.equals("Imp Wealth"))
							{
								JButton temp = new JButton("Use");
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
										
										//mess with panes
										scroll2.setSize(new Dimension(400,200));
										
										for(int i=infoPane.getComponents().length-1; i>0; i--) {
											infoPane.remove(i);
										}
										
									 add(itemChoosePane);
									 itemChoosePane.revalidate();
										
											//slap totems onto pane
											for(int q=0; q<game.getPrincess2List().size()-1; q++) {
												if(!game.wagon3.contains(game.getPrincess2List().get(q).totem.name)) {
													JLabel temp = new JLabel();
													paintImage(game.getPrincess2List().get(q).totem, temp);
													Totem totem = game.getPrincess2List().get(q).totem;
													temp.addMouseListener(new MouseAdapter() {
														@Override
														public void mouseClicked(MouseEvent me) {
														//add replace button
														JButton replace = new JButton("Renounce");
														replace.addMouseListener(new MouseAdapter() {
															@Override
															public void mouseClicked(MouseEvent me) {
																//Set unusable
																clickedItem.useable = false;
																//Set non-removable (now Ice Dragon can't leave)
																clickedItem.removable = false;
																//Set totem to worship
																totem.whichEffect = 2;
																//add new
																game.getWagon().addEffect(totem);
																
																scroll2.setSize(new Dimension(380,490));
																remove(itemChoosePane);
																updateFrameDefault(game);
																//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
															}
														});
														changeInfoPane(totem,temp,game);
														infoPaneAdd(replace);
														}
														});
													itemChoosePane.add(temp);
												}
		
											clickedItem.useable=false;
											
											infoPane.revalidate();
											infoPane.repaint();
											
										}
									}
								});
								
								
								infoPaneAdd(temp);
								if(!clickedItem.useable)temp.setEnabled(false);
							} 
							else if(e.name.equals("Lemminghearted Liberation"))
							{
								JButton temp = new JButton("Use");
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
										
										//mess with panes
										scroll2.setSize(new Dimension(400,200));
										
										for(int i=infoPane.getComponents().length-1; i>0; i--) {
											infoPane.remove(i);
										}
										
									 add(itemChoosePane);
									 itemChoosePane.revalidate();
										
											//slap companions onto pane
											for(int q=0; q<game.getPrincess3List().size()-1; q++) {
												if(!game.wagon4.contains(game.getPrincess3List().get(q).companion.name)) {
													JLabel temp = new JLabel();
													paintImage(game.getPrincess3List().get(q).companion, temp);
													Character c = game.getPrincess3List().get(q).companion;
													temp.addMouseListener(new MouseAdapter() {
														@Override
														public void mouseClicked(MouseEvent me) {
														//add replace button
														JButton replace = new JButton("Rescue");
														replace.addMouseListener(new MouseAdapter() {
															@Override
															public void mouseClicked(MouseEvent me) {
																//Set unusable
																clickedItem.useable = false;
																//Set non-removable (now Squire can't leave)
																clickedItem.removable = false;
																//Set totem to worship
																//add new
																game.wagon4.squireAdd(c);
																
																scroll2.setSize(new Dimension(380,490));
																remove(itemChoosePane);
																updateFrameDefault(game);
																//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
															}
														});
														changeInfoPane(c,temp,game);
														infoPaneAdd(replace);
														}
														});
													itemChoosePane.add(temp);
												}
		
											clickedItem.useable=false;
											
											infoPane.revalidate();
											infoPane.repaint();
											
										}
									}
								});
								
								
								infoPaneAdd(temp);
								if(!clickedItem.useable)temp.setEnabled(false);
							}
							else if(e.name.equals("Departer's Disquisition"))
							{
								JButton temp = new JButton("Use");
								temp.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent ae) {
										game.huntress=true;
										e.removable=false;
										e.useable=false;
										updateFrameDefault(game);
										//updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
										
									}});
								infoPaneAdd(temp);
								if(!clickedItem.useable)temp.setEnabled(false);
							}
							else if(e.name.equals("Seer's Seminar"))
							{
								JButton temp = new JButton("Use");
								temp.addActionListener(new ActionListener(){
									
									@Override
									public void actionPerformed(ActionEvent ae) {
										
										scroll2.setSize(new Dimension(400,200));
										
										for(int i=infoPane.getComponents().length-1; i>0; i--) {
											infoPane.remove(i);
										}
										
										add(itemChoosePane);
										itemChoosePane.revalidate();
										
										ArrayList<Item> chosenLust = game.getChosenLustGifts();
										
										for(int i = 0; i<chosenLust.size(); i++)
										{
										Item lust = chosenLust.get(i);
											JLabel temp = new JLabel();
											paintImage(chosenLust.get(i), temp);
											temp.addMouseListener(new MouseAdapter(){
												public void mouseClicked(MouseEvent me)
												{
													for(int i=infoPane.getComponents().length-1; i>0; i--) {
														infoPane.remove(i);
													}
													ArrayList<Item> unChosenLust = game.getUnChosenLustGifts(chosenLust);
													for(int i = 0; i<unChosenLust.size();i++)
													{
														Item gift = unChosenLust.get(i);
														JLabel temp = new JLabel();
														paintImage(gift,temp);
														temp.addMouseListener(new MouseAdapter(){
															public void mouseClicked(MouseEvent mee)
															{
																JButton temp = new JButton("Swap");
																temp.addActionListener(new ActionListener(){

																	@Override
																	public void actionPerformed(ActionEvent arg0) {
																		e.useable=false;
																		e.removable=false;
																		game.replaceLustGift(lust.name, gift);
																		scroll2.setSize(new Dimension(380,490));
																		remove(itemChoosePane);
																		updateFrameDefault(game);
																		
																	}});
																changeInfoPane(gift,null,game);
																infoPaneAdd(temp);
															}
														});
														infoPaneAdd(temp);
													}
												}
											});
											
											itemChoosePane.add(temp);
											
										}
										ArrayList<Item> chosenDowries = game.getChosenDowries();
										for(int i = 0; i<chosenDowries.size();i++)
										{
										Item dow = chosenDowries.get(i);
										if(dow.removable){
											
											JLabel temp = new JLabel();
											paintImage(chosenDowries.get(i), temp);
											temp.addMouseListener(new MouseAdapter(){
												public void mouseClicked(MouseEvent me)
												{
													for(int i=infoPane.getComponents().length-1; i>0; i--) {
														infoPane.remove(i);
													}
													ArrayList<Item> unChosenDowries = game.getUnChosenDowries(chosenDowries);
													for(int i = 0; i<unChosenDowries.size();i++)
													{
														Item gift = unChosenDowries.get(i);
														JLabel temp = new JLabel();
														paintImage(gift,temp);
														temp.addMouseListener(new MouseAdapter(){
															public void mouseClicked(MouseEvent mee)
															{
																JButton temp = new JButton("Swap");
																temp.addActionListener(new ActionListener(){

																	@Override
																	public void actionPerformed(ActionEvent arg0) {
																		e.useable=false;
																		e.removable=false;
																		game.replaceDowry(dow.name, gift);
																		scroll2.setSize(new Dimension(380,490));
																		remove(itemChoosePane);
																		updateFrameDefault(game);
																		
																	}});
																changeInfoPane(gift,null,game);
																infoPaneAdd(temp);
															}
														});
														infoPaneAdd(temp);
													}
												}
											});
											
											itemChoosePane.add(temp);
											
										}
										}
										
									}
								});
								infoPaneAdd(temp);
								if(!clickedItem.useable)temp.setEnabled(false);
							}
							else if(e.name.equals("Lunatic's Lecture"))
							{
								JButton temp = new JButton("Use");
								temp.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent arg0) {
										
										scroll2.setSize(new Dimension(400,200));
										
										for(int i=infoPane.getComponents().length-1; i>0; i--) {
											infoPane.remove(i);
										}
										
										add(itemChoosePane);
										itemChoosePane.revalidate();
										
										ArrayList<Character> chosen = game.getChosenCharacters();
										for(int i = 0; i<chosen.size(); i++)
										{
											JLabel temp = new JLabel();
											Character exchange = chosen.get(i);
											paintImage(exchange,temp);
											
											temp.addMouseListener(new MouseAdapter(){
												public void mouseClicked(MouseEvent me)
												{
													ArrayList<Character> all = game.getAllCharacters();
													for(int i = 0; i < all.size(); i++)
													{
														Character source = all.get(i);
														JLabel temp = new JLabel();
														paintImage(all.get(i),temp);
														temp.addMouseListener(new MouseAdapter(){
															public void mouseClicked(MouseEvent me)
															{
																JButton temp = new JButton("Swap");
																changeInfoPane(source, null, game);
																temp.addActionListener(new ActionListener(){

																	@Override
																	public void actionPerformed(ActionEvent arg0) {
																		exchange.good=source.good;
																		e.useable=false;
																		e.removable=false;
																		scroll2.setSize(new Dimension(380,490));
																		remove(itemChoosePane);
																		updateFrameDefault(game);
																		
																	}
																});
																infoPaneAdd(temp);
															}
														});
														infoPaneAdd(temp);
													}
												}
											});
											
											itemChoosePane.add(temp);
										}
									}});
								infoPaneAdd(temp);
								if(!clickedItem.useable)temp.setEnabled(false);
							}
							else if(e.name.equals("Custodian's Conference"))
							{
								JButton temp = new JButton("Use");
								temp.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent arg0) {
										
										scroll2.setSize(new Dimension(400,200));
										
										for(int i=infoPane.getComponents().length-1; i>0; i--) {
											infoPane.remove(i);
										}
										
										add(itemChoosePane);
										itemChoosePane.revalidate();
										ArrayList<Character> chosenChars = game.getChosenCharacters();
										for(int i = 0; i < chosenChars.size(); i++)
										{
											Character c = chosenChars.get(i);
											JLabel temp = new JLabel();
											paintImage(chosenChars.get(i), temp);
											
											temp.addMouseListener(new MouseAdapter(){
												
												public void mouseClicked(MouseEvent me)
												{
													for(int i=infoPane.getComponents().length-1; i>0; i--) {
														infoPane.remove(i);
													}
													for(int j = 0; j < 2; j++)
													{
														int index = j;
														JLabel temp = new JLabel();
														temp.setText(c.bad[j]);
														temp.addMouseListener(new MouseAdapter(){
															public void mouseClicked(MouseEvent me)
															{
																c.bad[index]="";
																e.useable=false;
																e.removable=false;
																
																scroll2.setSize(new Dimension(380,490));
																remove(itemChoosePane);
																updateFrameDefault(game);
															}
														});
														
														infoPaneAdd(temp);
														
													}
												}
												
											});
											
											
											itemChoosePane.add(temp);
										}
									}});
								infoPaneAdd(temp);
								if(!clickedItem.useable)temp.setEnabled(false);
							}
							else if(e.name.equals("Baroque Blandishment"))
							{
								JButton temp = new JButton("Use");
								temp.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent arg0) {
										
										scroll2.setSize(new Dimension(400,200));
										
										for(int i=infoPane.getComponents().length-1; i>0; i--) {
											infoPane.remove(i);
										}
										
										add(itemChoosePane);
										itemChoosePane.revalidate();
										ArrayList<Character> chosenChars = game.getChosenCharacters();
										
										for(int i = 0; i < chosenChars.size(); i++)
										{
											if(chosenChars.get(i) instanceof Princess){
											Character p1 = chosenChars.get(i);
											JLabel temp = new JLabel();
											paintImage(p1,temp);
											temp.addMouseListener(new MouseAdapter(){
												public void mouseClicked(MouseEvent me)
												{
													for(int i=infoPane.getComponents().length-1; i>0; i--) {
														infoPane.remove(i);
													}
													for(int i = 0; i < chosenChars.size(); i++)
													{

														Character p2 = chosenChars.get(i);
														if(p2 instanceof Princess && !p2.name.equals(p1.name))
														{
														JLabel temp = new JLabel();
														paintImage(chosenChars.get(i),temp);
														
														temp.addMouseListener(new MouseAdapter(){
															public void mouseClicked(MouseEvent me)
															{
																JButton temp = new JButton("Forge Alliance");
																changeInfoPane(p2, null, game);
																temp.addActionListener(new ActionListener(){

																	@Override
																	public void actionPerformed(ActionEvent ae) {
																		
																		Effect temp = new Effect("Unbreakable Alliance", "src/Effect icons/Noble Courtier.png", -1);
																		temp.description="An alliance has been forged between " + p1.name + " of " + p1.kingdom + " and " + p2.name + " of " + p2.kingdom + ".";
																		
																		p1.effects.add(temp);
																		p2.effects.add(temp);
																		
																		p1.removable=false;
																		p2.removable=false;
																		
																		e.useable=false;
																		e.removable=false;
																		
																		scroll2.setSize(new Dimension(380,490));
																		remove(itemChoosePane);
																		updateFrameDefault(game);
																	}
																});
																infoPaneAdd(temp);
															}
														});
														
														infoPaneAdd(temp);
														}
													}
												}
											});
											
											itemChoosePane.add(temp);
											}
										}
										
									}});
								infoPaneAdd(temp);
								if(!clickedItem.useable)temp.setEnabled(false);
							}
								
						
					} else {
						changeInfoPane(clickedItem, comp, game);
						JButton temp = new JButton("Remove");
						if(!clickedItem.removable) temp.setEnabled(false); 
						temp.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent ae) {
								if(clickedItem.removable) {
									game.getWagon().removeItem(clickedItem.name);
									updateFrameDefault(game);
									//updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
								}
							}
						});
						if(game.inOtherWagon(clickedItem.name))temp.setEnabled(false);
						
						changeInfoPane(clickedItem, comp, game);
						infoPaneAdd(temp);
						
						if(clickedItem.name.equals("Amethyst Gossamer")) {
							//mess with panes
							temp = new JButton("Use");
							temp.addActionListener(new ActionListener(){
								
								@Override
								public void actionPerformed(ActionEvent e) {
									scroll2.setSize(new Dimension(400,200));
									
									infoPane.remove(1);
									infoPane.remove(1);
									
									add(itemChoosePane);
									itemChoosePane.revalidate();
									
										//slap dowries onto pane
										for(int q=0; q<game.getPrincess1List().size()-1; q++) {
											if(!game.getWagon().contains(game.getPrincess1List().get(q).dowry1.name)) {
												JLabel temp = new JLabel();
												paintImage(game.getPrincess1List().get(q).dowry1, temp);
												Item dowry = game.getPrincess1List().get(q).dowry1;
												temp.addMouseListener(new MouseAdapter() {
													@Override
													public void mouseClicked(MouseEvent me) {
													//add replace button
													JButton replace = new JButton("Replace");
													replace.addMouseListener(new MouseAdapter() {
														@Override
														public void mouseClicked(MouseEvent me) {
															//Set unusable
															clickedItem.useable = false;
															//remove old
															int source = game.removeFromAllWagons("Amethyst Gossamer");
															
															//Set non-removable (now Drider can't leave)
															clickedItem.removable = false;
															clickedItem.useable=false;
															//add new
															game.getWagon(source).addItem(dowry);
															
															scroll2.setSize(new Dimension(380,490));
															remove(itemChoosePane);
															updateFrameDefault(game);
														}
													});
													changeInfoPane(dowry,temp,game);
													infoPaneAdd(replace);
													}
													});
												itemChoosePane.add(temp);
											}
											if(!game.getWagon().contains(game.getPrincess1List().get(q).dowry2.name)) {
												JLabel temp = new JLabel();
												paintImage(game.getPrincess1List().get(q).dowry2, temp);
												Item dowry = game.getPrincess1List().get(q).dowry2;
												temp.addMouseListener(new MouseAdapter() {
													@Override
													public void mouseClicked(MouseEvent me) {
													//add replace button
													JButton replace = new JButton("Replace");
													replace.addMouseListener(new MouseAdapter() {
														@Override
														public void mouseClicked(MouseEvent me) {
															//Set unusable
															clickedItem.useable = false;
															//remove old
															game.getWagon().removeItem("Amethyst Gossamer");
															//Set non-removable (now Drider can't leave)
															clickedItem.removable = false;
															//add new
															game.getWagon().addItem(dowry);
															
															scroll2.setSize(new Dimension(380,490));
															remove(itemChoosePane);
															updateFrameDefault(game);
															//updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
														}
													});
													changeInfoPane(dowry,temp,game);
													infoPaneAdd(replace);
													}
													});
												itemChoosePane.add(temp);
											}
	
										clickedItem.useable=false;
										infoPane.revalidate();
										infoPane.repaint();
									}
								}});
							infoPaneAdd(temp);
							
						} else if(clickedItem.name.equals("Encrusted Chest")) {
							//mess with panes
							
							temp = new JButton("Use");
							temp.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent e) {
									scroll2.setSize(new Dimension(400,200));
									
									infoPane.remove(1);
									infoPane.remove(1);
									
									add(itemChoosePane);
									itemChoosePane.revalidate();
									
									for(int i=0; i<game.wagon1.spaces.length; i++) {
										if(game.wagon1.spaces[i] instanceof Princess1) {
											JLabel temp = new JLabel();
											paintImage(game.wagon1.spaces[i], temp);
											Princess1 p = (Princess1) game.wagon1.spaces[i];
											temp.addMouseListener(new MouseAdapter() {
												@Override
												public void mouseClicked(MouseEvent me) {
													
													JLabel dow = new JLabel();
	
													if(!game.wagon1.contains(p.dowry1.name)) {
													paintImage(p.dowry1, dow);
													dow.addMouseListener(new MouseAdapter() {
														@Override
														public void mouseClicked(MouseEvent me) {
															//add replace button
															JButton replace = new JButton("Replace");
															replace.addMouseListener(new MouseAdapter() {
																@Override
																public void mouseClicked(MouseEvent me) {
																	//remove old
																	game.wagon1.removeItem(p.name);
																	//Set non-removable (now Dragon can't leave)
																	clickedItem.removable = false;
																	//add new
																	game.wagon1.addItem(p.dowry1);
																	
																	scroll2.setSize(new Dimension(380,490));
																	remove(itemChoosePane);
																	updateFrameDefault(game);
																	//updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
																}
															});	
															
															
															changeInfoPane(p.dowry1, replace, game);
															infoPaneAdd(replace);
														}
													});
													}
													infoPane.removeAll();
													infoPane.add(dow);
													
													dow = new JLabel();
													if(!game.wagon1.contains(p.dowry2.name)) {
													paintImage(p.dowry2, dow);
													dow.addMouseListener(new MouseAdapter() {
														@Override
														public void mouseClicked(MouseEvent me) {
															//add replace button
															JButton replace = new JButton("Replace");
															replace.addMouseListener(new MouseAdapter() {
																@Override
																public void mouseClicked(MouseEvent me) {
																	//remove old
																	game.wagon1.removeItem(p.name);
																	//Set non-removable (now Dragon can't leave)
																	clickedItem.removable = false;
																	//add new
																	game.wagon1.addItem(p.dowry2);
																	
																	scroll2.setSize(new Dimension(380,490));
																	remove(itemChoosePane);
																	updateFrameDefault(game);
																	//updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
																}
															});	
														
															changeInfoPane(p.dowry2, replace, game);
															infoPaneAdd(replace);
														}
													});
													}
													infoPaneAdd(dow);
												}
											});
											
											itemChoosePane.add(temp);
										}
									}
									for(int i=0; i<game.wagon2.spaces.length; i++) {
										if(game.wagon2.spaces[i] instanceof Princess1) {
											JLabel temp = new JLabel();
											paintImage(game.wagon2.spaces[i], temp);
											Princess1 p = (Princess1) game.wagon2.spaces[i];
											temp.addMouseListener(new MouseAdapter() {
												@Override
												public void mouseClicked(MouseEvent me) {
													
													JLabel dow = new JLabel();
	
													if(!game.wagon2.contains(p.dowry1.name)) {
													paintImage(p.dowry1, dow);
													dow.addMouseListener(new MouseAdapter() {
														@Override
														public void mouseClicked(MouseEvent me) {
															//add replace button
															JButton replace = new JButton("Replace");
															replace.addMouseListener(new MouseAdapter() {
																@Override
																public void mouseClicked(MouseEvent me) {
																	//remove old
																	game.wagon2.removeItem(p.name);
																	//Set non-removable (now Dragon can't leave)
																	clickedItem.removable = false;
																	//add new
																	game.wagon2.addItem(p.dowry1);
																	
																	scroll2.setSize(new Dimension(380,490));
																	remove(itemChoosePane);
																	updateFrameDefault(game);
																	//updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
																}
															});	
															
															
															changeInfoPane(p.dowry1, replace, game);
															infoPaneAdd(replace);
														}
													});
													}
													infoPane.removeAll();
													infoPane.add(dow);
													
													dow = new JLabel();
													if(!game.wagon2.contains(p.dowry2.name)) {
													paintImage(p.dowry2, dow);
													dow.addMouseListener(new MouseAdapter() {
														@Override
														public void mouseClicked(MouseEvent me) {
															//add replace button
															JButton replace = new JButton("Replace");
															replace.addMouseListener(new MouseAdapter() {
																@Override
																public void mouseClicked(MouseEvent me) {
																	//remove old
																	game.wagon2.removeItem(p.name);
																	//Set non-removable (now Dragon can't leave)
																	clickedItem.removable = false;
																	//add new
																	game.wagon2.addItem(p.dowry2);
																	
																	scroll2.setSize(new Dimension(380,490));
																	remove(itemChoosePane);
																	updateFrameDefault(game);
																	//updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
																}
															});	
														
															changeInfoPane(p.dowry2, replace, game);
															infoPaneAdd(replace);
														}
													});
													}
													infoPaneAdd(dow);
												}
											});
											
											itemChoosePane.add(temp);
										}
									}
								}});
							infoPaneAdd(temp);
						}
					}
				
				}
			});
		}
	///HELPERS///
	
	
	
	/**
	 * Grabs girl from component name and resizes it, then slaps it onto JLabel
	 * @param item
	 * @param comp
	 */
	void paintImage(Item item, Component comp) {
		BufferedImage bImg = null;
		try {
			bImg = ImageIO.read(new File(item.image));
		} catch (IOException e) {
			System.out.println("No file");
			e.printStackTrace();
		}
		BufferedImage resize = new BufferedImage((int)(bImg.getWidth()*.5),(int)(bImg.getHeight()*.5),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)resize.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(bImg, 0, 0, (int)(bImg.getWidth()*.5), (int)(bImg.getHeight()*.5), null);
		
		comp.setSize((int)(bImg.getWidth()*.5),(int)(bImg.getHeight()*.5));
		ImageIcon icon = new ImageIcon(resize);
		((JLabel) comp).setIcon(icon);
	}
	
	/**
	 * Grabs icon from location and resizes it, then slaps it onto JLabel
	 * @param loc
	 * @param comp
	 */
	void paintImage(String loc, Component comp) {
		BufferedImage bImg = null;
		try {
			bImg = ImageIO.read(new File(loc));
		} catch (IOException e) {
			System.out.println("No file");
			e.printStackTrace();
		}
		BufferedImage resize = new BufferedImage((int)(bImg.getWidth()*.5),(int)(bImg.getHeight()*.5),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)resize.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(bImg, 0, 0, (int)(bImg.getWidth()*.5), (int)(bImg.getHeight()*.5), null);
		
		comp.setSize((int)(bImg.getWidth()*.5),(int)(bImg.getHeight()*.5));
		ImageIcon icon = new ImageIcon(resize);
		((JLabel) comp).setIcon(icon);
	}

	/**
	 * Called to grab fonts from src file location.
	 * @param src
	 * @return font
	 */
	Font grabFonts(String src) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, new File(src));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	void infoPaneAdd(Component comp) {
		infoPane.add(comp);
		infoPane.revalidate();
		infoPane.repaint();
	}
	
	
	/**
	 * Update the entire frame.
	 * @param game
	 * @param i
	 */
	public void updateFrame(GameManager game, Item i){
		
		wagonPane1.removeAll();
		wagonPane2.removeAll();
		wagonPane3.removeAll();
		wagonPane4.removeAll();
		wagonPane(game);
		wagonPane1.repaint();
		wagonPane2.repaint();
		wagonPane3.repaint();
		wagonPane4.repaint();
		
		statPane.removeAll();
		statPane(game);
		statPane.repaint();

		itemChoosePane.removeAll();
		
		changeInfoPane(i, princessPane, game);
		
		princessPane.removeAll();
		princessPane(game);
		princessPane.repaint();
	}
	
	public void updateFrameDefault(GameManager game)
	{
		if(game.pageNumber<3)updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
		else if(game.pageNumber==3)updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
		else if(game.pageNumber==4)updateFrame(game, game.getPrincess3List().get(game.getPrincess3List().size()-1));
	}
}
	