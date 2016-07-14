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

public class PrincessCard extends JPanel {

	private JPanel contentPane;
	private JPanel middle;
	boolean[] choices = new boolean[]{false,false};
	
	/**
	 * Create the panel and add things to it.
	 */
	public PrincessCard(Item item, GameManager game) {
		

		//Grab fonts (should this be done every time? Ideally not)
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font blockFont = grabFonts("src/Fonts/04B_19__.ttf");
		ge.registerFont(blockFont);
		Font mainFont = grabFonts("src/Fonts/BebasNeue Bold.ttf");
		ge.registerFont(mainFont);
		
		contentPane = new JPanel();
		
		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		contentPane.add(header, "cell 0 0");
		
		if(item instanceof Princess1) {
			Princess1 princess = (Princess1) item;
			
			contentPane.setBackground(Color.WHITE);
			contentPane.setLayout(new MigLayout("", "[350]", "[][][][]"));
			header.setLayout(new MigLayout("", "[][]", "[][]"));
			
			middle = new JPanel();
			contentPane.add(middle, "cell 0 1, grow");
			middle.setLayout(new MigLayout("", "[][][]", "[][][][]"));
			
			JPanel bottom = new JPanel();
			contentPane.add(bottom, "cell 0 2, grow");
			bottom.setLayout(new MigLayout("", "[120][][]", "[][][]"));
			
			//Add elements to card
			//Title and pros/cons
			addJLabel(null, "<html><font style='font-family:Bebas Neue Bold;font-size:15;'>" +princess.kingdom+"'s<br><font style='font-family:04b_19;font-size:20px;'>"+princess.name.toUpperCase()+" PRINCESS"+"</font></html>", null, princess.col, null, null, header, "cell 1 0", false);
			addJLabel(null, "", null, null, princess.image, null, header, "cell 0 0 0 2,alignx left,aligny top", false);
			addJLabel(null, "<html>+ " + princess.good[0] +"<br>+ " + princess.good[1] + "<br>+ " + princess.good[2] + "<br>- " + princess.bad[0] + "<br>- " + princess.bad[1] + "</html>", mainFont, null, null, null, header, "cell 1 1", false);
			//Stat icons	
			String[] stats = new String[]{"Love","Lust","Wealth","Power"};
			for(int i=0; i<4; i++) {
				addJLabel(null, "" , null, null, "src/Stat icons/" + stats[i] + ".png", new Dimension(20,20), middle, "cell 0 " + i , false);
			}
			boolean template = false;
			if(!princess.name.equals("Template")) {
				//Add stat boxes
				int[] stat = new int[]{princess.love,princess.lust,princess.wealth,princess.power};
				for(int i=0; i<4; i++) {
					StatBarPainter boxTest = new StatBarPainter(stat[i],princess.col);
					boxTest.setMinimumSize(new Dimension(90,20));
					middle.add(boxTest, "cell 0 " + i);
				} 
			} else {
				//add Stat names
				for(int i=0; i<4; i++) {
					addJLabel("statNames", "   " + stats[i] + "   0 - 5", mainFont, null, null, null, middle, "cell 0 " + i + ",  w 170", false);
				}
				borderPaint("statNames", false, middle, Color.BLACK);
				template = true;
			}
			//Dowries
			if(princess.dowry1 != null) {
				addJLabel("dowry1", "<html>" + princess.dowry1.name + " -<br>" + princess.dowry1.description + "</html>", mainFont, null, null, null, middle, "cell 2 0 1 2,left", !template);
				addJLabel("dowry1", "", null, null, princess.dowry1.image, new Dimension(30,40), middle, "cell 1 0 1 2", !template);
			}
			if(princess.dowry2 != null) {
				addJLabel("dowry2", "<html>" + princess.dowry2.name + " -<br>" + princess.dowry2.description + "</html>", mainFont, null, null, null, middle, "cell 2 2 1 2,left", !template);
				addJLabel("dowry2", "", null, null, princess.dowry2.image, new Dimension(30,40), middle, "cell 1 2 1 2", !template);
			}
			//Kinks
			addJLabel(null, princess.kinks[0].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 0", false);
			addJLabel(null, princess.kinks[1].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 1", false);
			addJLabel(null, princess.turnoff.name, mainFont, null, "src/Stat icons/Dislikes.png", new Dimension(20,20), bottom, "cell 0 2", false);
			//Lust Item
			addJLabel(null, "<html>" + princess.lustGift.name + " -<br>" + princess.lustGift.description + "</html>", mainFont, null, null, null, bottom, "cell 2 0 0 3", false);
			addJLabel(null, "", null, null, princess.lustGift.image, new Dimension(40,40), bottom, "cell 1 0 0 3", false);
			
			addJLabel(null, "<html><font size='4'>\"" + princess.description+  "\"</font></html>", mainFont, princess.col, null, null, contentPane, "cell 0 3, left", false);
		
		} else if(item instanceof Princess2) {
			
			Princess2 princess = (Princess2) item;
			
			contentPane.setBackground(Color.WHITE);
			contentPane.setLayout(new MigLayout("", "[350]", "[][][][]"));
			header.setLayout(new MigLayout("", "[][]", "[][]"));
			
			middle = new JPanel();
			contentPane.add(middle, "cell 0 1, grow");
			middle.setLayout(new MigLayout("", "[][][]", "[][][][]"));
			
			JPanel bottom = new JPanel();
			contentPane.add(bottom, "cell 0 2, grow");
			bottom.setLayout(new MigLayout("", "[120][][]", "[][][]"));
			
			//Add elements to card
			//Title and pros/cons
			addJLabel(null, "<html><font style='font-family:Bebas Neue Bold;font-size:15;'>" +princess.kingdom+"'s<br><font style='font-family:04b_19;font-size:20px;'>"+princess.name.toUpperCase()+" PRINCESS"+"</font></html>", null, princess.col, null, null, header, "cell 1 0", false);
			addJLabel(null, "", null, null, princess.image, null, header, "cell 0 0 0 2,alignx left,aligny top", false);
			addJLabel(null, "<html>+ " + princess.good[0] +"<br>+ " + princess.good[1] + "<br>+ " + princess.good[2] + "<br>- " + princess.bad[0] + "<br>- " + princess.bad[1] + "</html>", mainFont, null, null, null, header, "cell 1 1", false);
			//Stat icons	
			String[] stats = new String[]{"Love","Lust","Wealth","Power"};
			for(int i=0; i<4; i++) {
				addJLabel(null, "" , null, null, "src/Stat icons/" + stats[i] + ".png", new Dimension(20,20), middle, "cell 0 " + i , false);
			}
			boolean template = false;
			if(!princess.name.equals("Example")) {
				//Add stat boxes
				int[] stat = new int[]{princess.love,princess.lust,princess.wealth,princess.power};
				for(int i=0; i<4; i++) {
					StatBarPainter boxTest = new StatBarPainter(stat[i],princess.col);
					boxTest.setMinimumSize(new Dimension(90,20));
					middle.add(boxTest, "cell 0 " + i);
				} 
			} else {
				//add Stat names
				for(int i=0; i<4; i++) {
					addJLabel("statNames", "   " + stats[i] + "   0 - 5", mainFont, null, null, null, middle, "cell 0 " + i + ",  w 170", false);
				}
				borderPaint("statNames", false, middle, Color.BLACK);
				template = true;
			}
			
			if(!game.getWagon().contains(princess.totem.name)) {
				addJLabel("Totem", "", null, null, princess.totem.image, new Dimension(40,80), middle, "cell 2 0 1 4", !template);
				addJLabel(null, princess.totem.name, mainFont, princess.col, null, null, middle, "cell 3 0", false);
				
				
				if(princess.totem.whichEffect != 2) addJLabel("Worship", "<html><font color = 'black'>Worshipped - </font><font color = '516290' size = '4'>" + princess.totem.worship + "</font></html>", mainFont, null, null, null, middle, "cell 3 1", !template);
				
				if(princess.totem.whichEffect != 1) addJLabel("Renounce", "<html><font color = 'black'>Renounced - </font><font color = '742f44' size = '4'>" + princess.totem.renounce + "</font></html>", mainFont, null, null, null, middle, "cell 3 2 0 2", !template);
			}
			//Kinks
			addJLabel(null, princess.kinks[0].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 0", false);
			addJLabel(null, princess.kinks[1].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 1", false);
			addJLabel(null, princess.turnoff.name, mainFont, null, "src/Stat icons/Dislikes.png", new Dimension(20,20), bottom, "cell 0 2", false);
			//Lust Item
			addJLabel(null, "<html>" + princess.lustGift.name + " -<br>" + princess.lustGift.description + "</html>", mainFont, null, null, null, bottom, "cell 2 0 0 3", false);
			addJLabel(null, "", null, null, princess.lustGift.image, new Dimension(40,40), bottom, "cell 1 0 0 3", false);
			
			addJLabel(null, "<html><font size='4'>\"" + princess.description+  "\"</font></html>", mainFont, princess.col, null, null, contentPane, "cell 0 3, left", false);
		
		} else if(item instanceof Princess3) {
			
			Princess3 princess = (Princess3) item;
			
			contentPane.setBackground(Color.WHITE);
			contentPane.setLayout(new MigLayout("", "[350]", "[][][][]"));
			header.setLayout(new MigLayout("", "[][]", "[][]"));
			
			middle = new JPanel();
			contentPane.add(middle, "cell 0 1, grow");
			middle.setLayout(new MigLayout("", "[][][]", "[][][][][][]"));
			
			JPanel bottom = new JPanel();
			contentPane.add(bottom, "cell 0 2, grow");
			bottom.setLayout(new MigLayout("", "[120][][]", "[][][]"));
			
			//Add elements to card
			//Title and pros/cons
			addJLabel(null, "<html><font style='font-family:Bebas Neue Bold;font-size:15;'>" +princess.kingdom+"'s<br><font style='font-family:04b_19;font-size:20px;'>"+princess.name.toUpperCase()+" PRINCESS"+"</font></html>", null, princess.col, null, null, header, "cell 1 0", false);
			addJLabel(null, "", null, null, princess.image, null, header, "cell 0 0 0 2,alignx left,aligny top", false);
			addJLabel(null, "<html>+ " + princess.good[0] +"<br>+ " + princess.good[1] + "<br>+ " + princess.good[2] + "<br>- " + princess.bad[0] + "<br>- " + princess.bad[1] + "</html>", mainFont, null, null, null, header, "cell 1 1", false);
			//Stat icons	
			String[] stats = new String[]{"Love","Lust","Wealth","Power"};
			for(int i=0; i<4; i++) {
				addJLabel(null, "" , null, null, "src/Stat icons/" + stats[i] + ".png", new Dimension(20,20), middle, "cell 0 " + i , false);
			}
			
			boolean def = false;
			if(!princess.name.equals("Default")) {
				//Add stat boxes
				int[] stat = new int[]{princess.love,princess.lust,princess.wealth,princess.power};
				for(int i=0; i<4; i++) {
					StatBarPainter boxTest = new StatBarPainter(stat[i],princess.col);
					boxTest.setMinimumSize(new Dimension(90,20));
					middle.add(boxTest, "cell 0 " + i);
				} 
			} else {
				//add Stat names
				for(int i=0; i<4; i++) {
					addJLabel("statNames", "   " + stats[i] + "   0 - 5", mainFont, null, null, null, middle, "cell 0 " + i + ",  w 170", false);
				}
				borderPaint("statNames", false, middle, Color.BLACK);
				def=true;
			}
			String rgb = Integer.toHexString(princess.col.getRGB());
			rgb = rgb.substring(2, rgb.length());
			
			//Display the Princess' companion and stuff
			addJLabel("Companion", "", null, null, princess.companion.image, null, middle, "cell 2 0 1 6", true);
			addJLabel(null, "<html><font color=" + rgb + ">" + princess.companion.name + System.lineSeparator() + "<font color=black>" + princess.companion.description + "</font></html>", mainFont, null, null, null, middle, "cell 1 0 1 4", false);
			
			addJLabel("Worship", "<html><font color = 'black'>Wealth - </font><font color = 'F4C43E' size = '4'>" + princess.wealthGift.description + "</font></html>", mainFont, null, null, null, middle, "cell 0 4 2 0", true);
			addJLabel("Renounce", "<html><font color = 'black'>Power - </font><font color = '86394D' size = '4'>" + princess.powerGift.description + "</font></html>", mainFont, null, null, null, middle, "cell 0 5 2 0", true);
			
			//Kinks
			addJLabel(null, princess.kinks[0].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 0", false);
			addJLabel(null, princess.kinks[1].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 1", false);
			addJLabel(null, princess.turnoff.name, mainFont, null, "src/Stat icons/Dislikes.png", new Dimension(20,20), bottom, "cell 0 2", false);
			//Lust Item
			addJLabel(null, "<html>" + princess.lustGift.name + " -<br>" + princess.lustGift.description + "</html>", mainFont, null, null, null, bottom, "cell 2 0 0 3", false);
			addJLabel(null, "", null, null, princess.lustGift.image, new Dimension(40,40), bottom, "cell 1 0 0 3", false);
			
			addJLabel(null, "<html><font size='4'>\"" + princess.description+  "\"</font></html>", mainFont, princess.col, null, null, contentPane, "cell 0 3, left", false);
		
		}else if(item instanceof Factionless){
			
			Factionless f = (Factionless) item;
			
			contentPane.setBackground(Color.WHITE);
			contentPane.setLayout(new MigLayout("", "[350]", "[][][][]"));
			header.setLayout(new MigLayout("", "[][]", "[][]"));
			
			middle = new JPanel();
			contentPane.add(middle, "cell 0 1, grow");
			middle.setLayout(new MigLayout("", "[][][]", "[][][][][][]"));
			
			JPanel bottom = new JPanel();
			contentPane.add(bottom, "cell 0 2, grow");
			bottom.setLayout(new MigLayout("", "[120][][]", "[][][]"));
			
			//Add elements to card
			//Title and pros/cons
			addJLabel(null, "<html><font style='font-family:Bebas Neue Bold;font-size:15;'>" +f.kingdom+"'s<br><font style='font-family:04b_19;font-size:20px;'>"+f.name.toUpperCase()+" PRINCESS"+"</font></html>", null, f.col, null, null, header, "cell 1 0", false);
			addJLabel(null, "", null, null, f.image, null, header, "cell 0 0 0 2,alignx left,aligny top", false);
			addJLabel(null, "<html>+ " + f.good[0] +"<br>+ " + f.good[1] + "<br>+ " + f.good[2] + "<br>- " + f.bad[0] + "<br>- " + f.bad[1] + "</html>", mainFont, null, null, null, header, "cell 1 1", false);
			//Stat icons	
			String[] stats = new String[]{"Love","Lust","Wealth","Power"};
			for(int i=0; i<4; i++) {
				addJLabel(null, "" , null, null, "src/Stat icons/" + stats[i] + ".png", new Dimension(20,20), middle, "cell 0 " + i , false);
			}
			if(!f.name.equals("Default")) {
				//Add stat boxes
				int[] stat = new int[]{f.love,f.lust,f.wealth,f.power};
				for(int i=0; i<4; i++) {
					StatBarPainter boxTest = new StatBarPainter(stat[i],f.col);
					boxTest.setMinimumSize(new Dimension(90,20));
					middle.add(boxTest, "cell 0 " + i);
				} 
			} else {
				//add Stat names
				for(int i=0; i<4; i++) {
					addJLabel("statNames", "   " + stats[i] + "   0 - 5", mainFont, null, null, null, middle, "cell 0 " + i + ",  w 170", false);
				}
				borderPaint("statNames", false, middle, Color.BLACK);
			}
			String rgb = Integer.toHexString(f.col.getRGB());
			rgb = rgb.substring(2, rgb.length());
			
			//Display the companion's effects
			addJLabel("Wealth", "<html><font color = 'black'>Wealth - </font><font color = 'F4C43E' size = '4'>" + f.effect1.description + "</font></html>", mainFont, null, null, null, middle, "cell 0 4 2 0", false);
			addJLabel("Power", "<html><font color = 'black'>Power - </font><font color = '86394D' size = '4'>" + f.effect2.description + "</font></html>", mainFont, null, null, null, middle, "cell 0 5 2 0", false);
			
			//Kinks
			addJLabel(null, f.kinks[0].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 0", false);
			addJLabel(null, f.kinks[1].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 1", false);
			addJLabel(null, f.turnoff.name, mainFont, null, "src/Stat icons/Dislikes.png", new Dimension(20,20), bottom, "cell 0 2", false);
			//Kingdom modifiers
			addJLabel(null, "<html>" + f.kingdom.name + " -<br>" + f.kingdom.description + "</html>", mainFont, null, null, null, bottom, "cell 2 0 0 3", false);
			//addJLabel(null, "", null, null, f.kingdom.image, new Dimension(40,40), bottom, "cell 1 0 0 3", false);
			
			addJLabel(null, "<html><font size='4'>\"" + f.description+  "\"</font></html>", mainFont, f.col, null, null, contentPane, "cell 0 3, left", false);
		
			
		}else if(item instanceof Character){
			Character c = (Character) item;
			
			contentPane.setBackground(Color.WHITE);
			contentPane.setLayout(new MigLayout("", "[350]", "[][][][]"));
			header.setLayout(new MigLayout("", "[][]", "[][]"));
			
			
			//Because there is no information to put in these panels I am currently leaving them out. If we add kinks and turn-offs we can add them back
//			middle = new JPanel();
//			contentPane.add(middle, "cell 0 1, grow");
//			middle.setLayout(new MigLayout("", "[][][]", "[][][][][][]"));
//			
			JPanel bottom = new JPanel();
			contentPane.add(bottom, "cell 0 2, grow");
			bottom.setLayout(new MigLayout("", "[120][][]", "[][][]"));
			
			
			//Add elements to card
			//Title and pros/cons
			addJLabel(null, "<html><font style='font-family:Bebas Neue Bold;font-size:15;'>" + c.kingdom+"'s<br><font style='font-family:04b_19;font-size:20px;'>" + c.name.toUpperCase()+"</font></html>", null, c.col, null, null, header, "cell 1 0", false);
			addJLabel(null, "", null, null, c.image, null, header, "cell 0 0 0 2,alignx left,aligny top", false);
			
			//Currently we don't define kinks and turn-offs for Princess' companions, We could consider doing that in a later version
			//Since we kind of want them to be stand alone characters
			//
			
			
			//addJLabel(null, "<html>+ " + c.good[0] +"<br>+ " + c.good[1] + "<br>+ " + c.good[2] + "<br>- " + c.bad[0] + "<br>- " + c.bad[1] + "</html>", mainFont, null, null, null, header, "cell 1 1", false);
			
			//Currently we don't define kinks and turn-offs for Princess' companions, We could consider doing that in a later version
			//Since we kind of want them to be stand alone characters
			//
			//Kinks
			addJLabel(null, c.kinks[0].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 0", false);
			addJLabel(null, c.kinks[1].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 1", false);
			addJLabel(null, c.turnoff.name, mainFont, null, "src/Stat icons/Dislikes.png", new Dimension(20,20), bottom, "cell 0 2", false);
			
			//Description
			addJLabel(null, "<html>" + c.description+  "</html>", mainFont, null, null, null, contentPane, "cell 0 3, left", false);
		
		}else if(item instanceof Totem) {
			Totem t = (Totem)item;
			contentPane.setLayout(new MigLayout("", "[]", "[]"));
			
			header.setLayout(new MigLayout("", "[70][270]", "[30][]"));
			
			JPanel panel1 = new JPanel();
			panel1.setLayout(new MigLayout("", "[]", "[]"));
			header.add(panel1, "cell 1 0");
			
			JPanel panel2 = new JPanel();
			panel2.setLayout(new MigLayout("", "[]", "[]"));
			header.add(panel2, "cell 1 1");
			
			addJLabel(null, "", null, null, item.image, null, header, "cell 0 0 0 2, center", false);
			addJLabel(null, item.name.toUpperCase(), blockFont, null, null, null, panel1, "cell 0 0", false);
			if(t.whichEffect != 2) addJLabel("Totem", "<html><font color='black'>Worship: <font color='516290'>" + t.worship + "</font></html>", mainFont, null, null, null, panel2, "cell 0 0", false);
			if(t.whichEffect != 1) addJLabel("Totem", "<html><font color='black'>Renounce: <font color='742f44'>" + t.renounce + "</font></html>", mainFont, null, null, null, panel2, "cell 0 0", false);
			
		} else if(item instanceof Effect) {
			Effect e = (Effect)item;
			contentPane.setLayout(new MigLayout("", "[]", "[]"));
			
			header.setLayout(new MigLayout("", "[70][270]", "[30][]"));
			
			JPanel panel1 = new JPanel();
			panel1.setLayout(new MigLayout("", "[]", "[]"));
			header.add(panel1, "cell 1 0");
			
			JPanel panel2 = new JPanel();
			panel2.setLayout(new MigLayout("", "[]", "[]"));
			header.add(panel2, "cell 1 1");
			
			addJLabel(null, "", null, null, item.image, null, header, "cell 0 0 0 2, center", false);
			addJLabel(null, item.name.toUpperCase(), blockFont, null, null, null, panel1, "cell 0 0", false);
			addJLabel(null, "<html>" + item.description + "</html>", mainFont, null, null, null, panel2, "cell 0 0", false);
		} else {
			contentPane.setLayout(new MigLayout("", "[]", "[]"));
			
			header.setLayout(new MigLayout("", "[70][270]", "[30][]"));
			
			JPanel panel1 = new JPanel();
			panel1.setLayout(new MigLayout("", "[]", "[]"));
			header.add(panel1, "cell 1 0");
			
			JPanel panel2 = new JPanel();
			panel2.setLayout(new MigLayout("", "[]", "[]"));
			header.add(panel2, "cell 1 1");
			
			addJLabel(null, "", null, null, item.image, null, header, "cell 0 0 0 2, center", false);
			addJLabel(null, item.name.toUpperCase(), blockFont, null, null, null, panel1, "cell 0 0", false);
			addJLabel(null, "<html>" + item.description + "</html>", mainFont, null, null, null, panel2, "cell 0 0", false);
		}
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
	 * Removes dowry from card
	 * @param whichDowry
	 */
	void removeDowries(int whichDowry) {
		Component[] components = ((Container) middle).getComponents();
		ArrayList<String> s = new ArrayList<String>();
		s.add("dowry" + whichDowry);
		for(int q=0; q<s.size(); q++) {
			for(int i=0; i<components.length; i++) {
				if(components[i].getName() != null && components[i].getName().equals(s.get(q))) {
					components[i].getParent().remove(components[i]);
				}
			}
		}
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
						borderPaint("dowry1", true, comp.getParent(), Color.RED);
					} else {
						borderPaint("dowry1", false, comp.getParent(), Color.RED);
					}
					choices[0] = !choices[0];
				} else if(comp.getName().equals("dowry2")) {
					if(choices[1]) {
						borderPaint("dowry2", true, comp.getParent(), Color.RED);
					} else {
						borderPaint("dowry2", false, comp.getParent(), Color.RED);
					}
					choices[1] = !choices[1];
				} else if(comp.getName().equals("Worship")) {
					if(choices[0]) {
						borderPaint("Worship", true, comp.getParent(), Color.RED);
					} else {
						if(choices[1]) {
							borderPaint("Renounce", true, comp.getParent(), Color.RED);
							choices[1] = !choices[1];
						}
						borderPaint("Worship", false, comp.getParent(), Color.RED);
					}
					choices[0] = !choices[0];
				} else if(comp.getName().equals("Renounce")) {
					if(choices[1]) {
						borderPaint("Renounce", true, comp.getParent(), Color.RED);
					} else {
						if(choices[0]) {
							borderPaint("Worship", true, comp.getParent(), Color.RED);
							choices[0] = !choices[0];
						}
						borderPaint("Renounce", false, comp.getParent(), Color.RED);
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
	 * @param color
	 */
	void borderPaint(String compName, boolean hasBorder, Component parent, Color color) {
		Component[] components = ((Container) parent).getComponents();
		for(int i=0; i<components.length; i++) {
			if(components[i].getName() != null && components[i].getName().equals(compName)) {
				if(!hasBorder) ((JComponent) components[i]).setBorder(BorderFactory.createLineBorder(color));
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