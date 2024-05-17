/*
 * sam woodburn
 * 12/10/23
 * SER120- final project
 * 
 * action button panel class- extends JPanel
 * 		adds buttons start and quit
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ActionButtonPanel extends JPanel {
	//variables
	protected JButton startOrPauseButton;
	private JButton quitButton;
	private String current;

	public ActionButtonPanel(App app) {
		//initialize panel
		super(new GridLayout(2, 1, 0, 10));
		this.setBackground(app.getBackground());

		//start button
		startOrPauseButton = new JButton("Start");
		current = "Start";
		startOrPauseButton.setPreferredSize(new Dimension(100, 75));
		//ActionListener class to handle the button click event
		startOrPauseButton.addActionListener(new ActionListener() {
			@Override
			//starts and stops game
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				if (action.equalsIgnoreCase("start")) {
					((JButton) e.getSource()).setText("Pause");
					app.getGameBoard().startMoving();
					app.startDemoMode();
					current = "Pause";

				} else {
					((JButton) e.getSource()).setText("Start");
					app.stopDemoMode();
					current = "Start";
				}
			}
		});
		//add start or pause to panel
		this.add(startOrPauseButton);

		//quit button
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			//action for quit button
			@Override
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to give up?", "Please don't go!",
						JOptionPane.YES_NO_OPTION);
				if (input == 0) {
					System.exit(0);
				}
			}
		});
		//add quit button to panel
		this.add(quitButton);
	}//end constructor
	
	//egt current start pause button state
	public String getCurrent() {
		return this.current;
	}

	public void setText(String text) {
		startOrPauseButton.setText(text);
	}
}//end action button class




