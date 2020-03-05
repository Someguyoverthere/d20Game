package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import engine.CoreEngine;
import common.GameLog;


import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTextLog;
	private JButton btnStartAttack;
	private boolean gameStarted = false;
	private boolean inBattle = false;
	private boolean inMenu = false;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GUI frame = new GUI();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		
		
		
		initComponents();
		createEvents();
		
		
	}

	//========================================
	//
	//========================================
	private void createEvents() {
		
		//Game Engine Start
		if(gameStarted == false) {
			btnStartAttack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnStartAttack.setText("Attack");
					gameStarted = true;
					CoreEngine CoreEngine = new CoreEngine();
					GameLog gameLog = new GameLog();
					gameLog.append("Game Start");
					updateLog(gameLog);
				}
			});
		}
		
		// TODO Auto-generated method stub
		
		
		
		
		
	}
	
	private void updateLog(GameLog gameLog) {
		ArrayList<String> tempLog = gameLog.getLastMessages();
		String text = "";
		
		int i = 0; while (i < tempLog.size()) i++;
		{
			text += tempLog.get(i);
			
			
		}
		
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		
		setTitle("d20Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/Images/fireball-sky-3.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 471);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnStartAttack = new JButton("Start");
		btnStartAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		lblTextLog = new JLabel(" ");
		lblTextLog.setBackground(Color.BLACK);
		
		scrollPane = new JScrollPane();
		
		JPanel pnlStats = new JPanel();
		pnlStats.setBorder(new TitledBorder(null, "Stats", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnStartAttack, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblTextLog, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlStats, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(144)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlStats, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTextLog, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStartAttack)
					.addContainerGap())
		);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(16, 32, 299, 14);
		progressBar.setValue(100);
		progressBar.setForeground(Color.BLUE);
		
		JLabel lblHitPoints = new JLabel("Hit Points");
		lblHitPoints.setBounds(6, 16, 46, 14);
		
		JLabel lblHpAmount = new JLabel("hp amount");
		lblHpAmount.setBounds(62, 16, 51, 14);
		pnlStats.setLayout(null);
		pnlStats.add(progressBar);
		pnlStats.add(lblHitPoints);
		pnlStats.add(lblHpAmount);
		contentPane.setLayout(gl_contentPane);
		
	}
}
