package towerpackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

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


public class HoverTest extends JFrame {
	
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
				
				
				//Put out frame
				try {
					HoverTest frame = new HoverTest(game);
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
	HoverTest(GameManager game) {
		
		//Set Frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 930, 550);
		JPanel framePane = new JPanel();
		framePane.setLayout(null);
		setContentPane(framePane);
		
		princessPane = new JPanel();
		princessPane.setLayout(new WrapLayout());
		
		princessPane(game);//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH
		
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
		
		changeInfoPane(game.getPrincess1List().get(game.getPrincess1List().size()-1), null, infoPane, game);//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH
		
		itemChoosePane = new JPanel();
		itemChoosePane.setSize(new Dimension(380,280));
		itemChoosePane.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
		itemChoosePane.setLocation(340,220);

		wagonPane = new JPanel();
		wagonPane.setSize(new Dimension(170,360));
		wagonPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		wagonPane.setLocation(730,10);
		
		wagonPane(game);//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH
		
		add(wagonPane);
		
		statPane = new JPanel();
		statPane.setSize(new Dimension(170,120));
		statPane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		statPane.setLocation(730,380);
		statPane.setLayout(new MigLayout("", "[][]", "[][][][]"));
		
		statPane(game);//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH
		
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
					addItemClick(temp, game.getPrincess1List().get(i), null, game);
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
					addItemClick(temp, game.getPrincess2List().get(i), null, game);
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
	void changeInfoPane(Item clicked, Item itemUsed, Component comp, GameManager game) {
		
		infoPane.removeAll();
		princessCard = new PrincessTesting(clicked);
		infoPane.add(princessCard.provideInput());
		
		//Add button
		JButton button = new JButton(); {
			if(comp.getParent() == itemChoosePane) {
				button.setText("Replace");
				addItemReplaceClick(itemUsed, clicked, button, game);
			}
			if(comp.getParent() == princessPane) {
				button.setText("Add");
				addAddClick(clicked, button, game);
				
				if(clicked instanceof Princess1) {
					Princess1 p = (Princess1) clicked;
					if(game.getWagon().contains(p.dowry1.name)) princessCard.removeDowries(1);
					if(game.getWagon().contains(p.dowry2.name)) princessCard.removeDowries(2);
				}
				
				if(clicked instanceof Princess2) {
					Princess2 p = (Princess2) clicked;
					p.totem.whichEffect = 0;
					game.getWagon().removeEffect(p.totem.name);
				}
				
				int count = 0;
				for(int q=1; q<game.getWagon().spaces.length; q++) {
				    if(game.getWagon().spaces[q] != null) {
				        count++;
				    }
				}
				if(count==4) button.setEnabled(false);
			
			} else if(comp.getParent() == wagonPane) {
				button.setText("Remove");
				addRemoveClick(clicked, button, game);
				if(clicked instanceof Princess1) {
					Princess1 p = (Princess1) clicked;
					if(!game.getWagon().contains(p.dowry1.name)) princessCard.removeDowries(1);
					if(!game.getWagon().contains(p.dowry2.name)) princessCard.removeDowries(2); 
					
					if((!p.dowry1.removable) || !p.dowry2.removable) button.setEnabled(false);
				}
				if(clicked instanceof Princess2) {
					Princess2 p = (Princess2) clicked;
					
				}
				
				if(!clicked.removable) button.setEnabled(false);
			}
		};

		if(!(comp == princessPane) && !(clicked.name.equals("Template")) && !(clicked.name.equals("Example"))) infoPane.add(button);
		
		if(clicked.useable) {
			JButton useButton = new JButton("Use");
			addUseClick(clicked, useButton, game);
			if(!(comp.getParent()==infoPane)) infoPane.add(useButton);
		}
		
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
				addItemClick(temp, game.getWagon().spaces[i], null, game);
			}	
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
		
		for(int i=0; i<icons.length; i++) {
			JLabel tempNum = new JLabel();
			tempNum.setText(Integer.toString(game.getStats()[i]));
			statPane.add(tempNum, "cell 1 " + (2 + i));
		}
		
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
					
				}
			}
		});
		statPane.add(next, "cell 1 4 0 2");
	}

	///CLICK LISTENERS//
	
	/**
	 * Add click listener to a component (princess or item)
	 * @param comp
	 * @param i
	 * @param game
	 */
	void addItemClick(Component comp, Item i, Item itemUsed, GameManager game) {
		comp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				changeInfoPane(i, itemUsed, comp, game);
			}
		});
	}
	
	void addAddClick(Item i, JButton comp, GameManager game) {
		comp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					boolean succeed = game.getWagon().add(i);
					//Add chosen items + princess
					if(i instanceof Princess1 && succeed) {
						Princess1 p = (Princess1) i;
						if(princessCard.getChosen()[0]) game.getWagon().add(p.dowry1);
						if(princessCard.getChosen()[1]) game.getWagon().add(p.dowry2);
					}
					
					if(i instanceof Princess2 && succeed) {
						Princess2 p = (Princess2) i;
						if(princessCard.getChosen()[0]) {
							p.totem.whichEffect = 1;
							game.getWagon().add(p.totem);
						}
						if(princessCard.getChosen()[1]) {
							p.totem.whichEffect = 2;
							game.getWagon().add(p.totem);
						}
					}
					updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
					infoPane.revalidate();
					infoPane.repaint();
				}
		});
	}
	
	void addRemoveClick(Item i, JButton comp, GameManager game) {
		comp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					if(i instanceof Princess1) {
						Princess1 p = (Princess1) i;
						
						if((p.dowry1.removable) && p.dowry2.removable) {
							game.getWagon().removeItem(p.name);
							game.getWagon().removeItem(p.dowry1.name);
							game.getWagon().removeItem(p.dowry2.name);
						}
					} else {
						game.getWagon().removeItem(i.name);
					}
					updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
			}
		});
	}
	
	void addUseClick(Item i, JButton comp, GameManager game) {
		comp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//mess with panes
				infoPane.setSize(new Dimension(380,200));
				
				infoPane.remove(1);
				infoPane.remove(1);
				
				add(itemChoosePane);
				
				if(i.name.equals("Amethyst Gossamer")) {
					//slap dowries onto pane
					for(int q=0; q<game.getPrincess1List().size()-1; q++) {
						if(!game.getWagon().contains(game.getPrincess1List().get(q).dowry1.name)) {
							JLabel temp = new JLabel();
							paintImage(game.getPrincess1List().get(q).dowry1, temp);
							addItemClick(temp, game.getPrincess1List().get(q).dowry1, i, game);
							itemChoosePane.add(temp);
						}
						if(!game.getWagon().contains(game.getPrincess1List().get(q).dowry2.name)) {
							JLabel temp = new JLabel();
							paintImage(game.getPrincess1List().get(q).dowry2, temp);
							addItemClick(temp, game.getPrincess1List().get(q).dowry2, i, game);
							itemChoosePane.add(temp);
						}
					}

					i.useable=false;
					infoPane.revalidate();
					infoPane.repaint();
				} else if(i.name.equals("Encrusted Chest")) {
					//slap wagon'd princesses onto pane
					for(Item item : game.getWagon().spaces) {
						if(item != null && item instanceof Princess1) {
							JLabel temp = new JLabel();
							paintImage(item, temp);
							temp.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent me) {
									itemChoosePane.removeAll();
									
									JLabel temp = new JLabel();
									paintImage(((Princess1)item).dowry1, temp);
									addItemClick(temp, ((Princess1)item).dowry1, item, game);
									itemChoosePane.add(temp);
									
									temp = new JLabel();
									paintImage(((Princess1)item).dowry2, temp);
									addItemClick(temp, ((Princess1)item).dowry2, item, game);
									itemChoosePane.add(temp);
									
									itemChoosePane.revalidate();
									itemChoosePane.repaint();
								}
								
							});
							itemChoosePane.add(temp);
						}
					}
					infoPane.revalidate();
					infoPane.repaint();
				}
			}
		});
	}

	void addItemReplaceClick (Item usedItem, Item clickedItem, JButton comp, GameManager game) {
		comp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				game.getWagon().removeItem(usedItem.name);
				game.getWagon().add(clickedItem);
				infoPane.setSize(new Dimension(380,490));
				remove(itemChoosePane);
				
				updateFrame(game, game.getPrincess1List().get(game.getPrincess1List().size()-1));
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
		
		changeInfoPane(i, null, princessPane, game);
		
		princessPane.removeAll();
		princessPane(game);
		princessPane.repaint();
	}

}