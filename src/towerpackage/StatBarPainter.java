package towerpackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.lang.reflect.Field;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatBarPainter extends JLabel {
	
	int boxes;
	Color color;
	StatBarPainter(int powerLevel, Color princessColor) {
		boxes = powerLevel;
		color = princessColor;
	}
	
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		//Draw the first 5 outlines
		for(int i=0; i<=60; i=i+15) {
			g.drawRect(i, 4, 15, 12);
		}
		//Draw however many boxes
		g.setColor(color);
		for(int i=1; i<((boxes*15)+1); i=i+15) {
			g.fillRect(i, 5, 14, 11);
		}
		if(boxes==6) {
			g.setColor(Color.BLACK);
			g.drawRect(75, 4, 15, 12);
			g.setColor(color);
			g.fillRect(76, 5, 14, 11);
		}
        
    }
	

}
