/*
 * sam woodburn
 * 12/10/23
 * SER120- final project
 * 
 * control panel class- extends JPanel
 * 		adds buttons to the panel which allow user to select their avatar
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JPanel;


public class ControlPanel extends JPanel {
	// variables
	protected DifficultyButtonPanel difficultyPanel;
	protected ActionButtonPanel startPanel;
	private NumEnemiesPanel enemyPanel;
	private DirectionButtonPanel directPanel;
	protected ScorePanel scorePanel;
	private CharacterPanel characterPanel;
	private int numEnemies;
	private App app;
	protected MovingCharacterProxy moveProxy;

	//constructor
	public ControlPanel(App app) {
		//initialize panel
		super(new GridLayout(6,1));
		this.setBackground(app.getBackground());
		this.setPreferredSize(new Dimension(1000, 300));
		this.app = app;
		moveProxy = new MovingCharacterProxy(app.getGameBoard().getCharacter());

		//panel layout
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		Insets defaultInsets = new Insets(10, 10, 10, 10);
		Insets largeLeftInsets = new Insets(10, 50, 10, 10);

		//add each panel to the control panel, in the desired order
		scorePanel = new ScorePanel(this.app);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 10;
		c.gridheight = 1;
		c.weightx = 2;
		this.add(scorePanel, c);

		difficultyPanel = new DifficultyButtonPanel(this.app);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = defaultInsets;
		c.weightx = 0;
		c.fill = GridBagConstraints.VERTICAL;
		this.add(difficultyPanel, c);

		enemyPanel = new NumEnemiesPanel(this.app);
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		this.add(enemyPanel, c);

		characterPanel = new CharacterPanel(this.app, moveProxy);
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		this.add(characterPanel, c);

		startPanel = new ActionButtonPanel(this.app);
		c.gridx = 7;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = largeLeftInsets;
		this.add(startPanel, c);

		directPanel = new DirectionButtonPanel(this.app, moveProxy);
		c.gridx = 9;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		this.add(directPanel, c);
	}

	//getter and setter for number of enemies
	public int getNumEnemies() {
		return numEnemies;
	}
	public void setNumEnemies(int numEnemies) {
		this.numEnemies = numEnemies;
	}

	//get start panel to return
	public ActionButtonPanel getStartPanel() {
		return startPanel;
	}
	
	//set score
	public void setScore(int score) {
		this.scorePanel.setScore(score);
	}
	public int getScore() {
		return this.scorePanel.getScore();
	}
	
}// end control panel class



