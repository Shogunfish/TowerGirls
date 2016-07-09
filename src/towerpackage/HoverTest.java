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
		changeInfoPane(game.princesses1.get(game.princesses1.size()-1));

		wagonPane = new JPanel();
		wagonPane.setSize(new Dimension(170,490));
		wagonPane.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		wagonPane.setLocation(730,10);
		wagonPane(game);
		add(wagonPane);
	}
	
	void princessPane(GameManager game) {
			//Slap girls onto the window according to princessOrder
			for(int i=0; i<game.princesses1.size(); i++) 
			{
				if(!Arrays.asList(game.wagon1.spaces).contains(game.princesses1.get(i))) 
				{
					JLabel temp = new JLabel();
					
					//Set the name of the JLabel, used for lookup later
					temp.setName(game.princesses1.get(i).name);
					princessPane.add(temp);
						
					//Paint the image (half size)
					paintImage(game.princesses1.get(i),temp);
						
					//Add mouse hover listener
					addPrincessClick(temp, game.princesses1.get(i), game);
				}
			}
	}
	
	void wagonPane(GameManager game) {
		for(int i = 0; i < game.wagon1.spaces.length ; i++)
			{
				JLabel temp = new JLabel();
				
				if(game.wagon1.spaces[i] != null) {
					temp.setName(game.wagon1.spaces[i].name);
					wagonPane.add(temp);
					paintImage(game.wagon1.spaces[i], temp);
					addWagonClick(temp, game.wagon1.spaces[i], game);
				}	
			}
		
	}

	/**
	 * Grabs girl from component name and resizes it, then slaps it onto JLabel
	 */
	void paintImage(Item item, Component comp) {
		comp.setName(item.name);
		
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

	void addPrincessClick(Component comp, Princess1 p, GameManager game) {
		comp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				changeInfoPane(p, comp, game);
				
			}
		});
	}
	
	void addWagonClick(Component comp, Item i, GameManager game) {
		comp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				changeInfoPane(i, comp, game);
				
			}
		});
	}
	
	void changeInfoPane(Item i, Component comp, GameManager game) {
		infoPane.removeAll();
		princessCard = new PrincessTesting(i);
		infoPane.add(princessCard.provideInput());
		JButton button = new JButton();
		if(comp.getParent() == princessPane) {
			button.setText("Add");
		} else {
			button.setText("Remove");
		}
		
		addButtonClick(i, button, game);
		if(!i.name.equals("Template")) infoPane.add(button);
		
		if(i.useable) {
			JButton useButton = new JButton("Use");
			addButtonClick(i, useButton, game);
			infoPane.add(useButton);
		}
		
		infoPane.validate();
		infoPane.repaint();
	}
	
	void changeInfoPane(Princess1 p) {
		infoPane.removeAll();
		princessCard = new PrincessTesting(p);
		infoPane.add(princessCard.provideInput());
		infoPane.validate();
		infoPane.repaint();
	}
	
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
					
					//Repaint 
					wagonPane.removeAll();
					wagonPane(game);
					wagonPane.repaint();
					
					changeInfoPane(game.princesses1.get(game.princesses1.size()-1));
					
					princessPane.removeAll();
					princessPane(game);
					princessPane.repaint();
					
				} else if(((AbstractButton) comp).getText().equals("Remove")) {
					if(i instanceof Princess1) {
						Princess1 p = (Princess1) i;
						
						if(p.dowry1.removable && p.dowry2.removable)
						{
							game.wagon1.removeItem(p.name);
						game.wagon1.removeItem(p.dowry1.name);
						game.wagon1.removeItem(p.dowry2.name);

						((AbstractButton) comp).setText("Add");
						}
					} else if(i instanceof Item) {
						game.wagon1.removeItem(i.name);
						changeInfoPane(game.princesses1.get(game.princesses1.size()-1));

						((AbstractButton) comp).setText("Add");
					}
					//Repaint 
					wagonPane.removeAll();
					wagonPane(game);
					wagonPane.repaint();
					
					princessPane.removeAll();
					princessPane(game);
					princessPane.repaint();
				} else if(((AbstractButton) comp).getText().equals("Use")) {
					i.use(game);
				}
			}
		});
	}

}