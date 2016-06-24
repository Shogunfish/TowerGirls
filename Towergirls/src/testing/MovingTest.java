package testing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class MovingTest extends JFrame {

	private JPanel contentPane;
	static int x = 10;
	static int y = 10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovingTest frame = new MovingTest();
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
	MovingTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLayout(new BorderLayout());
		
	    contentPane = new JPanel(){
	        @Override
	        public void paintComponent(Graphics g){
	            super.paintComponent(g);
	            BufferedImage img = null;
				try {
					img = ImageIO.read(new File("../Girls/Kobold.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, x, y, (int)(img.getWidth()*.5), (int)(img.getHeight()*.5), null);
	        }
	    };
	    add(contentPane);

		contentPane.setFocusable(true);
		contentPane.addKeyListener(new CustomKeyListener());
		
	}
	
	void Motion(int xx, int yy) {
		x = x+xx;
		y = y+yy;
		repaint();
	}
	
	boolean held;
	Timer timer = new Timer();
	boolean firstPress = true;
	TimerTask velocity;
	int xnum;
	int ynum;
	class CustomKeyListener implements KeyListener{
		public void keyTyped(KeyEvent e) {
		}
		public void keyPressed(KeyEvent e) {
			if (firstPress) {
				velocity = new TimerTask() {
					@Override
					public void run() {
						//Get key(s)
						int xMod = 0;
						int yMod = 0;
						if(e.getKeyCode() == KeyEvent.VK_LEFT) xMod = -1;
						if(e.getKeyCode() == KeyEvent.VK_RIGHT) xMod = 1; 
						if(e.getKeyCode() == KeyEvent.VK_UP) yMod = -1;
						if(e.getKeyCode() == KeyEvent.VK_DOWN) yMod = 1;
						//Add to velocity up to cap
						if(Math.abs(xnum)<=4) xnum = xnum + xMod;
						if (Math.abs(ynum)<=4) ynum = ynum + yMod;
						
						//move item
						Motion(xnum,ynum);
					}
				};
				timer.schedule(velocity, 10, 10);
			}
			firstPress = false;
		}
		public void keyReleased(KeyEvent e) {
			firstPress = true;
			velocity.cancel();
			timer.purge();
			xnum = 0;
			ynum = 0;
			
		}
	}
}
