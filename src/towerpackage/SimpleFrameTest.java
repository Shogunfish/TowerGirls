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
import javax.swing.AbstractButton;
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
	JPanel princessPane;
	PrincessTesting princessCard;
	JPanel infoPane;
	JPanel wagonPane;
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
				game.wagon1 = new Wagon(4, "Wally");
				game.wagon2 = new Wagon(4, "Michael");
				game.wagon3 = new Wagon(4, "Ganondorf");
				game.wagon4 = new Wagon(8, "Toby");
				
				//Populate princess arrays
				TextFileTesting test = new TextFileTesting();
				String[] princessNames = new String[]{"Kobold","Human","Insect","Skeleton","Slime","Mermaid","Knight","Harpy","Boy","Orc","Dwarf","Amazon","Ghost","Golem","Succubus","Goblin","Drider","Mimic","Dragon","Template"};
				for(String s : princessNames) {
					try {
						game.princesses1.add(test.readPrincess1("src/Text Files/Princesses.txt",s));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				princessNames = new String[]{"Merrow","Demon","Gnome","Serpent","Kaiju","Rust","Lampad","Orphan","Squid","Shark","Book","Shadow","Gargoyle","Candy","Wyvern","Template"};
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
				princessNames = new String[]{"Paladin"};
				for(String s : princessNames) {
					try {
						game.princesses4.add(test.readPrincess3("src/Text Files/Princesses.txt",s));
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
		setBounds(100, 100, 930, 550);
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
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		add(scroll);
		
		infoPane = new JPanel();
		infoPane.setSize(new Dimension(380,490));
		infoPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		infoPane.setLocation(340,10);
		add(infoPane);
		
		changeInfoPane(game.getPrincess1List().get(game.getPrincess1List().size()-1), infoPane, game);
		
		itemChoosePane = new JPanel();
		itemChoosePane.setSize(new Dimension(380,280));
		itemChoosePane.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		itemChoosePane.setLocation(340,220);

		wagonPane = new JPanel();
		wagonPane.setSize(new Dimension(170,360));
		wagonPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		wagonPane.setLocation(730,10);
		
		wagonPane(game);
		
		add(wagonPane);
		
		statPane = new JPanel();
		statPane.setSize(new Dimension(170,120));
		statPane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		statPane.setLocation(730,380);
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
		princessCard = new PrincessTesting(clicked, game);
		infoPane.add(princessCard.provideInput());
		infoPane.validate();
		infoPane.repaint();
	}
	
	/**
	 * Build the right third: wagonPane
	 * @param game
	 */
	void wagonPane(GameManager game) {
		for(int i=0; i<game.getWagon().spaces.length; i++) {
			JLabel temp = new JLabel();
			if(game.getWagon().spaces[i] != null) {
				temp.setName(game.getWagon().spaces[i].name);
				wagonPane.add(temp);
				paintImage(game.getWagon().spaces[i], temp);
				addObjectClick(game.getWagon().spaces[i], temp, game);
			}
		}
		for(Effect e : game.getWagon().effects) {
			JLabel temp = new JLabel();
			temp.setName(e.name);
			wagonPane.add(temp);
			paintImage(e, temp);
			addObjectClick(e, temp, game);
		}
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
		JLabel title = new JLabel("<html>Wagon's space: </html>");
		title.setFont(mainFont.deriveFont(16f));
		statPane.add(title, "cell 0 0 3 2");
		JLabel spaces = new JLabel(count + "/" + (game.getWagon().spaces.length-1));
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
			}
		});
		statPane.add(next, "cell 1 4 0 2");
	}

	///CLICK LISTENER//

	void addObjectClick(Item clickedItem, Component comp, GameManager game) {
		comp.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent me) {
				Item infoItem = null;
				if(game.pageNumber <3) infoItem = game.getPrincess1List().get(game.getPrincess1List().size()-1);
				if(game.pageNumber==3) infoItem = game.getPrincess2List().get(game.getPrincess1List().size()-1);

					//This is not an item "use" result
					if(clickedItem instanceof Princess1) {
						Princess1 p = (Princess1)clickedItem;
						//Princess1
						//If this princess is in the wagon
						if(game.getWagon().contains(clickedItem.name)) {
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
										updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
									
								}
							});
							changeInfoPane(clickedItem, comp, game);
							if(!game.getWagon().contains(p.dowry1.name)) princessCard.removeDowries(1);
							if(!game.getWagon().contains(p.dowry2.name)) princessCard.removeDowries(2);
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
									updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
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
						if(game.getWagon().contains(clickedItem.name)) {
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
										updateFrame(game, game.princesses2.get(game.princesses2.size()-1));
								}
							});
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
									updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
								}
							});
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
									
									updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
								}
							});
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
									
									updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
								}
							});
							if(game.getStats()[3] < 3 || game.getWagon().contains(p.totem.name)) temp.setEnabled(false);
							infoPaneAdd(temp);
						}
						//Draw princess
					} else if(clickedItem instanceof Effect) {
						if(clickedItem instanceof Totem) {
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
										infoPane.setSize(new Dimension(380,200));
										
										for(int i=infoPane.getComponents().length-1; i>0; i--) {
											infoPane.remove(i);
										}
										
										add(itemChoosePane);
										
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
																
																infoPane.setSize(new Dimension(380,490));
																remove(itemChoosePane);
																updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
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
																
																infoPane.setSize(new Dimension(380,490));
																remove(itemChoosePane);
																updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
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
										
										updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
									}
								});
								if(!t.removable) temp.setEnabled(false);
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
										
										updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
									}
								});
								infoPaneAdd(temp);
							} else if(Arrays.equals(t.cost, new int[]{0,0,0,0}) && t.whichEffect == 2) {
								//buyout
								JButton temp = new JButton("Buy out: Wealth");
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
										t.cost = new int[]{0,0,-1,0};
										t.whichEffect = 1;
										
										updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
									}
								});
								infoPane.add(temp);
								if(game.getWagon().getStats()[2] < 1) temp.setEnabled(false);
								
								temp = new JButton("Buy out: Power");
								temp.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent ae) {
										t.cost = new int[]{0,0,0,-1};
										t.whichEffect = 1;

										updateFrame(game, game.getPrincess2List().get(game.getPrincess2List().size()-1));
									}
								});
								if(game.getWagon().getStats()[3] < 1) temp.setEnabled(false);
								
								infoPaneAdd(temp);
							}
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
									updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
								}
							}
						});
						changeInfoPane(clickedItem, comp, game);
						infoPaneAdd(temp);
						
						if(clickedItem.useable) {
							temp = new JButton("Use");
							temp.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent ae) {
									//This is a "use" result
									if(clickedItem.name.equals("Amethyst Gossamer")) {
										//mess with panes
										infoPane.setSize(new Dimension(380,200));
										
										infoPane.remove(1);
										infoPane.remove(1);
										
										add(itemChoosePane);
										
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
																game.getWagon().removeItem("Amethyst Gossamer");
																//Set non-removable (now Drider can't leave)
																clickedItem.removable = false;
																//add new
																game.getWagon().addItem(dowry);
																
																infoPane.setSize(new Dimension(380,490));
																remove(itemChoosePane);
																updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
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
																
																infoPane.setSize(new Dimension(380,490));
																remove(itemChoosePane);
																updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
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
									} else if(clickedItem.name.equals("Encrusted Chest")) {
										//mess with panes
										infoPane.setSize(new Dimension(380,200));
										
										infoPane.remove(1);
										infoPane.remove(1);
										
										add(itemChoosePane);
										
										for(int i=0; i<game.getWagon().spaces.length; i++) {
											if(game.getWagon().spaces[i] instanceof Princess1) {
												JLabel temp = new JLabel();
												paintImage(game.getWagon().spaces[i], temp);
												Princess1 p = (Princess1) game.getWagon().spaces[i];
												temp.addMouseListener(new MouseAdapter() {
													@Override
													public void mouseClicked(MouseEvent me) {
														
														JLabel dow = new JLabel();
		
														if(!game.getWagon().contains(p.dowry1.name)) {
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
																		game.getWagon().removeItem(p.name);
																		//Set non-removable (now Dragon can't leave)
																		clickedItem.removable = false;
																		//add new
																		game.getWagon().addItem(p.dowry1);
																		
																		infoPane.setSize(new Dimension(380,490));
																		remove(itemChoosePane);
																		updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
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
														if(!game.getWagon().contains(p.dowry2.name)) {
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
																		game.getWagon().removeItem(p.name);
																		//Set non-removable (now Dragon can't leave)
																		clickedItem.removable = false;
																		//add new
																		game.getWagon().addItem(p.dowry2);
																		
																		infoPane.setSize(new Dimension(380,490));
																		remove(itemChoosePane);
																		updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
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
												
												infoPaneAdd(temp);
											}
										}
		
									} else if(clickedItem.name.equals("Freyda, Dragon Priestess of Ice")) {
									}
								}
							});
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
	public void updateFrame(GameManager game, Item i) {
		wagonPane.removeAll();
		wagonPane(game);
		wagonPane.repaint();
		
		statPane.removeAll();
		statPane(game);
		statPane.repaint();

		itemChoosePane.removeAll();
		
		changeInfoPane(i, princessPane, game);
		
		princessPane.removeAll();
		princessPane(game);
		princessPane.repaint();
	}

}
