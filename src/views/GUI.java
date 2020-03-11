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
import common.*;
import common.actor.player.Player;
import common.actor.*;
import common.actor.objects.*;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.TextArea;
import javax.swing.JTextArea;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnStartAttack;
	private boolean gameStarted = false;
	private boolean inBattle = false;
	private boolean inMenu = false;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JTextArea textLog;
	private JMenuItem mntmNewGame;
	private JMenuItem mntmSaveGame;
	private JMenuItem mntmLoadGame;
	private JMenuItem mntmExit;
	private JLabel lblStrNum;
	
	
	

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
		
		mntmNewGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					System.out.println("Init GameLog");
					GameLog gameLog = new GameLog();
					System.out.println("Gamelog initialized");
					
					if(gameStarted != true) {
						
						btnStartAttack.setText("Attack");
						gameStarted = true;
						System.out.println("Init core Engine");
						CoreEngine CoreEngine = new CoreEngine();
						
						
						
						gameLog.append("Game Start");
						gameLog.append("=============");
						updateLog(gameLog);
						
						Player player = CoreEngine.initializeGame();
						updateStats(player.getStr(), lblStrNum);
						
					}
					else {
						gameLog.append("Error: Game already in progress.");
						updateLog(gameLog);
					}
					
				}
			});
		
		
		
		
		
		
		
		
	}
	
	private void updateLog(GameLog gameLog) {
		ArrayList<String> tempLog = gameLog.getLastMessages();
		//String text = "";
		
		for ( int i = tempLog.size()-1; (i >= 0); i--)
		{
			
			textLog.append(tempLog.get(i));
			
			
			
		}
		
		
		
	}
	
	private void updateStats(int stat, JLabel attribute) {
		attribute.setText(String.valueOf(stat));
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		
		setTitle("d20Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/Images/fireball-sky-3.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 471);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmNewGame = new JMenuItem("New Game");
		mnFile.add(mntmNewGame);
		
		mntmSaveGame = new JMenuItem("Save Game");
		mnFile.add(mntmSaveGame);
		
		mntmLoadGame = new JMenuItem("Load Game");
		mnFile.add(mntmLoadGame);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnStartAttack = new JButton("Start");
		btnStartAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		scrollPane = new JScrollPane();
		
		JPanel pnlStats = new JPanel();
		pnlStats.setBorder(new TitledBorder(null, "Stats", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		textLog = new JTextArea();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnStartAttack, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textLog, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlStats, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
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
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(pnlStats, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
								.addComponent(textLog, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE))))
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
		
		JLabel lblStrength = new JLabel("Strength:");
		lblStrength.setBounds(16, 57, 46, 14);
		pnlStats.add(lblStrength);
		
		lblStrNum = new JLabel("?");
		lblStrNum.setBounds(67, 57, 46, 14);
		pnlStats.add(lblStrNum);
		contentPane.setLayout(gl_contentPane);
		
	}
}
