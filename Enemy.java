/*
 * sam woodburn 
 * 12/10/23
 * SER120- final project
 * 
 * Enemy class
 * 	  extends Avatar class to make an enemy (witch)
 */

import java.awt.Image;
import java.awt.Rectangle;

public class Enemy extends Avatar {
	//variables
	private int xDelta = 0;
	private int yDelta = 0;
	private boolean draggable = false;
	
	//constructors
	public Enemy(int x, int y) {
		super(Avatar.OPTION.Witch, x, y);
	}
	public Enemy(int x, int y, int width, int height) {
		super(Avatar.OPTION.Witch, x, y, width, height);
	}
	
	public int getSize() {
		return width;
	}
	
	//change position, bounce off top and bottom
	public void recalculatePosition(Rectangle bounds) {
		x += xDelta;
		y += yDelta;
		if(y < bounds.getY() || y > bounds.getHeight() - height) {
			yDelta *= -1; //Reverse the Y direction
		}
		
	}

	//past x parameter
	public boolean pastXBoundary(double maxX) {
		return x >= maxX + 5;
	}
	
	//getter setter xDelta
	public int getxDelta() {
		return xDelta;
	}
	public void setxDelta(int xDelta) {
		this.xDelta = xDelta;
	}

	//getter setter yDelta
	public int getyDelta() {
		return yDelta;
	}
	public void setyDelta(int yDelta) {
		this.yDelta = yDelta;
	}
	
}// end enemy class