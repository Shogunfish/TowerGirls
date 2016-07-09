package towerpackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

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
	
	//static Wagon wagon;
	//static ArrayList<Princess1> princesses = new ArrayList<Princess1>();
	

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
				String[] princessNames = new String[]{"Kobold","Human","Insect","Skeleton","Slime","Mermaid","Knight","Harpy","Boy","Orc","Dwarf","Amazon","Ghost","Golem","Succubus","Goblin","Drider","Mimic","Dragon","Template"};
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
		changeInfoPane(game.princesses1.get(game.princesses1.size()-1), infoPane, game);

		wagonPane = new JPanel();
		wagonPane.setSize(new Dimension(170,490));
		wagonPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		wagonPane.setLocation(730,10);
		wagonPane(game);
		add(wagonPane);
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
					addClick(temp, game.princesses1.get(i), game);
				}
			}
	}

	/**
	 * Build the right third: wagonPane
	 * @param game
	 */
	void wagonPane(GameManager game) {
		for(int i = 0; i < game.wagon1.spaces.length ; i++)
			{
				JLabel temp = new JLabel();
				if(game.wagon1.spaces[i] != null) {
					temp.setName(game.wagon1.spaces[i].name);
					wagonPane.add(temp);
					paintImage(game.wagon1.spaces[i], temp);
					addClick(temp, game.wagon1.spaces[i], game);
				}	
			}
	}

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
	 * Add click listener to a component (princess or item)
	 * @param comp
	 * @param i
	 * @param game
	 */
	void addClick(Component comp, Item i, GameManager game) {
		comp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				changeInfoPane(i, comp, game);
				
			}
		});
	}
	
	/**
	 * Change the middle third: infoPane
	 * @param i
	 * @param comp
	 * @param game
	 */
	void changeInfoPane(Item i, Component comp, GameManager game) {
		infoPane.removeAll();
		
		//check if wagon shit
		princessCard = new PrincessTesting(i);
		infoPane.add(princessCard.provideInput());
		
		//Add button
		JButton button = new JButton(); {
			if(comp.getParent() == princessPane) {
				button.setText("Add");
				if(i instanceof Princess1) {
					Princess1 p = (Princess1) i;
					if(game.wagon1.contains(p.dowry1.name)) princessCard.removeDowries(1);
					if(game.wagon1.contains(p.dowry2.name)) princessCard.removeDowries(2);
				}
			} else if(comp.getParent() == wagonPane) {
				button.setText("Remove");
				if(i instanceof Princess1) {
					Princess1 p = (Princess1) i;
					if(!game.wagon1.contains(p.dowry1.name)) princessCard.removeDowries(1);
					if(!game.wagon1.contains(p.dowry2.name)) princessCard.removeDowries(2); 
					
					if((!p.dowry1.removable) || !p.dowry2.removable) {
						button.setEnabled(false);
					}
				}
				if(!i.removable) button.setEnabled(false);
			}
			addButtonClick(i, button, game);
		};
		if(!(comp == princessPane)) infoPane.add(button);
		
		if(i.useable) {
			JButton useButton = new JButton("Use");
			addButtonClick(i, useButton, game);
			if(!(comp.getParent()==infoPane)) infoPane.add(useButton);
		}
		infoPane.validate();
		infoPane.repaint();
		button.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	/**
	 * Add click listener to the add/remove button 
	 * @param i
	 * @param comp
	 * @param game
	 */
	void addButtonClick(Item i, Component comp, GameManager game) {
		comp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				if(((AbstractButton) comp).getText().equals("Add")) {
					boolean succeed = game.wagon1.add(i);
					//Add items
					if(i instanceof Princess1 && succeed) {
						Princess1 p = (Princess1) i;
						if(princessCard.getChosen()[0]) game.wagon1.add(p.dowry1);
						if(princessCard.getChosen()[1]) game.wagon1.add(p.dowry2);
					}
					((AbstractButton) comp).setText("Remove");
					updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
				} else if(((AbstractButton) comp).getText().equals("Remove")) {
					if(i instanceof Princess1) {
						Princess1 p = (Princess1) i;
						
						if((p.dowry1.removable) && p.dowry2.removable) {
							game.wagon1.removeItem(p.name);
							game.wagon1.removeItem(p.dowry1.name);
							game.wagon1.removeItem(p.dowry2.name);

							((AbstractButton) comp).setText("Add");
						}
					} else {
						game.wagon1.removeItem(i.name);

						((AbstractButton) comp).setText("Add");
					}
					updateFrame(game, game.princesses1.get(game.princesses1.size()-1));
				} else if(((AbstractButton) comp).getText().equals("Use")) {
					updateFrame(game, i.use(game));
				}
			}
		});
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
		
		changeInfoPane(i, princessPane, game);
		
		princessPane.removeAll();
		princessPane(game);
		princessPane.repaint();
	}

}