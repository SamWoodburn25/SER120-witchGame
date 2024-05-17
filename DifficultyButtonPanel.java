/*
 * sam woodburn 
 * 12/10/23
 * SER120- final project
 * 
 * DifficultyButtonPanel class
 * 	  adds three difficulty level buttons to the panel
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;


public class DifficultyButtonPanel extends JPanel {

	//variables
	private JLabel label;
	private JRadioButton easy;
	private JRadioButton normal;
	private JRadioButton difficult;
	private ButtonGroup bg;
	private App app;
	protected String selected;

	//constructor
	public DifficultyButtonPanel(App app) {
		super(new GridLayout(5, 1));
		this.setBackground(app.getBackground());
		this.app = app;
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		selected = "easy";

		//label
		label = new JLabel("  Difficulty:    ");
		this.add(label);

		//listener
		DifficultyItemListener itemListener = new DifficultyItemListener();
		buttonActionListener actionlis = new buttonActionListener();

		// make buttons
		easy = new JRadioButton("Easy");
		easy.setBackground(this.getBackground());
		easy.addItemListener(itemListener);
		easy.addActionListener(actionlis);

		normal = new JRadioButton("Normal");
		normal.setBackground(this.getBackground());
		normal.addItemListener(itemListener);
		normal.addActionListener(actionlis);

		difficult = new JRadioButton("Difficult");
		difficult.setBackground(this.getBackground());
		difficult.addItemListener(itemListener);
		difficult.addActionListener(actionlis);

		// make button group and add
		bg = new ButtonGroup();
		bg.add(easy);
		bg.add(normal);
		bg.add(difficult);

		// add buttons
		this.add(easy);
		this.add(normal);
		this.add(difficult);
	}

	 class buttonActionListener implements ActionListener {
		 	@Override
	      public void actionPerformed(ActionEvent e) {
	        selected = bg.getSelection().getActionCommand();
	      }
	    }
	/*
	 * listener class, implements item listener
	 */
	class DifficultyItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent ex) {
			String item = ((AbstractButton) ex.getItemSelectable()).getActionCommand();
			switch (item) {
			case "Easy":
				app.setDifficulty(App.DIFFICULTY.EASY);
				app.getGameBoard().setScoreIncrement(1);
				break;
			case "Normal":
				app.setDifficulty(App.DIFFICULTY.NORMAL);
				app.getGameBoard().setScoreIncrement(2);
				break;
			case "Difficult":
				app.setDifficulty(App.DIFFICULTY.HARD);
				app.getGameBoard().setScoreIncrement(3);
				break;
			}
		}
	}
}
