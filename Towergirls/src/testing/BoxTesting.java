package testing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class BoxTesting extends JPanel {
	
	Component comp;
	int boxes;
	BoxTesting(Component component, int powerLevel) {
		comp=component;
		boxes=powerLevel;
		setBackground(new Color(0,0,0,65));
	}
	
    public void paint(Graphics g) {
		System.out.println("Got here");
		super.paintComponent(g);
		g.setColor(Color.RED);
        g.drawRect(50, 50, 20, 20);
    }
	

}
