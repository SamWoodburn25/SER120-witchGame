/*
 * sam woodburn
 * 12/10/23
 * SER120- final project
 * 
 * score panel class- extends JPanel
 * 		adds a jlabel score to the panel which keeps track of how many enemies the avatar dodged
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


public class ScorePanel extends JPanel {
	//variables
	private JLabel label;
	private JLabel currentScore;
	private int score = 0;
	private App app;

	//constructor
	public ScorePanel(App app) {
		//initialize panel
		super(new FlowLayout());
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.setBackground(new Color(199, 17, 181));	//dark pink

		// initialize score label
		Font font = new Font("LucidaSans", Font.BOLD, 14);
		label = new JLabel("SCORE: ");
		label.setFont(font);
		label.setForeground(new Color(246, 149, 236));	//light pink

		//initialize current score
		currentScore = new JLabel("0");
		currentScore.setFont(font);
		currentScore.setForeground(new Color(246, 149, 236));

		//add to panel
		this.add(label);
		this.add(currentScore);
	}

	//set score to the parameter
	public void setScore(int score) {
		this.score = score;
		updateCurrentScore();
	}
	public int getScore() {
		return this.score;
	}

	//increase score by 1
	public void incrementScore() {
		this.score++;
		updateCurrentScore();
	}

	//update the score
	public void updateCurrentScore() {
		this.currentScore.setText(score + "");
	}
}

