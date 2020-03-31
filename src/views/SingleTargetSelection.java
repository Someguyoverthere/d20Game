package views;

import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import common.actor.creature;
import java.awt.GridLayout;

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
		frame.getContentPane().setLayout(new GridLayout(1, 0, 20, 20));
		frame.setVisible(true);
		//frame.setLayout(new GridLayout());
		
		//generating buttons

		for (int row = 0; row<7; row++) {
		    for (int col = 0; col<10; col++) {
		        JButton b = new JButton();
		        frame.getContentPane().add(b);
		        //frame.getContentPane().add(b, "cell "+ col +" "+ row +",grow");
		    }
		}
		
	}
	
	private int[] calculateLayout() {
		int[] bounds = new int[4];
		
		return bounds;
		
	}

}
