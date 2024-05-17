/*
 * sam woodburn 
 * 12/10/23
 * SER120- final project
 * 
 * title panel class- extends JPanel
 * 		displays title message
 */

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TitlePanel extends JPanel {
	//variables
	private static final String DEFAULT_MSG = "Welcome to Enemy Attack!!!";	//title message
	private JLabel messageLabel;

	//constructor
	public TitlePanel(App app) {
		//initialize panel
		super(new GridLayout(0, 5));
		this.setBackground(new Color(80, 20, 188));	//dark purple

		//adding title message
		messageLabel = new JLabel(DEFAULT_MSG);
		messageLabel.setForeground(Color.white);
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(messageLabel);
	}

	//set message
	public void setMessage(String message) {
		messageLabel.setText(message);
	}
	
}//end title panel class




