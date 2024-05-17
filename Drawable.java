/*
 * sam woodburn 
 * 12/10/23
 * SER120- final project
 * 
 * drawable interface
 * 		Interface implemented by all objects that are Drawable by the application
 */

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

public interface Drawable {
	//draw
	void draw(Graphics g, JPanel parent);
	//contains
	boolean contains(Point p);

	//dragging
	void drag(Point point, Rectangle bounds);
	
	public void clearOffset();
	
	public void startDragging(Point startingPoint);
	
	public void stopDragging();
	
	public boolean isDragging();
}



