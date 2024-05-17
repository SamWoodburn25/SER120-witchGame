/*
 * sam woodburn
 * 12/10/23
 * SER120- final project
 * 
 * direction button panel class- extends JPanel
 * 		adds buttons to the panel which allow user move the avatar in that direction
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DirectionButtonPanel extends JPanel {

	//instance variables
	private DirectionButton upBttn;
	private DirectionButton downBttn;
	private JLabel instructions, p1, p2, p3, p4;
	private ButtonGroup bttngrp;
	private App app;
	private MovingCharacterProxy moveProxy;

	//constructor
	public DirectionButtonPanel(App app, MovingCharacterProxy moveProxy) {
		super();
		this.app = app;
		this.setBackground(app.getBackground());
		this.moveProxy = moveProxy;

		//setting the layout
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		bttngrp = new ButtonGroup();
		
		//instantiate each button and add to this panel
		upBttn = new DirectionButton("upArrow.png", bttngrp);
		upBttn.addActionListener(new UpDirectionListener());
		c.gridx = 1;
		c.gridy = 0;
		this.add(upBttn, c);
		
		downBttn = new DirectionButton("downArrow.png", bttngrp);
		downBttn.addActionListener(new DownDirectionListener());
		c.gridx = 1;
		c.gridy = 2;
		this.add(downBttn, c);
		
		JPanel instruct = new JPanel(new GridLayout(5,1));
		instructions = new JLabel("Instructions:");
		instruct.add(instructions);
		
		p1 = new JLabel("1) Choose character, number of enemies, and difficulty.");
		instruct.add(p1);
		
		p2 = new JLabel("2) Using these arrows or keyboard arrows move character.");
		instruct.add(p2);
		
		p3 = new JLabel("3) Press start and move up or down to avoid the enemies.");
		instruct.add(p3);
		
		p4 = new JLabel("4) Increasing difficulty will earn you more points!");
		instruct.add(p4);
		
		c.gridx = 2;
		c.gridy = 1;
		this.add(instruct, c);

	}// end constructor

	
	/*
	 * up listener implements action listener interface
	 * 		moves character up 25px
	 */
	public class UpDirectionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			moveProxy.moveUp();
			app.getGameBoard().repaint();
		}
	}
	
	/*
	 * down listener implements action listener interface
	 * 		moves character down 25px
	 */
	public class DownDirectionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			moveProxy.moveDown();
			app.getGameBoard().repaint();
		}
	}
	
	
	/*
	 * direction button class- extends JButton
	 * makes a jbutton with an image which is taken as a parameter
	 */
	public class DirectionButton extends JButton {
		public DirectionButton(String imageicon, ButtonGroup bttngrp) {
			super();
			Image img;
			img = new ImageIcon(this.getClass().getResource(imageicon)).getImage().getScaledInstance(50, 50, 50);
			setIcon(new ImageIcon(img));
			bttngrp.add(this);
		}
		
	}//end direction button class
	
}//end direction button panel class




