package project;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class PrincessCard extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PrincessCard(Princess1 princess) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 543);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[125px,grow]", "[175px,grow][grow][67.00,grow][14px]"));
		ImageIcon love = new ImageIcon(PrincessCard.class.getResource("/Stat icons/Love.png"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[125px][]", "[175px][]"));
		
		//Kingdom and name
		JLabel title = new JLabel("<html><font color=" + princess.getColor() + " size='4'>" +princess.getTitle()[0]+"'s<br><font size='5'>"+princess.getName()+" Princess"+"</font></html>");
		panel.add(title, "cell 1 0");
		
		//Avatar
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PrincessCard.class.getResource("/Girls/" + princess.getName() + ".png")));
		panel.add(label, "cell 0 0 0 2,alignx left,aligny top");
		
		//Get traits
		JLabel lblNewLabel_2 = new JLabel("<html>+" + princess.getGood()[0] +"<br>+" + princess.getGood()[1] + "<br>+" + princess.getGood()[2] + "<br>-" + princess.getBad()[0] + "<br>-" + princess.getBad()[1] + "</html>");
		panel.add(lblNewLabel_2, "cell 1 1");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new MigLayout("", "[][][][]", "[][][][]"));
		
		//Love stat and grid
		JLabel statLove = new JLabel("");
		panel_1.add(statLove, "cell 0 0");
		//This process turns the icon into an image and back again, resizing it according to the final numbers, below
		statLove.setIcon((new ImageIcon(getScaledImage(love.getImage(),20,20))));
		JLabel grid1 = new JLabel("");
		ImageIcon grid = new ImageIcon(PrincessCard.class.getResource("/Stat icons/Stat bar.png"));
		grid1.setIcon((new ImageIcon(getScaledImage(grid.getImage(),80,20))));
		panel_1.add(grid1, "cell 1 0");

		//Lust stat and grid
		JLabel statLust = new JLabel("");
		panel_1.add(statLust, "cell 0 1");
		ImageIcon lust = new ImageIcon(PrincessCard.class.getResource("/Stat icons/Lust.png"));
		statLust.setIcon((new ImageIcon(getScaledImage(lust.getImage(),20,20))));
		JLabel grid2 = new JLabel("");
		grid = new ImageIcon(PrincessCard.class.getResource("/Stat icons/Stat bar.png"));
		grid2.setIcon((new ImageIcon(getScaledImage(grid.getImage(),80,20))));
		panel_1.add(grid2, "cell 1 1");
		
		//Wealth stat and grid
		JLabel statWealth = new JLabel("");
		panel_1.add(statWealth, "flowy,cell 0 2");
		ImageIcon wealth = new ImageIcon(PrincessCard.class.getResource("/Stat icons/Wealth.png"));
		statWealth.setIcon((new ImageIcon(getScaledImage(wealth.getImage(),20,20))));
		ImageIcon power = new ImageIcon(PrincessCard.class.getResource("/Stat icons/Power.png"));
		JLabel grid3 = new JLabel("");
		grid = new ImageIcon(PrincessCard.class.getResource("/Stat icons/Stat bar.png"));
		grid3.setIcon((new ImageIcon(getScaledImage(grid.getImage(),80,20))));
		panel_1.add(grid3, "cell 1 2");
		
		//Power stat and grid
		JLabel statPower = new JLabel("");
		panel_1.add(statPower, "cell 0 3");
		statPower.setIcon((new ImageIcon(getScaledImage(power.getImage(),20,20))));
		JLabel grid4 = new JLabel("");
		grid = new ImageIcon(PrincessCard.class.getResource("/Stat icons/Stat bar.png"));
		grid4.setIcon((new ImageIcon(getScaledImage(grid.getImage(),80,20))));
		panel_1.add(grid4, "cell 1 3");
		ImageIcon kink = new ImageIcon(PrincessCard.class.getResource("/Stat icons/Likes.png"));
		
		//Dowries
		//Regex matches "words words words -" and divides that from "- words words words" with a new line
		Pattern regex = Pattern.compile("^[^\\-]*");
        Matcher regexMatcher = regex.matcher(princess.getGifts()[0].getName());
        if (regexMatcher.find()) {
        }
        regex = Pattern.compile("-(.*)");
        Matcher regexMatcher2 = regex.matcher(princess.getGifts()[0].getName());
        if (regexMatcher2.find()) {
        }
		JLabel dowry1 = new JLabel("<html>" + regexMatcher.group(0) + "-<br>" + regexMatcher2.group(1) + "</html>");
		panel_1.add(dowry1, "cell 3 0 1 2,alignx right");
		//Dowry images pull from /Dowries/name_of_dowry (BE SURE FILES ARE CAPITALIZED, e.g. Red Candle the Blue)
		JLabel dowry1Img = new JLabel();
		String dowryLoc = "/Dowries/" + regexMatcher.group(0);
		ImageIcon dowry = new ImageIcon(PrincessCard.class.getResource(dowryLoc.substring(0,dowryLoc.length()-1)+".png"));
		dowry1Img.setIcon((new ImageIcon(getScaledImage(dowry.getImage(),30,40))));
		panel_1.add(dowry1Img, "cell 2 0 1 2,alignx right");
		
		//2nd dowry
		regex = Pattern.compile("^[^\\-]*");
        regexMatcher = regex.matcher(princess.getGifts()[1].getName());
        if (regexMatcher.find()) {
        }
        regex = Pattern.compile("-(.*)");
        regexMatcher2 = regex.matcher(princess.getGifts()[1].getName());
        if (regexMatcher2.find()) {
        }
		JLabel dowry2 = new JLabel("<html>" + regexMatcher.group(0) + "-<br>" + regexMatcher2.group(1) + "</html>");
		panel_1.add(dowry2, "cell 3 2 1 2,alignx right");
		//2nd dowry image
		JLabel dowry2Img = new JLabel("");
		dowryLoc = "/Dowries/" + regexMatcher.group(0);
		dowry = new ImageIcon(PrincessCard.class.getResource(dowryLoc.substring(0,dowryLoc.length()-1)+".png"));
		dowry2Img.setIcon((new ImageIcon(getScaledImage(dowry.getImage(),30,40))));
		panel_1.add(dowry2Img, "cell 2 2 1 2,alignx right");
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "cell 0 2,grow");
		panel_2.setLayout(new MigLayout("", "[][][]", "[][][]"));
		
		//Kinks
		JLabel kink1 = new JLabel(princess.getKinks()[0].getName());
		panel_2.add(kink1, "cell 0 0");
		kink1.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));
		
		JLabel kink2 = new JLabel(princess.getKinks()[1].getName());
		panel_2.add(kink2, "cell 0 1");
		kink2.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));

		JLabel kink3 = new JLabel(princess.getTurnOff().getName());
		panel_2.add(kink3, "cell 0 2");
		kink = new ImageIcon(PrincessCard.class.getResource("/Stat icons/Dislikes.png"));
		kink3.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));
		
		//Lust item w/ regex
		regex = Pattern.compile("^[^\\-]*");
        regexMatcher = regex.matcher(princess.getGifts()[2].getName());
        if (regexMatcher.find()) {
        }
        regex = Pattern.compile("-(.*)");
        regexMatcher2 = regex.matcher(princess.getGifts()[2].getName());
        if (regexMatcher2.find()) {
        }
        System.out.println(princess.getGifts()[2].getName());
		JLabel lustItem = new JLabel("<html>" + regexMatcher.group(0) + "-<br>" + regexMatcher2.group(1) + "</html>");
		panel_2.add(lustItem, "cell 2 0 1 2");
		//Lust item
		JLabel lustImg = new JLabel("");
		String lustLoc = "/LustItems/" + regexMatcher.group(0);
		lust = new ImageIcon(PrincessCard.class.getResource(lustLoc.substring(0,lustLoc.length()-1) + ".png"));
		lustImg.setIcon((new ImageIcon(getScaledImage(lust.getImage(),40,40))));
		panel_2.add(lustImg, "cell 1 0 1 2");
		
		//Description
		JLabel desc = new JLabel("<html><font color=" + princess.getColor() + " size='2'>\"" + princess.getTitle()[1] +  "\"</font></html>");
		contentPane.add(desc, "cell 0 3, alignx center");
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