package towerpackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.coobird.thumbnailator.Thumbnails;

public class shadowTesting extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					shadowTesting frame = new shadowTesting();
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
	public shadowTesting() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//Dragon JLabel
		JLabel Dragon = new JLabel("");
		//Set the name of the JLabel, used for lookup later
		Dragon.setName("Dragon");
		contentPane.add(Dragon);
		Dragon.setLocation(0,0);
		//Paint the image (half size)
		paintImage(Dragon);
		
		JLabel dragonShadow = new JLabel("");
		dragonShadow.setName("Dragon");
		contentPane.add(dragonShadow);
		dragonShadow.setLocation((int) (Dragon.getWidth()/4),(int) (Dragon.getHeight()/4.5));
		paintShadow(dragonShadow);
	}
	
	/**
	 * Paint the image.
	 */
	void paintImage(Component comp) {
		//Grab image
		BufferedImage bImg = null;
		try {
			bImg = ImageIO.read(new File("src/Girls/" + comp.getName() + ".png"));
		} catch (IOException e) {
			System.out.println("No file");
			e.printStackTrace();
		}
		
		//Resize original
		BufferedImage resize = null;
		try {
			resize = resizeCoo(bImg, (int)(bImg.getWidth()*.5), (int)(bImg.getHeight()*.5));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Set component size, add icon to it
		comp.setSize((int)(resize.getWidth()),(int)(resize.getHeight()));
		ImageIcon icon = new ImageIcon(resize);
		((JLabel) comp).setIcon(icon);
	}
	
	/**
	 * Paint the shadow.
	 */
	void paintShadow(Component comp) {
		//Grab image
		BufferedImage bImg = null;
		try {
			bImg = ImageIO.read(new File("src/Girls/" + comp.getName() + ".png"));
		} catch (IOException e) {
			System.out.println("No file");
			e.printStackTrace();
		}
		
		//Resize original, rotate it 90 degrees
		BufferedImage resize = null;
		try {
			resize = Thumbnails.of(bImg)
					.scale(.4)
					.rotate(55)
					.asBufferedImage();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Shadow now
		Color fillColor = new Color(0, 0, 0, .5f); // Black

		for (int y = 0; y < resize.getHeight(); y++) {
		    for (int x = 0; x < resize.getWidth(); x++) {
		        int color = resize.getRGB(x, y);
		        int alpha = (color & 0xff000000) >> 24;

		        if (alpha != 0) {
		        	resize.setRGB(x, y, fillColor.getRGB());
		        }
		    }
		}
		
		//Set component size, add icon to it
		comp.setSize((int)(resize.getWidth()),(int)(resize.getHeight()));
		ImageIcon icon = new ImageIcon(resize);
		((JLabel) comp).setIcon(icon);
	}
	
	public static BufferedImage resizeCoo(BufferedImage img, int newW, int newH) throws IOException {
		  return Thumbnails.of(img).forceSize(newW, newH).asBufferedImage();
		}

}
