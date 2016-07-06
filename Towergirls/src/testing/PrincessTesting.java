package testing;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.miginfocom.swing.MigLayout;
import testing.TextFileTesting.Princess1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

public class PrincessTesting extends JPanel {

	private JPanel contentPane;
	JLabel dowry1 = new JLabel();
	
	/**
	 * Create the panel.
	 */
	public PrincessTesting(Princess1 princess) {
		setBackground(Color.WHITE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new MigLayout("", "[350]", "[][][][]"));

		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		contentPane.add(header, "cell 0 0");
		header.setLayout(new MigLayout("", "[][]", "[][]"));
		
		//Grab fonts (should this be done every time? Ideally not)
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font blockFont = null;
		try {
			blockFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/04B_19__.ttf"));
			ge.registerFont(blockFont);
			blockFont.deriveFont(100f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Font mainFont = null;
		try {
			mainFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/BebasNeue Bold.ttf"));
			ge.registerFont(mainFont);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Kingdom and name
		JLabel title = new JLabel("<html><font style='font-family:Bebas Neue Bold;font-size:15;'>" +princess.kingdom+"'s<br><font style='font-family:04b_19;font-size:20px;'>"+princess.name.toUpperCase()+" PRINCESS"+"</font></html>");
		title.setForeground(princess.col);
		header.add(title, "cell 1 0");
		
		//Avatar
		JLabel avatar = new JLabel("");
		avatar.setIcon(new ImageIcon(princess.image));
		header.add(avatar, "cell 0 0 0 2,alignx left,aligny top");
		
		//Get traits
		JLabel traits = new JLabel("<html>+ " + princess.good[0] +"<br>+ " + princess.good[1] + "<br>+ " + princess.good[2] + "<br>- " + princess.bad[0] + "<br>- " + princess.bad[1] + "</html>");
		traits.setFont(mainFont.deriveFont(16f));
		header.add(traits, "cell 1 1");
		
		JPanel middle = new JPanel();
		contentPane.add(middle, "cell 0 1, grow");
		middle.setLayout(new MigLayout("", "[120][][]", "[][][][]"));
		
		//Love stat and grid
		JLabel statLove = new JLabel("");
		middle.add(statLove, "cell 0 0");
		//This process turns the icon into an image and back again, resizing it according to the final numbers, below
		ImageIcon love = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Love.png"));
		statLove.setIcon((new ImageIcon(getScaledImage(love.getImage(),20,20))));
		//Add grid
		BoxTesting boxTest = new BoxTesting(princess.love,princess.col);
		boxTest.setMinimumSize(new Dimension(90,20));
		middle.add(boxTest, "cell 0 0,grow,flowy");

		//Lust stat and grid
		JLabel statLust = new JLabel("");
		middle.add(statLust, "cell 0 1");
		ImageIcon lust = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Lust.png"));
		statLust.setIcon((new ImageIcon(getScaledImage(lust.getImage(),20,20))));
		//Add grid
		boxTest = new BoxTesting(princess.lust,princess.col);
		middle.add(boxTest, "cell 0 1,grow,flowy");
		
		//Wealth stat and grid
		JLabel statWealth = new JLabel("");
		middle.add(statWealth, "cell 0 2");
		ImageIcon wealth = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Wealth.png"));
		statWealth.setIcon((new ImageIcon(getScaledImage(wealth.getImage(),20,20))));
		//Add grid
		boxTest = new BoxTesting(princess.wealth,princess.col);
		middle.add(boxTest, "cell 0 2,grow,flowy");
		
		//Power stat and grid
		JLabel statPower = new JLabel("");
		middle.add(statPower, "cell 0 3");
		ImageIcon power = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Power.png"));
		statPower.setIcon((new ImageIcon(getScaledImage(power.getImage(),20,20))));
		//Add grid
		boxTest = new BoxTesting(princess.power,princess.col);
		middle.add(boxTest, "cell 0 3,grow");
		
		//Dowries
		//Regex matches "words words words -" and divides that from "- words words words" with a new line
		Pattern regex = Pattern.compile("^[^\\-]*");
        Matcher regexMatcher = regex.matcher(princess.dowry1.name);
        if (regexMatcher.find()) {
        }
        regex = Pattern.compile("-(.*)");
        Matcher regexMatcher2 = regex.matcher(princess.dowry1.name);
        if (regexMatcher2.find()) {
        }
		dowry1.setText("<html>" + regexMatcher.group(0) + "-<br>" + regexMatcher2.group(1) + "</html>");
		dowry1.setFont(mainFont.deriveFont(16f));
		middle.add(dowry1, "cell 2 0 1 2,left");
		//Dowry images pull from /Dowries/name_of_dowry (BE SURE FILES ARE CAPITALIZED, e.g. Red Candle the Blue)
		JLabel dowry1Img = new JLabel();
		String dowryLoc = "/Dowries/" + regexMatcher.group(0);
		ImageIcon dowry = new ImageIcon(PrincessTesting.class.getResource(dowryLoc.substring(0,dowryLoc.length()-1)+".png"));
		dowry1Img.setIcon((new ImageIcon(getScaledImage(dowry.getImage(),30,40))));
		middle.add(dowry1Img, "cell 1 0 1 2");
		
		//2nd dowry
		regex = Pattern.compile("^[^\\-]*");
        regexMatcher = regex.matcher(princess.dowry2.name);
        if (regexMatcher.find()) {
        }
        regex = Pattern.compile("-(.*)");
        regexMatcher2 = regex.matcher(princess.dowry2.name);
        if (regexMatcher2.find()) {
        }
		JLabel dowry2 = new JLabel("<html>" + regexMatcher.group(0) + "-<br>" + regexMatcher2.group(1) + "</html>");
		dowry2.setFont(mainFont.deriveFont(16f));
		middle.add(dowry2, "cell 2 2 1 2,left");
		//2nd dowry image
		JLabel dowry2Img = new JLabel("");
		dowryLoc = "/Dowries/" + regexMatcher.group(0);
		dowry = new ImageIcon(PrincessTesting.class.getResource(dowryLoc.substring(0,dowryLoc.length()-1)+".png"));
		dowry2Img.setIcon((new ImageIcon(getScaledImage(dowry.getImage(),30,40))));
		middle.add(dowry2Img, "cell 1 2 1 2");
		
		
		JPanel bottom = new JPanel();
		contentPane.add(bottom, "cell 0 2, grow");
		bottom.setLayout(new MigLayout("", "[120][][]", "[][][]"));
		
		//Kinks
		JLabel kink1 = new JLabel(princess.kinks[0].name);
		kink1.setFont(mainFont.deriveFont(16f));
		bottom.add(kink1, "cell 0 0");
		ImageIcon kink = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Likes.png"));
		kink1.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));
		
		JLabel kink2 = new JLabel(princess.kinks[1].name);
		kink2.setFont(mainFont.deriveFont(16f));
		bottom.add(kink2, "cell 0 1");
		kink2.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));

