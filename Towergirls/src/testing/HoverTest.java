package testing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
import javax.swing.border.EmptyBorder;

import testing.TextFileTesting;

public class HoverTest extends JFrame {

	private JPanel contentPane;

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
		//TextFileReader.doIt();
	}

	/**
	 * Create the frame.
	 */
	JWindow window;
	public HoverTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel Kobold = new JLabel("");
		contentPane.add(Kobold);
		Kobold.setLocation(10,10);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("../Girls/Kobold.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedImage resize = new BufferedImage((int)(img.getWidth()*.5),(int)(img.getHeight()*.5),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)resize.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(img, 0, 0, (int)(img.getWidth()*.5), (int)(img.getHeight()*.5), null);
		
		Kobold.setSize((int)(img.getWidth()*.5),(int)(img.getHeight()*.5));
		ImageIcon icon = new ImageIcon(resize);
		Kobold.setIcon(icon);
		
		Kobold.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent me) {
				window = new JWindow();
				
				TextFileTesting test = new TextFileTesting();
				try {
					test.readTextFile("src/Text files/Princesses.txt", "Kobold");
				} catch (IOException e) {
					e.printStackTrace();
				}
				PrincessTesting princess = new PrincessTesting(test.givePrincess());
				
				window.add(princess.provideInput());
				window.setSize(300,550);
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
