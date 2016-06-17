import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import net.miginfocom.swing.MigLayout;

public class PrincessCard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincessCard frame = new PrincessCard();
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
	public PrincessCard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[125px,grow]", "[175px,grow][grow][67.00,grow][14px]"));
		ImageIcon love = new ImageIcon(PrincessCard.class.getResource("/statIcons/Love.png"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[125px][]", "[175px][]"));
		
		JLabel title = new JLabel("<html>Fertile Factory Kingdom's<br>Goblin Princess</html>");
		panel.add(title, "cell 1 0");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PrincessCard.class.getResource("/Girls/Frog.png")));
		panel.add(label, "cell 0 1,alignx left,aligny top");
		
		JLabel lblNewLabel_2 = new JLabel("<html>+Always well provided for<br>+Aroused 24/7<br>+Slave embassies in every kingdom<br>-Demands you sleep only with her<br>-Goblins always try to get the D</html>");
		panel.add(lblNewLabel_2, "cell 1 1");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new MigLayout("", "[][][][]", "[][][][]"));
		
		JLabel statLove = new JLabel("");
		panel_1.add(statLove, "cell 0 0");
		statLove.setIcon((new ImageIcon(getScaledImage(love.getImage(),20,20))));
		
		JLabel grid1 = new JLabel("");
		ImageIcon grid = new ImageIcon(PrincessCard.class.getResource("/statIcons/Stat bar.png"));
		grid1.setIcon((new ImageIcon(getScaledImage(grid.getImage(),80,20))));
		panel_1.add(grid1, "cell 1 0");
		
		JLabel dowry1 = new JLabel();
		ImageIcon dowry = new ImageIcon(PrincessCard.class.getResource("/powerIcons/Dowry.png"));
		dowry1.setIcon((new ImageIcon(getScaledImage(dowry.getImage(),20,40))));
		panel_1.add(dowry1, "cell 2 0 1 2,alignx right");
		
		JLabel statLust = new JLabel("");
		panel_1.add(statLust, "cell 0 1");
		ImageIcon lust = new ImageIcon(PrincessCard.class.getResource("/statIcons/Lust.png"));
		statLust.setIcon((new ImageIcon(getScaledImage(lust.getImage(),20,20))));
		
		JLabel grid2 = new JLabel("");
		grid = new ImageIcon(PrincessCard.class.getResource("/statIcons/Stat bar.png"));
		grid2.setIcon((new ImageIcon(getScaledImage(grid.getImage(),80,20))));
		panel_1.add(grid2, "cell 1 1");
		
		JLabel statWealth = new JLabel("");
		panel_1.add(statWealth, "flowy,cell 0 2");
		ImageIcon wealth = new ImageIcon(PrincessCard.class.getResource("/statIcons/Wealth.png"));
		statWealth.setIcon((new ImageIcon(getScaledImage(wealth.getImage(),20,20))));
		ImageIcon power = new ImageIcon(PrincessCard.class.getResource("/statIcons/Power.png"));
		
		JLabel grid3 = new JLabel("");
		grid = new ImageIcon(PrincessCard.class.getResource("/statIcons/Stat bar.png"));
		grid3.setIcon((new ImageIcon(getScaledImage(grid.getImage(),80,20))));
		panel_1.add(grid3, "cell 1 2");

		JLabel lblNewLabel_3 = new JLabel("<html>King's Crown -<br>Charisma boost causes lovers to overlook unfaithfulness</html>");
		panel_1.add(lblNewLabel_3, "cell 3 0 1 2,alignx right");

		JLabel lblNewLabel_5 = new JLabel("<html>Breeding Contract -<br>Own anyone you knock up</html>");
		panel_1.add(lblNewLabel_5, "cell 3 2 1 2,alignx right");
		
		JLabel dowry2 = new JLabel("");
		dowry = new ImageIcon(PrincessCard.class.getResource("/powerIcons/Dowry.png"));
		dowry2.setIcon((new ImageIcon(getScaledImage(dowry.getImage(),20,40))));
		panel_1.add(dowry2, "cell 2 2 1 2,alignx right");
		
		JLabel statPower = new JLabel("");
		panel_1.add(statPower, "cell 0 3");
		statPower.setIcon((new ImageIcon(getScaledImage(power.getImage(),20,20))));
		
		JLabel grid4 = new JLabel("");
		grid = new ImageIcon(PrincessCard.class.getResource("/statIcons/Stat bar.png"));
		grid4.setIcon((new ImageIcon(getScaledImage(grid.getImage(),80,20))));
		panel_1.add(grid4, "cell 1 3");
		ImageIcon kink = new ImageIcon(PrincessCard.class.getResource("/statIcons/Likes.png"));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "cell 0 2,grow");
		panel_2.setLayout(new MigLayout("", "[][][]", "[][][]"));
		
		JLabel kink1 = new JLabel("Ballsucking");
		panel_2.add(kink1, "cell 0 0");
		kink1.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));
		
		JLabel kink2 = new JLabel("Impregnation");
		panel_2.add(kink2, "cell 0 1");
		kink2.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));
		
		JLabel lustImg = new JLabel("");
		lust = new ImageIcon(PrincessCard.class.getResource("/PowerIcons/Lust.png"));
		lustImg.setIcon((new ImageIcon(getScaledImage(lust.getImage(),20,40))));
		panel_2.add(lustImg, "cell 1 0 1 2");
		
		JLabel lustItem = new JLabel("<html>Stud's Draught -<br>Produce an overflowing amount of seed that can impregnate anyone</html>");
		panel_2.add(lustItem, "cell 2 0 1 2");
		
		JLabel kink3 = new JLabel("Lover jerking it");
		panel_2.add(kink3, "cell 0 2");
		kink = new ImageIcon(PrincessCard.class.getResource("/statIcons/Dislikes.png"));
		kink3.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));
		
		JLabel desc = new JLabel("<html>\"The haughty, coquettish princess of a breeding empire. Rather than choose many partners, she wishes to mate with one partner forever.\" </html>");
		contentPane.add(desc, "cell 0 3");
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}

}
