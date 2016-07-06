package testing;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import testing.TextFileTesting;

//¯\_(:))_/¯

//o                 /' ) 
///'   (                          ,
//__/'     )                        .' `;
//o      _.-~~~~'          ``---..__             .'   ;
//_.--'  b)                       ``--...____.'   .'
//(     _.      )).      `-._                     <
//`\|\|\|\|)-.....___.-     `-.         __...--'-.'.
//`---......_______,,,`.___.'----... .'         `.;
//              `-`           ` 



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
		setBounds(100, 100, 700, 510);
		princessPane = new JPanel();
		princessPane.setLayout(null);
		addHover(princessPane);
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
	}
	
	void paintPrincesses(String[] princessOrder, ArrayList<String> exclude) {
		
		//Slap girls onto the window according to princessOrder
		int xCoord = 10;
		int yCoord = 10;
		for(int i=0; i<princessOrder.length; i++) {
			for(int q=0; q<4 && q+i<princessOrder.length; q++) {
				if(!exclude.contains(princessOrder[i+q])) {
					JLabel temp = new JLabel();
					//Set the name of the JLabel, used for lookup later
					temp.setName(princessOrder[i+q]);
					princessPane.add(temp);
					temp.setLocation(xCoord,yCoord);
					//Paint the image (half size)
					paintImage(temp);
					//Add mouse hover listener
					addHover(temp);
				}
				
				xCoord += 70;
			}
			i+=3;
			xCoord = 10;
			yCoord += 90;
		}
	}
	
	/*
	 * Grabs girl from component name and resizes it, then slaps it onto JLabel
	 */
	void paintImage(Component comp) {
		BufferedImage bImg = null;
		try {
			bImg = ImageIO.read(new File("src/Girls/" + comp.getName() + ".png"));
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
//			@Override
//			public void mouseEntered(MouseEvent me) {
//				window = new JWindow();
//				
//				TextFileTesting test = new TextFileTesting();
//				try {
//					test.readTextFile("src/Text files/Princesses.txt", comp.getName());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				PrincessTesting princess = new PrincessTesting(test.princessBuild);
//				
//				window.add(princess.provideInput());
//				window.setPreferredSize(new Dimension(364,450));
//				window.pack();
//				window.setLocation(me.getLocationOnScreen());
//				window.setVisible(true);
//			}
//			@Override
//			public void mouseExited(MouseEvent me) {
//				window.dispose();
//			}

			@Override
			public void mouseClicked(MouseEvent me) {
//				chosen.add(me.getComponent().getName());
//				princessPane.removeAll();
//				window.dispose();
//				princessPane.revalidate();
//				princessPane.repaint();
//				paintPrincesses(new String[]{"Kobold","Human","Insect","Skeleton","Slime","Mermaid","Knight","Harpy","Boy","Orc","Dwarf","Amazon","Ghost","Golem","Succubus","Goblin","Drider","Mimic","Dragon"},chosen);
//				window.dispose();
				
				TextFileTesting test = new TextFileTesting();
				try {
					test.readTextFile("src/Text files/Princesses.txt", comp.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
				PrincessTesting princess = new PrincessTesting(test.princessBuild);
//				if(!first) window.removeAll();
				choicePane.removeAll();
				choicePane.add(princess.provideInput());
				choicePane.validate();
				choicePane.repaint();
//				choicePane.setPreferredSize(new Dimension(364,450));
//				first = false;
			}
		});
	}

}
