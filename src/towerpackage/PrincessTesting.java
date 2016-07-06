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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

public class PrincessTesting extends JPanel {

	private JPanel contentPane;
	JPanel middle;
	boolean[] choices = new boolean[]{false,false};
	
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
		
		middle = new JPanel();
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
		addJLabel(null, "<html><font style='font-family:Bebas Neue Bold;font-size:15;'>" +princess.kingdom+"'s<br><font style='font-family:04b_19;font-size:20px;'>"+princess.name.toUpperCase()+" PRINCESS"+"</font></html>", null, princess.col, null, null, header, "cell 1 0", false);
		addJLabel(null, "", null, null, princess.image, null, header, "cell 0 0 0 2,alignx left,aligny top", false);
		addJLabel(null, "<html>+ " + princess.good[0] +"<br>+ " + princess.good[1] + "<br>+ " + princess.good[2] + "<br>- " + princess.bad[0] + "<br>- " + princess.bad[1] + "</html>", mainFont, null, null, null, header, "cell 1 1", false);
		//Stats
		addJLabel(null, "" , null, null, "src/Stat icons/Love.png", new Dimension(20,20), middle, "cell 0 0", false);
		addJLabel(null, "" , null, null, "src/Stat icons/Lust.png", new Dimension(20,20), middle, "cell 0 1", false);
		addJLabel(null, "" , null, null, "src/Stat icons/Wealth.png", new Dimension(20,20), middle, "cell 0 2", false);
		addJLabel(null, "" , null, null, "src/Stat icons/Power.png", new Dimension(20,20), middle, "cell 0 3", false);
		//Add stat boxes
		int[] stat = new int[]{princess.love,princess.lust,princess.wealth,princess.power};
		for(int i=0; i<4; i++) {
			BoxTesting boxTest = new BoxTesting(stat[i],princess.col);
			boxTest.setMinimumSize(new Dimension(90,20));
			middle.add(boxTest, "cell 0 " + i + ",grow,flowy");
		}
		//Dowries
		addJLabel("dowry1", "<html>" + regexChecker(princess.dowry1.name)[0] + "-<br>" + regexChecker(princess.dowry1.name)[1] + "</html>", mainFont, null, null, null, middle, "cell 2 0 1 2,left", true);
		String dowryLoc = "/Dowries/" + regexChecker(princess.dowry1.name)[0];
		addJLabel("dowry1", "", null, null, "src/" + dowryLoc.substring(0,dowryLoc.length()-1)+".png", new Dimension(30,40), middle, "cell 1 0 1 2", true);
		addJLabel("dowry2", "<html>" + regexChecker(princess.dowry2.name)[0] + "-<br>" + regexChecker(princess.dowry2.name)[1] + "</html>", mainFont, null, null, null, middle, "cell 2 2 1 2,left", true);
		dowryLoc = "/Dowries/" + regexChecker(princess.dowry2.name)[0];
		addJLabel("dowry2", "", null, null, "src/" + dowryLoc.substring(0,dowryLoc.length()-1)+".png", new Dimension(30,40), middle, "cell 1 2 1 2", true);
		//Kinks
		addJLabel(null, princess.kinks[0].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 0", false);
		addJLabel(null, princess.kinks[1].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 1", false);
		addJLabel(null, princess.turnoff.name, mainFont, null, "src/Stat icons/Dislikes.png", new Dimension(20,20), bottom, "cell 0 2", false);
		//Lust Item
		addJLabel(null, "<html>" + regexChecker(princess.lustGift.name)[0] + "-<br>" + regexChecker(princess.lustGift.name)[1] + "</html>", mainFont, null, null, null, bottom, "cell 2 0 0 3", false);
		String lustLoc = "/Lust items/" + regexChecker(princess.lustGift.name)[0];
		addJLabel(null, "", null, null, "src/" + lustLoc.substring(0,lustLoc.length()-1)+".png", new Dimension(40,40), bottom, "cell 1 0 0 3", false);
		
		addJLabel(null, "<html><font size='4'>\"" + princess.description+  "\"</font></html>", mainFont, princess.col, null, null, contentPane, "cell 0 3, left", false);
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
	void addJLabel(String name, String text, Font font, Color col, String img, Dimension dim, JComponent comp, String location, boolean mouse) {
		JLabel temp = new JLabel(text);
		if(name != null) temp.setName(name);
		if(font != null) temp.setFont(font.deriveFont(16f));
		if(col != null) temp.setForeground(col);
		if(img != null) {
			ImageIcon icon = new ImageIcon(img);
			if(dim != null) temp.setIcon(new ImageIcon(getScaledImage(icon.getImage(),dim.width,dim.height)));
			else temp.setIcon(icon);
		}
		if(mouse) {
			addChoice(temp);
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
	 * Get what items have been chosen
	 * @return array of choices
	 */
	boolean[] getChosen() {
		return choices;
	}
	
	
	/**
	 * Add mouselistener that calls borderPaint, checking whether the item has been chosen already
	 * @param comp
	 */
	void addChoice(Component comp) {
		comp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				//check if chosen
				if(comp.getName().equals("dowry1")) {
					if(choices[0]) {
						borderPaint("dowry1", true, comp.getParent());
					} else {
						borderPaint("dowry1", false, comp.getParent());
					}
					choices[0] = !choices[0];
				} else if(comp.getName().equals("dowry2")) {
					if(choices[1]) {
						borderPaint("dowry2", true, comp.getParent());
					} else {
						borderPaint("dowry2", false, comp.getParent());
					}
					choices[1] = !choices[1];
				}
			}
		});
	}
	
	/**
	 * Paint a border on all components matching compName
	 * @param compName
	 * @param hasBorder
	 * @param parent
	 */
	void borderPaint(String compName, boolean hasBorder, Component parent) {
		Component[] components = ((Container) parent).getComponents();
		for(int i=0; i<components.length; i++) {
			if(components[i].getName() != null && components[i].getName().equals(compName)) {
				if(!hasBorder) ((JComponent) components[i]).setBorder(BorderFactory.createLineBorder(Color.RED));
				else ((JComponent) components[i]).setBorder(BorderFactory.createEmptyBorder());
			}
		}
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