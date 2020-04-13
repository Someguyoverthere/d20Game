package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import common.actor.creatures.*;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAttack;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JTextArea textLog;
	private JMenuItem mntmNewGame;
	private JMenuItem mntmSaveGame;
	private JMenuItem mntmLoadGame;
	private JMenuItem mntmExit;
	private JLabel lblStrNum;
	private JScrollPane scrollPane_1;
	private JMenuItem mntmEnd;
	private JLabel lblDexNum;
	private JLabel lblConNum;
	private JLabel lblIntNum;
	private JLabel lblWisNum;
	private JLabel lblChaNum;
	private JLabel lblHPAmount;

	//fields specific to gameplay
	GameLog gameLog;
	CoreEngine CoreEngine;
	ArrayList<creature> enemyCreatures = new ArrayList<creature>();
	ArrayList<creature> alliedCreatures = new ArrayList<creature>();
	private boolean gameStarted = false;
	private boolean inBattle = false;
	private boolean inMenu = false;
	private int logLineNumber;
	private int actionsRemaining;
	private int globalTurnCount;
	private int battleTurnCount;
	
	

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
				} catch (Exception e) {
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

	// ========================================
	//Buttons doing things should go under here
	// ========================================
	private void createEvents() {

		// Game Engine Start
		mntmNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (gameStarted != true) {

					btnAttack.setText("Attack");
					gameStarted = true;
					System.out.println("Init core Engine");
					CoreEngine = new CoreEngine();

					gameLog.append("Game Start");
					gameLog.append("=============");
					updateLog();

					// Player player = CoreEngine.initializeGame();
					// Player character will always take array slot 0
					// I.E creatures.get(0)
					alliedCreatures.add(CoreEngine.initializeGame());
					updateStats();
					btnAttack.setEnabled(true);
					//TrainingDummy dummy = new TrainingDummy();
					//enemyCreatures.add(dummy);
					initializeBattle();
					gameLog.append("Battle Start!");
					gameLog.append("Enemy " + enemyCreatures.get(0).getName());
					if(enemyCreatures.size() > 1) {
						gameLog.append("along with!");
						for(int i = 1; i < enemyCreatures.size(); i++) {
							gameLog.append(enemyCreatures.get(i).getName());
						}
					}
					
					inBattle = true;

				} else {
					gameLog.append("Error: Game already in progress.");
					
				}
			
				updateLog();

			}
		});
		// ========================================
		// Exit menu function
		// ========================================
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(DO_NOTHING_ON_CLOSE);
			}
		});
		// ========================================
		// Attack button
		// ========================================
		btnAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String[] monsters = new String[enemyCreatures.size()+1];
				for(int i = 0; i < monsters.length-1; i++) {
					monsters[i] = enemyCreatures.get(i).getName();
				}
				monsters[monsters.length-1] = "Cancel";
				//pretty sure this will be useful somewhere later, but not here
				
				
				int selection = JOptionPane.showOptionDialog(null, "Please select a target", "Select Target", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, monsters, 0);
				System.out.println("selection" + selection);
				System.out.println("cancel location: " + (monsters.length-1));
				
				if((selection != (-1))){
					if((selection != (monsters.length-1))){
						System.out.println("Running attack action");
						CoreEngine.meleeAttackAction(alliedCreatures.get(0), enemyCreatures, gameLog, selection);
						actionsRemaining--;
						
					}

					
					
				}
				
				
				
				//SingleTargetSelection something = new SingleTargetSelection();
				//something.TargetSelection(enemyCreatures);
				//TargetingPane.singleTargetDialogue(null, "Please select a target", "Select Target", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, monsters, 0);

				updateLog();
				turnCheck();
			}
		});
		// ========================================
		// Reset the game to its initial state
		// ========================================
		mntmEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (gameStarted != false) {
					int selection = JOptionPane.showConfirmDialog(mntmEnd, "Are you sure you wish to end this game?");
					if(selection == 0) {
						
						alliedCreatures.clear();
						enemyCreatures.clear();
						btnAttack.setEnabled(false);
						gameStarted = false;
						gameLog.resetLog();
						logLineNumber = gameLog.getSize();
						textLog.setText("");
						
					}
					
					
					
				} else {
					gameLog.append("Can't reset what hasn't started.");
					updateLog();
				}

			}
		});
		
		mntmSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo0) {
				
			}
		});

	}//Create Events End
	
	

	
	
	private void turnCheck() {
		if(actionsRemaining == 0) {
			if(alliedCreatures.size() > 1) {
				CoreEngine.aITurn(alliedCreatures, enemyCreatures, gameLog);
			}
			
			CoreEngine.aITurn(enemyCreatures, alliedCreatures, gameLog);
			updateLog();
			actionsRemaining = 3;
		}
			
		
	}

	private void initializeBattle() {
		CoreEngine.initializeMobs(alliedCreatures.get(0).getCR(), enemyCreatures);
		actionsRemaining = 3;
		
	}
	
	

	
	private void updateLog() {
		ArrayList<String> tempLog = gameLog.getLastMessages(logLineNumber);
		

		for (int i = 0; (i < tempLog.size()); i++) {
			

			textLog.append(tempLog.get(i));

		}
		logLineNumber += tempLog.size();

	}
	
	
	

	//Only kept around just in case
	@Deprecated
	private void updateLog(boolean something) {
		ArrayList<String> tempLog = gameLog.getLastMessages(1);

		for (int i = tempLog.size() - 1; (i >= 0); i--) {

			textLog.append(tempLog.get(i));

		}

	}

	// For updating character attributes when it would be nessecary
	private void updateStats() {
		lblStrNum.setText(String.valueOf(alliedCreatures.get(0).getStr()));
		lblDexNum.setText(String.valueOf(alliedCreatures.get(0).getDex()));
		lblConNum.setText(String.valueOf(alliedCreatures.get(0).getCon()));
		lblIntNum.setText(String.valueOf(alliedCreatures.get(0).getIntel()));
		lblWisNum.setText(String.valueOf(alliedCreatures.get(0).getWis()));
		lblChaNum.setText(String.valueOf(alliedCreatures.get(0).getCha()));
		lblHPAmount.setText(String.valueOf(alliedCreatures.get(0).getHpCurrent()) + "/" + String.valueOf(alliedCreatures.get(0).getHpMax()));

	}

	// ========================================
	// GUI junk
	// ========================================

	private void initComponents() {
		// TODO Auto-generated method stub

		System.out.println("Init GameLog");
		gameLog = new GameLog();
		System.out.println("Gamelog initialized");
		logLineNumber = gameLog.getSize();

		setTitle("d20Game");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/Images/fireball-sky-3.png")));
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

		mntmEnd = new JMenuItem("End Game");
		mnFile.add(mntmEnd);

		mntmExit = new JMenuItem("Exit Game");
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnAttack = new JButton("Attack");
		btnAttack.setEnabled(false);
		btnAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JPanel pnlStats = new JPanel();
		pnlStats.setBorder(new TitledBorder(null, "Stats", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JComboBox comboBox = new JComboBox();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnAttack)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pnlStats, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAttack)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(9)
							.addComponent(pnlStats, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
				textLog = new JTextArea();
				textLog.setEditable(false);
				textLog.setColumns(10);
				textLog.setLineWrap(true);
				textLog.setRows(10);
				scrollPane_1.setViewportView(textLog);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(16, 32, 299, 14);
		progressBar.setValue(100);
		progressBar.setForeground(Color.BLUE);

		JLabel lblHitPoints = new JLabel("Hit Points");
		lblHitPoints.setBounds(6, 16, 46, 14);

		lblHPAmount = new JLabel("hp amount");
		lblHPAmount.setBounds(62, 16, 51, 14);
		pnlStats.setLayout(null);
		pnlStats.add(progressBar);
		pnlStats.add(lblHitPoints);
		pnlStats.add(lblHPAmount);

		JLabel lblStrength = new JLabel("Strength:");
		lblStrength.setBounds(16, 57, 46, 14);
		pnlStats.add(lblStrength);

		lblStrNum = new JLabel("?");
		lblStrNum.setBounds(92, 57, 46, 14);
		pnlStats.add(lblStrNum);
		
		lblDexNum = new JLabel("?");
		lblDexNum.setBounds(92, 70, 46, 14);
		pnlStats.add(lblDexNum);
		
		JLabel lblDexterity = new JLabel("Dexterity:");
		lblDexterity.setBounds(16, 70, 49, 14);
		pnlStats.add(lblDexterity);
		
		lblConNum = new JLabel("?");
		lblConNum.setBounds(92, 82, 46, 14);
		pnlStats.add(lblConNum);
		
		JLabel lblConstitution = new JLabel("Constitution:");
		lblConstitution.setBounds(16, 82, 62, 14);
		pnlStats.add(lblConstitution);
		
		lblIntNum = new JLabel("?");
		lblIntNum.setBounds(92, 95, 46, 14);
		pnlStats.add(lblIntNum);
		
		JLabel lblIntelligence = new JLabel("Intelligence:");
		lblIntelligence.setBounds(16, 95, 59, 14);
		pnlStats.add(lblIntelligence);
		
		lblWisNum = new JLabel("?");
		lblWisNum.setBounds(92, 107, 46, 14);
		pnlStats.add(lblWisNum);
		
		JLabel lblWisdom = new JLabel("Wisdom:");
		lblWisdom.setBounds(16, 107, 41, 14);
		pnlStats.add(lblWisdom);
		
		lblChaNum = new JLabel("?");
		lblChaNum.setBounds(92, 120, 46, 14);
		pnlStats.add(lblChaNum);
		
		JLabel lblCharisma = new JLabel("Charisma:");
		lblCharisma.setBounds(16, 120, 48, 14);
		pnlStats.add(lblCharisma);
		contentPane.setLayout(gl_contentPane);

	}
}