		JLabel kink3 = new JLabel(princess.turnoff.name);
		kink3.setFont(mainFont.deriveFont(16f));
		bottom.add(kink3, "cell 0 2");
		kink = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Dislikes.png"));
		kink3.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));
		
		//Lust item w/ regex
		regex = Pattern.compile("^[^\\-]*");
        regexMatcher = regex.matcher(princess.lustGift.name);
        if (regexMatcher.find()) {
        }
        regex = Pattern.compile("-(.*)");
        regexMatcher2 = regex.matcher(princess.lustGift.name);
        if (regexMatcher2.find()) {
        }
		JLabel lustItem = new JLabel("<html>" + regexMatcher.group(0) + "-<br>" + regexMatcher2.group(1) + "</html>");
		lustItem.setFont(mainFont.deriveFont(16f));
		bottom.add(lustItem, "cell 2 0 0 3");
		//Lust item
		JLabel lustImg = new JLabel("");
		String lustLoc = "/Lust items/" + regexMatcher.group(0);
		lust = new ImageIcon(PrincessTesting.class.getResource(lustLoc.substring(0,lustLoc.length()-1) + ".png"));
		lustImg.setIcon((new ImageIcon(getScaledImage(lust.getImage(),40,40))));
		bottom.add(lustImg, "cell 1 0 0 3");
		
		//Description
		JLabel desc = new JLabel("<html><font size='4'>\"" + princess.description+  "\"</font></html>");
		desc.setForeground(princess.col);
		desc.setFont(mainFont);
		contentPane.add(desc, "cell 0 3, left");
		
		addChoice(dowry2);
		addChoice(dowry2Img);
		addChoice(dowry1);
		addChoice(dowry1Img);
	}
	
	void setChosen(Color color, Component comp) {
//		if()
		((JComponent) comp).setBorder(BorderFactory.createLineBorder(color));
	}
	
	void addChoice(Component comp) {
		comp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				setChosen(Color.RED,comp);
			}
		});
	}
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}

	JComponent provideInput() {
		return contentPane;
	}
}