/*
 * sam woodburn 
 * 12/10/23
 * SER120- final project
 * 
 * App class
 * 	  makes a frame which the game panel, title panel, and control panel are added
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class App {
	//difficulty variables
	public enum DIFFICULTY {
		EASY, NORMAL, HARD
	};

	//instance variables: title, game board, and timer
	protected TitlePanel titlePanel;
	private GameBoard gameBoard;
	private Timer gameTimer;
	protected ControlPanel cntrlpnl;
	private int timerSpeed;

	//instantiate variables
	private DIFFICULTY difficulty = DIFFICULTY.EASY;
	private int numEnemies = 10;
	private boolean inDemoMode = true;

	//constructor
	public App() {
		super();
		timerSpeed = 60;
	}

	// start a timer to refresh and update the GameBoard using the timer listener
	public void startTimer() {
		//game resets after this time, also the speed?
		gameTimer = new Timer(timerSpeed, timerListener);
		gameTimer.start();
	}

	/**
	 * ActionListener class that listens to the game clock timer and updates game board
	 */
	ActionListener timerListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			gameBoard.updateBoard();
		}
	};

	//start game play
	public void startDemoMode() {
		this.inDemoMode = true;
		startTimer();  //default speed
	}

	//stop game play
	public void stopDemoMode() {
		this.inDemoMode = false;
		gameTimer.stop();
	}

	//during game
	public boolean inDemoMode() {
		return this.inDemoMode;
	}

	//setter and getter for difficulty
	public void setDifficulty(DIFFICULTY level) {
		System.out.println("Setting difficulty to: " + level);
		this.difficulty = level;
		if(level == DIFFICULTY.EASY) {
			getGameBoard().getCharacter().setSize(30);
		}
		if(level == DIFFICULTY.NORMAL) {
			getGameBoard().getCharacter().setSize(60);
		}
		if(level == DIFFICULTY.HARD) {
			getGameBoard().getCharacter().setSize(100);
		}
	}
	public DIFFICULTY getDifficultyLevel() {
		return this.difficulty;
	}

	//setter and getter for number of enemies
	public void setNumEnemies(int newNum) {
		this.numEnemies = newNum;
	}
	public int getNumEnemies() {
		return this.numEnemies;
	}

	//getter game board
	public GameBoard getGameBoard() {
		return gameBoard;
	}

	//set title message
	public void setMessage(String message) {
		this.titlePanel.setMessage(message);
	}

	//sets and gets color of control panel
	public Color getBackground() {
		return new Color(210, 228, 240);
	}
	
	//set score
	public void setScore(int score) {
		this.cntrlpnl.setScore(score);
	}
	public int getScore() {
		return this.cntrlpnl.getScore();
	}
	
	//character is hit by enemy
	public void hitEnemy() {
		System.out.println("hit");
		lose();
	}
	
	//player lost the game
	public void lose() {
		System.out.println("lost");
		this.gameBoard.showLoseImage();
	}
	
	public void reset() {
		this.gameBoard.resetEnemies();
		this.gameBoard.removeAll();
		this.gameBoard.revalidate();
		this.gameBoard.repaint();
		this.titlePanel.setMessage("Welcome to enemy attack!!!");
		this.cntrlpnl.startPanel.setText("Start");
	}

	//main method
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			App app = new App();
			//instantiate panels
			TitlePanel titlePnl = new TitlePanel(app);
			GameBoard gmBoard = new GameBoard(app);
			app.gameBoard = gmBoard;
			app.titlePanel = titlePnl;
			ControlPanel controlPnl = new ControlPanel(app);
			app.cntrlpnl = controlPnl;

			//instantiate frame
			JFrame frame = new JFrame("Avoid the Enemies!!!");
			frame.setResizable(false);
			frame.setBackground(app.getBackground());
			frame.setPreferredSize(new Dimension(1000,800));
			
			KeyEventPostProcessor kepp = new KeyEventPostProcessor() {
				@Override
				public boolean postProcessKeyEvent(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_UP) {
						app.getGameBoard().getCharacter().moveUp();
					}
					if(e.getKeyCode() == KeyEvent.VK_DOWN) {
						app.getGameBoard().getCharacter().moveDown();
					}
					return true;
				}
			};
			
			KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			kfm.addKeyEventPostProcessor(kepp);

			//add panels to frame
			frame.add(titlePnl, BorderLayout.NORTH);
			frame.add(gmBoard, BorderLayout.CENTER);
			frame.add(controlPnl, BorderLayout.SOUTH);

			//other frame specifications
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			//start timer
			app.startDemoMode();
			
		});
	}
}
