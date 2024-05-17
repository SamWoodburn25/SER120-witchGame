/*
 * sam woodburn 
 * 12/10/23
 * SER120- final project
 * 
 * num enemies panel class- implements JPanel
 * 		buttons to set number of enemies
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;


public class NumEnemiesPanel extends JPanel {

	//variables
	private JLabel label;
	private JRadioButton num1, num2, num3, num4, num5;
	private ButtonGroup bg;
	private int numEnemies;
	private App app;
	// private Enemy[] enemyArray;

	public NumEnemiesPanel(App app) {
		//initialize panel
		super(new GridLayout(6, 1));
		this.setPreferredSize(new Dimension(300,300));
		this.setBackground(app.getBackground());
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		this.app = app;

		// make label and add
		label = new JLabel("  Enemies:  ");
		this.add(label);

		// make number buttons and add listener
		num3 = new JRadioButton("3");
		num3.setBackground(getBackground());
		num3.addActionListener(new numEnemyListener(3));

		num1 = new JRadioButton("1");
		num1.setBackground(getBackground());
		num1.addActionListener(new numEnemyListener(1));

		num4 = new JRadioButton("4");
		num4.setBackground(getBackground());
		num4.addActionListener(new numEnemyListener(4));

		num2 = new JRadioButton("2");
		num2.setBackground(getBackground());
		num2.addActionListener(new numEnemyListener(2));

		num5 = new JRadioButton("5");
		num5.setBackground(getBackground());
		num5.addActionListener(new numEnemyListener(5));

		// make button group and add
		bg = new ButtonGroup();
		bg.add(num1);
		bg.add(num2);
		bg.add(num3);
		bg.add(num4);
		bg.add(num5);

		// add buttons to panel
		this.add(num1);
		this.add(num2);
		this.add(num3);
		this.add(num4);
		this.add(num5);

	}

	//getter and setter number of enemies
	public int getNumEnemies() {
		return numEnemies;
	}
	public void setNumEnemies(int numEnemies) {
		this.numEnemies = numEnemies;
	}

	/*
	 * action listener for number of enemies, on action performed set Enemies to num
	 */
	public class numEnemyListener implements ActionListener {
		private int num;

		//constructor
		public numEnemyListener(int num) {
			this.num = num;
		}

		//action performed
		@Override
		public void actionPerformed(ActionEvent e) {
			app.getGameBoard().resetEnemies();
			setNumEnemies(num);
			int size = 10;
			switch(num) {
				case 1:
					size = 100;   
					break;
				case 2:
					size = 80;
					break;
				case 3:
					size = 60;
					break;
				case 4:
					size = 50;
					break;
				case 5:
					size = 40;
					break;
			}
			app.getGameBoard().addEnemies(num, size);
			app.getGameBoard().repaint();
		}
		
	}// end listener class
	
}// end enemy panel class
