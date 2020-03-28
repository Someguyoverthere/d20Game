package views;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import common.actor.creature;

public class SingleTargetSelection {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void TargetSelection(ArrayList<creature> monsters) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SingleTargetSelection window = new SingleTargetSelection();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SingleTargetSelection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
