/*
 * sam woodburn 
 * 12/10/23
 * SER120- final project
 * 
 * game board class- extends JPanel
 * 		adds avatar and enemies to the panel, begins the game, moves the enemies
 */


import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameBoard extends JPanel {
	//variables
	private Avatar character = null;
	private boolean gameStarted = false;
	private App app;
	private int scoreIncrement;
	Random rand = new Random(System.currentTimeMillis());
	protected List<Enemy> enemies = null;
	private static final Image loseImage = new ImageIcon(GameBoard.class.getResource("Lose.png")).getImage().getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
	private static final Image winImage = new ImageIcon(GameBoard.class.getResource("Win.png")).getImage().getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);

	
	//constructor
	public GameBoard(App app) {
		// initialize panel and default values
		super();
		this.setPreferredSize(new Dimension(1000, 600));
		this.app = app;
		this.setBackground(Color.white);
		//set default avatar
		character = new Avatar(800, 300);  
		scoreIncrement = 1;
		
		//add mouse listener to drag
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(character != null) {
					character.startDragging(e.getPoint());
				}
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(character != null) {
					character.stopDragging();
				}
				repaint();
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(character != null) {
					character.drag(e.getPoint(), getBounds());
				}
			}
		});
	}

	//override paint component, draw character and enemy on panel
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (character != null) {
			character.draw(g, this);
		}
		drawEnemies(g);
	}

	//sets the character to the avatar parameter and draws it on the panel
	public void setCharacter(Avatar newAvatar) {
		character = newAvatar;
		character.draw(getGraphics(), this);
		repaint();
	}
	public Avatar getCharacter() {
		return this.character;
	}

	//start and pause game 
	public void startGame() {
		app.stopDemoMode();
		this.gameStarted = true;
		
	}
	public void pauseGame() {
		this.gameStarted = false;
	}

	//clear enemy array list
	public void resetEnemies() {
		enemies = new ArrayList<>();
	}
	
	//reset game
	public void reset() {
		app.reset();
	}

	//adding enemies to the screen according to the difficulty
	public void addEnemies(int num, int size) {
		int numToAdd = num - enemies.size();
		int y = 50;
		for (int i = 0; i < numToAdd; i++) {
			addEnemy(size, y);
			y+=(size*2);
		}
	}

	//adds an enemy to the screen
	private void addEnemy(int size, int y) {
		Enemy newEnemy = new Enemy(0, y, size, size);
		this.enemies.add(newEnemy);
	}

	//move the enemies
	public void startMoving() {
		for(Enemy enem: enemies) {
			enem.setxDelta(5); // constant over time
			enem.setyDelta(rand.nextInt(0, 10)); // randomly between the range inclusive
		}
	}
	
	//change enemy position
	public void moveEnemies() {
		ListIterator<Enemy> iter = enemies.listIterator();
		while (iter.hasNext()) {
			Enemy enemy = (Enemy)iter.next();
			enemy.recalculatePosition(getBounds());

			// But if the enemy has gone outside the right border (maxX) remove it.
			if (enemy.pastXBoundary(getBounds().getMaxX())) {
				iter.remove();
			}
		}
	}

	//draw enemy to this panel
	private void drawEnemies(Graphics g) {
		if (enemies != null) {
			for (Avatar enemy : enemies) {
				enemy.draw(g, this);
			}
		}
	}
	
	//lose and win methods
	public void loseIfHitEnemy() {
		synchronized (enemies) {
			for(Enemy enemy: enemies) {
				if(character.intersects(enemy)) {
					app.hitEnemy();
					enemies.remove(enemy);
					showContinueButton();
					app.cntrlpnl.scorePanel.setScore(0);
					return;
				}
			}
		}
	}
	public void setScoreIncrement(int num) {
		scoreIncrement = num;
	}
	public void winIfNoHit() {
		synchronized (enemies) {
			for(Enemy enemy: enemies) {
				if(enemy.pastXBoundary(990)) {
					System.out.println("win");
					showWinImage();
					showContinueButton();
					app.setScore(app.getScore() + (enemies.size()*scoreIncrement));
					return;
				}
			}
		} 
	}
	
	public void showContinueButton() {
		JButton cont = new JButton("continue");
		this.add(cont);
		cont.setVisible(true);
		cont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(getParent(), "confirm continue to play", "beat your score!",JOptionPane.YES_NO_OPTION);
				if (input == 0) {
					reset();
				}
			}
		});
	}
	
	//show lose and win image
	public void showLoseImage() {
		app.stopDemoMode();
		JLabel loseImageLabel = new JLabel(new ImageIcon(loseImage));
		this.add(loseImageLabel);
		loseImageLabel.setVisible(true);
		app.titlePanel.setMessage("\t\tYou lose!");
	}
	public void showWinImage() {
		app.stopDemoMode();
		JLabel winImageLabel = new JLabel(new ImageIcon(winImage));
		this.add(winImageLabel);
		winImageLabel.setVisible(true);
		app.titlePanel.setMessage("\t\tYou Win!!!");
	}

	
	 //update the game board panel
	public void updateBoard() {
		Graphics g = getGraphics();
		if (!app.inDemoMode()) {
			
		} else {
			if (enemies == null) {
				resetEnemies();
			} else {
				// move existing
				moveEnemies();
				//check if avatar is hit and end the game
				loseIfHitEnemy();
				//check if win
				winIfNoHit();
			}
			drawEnemies(g);
		}
		repaint();
	}
	
}//end game board class






