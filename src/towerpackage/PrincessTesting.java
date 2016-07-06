package towerpackage;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;
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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

public class PrincessTesting extends JPanel {

	private JPanel contentPane;
	boolean[] itemChoices = new boolean[]{false,false,false};
	
	/**
	 * Create the panel and add things to it.
	 */
	public PrincessTesting(Princess1 princess) {
		//Set up containers
		setBackground(Color.WHITE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new MigLayout("", "[350]", "[][][][]"));

		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		contentPane.add(header, "cell 0 0");
		header.setLayout(new MigLayout("", "[][]", "[][]"));
		
		JPanel middle = new JPanel();
		contentPane.add(middle, "cell 0 1, grow");
		middle.setLayout(new MigLayout("", "[120][][]", "[][][][]"));
		
		JPanel bottom = new JPanel();
		contentPane.add(bottom, "cell 0 2, grow");
		bottom.setLayout(new MigLayout("", "[120][][]", "[][][]"));
		
		//Grab fonts (should this be done every time? Ideally not)
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font blockFont = grabFonts("src/Fonts/04B_19__.ttf");
		ge.registerFont(blockFont);
		Font mainFont = grabFonts("src/Fonts/BebasNeue Bold.ttf");
		ge.registerFont(mainFont);
		
		//Add elements to card
		addJLabel("<html><font style='font-family:Bebas Neue Bold;font-size:15;'>" +princess.kingdom+"'s<br><font style='font-family:04b_19;font-size:20px;'>"+princess.name.toUpperCase()+" PRINCESS"+"</font></html>", null, princess.col, null, null, header, "cell 1 0");
		addJLabel("", null, null, princess.image, null, header, "cell 0 0 0 2,alignx left,aligny top");
		addJLabel("<html>+ " + princess.good[0] +"<br>+ " + princess.good[1] + "<br>+ " + princess.good[2] + "<br>- " + princess.bad[0] + "<br>- " + princess.bad[1] + "</html>", mainFont, null, null, null, header, "cell 1 1");
		//Stats
		addJLabel("" , null, null, "src/Stat icons/Love.png", new Dimension(20,20), middle, "cell 0 0");
		addJLabel("" , null, null, "src/Stat icons/Lust.png", new Dimension(20,20), middle, "cell 0 1");
		addJLabel("" , null, null, "src/Stat icons/Wealth.png", new Dimension(20,20), middle, "cell 0 2");
		addJLabel("" , null, null, "src/Stat icons/Power.png", new Dimension(20,20), middle, "cell 0 3");
		//Add stat boxes (FIX ME)
		for(int i=0; i<4; i++) {
			BoxTesting boxTest = new BoxTesting(princess.love,princess.col);
			boxTest.setMinimumSize(new Dimension(90,20));
			middle.add(boxTest, "cell 0 " + i + ",grow,flowy");
		}
		//Dowries
		addJLabel("<html>" + regexChecker(princess.dowry1.name)[0] + "-<br>" + regexChecker(princess.dowry1.name)[1] + "</html>", mainFont, null, null, null, middle, "cell 2 0 1 2,left");
		String dowryLoc = "/Dowries/" + regexChecker(princess.dowry1.name)[0];
		addJLabel("", null, null, "src/" + dowryLoc.substring(0,dowryLoc.length()-1)+".png", new Dimension(30,40), middle, "cell 1 0 1 2");
		addJLabel("<html>" + regexChecker(princess.dowry2.name)[0] + "-<br>" + regexChecker(princess.dowry2.name)[1] + "</html>", mainFont, null, null, null, middle, "cell 2 2 1 2,left");
		dowryLoc = "/Dowries/" + regexChecker(princess.dowry2.name)[0];
		addJLabel("", null, null, "src/" + dowryLoc.substring(0,dowryLoc.length()-1)+".png", new Dimension(30,40), middle, "cell 1 2 1 2");
		//Kinks
		addJLabel(princess.kinks[0].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 0");
		addJLabel(princess.kinks[1].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 1");
		addJLabel(princess.turnoff.name, mainFont, null, "src/Stat icons/Dislikes.png", new Dimension(20,20), bottom, "cell 0 2");
		//Lust Item
		addJLabel("<html>" + regexChecker(princess.lustGift.name)[0] + "-<br>" + regexChecker(princess.lustGift.name)[1] + "</html>", mainFont, null, null, null, bottom, "cell 2 0 0 3");
		String lustLoc = "/Lust items/" + regexChecker(princess.lustGift.name)[0];
		addJLabel("", null, null, "src/" + lustLoc.substring(0,lustLoc.length()-1)+".png", new Dimension(40,40), bottom, "cell 1 0 0 3");
		
		addJLabel("<html><font size='4'>\"" + princess.description+  "\"</font></html>", mainFont, princess.col, null, null, contentPane, "cell 0 3, left");
	}
	
	/**
	 * add JLabel.
	 * @param text
	 * @param font
	 * @param col
	 * @param img
	 * @param dim
	 * @param comp
	 * @param location
	 */
	void addJLabel(String text, Font font, Color col, String img, Dimension dim, JComponent comp, String location) {
		JLabel temp = new JLabel(text);
		if(font != null) temp.setFont(font.deriveFont(16f));
		if(col != null) temp.setForeground(col);
		if(img != null) {
			ImageIcon icon = new ImageIcon(img);
			if(dim != null) temp.setIcon(new ImageIcon(getScaledImage(icon.getImage(),dim.width,dim.height)));
			else temp.setIcon(icon);
		}
		comp.add(temp, location);
	}
	
	/**
	 * Checks a string using regex.
	 * @param str
	 * @return String array of regex matched (2 items)--first "Words words -" then "description description"
	 */
	String[] regexChecker(String str) {
		Pattern regex = Pattern.compile("^[^\\-]*");
        Matcher regexMatcher = regex.matcher(str);
        if (regexMatcher.find()) {
        }
        regex = Pattern.compile("-(.*)");
        Matcher regexMatcher2 = regex.matcher(str);
        if (regexMatcher2.find()) {
        }
        
        String[] regexResult = new String[]{regexMatcher.group(0),regexMatcher2.group(1)};
		return regexResult;
	}
	
	/**
	 * Called to grab fonts from src file location.
	 * @param src
	 * @return font
	 */
	Font grabFonts(String src) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, new File(src));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Fiddling with choosing items/dowries
	 * @param color
	 * @param comp
	 */
	
	
	void setChosen(Color color, Component comp) {
		((JComponent) comp).setBorder(BorderFactory.createLineBorder(color));
		
		if(comp.getName().equals("1")) itemChoices[0] = !itemChoices[0];
		
		else if(comp.getName().equals("2")) itemChoices[1] = !itemChoices[1];
	}
	
	
	/**
	 * Fiddling with mouselistener choosing items/dowries
	 * @param comp
	 */
	void addChoice(Component comp) {
		comp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				setChosen(Color.RED,comp);
			}
		});
	}
	
	/**
	 * For resizing images in an intelligent way. Should replace at some time with cooBird's Thumbnailator
	 * @param srcImg
	 * @param w
	 * @param h
	 * @return a scaled Image
	 */
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	/**
	 * Called upon to return all of the above for use in a window
	 * @return the window
	 */

	JComponent provideInput() {
		return contentPane;
	}
}