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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
	JPanel choicePane;
	ArrayList<String> chosen = new ArrayList<String>();
	JWindow window = new JWindow();
	boolean first = true;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HoverTest frame = new HoverTest();
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
	HoverTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 510);
		princessPane = new JPanel();
		princessPane.setLayout(null);
		setContentPane(princessPane);
		
		choicePane = new JPanel();
		choicePane.setSize(new Dimension(380,450));
		choicePane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		choicePane.setLocation(300,10);
		
		TextFileTesting test = new TextFileTesting();
		try {
			test.readTextFile("src/Text files/Princesses.txt", "Template");
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrincessTesting princess = new PrincessTesting(test.princessBuild);
//		princess.setOutline(Color.RED,princess.dowry1);
		choicePane.add(princess.provideInput());
		
		princessPane.add(choicePane);
		
		paintPrincesses(new String[]{"Kobold","Human","Insect","Skeleton","Slime","Mermaid","Knight","Harpy","Boy","Orc","Dwarf","Amazon","Ghost","Golem","Succubus","Goblin","Drider","Mimic","Dragon","Template"},chosen);
		
		TextFileTesting b = new TextFileTesting();
		try {
			b.readTextFile("src/Text files/Princesses.txt", "Kobold");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Wagon blah = new Wagon(8,"Jimbob");
		for(int i = 0; i<8; i++)
		{
			blah.add(new Item("Kobold", "src/Girls/Kobold.png"));
		}
		paintWagon(blah);
	
	}
	
	void paintPrincesses(String[] princessOrder, ArrayList<String> exclude) {
		
		//Slap girls onto the window according to princessOrder
		int xCoord = 10;
		int yCoord = 10;
		for(int i=0; i<princessOrder.length; i++) 
		{
			
			if(!exclude.contains(princessOrder[i])) 
			{
				JLabel temp = new JLabel();
				
				//Set the name of the JLabel, used for lookup later
				temp.setName(princessOrder[i]);
				princessPane.add(temp);
				temp.setLocation(xCoord,yCoord);
					
				
				TextFileTesting b = new TextFileTesting();
				try {
					b.readTextFile("src/Text files/Princesses.txt", princessOrder[i]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Paint the image (half size)
				paintImage(b.princessBuild,temp);
					
				//Add mouse hover listener
				addHover(temp);
			}
			
			//Make the appropriate location adjustment based on the current index
			if(i%4==3)
			{
			xCoord = 10;
			yCoord += 90;
			}
			else
			{
			xCoord += 70;
			}
		
		}
	}

/*
 * This is the method that paints the wagon. Currently all it does is add two columns of 
 * hover-enabled princesses over to the right of the card. Things it needs include:
 * 
 * It should draw some kind of wagon-like shape around itself
 * 
 * It should display the effects currently in play
 * 
 * It currently will fail if it has a non-princess item inside it, ultimately it should not do that
 * 
 * It should have its own set of interactions which are different from the princesses that are displayed on the left side
 * rather than just using the same hover behavior
 * 
 * It should use a different version of the princess card without dowry items
 * 
 */
	void paintWagon(Wagon wag)
	{
		int xCoord = 700;
		int yCoord = 10;
		
		for(int i = 0; i < wag.spaces.length ; i++)
		{
			
			JLabel temp = new JLabel();
			
			temp.setName(wag.spaces[i].name);
			princessPane.add(temp);
			temp.setLocation(xCoord,yCoord);;
			paintImage(wag.spaces[i], temp);
			addHover(temp);
			
			if(i%2==1)
			{
				xCoord = 700;
				yCoord += 90;
			}
			else
			{
				xCoord+=70;
			}
		}
	}
	
	/*
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

	void addHover(Component comp) {
		comp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				TextFileTesting test = new TextFileTesting();
				try {
					test.readTextFile("src/Text files/Princesses.txt", comp.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
				PrincessTesting princess = new PrincessTesting(test.princessBuild);
				choicePane.removeAll();
				choicePane.add(princess.provideInput());
				choicePane.validate();
				choicePane.repaint();
			}
		});
	}

}