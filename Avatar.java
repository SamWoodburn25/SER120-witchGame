/*
 * sam woodburn
 * 12/10/23
 * SER120- final project
 * 
 * avatar class- implements Drawable interface
 * 		initializes all character images and enemy image, with parameters to set location and size
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class Avatar implements Drawable {

	//image icon variables for all avatars
	private static final ImageIcon lionImage = new ImageIcon(Avatar.class.getResource("lionImage.png"));
	private static final ImageIcon elephantImage = new ImageIcon(Avatar.class.getResource("elephantImage.png"));
	private static final ImageIcon turtleImage = new ImageIcon(Avatar.class.getResource("turtleImage.png"));
	protected static final ImageIcon witchImage = new ImageIcon(Avatar.class.getResource("WitchIcon.png"));

	//option variables
	public static enum OPTION {
		Lion, Elephant, Turtle, Witch
	};

	//other variables
	protected Image chosen = null;
	protected int x, y;
	protected int xDelta, yDelta = 0;
	protected int width = 50;
	protected int height = 50;
	protected Point offset;
	public boolean draggable = true;
	public boolean isDragging = false;

	//default
	public Avatar(int x, int y) {
		super();
		chosen = Avatar.getImageIcon(lionImage).getImage();
		this.x = x;
		this.y = y;
	}
	//constructor with x,y, and pre-set width and height
	public Avatar(Avatar.OPTION option, int x, int y) {
		super();
		chosen = Avatar.getImageIcon(option).getImage();
		this.x = x;
		this.y = y;
	}
	//constructor with image as an option
	public Avatar(Image option, int x, int y) {
		super();
		chosen =  option;
		this.x = x;
		this.y = y;
	}
	//constructor with x, y, width, height
	public Avatar(Avatar.OPTION option, int x, int y, int width, int height) {
		this(option, x, y);
		this.width = width;
		this.height = height;
	}
	
	//this avatar intersects with the avatar parameter
	public boolean intersects(Avatar avatar) {
		return this.getBounds().intersects(avatar.getBounds());
	}
	
	//get bounds
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	//set size
	public void setSize(int size) {
		this.width = size;
		this.height = size;
	}
	
	//move methods
	public void moveDown() {
		  int newY = this.getY() + 15;
	      this.setLocation(this.getX(), newY);
	  }
	  public void moveUp() {
	    int newY = this.getY() - 15;
		  this.setLocation(this.getX(), newY);
	 }

	//draws the image
	@Override
	public void draw(Graphics g, JPanel parent) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.white);
		clear(g);
		// This is supposed to help smooth out rounded edges, especially when dragging
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(chosen, x, y, width, height, parent);
	}
	
	//clear
	public void clear(Graphics g) {
		g.clearRect(x, y, width, height);
		g.fillRect(x, y, width, height);
	}

	//contains
	@Override
	public boolean contains(Point p) {
		return false;
	}

	//sets and returns and ImageIcon(avatar)
	public static ImageIcon getImageIcon(Avatar.OPTION option) {
		switch (option) {
		case Lion:
			return lionImage;
		case Elephant:
			return elephantImage;
		case Turtle:
			return turtleImage;
		default:
			return witchImage;
		}
	}
	public static ImageIcon getImageIcon(ImageIcon option) {
		if(option == lionImage) 
			return lionImage;
		else if(option == elephantImage) 
			return elephantImage;
		else if(option == turtleImage) 
			return turtleImage;
		else
			return witchImage;
	}

	//getter and setter for  the upper left x coordinate
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

	//getter and setter for the upper left y coordinate
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	//getter and setter for  the upper left x coordinate
	public int getxDelta() {
		return xDelta;
	}
	public void setxDelta(int xDelta) {
		this.xDelta = xDelta;
	}

	//getter and setter for the upper left y coordinate
	public int getyDelta() {
		return yDelta;
	}
	public void setyDelta(int yDelta) {
		this.yDelta = yDelta;
	}

	//getter and setter for width
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	//getter and setter for height
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	//set location
	public void setLocation(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	//clear point
	public void clearOffset() {
		this.offset = new Point(0, 0);
	}
	
	//dragging methods
	public void startDragging(Point startingPoint) {
		if(this.draggable) {
			this.isDragging = true;
			setOffset(startingPoint);
		}
	}
	public void stopDragging() {
		this.isDragging = false;
		clearOffset();
	}
	public boolean isDragging() {
		return isDragging;
	}
	@Override
	public void drag(Point point, Rectangle bounds) {
		if(this.draggable) {
			int newY = offset.y + point.y;
			if(newY >= (bounds.getY() - height + height / 2) && (newY + height) <= bounds.getHeight()) {
				y = offset.y + point.y;
			}
		}
	}
	//set offset
	public void setOffset(Point startingPoint) {
		this.offset = new Point(x - startingPoint.x, y - startingPoint.y);
	}
	
	//recalculate position
	public void recalculatePosition(int xDelta, int yDelta, Rectangle bounds) {
		this.xDelta = xDelta;
		this.yDelta = yDelta;
		recalculatePosition(bounds);
	}
	public void recalculatePosition(Rectangle bounds) {
		int calcX = x + xDelta;
		if(calcX >= (bounds.getX() - width + width / 2) && (calcX + width) <= bounds.getWidth()) {
			x = calcX;
		}
		
		int calcY = y + yDelta;
		if(calcY >= (bounds.getY() - height + height / 2) && (calcY + height) <= bounds.getHeight()) {
			y = calcY;
		}
	}
	
}//end avatar class

