package testing;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.miginfocom.swing.MigLayout;
import testing.TextFileTesting.Princess1;

import java.awt.Color;
import java.awt.Dimension;

public class PrincessTesting extends JPanel {

	private JPanel contentPane;
	
	/**
	 * Create the panel.
	 */
	public PrincessTesting(Princess1 princess) {
		setBackground(Color.WHITE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[]", "[grow][][][]"));

		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		contentPane.add(header, "cell 0 0,grow");
		header.setLayout(new MigLayout("", "[grow][]", "[grow][]"));
		
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
		JLabel title = new JLabel("<html><font style='font-family:Bebas Neue Bold;font-size:19;'>" +princess.getTitle()[0]+"'s<br><font style='font-family:04b_19;font-size:20px;'>"+princess.getName()+" Princess"+"</font></html>");
		title.setForeground(princess.getColor());
		header.add(title, "cell 1 0");
		
		//Avatar
		JLabel avatar = new JLabel("");
		avatar.setIcon(new ImageIcon(PrincessTesting.class.getResource("/Girls/" + princess.getName() + ".png")));
		header.add(avatar, "cell 0 0 0 2,alignx left,aligny top");
		
		//Get traits
		JLabel traits = new JLabel("<html>+ " + princess.getGood()[0] +"<br>+ " + princess.getGood()[1] + "<br>+ " + princess.getGood()[2] + "<br>- " + princess.getBad()[0] + "<br>- " + princess.getBad()[1] + "</html>");
		traits.setFont(mainFont.deriveFont(16f));
		header.add(traits, "cell 1 1");
		
		JPanel middle = new JPanel();
		contentPane.add(middle, "cell 0 1");
		middle.setLayout(new MigLayout("", "[][][]", "[][][][]"));
		
		//Love stat and grid
		JLabel statLove = new JLabel("");
		middle.add(statLove, "cell 0 0");
		//This process turns the icon into an image and back again, resizing it according to the final numbers, below
		ImageIcon love = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Love.png"));
		statLove.setIcon((new ImageIcon(getScaledImage(love.getImage(),20,20))));
		//Add grid
		BoxTesting boxTest = new BoxTesting(princess.getStats()[0],princess.getColor());
		boxTest.setMinimumSize(new Dimension(90,20));
		middle.add(boxTest, "cell 0 0,grow,flowy");

		//Lust stat and grid
		JLabel statLust = new JLabel("");
		middle.add(statLust, "cell 0 1");
		ImageIcon lust = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Lust.png"));
		statLust.setIcon((new ImageIcon(getScaledImage(lust.getImage(),20,20))));
		//Add grid
		boxTest = new BoxTesting(princess.getStats()[1],princess.getColor());
		middle.add(boxTest, "cell 0 1,grow,flowy");
		
		//Wealth stat and grid
		JLabel statWealth = new JLabel("");
		middle.add(statWealth, "cell 0 2");
		ImageIcon wealth = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Wealth.png"));
		statWealth.setIcon((new ImageIcon(getScaledImage(wealth.getImage(),20,20))));
		//Add grid
		boxTest = new BoxTesting(princess.getStats()[2],princess.getColor());
		middle.add(boxTest, "cell 0 2,grow,flowy");
		
		//Power stat and grid
		JLabel statPower = new JLabel("");
		middle.add(statPower, "cell 0 3");
		ImageIcon power = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Power.png"));
		statPower.setIcon((new ImageIcon(getScaledImage(power.getImage(),20,20))));
		//Add grid
		boxTest = new BoxTesting(princess.getStats()[3],princess.getColor());
		middle.add(boxTest, "cell 0 3,grow");
		
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
		dowry1.setFont(mainFont.deriveFont(16f));
		middle.add(dowry1, "cell 2 0 1 2,alignx right");
		//Dowry images pull from /Dowries/name_of_dowry (BE SURE FILES ARE CAPITALIZED, e.g. Red Candle the Blue)
		JLabel dowry1Img = new JLabel();
		String dowryLoc = "/Dowries/" + regexMatcher.group(0);
		ImageIcon dowry = new ImageIcon(PrincessTesting.class.getResource(dowryLoc.substring(0,dowryLoc.length()-1)+".png"));
		dowry1Img.setIcon((new ImageIcon(getScaledImage(dowry.getImage(),30,40))));
		middle.add(dowry1Img, "cell 1 0 1 2,alignx right");
		
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
		dowry2.setFont(mainFont.deriveFont(16f));
		middle.add(dowry2, "cell 2 2 1 2,alignx right");
		//2nd dowry image
		JLabel dowry2Img = new JLabel("");
		dowryLoc = "/Dowries/" + regexMatcher.group(0);
		dowry = new ImageIcon(PrincessTesting.class.getResource(dowryLoc.substring(0,dowryLoc.length()-1)+".png"));
		dowry2Img.setIcon((new ImageIcon(getScaledImage(dowry.getImage(),30,40))));
		middle.add(dowry2Img, "cell 1 2 1 2,alignx right");
		
		
		JPanel bottom = new JPanel();
		contentPane.add(bottom, "cell 0 2");
		bottom.setLayout(new MigLayout("", "[][][]", "[][][]"));
		
		//Kinks
		JLabel kink1 = new JLabel(princess.getKinks()[0].getName());
		kink1.setFont(mainFont.deriveFont(16f));
		bottom.add(kink1, "cell 0 0");
		ImageIcon kink = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Likes.png"));
		kink1.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));
		
		JLabel kink2 = new JLabel(princess.getKinks()[1].getName());
		kink2.setFont(mainFont.deriveFont(16f));
		bottom.add(kink2, "cell 0 1");
		kink2.setIcon((new ImageIcon(getScaledImage(kink.getImage(),20,20))));

		JLabel kink3 = new JLabel(princess.getTurnOff().getName());
		kink3.setFont(mainFont.deriveFont(16f));
		bottom.add(kink3, "cell 0 2");
		kink = new ImageIcon(PrincessTesting.class.getResource("/Stat icons/Dislikes.png"));
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
		JLabel lustItem = new JLabel("<html>" + regexMatcher.group(0) + "-<br>" + regexMatcher2.group(1) + "</html>");
		lustItem.setFont(mainFont.deriveFont(16f));
		bottom.add(lustItem, "cell 2 0 0 3");
		//Lust item
		JLabel lustImg = new JLabel("");
		String lustLoc = "/LustItems/" + regexMatcher.group(0);
		lust = new ImageIcon(PrincessTesting.class.getResource(lustLoc.substring(0,lustLoc.length()-1) + ".png"));
		lustImg.setIcon((new ImageIcon(getScaledImage(lust.getImage(),40,40))));
		bottom.add(lustImg, "cell 1 0 0 3");
		
		//Description
		JLabel desc = new JLabel("<html><font size='4'>\"" + princess.getTitle()[1] +  "\"</font></html>");
		desc.setForeground(princess.getColor());
		desc.setFont(mainFont);
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

	JComponent provideInput() {
		return contentPane;
	}
}