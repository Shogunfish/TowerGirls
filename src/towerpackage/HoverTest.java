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
				game.wagon1 = new Wagon(4, "Wally");
				
				//Populate princess array
				TextFileTesting test = new TextFileTesting();
//				String[] princessNames = new String[]{"Kobold","Human","Insect","Skeleton","Slime","Mermaid","Knight","Harpy","Boy","Orc","Dwarf","Amazon","Ghost","Golem","Succubus","Goblin","Drider","Mimic","Dragon","Template"};
				String[] princessNames = new String[]{"Merrow","Demon","Gnome","Serpent","Kaiju","Rust","Lampad","Orphan","Squid","Shark","Book","Shadow","Gargoyle","Candy","Wyvern","Template"};
				for(String s : princessNames) {
					try {
						game.princesses1.add(test.readTextFile("src/Text Files/Princesses.txt",s));
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
		princessPane.setSize(new Dimension(320,490));
		princessPane.setBorder(BorderFactory.createLineBorder(Color.RED));
		princessPane.setLocation(10,10);
		princessPane(game);
		add(princessPane);
		
		infoPane = new JPanel();
		infoPane.setSize(new Dimension(380,490));
		infoPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		infoPane.setLocation(340,10);
		add(infoPane);
		changeInfoPane(game.princesses1.get(game.princesses1.size()-1), null, infoPane, game);
		
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
			for(int i=0; i<game.princesses1.size(); i++) 
			{
				if(!Arrays.asList(game.wagon1.spaces).contains(game.princesses1.get(i))) 
				{
					JLabel temp = new JLabel();
					temp.setName(game.princesses1.get(i).name);
					princessPane.add(temp);
					paintImage(game.princesses1.get(i),temp);
					addItemClick(temp, game.princesses1.get(i), null, game);
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
					if(game.wagon1.contains(p.dowry1.name)) princessCard.removeDowries(1);
					if(game.wagon1.contains(p.dowry2.name)) princessCard.removeDowries(2);
				}
				
				int count = 0;
				for(int q=1; q<game.wagon1.spaces.length; q++) {
				    if(game.wagon1.spaces[q] != null) {
				        count++;
				    }
				}
				if(count==4) button.setEnabled(false);
			
			} else if(comp.getParent() == wagonPane) {
				button.setText("Remove");
				addRemoveClick(clicked, button, game);
				if(clicked instanceof Princess1) {
					Princess1 p = (Princess1) clicked;
					if(!game.wagon1.contains(p.dowry1.name)) princessCard.removeDowries(1);
					if(!game.wagon1.contains(p.dowry2.name)) princessCard.removeDowries(2); 
					
					if((!p.dowry1.removable) || !p.dowry2.removable) button.setEnabled(false);
				}
				if(!clicked.removable) button.setEnabled(false);
			}
		};

		if(!(comp == princessPane) && !(clicked.name.equals("Template"))) infoPane.add(button);
		
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
		for(int i=0; i<game.wagon1.spaces.length; i++) {
			JLabel temp = new JLabel();
			if(game.wagon1.spaces[i] != null) {
				temp.setName(game.wagon1.spaces[i].name);
				wagonPane.add(temp);
				paintImage(game.wagon1.spaces[i], temp);
				addItemClick(temp, game.wagon1.spaces[i], null, game);
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
			tempNum.setText(Integer.toString(game.wagon1.getStats()[i]));
			statPane.add(tempNum, "cell 1 " + (2 + i));
		}
		
		int count = 0;
		for(int i=1; i<game.wagon1.spaces.length; i++) {
		    if(game.wagon1.spaces[i] != null) {
		        count++;
		    }
		}
		JLabel title = new JLabel("<html>Wagon's space: </html>");
		title.setFont(mainFont.deriveFont(16f));
		statPane.add(title, "cell 0 0 3 2");
		JLabel spaces = new JLabel(count + "/" + (game.wagon1.spaces.length-1));
		spaces.setFont(blockFont.deriveFont(16f));
		statPane.add(spaces, "cell 3 0 0 2");
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
					boolean succeed = game.wagon1.add(i);
					//Add chosen items + princess
					if(i instanceof Princess1 && succeed) {
						Princess1 p = (Princess1) i;
						if(princessCard.getChosen()[0]) game.wagon1.add(p.dowry1);
						if(princessCard.getChosen()[1]) game.wagon1.add(p.dowry2);
					}
					updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
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
							game.wagon1.removeItem(p.name);
							game.wagon1.removeItem(p.dowry1.name);
							game.wagon1.removeItem(p.dowry2.name);
						}
					} else {
						game.wagon1.removeItem(i.name);
					}
					updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
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
					for(int q=0; q<game.princesses1.size()-1; q++) {
						if(!game.wagon1.contains(game.princesses1.get(q).dowry1.name)) {
							JLabel temp = new JLabel();
							paintImage(game.princesses1.get(q).dowry1, temp);
							addItemClick(temp, game.princesses1.get(q).dowry1, i, game);
							itemChoosePane.add(temp);
						}
						if(!game.wagon1.contains(game.princesses1.get(q).dowry2.name)) {
							JLabel temp = new JLabel();
							paintImage(game.princesses1.get(q).dowry2, temp);
							addItemClick(temp, game.princesses1.get(q).dowry2, i, game);
							itemChoosePane.add(temp);
						}
					}
					infoPane.revalidate();
					infoPane.repaint();
				} else if(i.name.equals("Encrusted Chest")) {
					//slap wagon'd princesses onto pane
					for(Item item : game.wagon1.spaces) {
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
				} else if(i.name.equals("Encrusted Chest")) {
					
				}
				i.useable=false;
			}
		});
	}

	void addItemReplaceClick (Item usedItem, Item clickedItem, JButton comp, GameManager game) {
		comp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				game.wagon1.removeItem(usedItem.name);
				game.wagon1.add(clickedItem);
				infoPane.setSize(new Dimension(380,490));
				remove(itemChoosePane);
				
				updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
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