package testing;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.OverlayLayout;

import testing.TextFileTesting;

public class HoverTest extends JFrame {

	JPanel contentPane;

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
	JWindow window;
	HoverTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//Later, I should have this auto-pull from an array
		
		//Kobold JLabel
				JLabel Kobold = new JLabel("");
				//Set the name of the JLabel, used for lookup later
				Kobold.setName("Kobold");
				contentPane.add(Kobold);
				Kobold.setLocation(10,10);
				//Paint the image (half size)
				paintImage(Kobold);
				//Add mouse hover listener
				addHover(Kobold);
				
		//Human JLabel
				JLabel Human = new JLabel("");
				//Set the name of the JLabel, used for lookup later
				Human.setName("Human");
				contentPane.add(Human);
				Human.setLocation(80,10);
				//Paint the image (half size)
				paintImage(Human);
				//Add mouse hover listener
				addHover(Human);
				
		//Knight JLabel
				JLabel Knight = new JLabel("");
				//Set the name of the JLabel, used for lookup later
				Knight.setName("Knight");
				contentPane.add(Knight);
				Knight.setLocation(150,10);
				//Paint the image (half size)
				paintImage(Knight);
				//Add mouse hover listener
				addHover(Knight);
		
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
			@Override
			public void mouseEntered(MouseEvent me) {
				window = new JWindow();
				
				TextFileTesting test = new TextFileTesting();
				try {
					test.readTextFile("src/Text files/Princesses.txt", comp.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
				PrincessTesting princess = new PrincessTesting(test.givePrincess());
				window.add(princess.provideInput());
				window.setSize(350,475);
				window.setLocation(me.getLocationOnScreen());
				window.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent me) {
				window.dispose();
			}
		});
	}

}
