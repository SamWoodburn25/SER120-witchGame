/*
 * sam woodburn
 * 12/10/23
 * SER120- final project
 * 
 * character panel class- extends JPanel
 * 		adds buttons to the panel which allow user to select their avatar
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CharacterPanel extends JPanel {

	//variables
	private App app;
	private JLabel label;
	private JButton lionBtn;
	private JButton elephantBtn;
	private JButton turtleBtn;
	private MovingCharacterProxy moveProxy;

	//constructor
	public CharacterPanel(App app, MovingCharacterProxy moveProxy) {
		super(new GridLayout(5, 1));
		this.setBackground(app.getBackground());
		this.setPreferredSize(new Dimension(150, 200));
		this.app = app;
		this.moveProxy = moveProxy;

		//title for the character panel
		label = new JLabel("  Choose your avatar!  ");
		this.add(label);

		//lion avatar button, add listener, add to panel
		lionBtn = new JButton(new ImageIcon(Avatar.getImageIcon(Avatar.OPTION.Lion).getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		lionBtn.addActionListener(new CharacterButtonListener(Avatar.OPTION.Lion));
		lionBtn.setSize(20, 20);
		this.add(lionBtn);

		//elephant avatar button, add listener, add to panel
		elephantBtn = new JButton(new ImageIcon(Avatar.getImageIcon(Avatar.OPTION.Elephant).getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		elephantBtn.addActionListener(new CharacterButtonListener(Avatar.OPTION.Elephant));
		lionBtn.setSize(20, 20);
		this.add(elephantBtn);

		//turtle avatar button, add listener, add to panel
		turtleBtn = new JButton(new ImageIcon(Avatar.getImageIcon(Avatar.OPTION.Turtle).getImage().getScaledInstance(30,30, java.awt.Image.SCALE_SMOOTH)));
		turtleBtn.addActionListener(new CharacterButtonListener(Avatar.OPTION.Turtle));
		lionBtn.setSize(20, 20);
		this.add(turtleBtn);
	}//end constructor

	/*
	 * character button listener class, implements action listener interface
	 */
	public class CharacterButtonListener implements ActionListener {
		private Avatar.OPTION option;
		//take option variable and set it to this.option
		public CharacterButtonListener(Avatar.OPTION option) {
			this.option = option;
		}

		//when button is clicked set the character on the game board to the option variable
		@Override
		public void actionPerformed(ActionEvent e) {
			Avatar selected = new Avatar(option, 800, 300);
			app.getGameBoard().setCharacter(selected);
			moveProxy.setCharacter(selected);
		}
	}//end listener class
	
}//end character panel class



