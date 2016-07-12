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
	private JPanel middle;
	boolean[] choices = new boolean[]{false,false};
	
	/**
	 * Create the panel and add things to it.
	 */
	public PrincessTesting(Item item) {
		

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
					BoxTesting boxTest = new BoxTesting(stat[i],princess.col);
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
					BoxTesting boxTest = new BoxTesting(stat[i],princess.col);
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
			
			addJLabel("Totem", "", null, null, princess.totem.image, new Dimension(40,80), middle, "cell 2 0 1 4", !template);
			addJLabel(null, princess.totem.name, mainFont, princess.col, null, null, middle, "cell 3 0", false);
			
			if(princess.totem.whichEffect != 2) addJLabel("Worship", "<html><font color = 'black'>Worshipped - </font><font color = '516290' size = '4'>" + princess.totem.worship + "</font></html>", mainFont, null, null, null, middle, "cell 3 1", !template);
			if(princess.totem.whichEffect != 1) addJLabel("Renounce", "<html><font color = 'black'>Renounced - </font><font color = '742f44' size = '4'>" + princess.totem.renounce + "</font></html>", mainFont, null, null, null, middle, "cell 3 2 0 2", !template);
			
			//Kinks
			addJLabel(null, princess.kinks[0].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 0", false);
			addJLabel(null, princess.kinks[1].name, mainFont, null, "src/Stat icons/Likes.png", new Dimension(20,20), bottom, "cell 0 1", false);
			addJLabel(null, princess.turnoff.name, mainFont, null, "src/Stat icons/Dislikes.png", new Dimension(20,20), bottom, "cell 0 2", false);
			//Lust Item
			addJLabel(null, "<html>" + princess.lustGift.name + " -<br>" + princess.lustGift.description + "</html>", mainFont, null, null, null, bottom, "cell 2 0 0 3", false);
			addJLabel(null, "", null, null, princess.lustGift.image, new Dimension(40,40), bottom, "cell 1 0 0 3", false);
			
			addJLabel(null, "<html><font size='4'>\"" + princess.description+  "\"</font></html>", mainFont, princess.col, null, null, contentPane, "cell 0 3, left", false);
			
		} else if(item instanceof Item) {
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