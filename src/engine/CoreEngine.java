package engine;

import common.actor.*;
import common.actor.objects.weapons.*;
import common.actor.player.Player;

import java.util.ArrayList;

import common.GameLog;
import common.Roller;
import common.actor.creatures.*;

public class CoreEngine {

	public Player initializeGame() {
		Player player = new Player(false);
		player.addItem(new ArmingSword());
		player.equipMainHand(0);
		return player;

	}

	public int meleeAttackAction(creature attacker, ArrayList<creature> defender, GameLog gameLog,
			int selectedDefender) {
		if (attacker.isWieldingMainhand() == true) {
			if ((attacker.getAttackBonus()) + Roller.rollTotal(1, 20) > defender.get(selectedDefender).getAC()) {
				int damage = attacker.rollDamageDice() + attacker.getMod(attacker.getStr());
				defender.get(selectedDefender).takeDamage(damage);

				if (damage != 0) {
					gameLog.append(attacker.getName() + " dealt " + damage + " damage to"
							+ defender.get(selectedDefender).getName());

					System.out.println("percent: " + defender.get(selectedDefender).getHPRemainingPercent() + "hp "
							+ defender.get(selectedDefender).getHpCurrent() + "/"
							+ defender.get(selectedDefender).getHpMax());

					// Varies text depending on opponent remaining HP
					healthCheck(defender.get(selectedDefender), gameLog);
					

				}

			} else {
				gameLog.append("Failed to damage " + defender.get(selectedDefender).getName() + "!");
			}

		}

		return 0;

	}

	public int rangedAttackAction(creature creature1, creature creature2) {
		if (creature1.isWieldingMainhand() == true) {
			if ((creature1.getAttackBonus()) + Roller.rollTotal(1, 20) > creature2.getAC()) {
				int damage = creature1.rollDamageDice() + creature1.getMod(creature1.getStr());
				creature2.takeDamage(damage);
				return damage;

			}

		}

		return 0;

	}

	public void aITurn(ArrayList<creature> attacker, ArrayList<creature> defender, GameLog gameLog) {
		System.out.println("AI Turn");
		int i = 0;
		int target = 0;

		// Check to make sure the target is valid
		while (deathCheck(defender.get(target))) {
			target = Roller.rollTotal(1, defender.size()) - 1;

		}

		// advances the array incase player is there, so it won't take any action
		if (attacker.get(0).getName().equals("Player")) {
			System.out.println("Player check success");
			i++;
		}
		// Run a loop for each attacker to take an action
		while (i < attacker.size()) {
			System.out.println("Attacker loop");

			// check to see if the attacker is alive and able to attack
			if (!deathCheck(attacker.get(i))) {

				switch (attacker.get(i).AI()) {
				case "meleeAttack":
					meleeAttackAction(attacker.get(i), defender, gameLog, target);
					break;

				default:
					gameLog.append(attacker.get(i).getName() + " does nothing.");
					break;
				}
				
				

			} else {
				System.out.println(attacker.get(i) + "is unable to fight.");

			}

			i++;

		}

	}

	// checks if a creature is dead or incapacitated
	public boolean deathCheck(creature creature) {
		if (creature.isDead() || creature.isIncapacitated() || creature.getHpCurrent() <= 0) {
			return true;
		}
		return false;
	}
	
	public void healthCheck(creature defender, GameLog gameLog) {
		if (defender.getHPRemainingPercent() > 75) {
			gameLog.append(defender.getName() + " looks minorly injuried!");

		}
		if (defender.getHPRemainingPercent() > 50
				&& defender.getHPRemainingPercent() < 75) {
			gameLog.append(defender.getName() + " looks moderately injuried!");

		}
		if (defender.getHPRemainingPercent() > 25
				&& defender.getHPRemainingPercent() < 50) {
			gameLog.append(defender.getName() + " looks majorly injuried!");

		}
		if (defender.getHPRemainingPercent() > 0
				&& defender.getHPRemainingPercent() < 25) {
			gameLog.append(defender.getName() + " looks gravely injuried!");

		}
		
		if (defender.getHPRemainingPercent() <= 0) {
			gameLog.append(defender.getName() + " has fallen in battle!");
		}
		
	}

	// called by GUI to generated the encounter
	public void initializeMobs(double playerLevel, ArrayList<creature> enemies) {

		// Generates the target CR
		// weirdly the roller doesn't work if it's one line with target CR
		int roll = Roller.rollTotal(1, 4) - 2;
		double targetCR = playerLevel + roll;

		// decision tree if CR is less than 1
		if (targetCR < 1) {
			if (targetCR == 0) {

				targetCR = .5;
			} else {

				targetCR = .25;
			}

		}

		// For testing only
		targetCR = 5;

		createMobs(enemies, targetCR);
		System.out.println("Encounter CR is: " + calcEncounterCR(enemies));

	}

	// sets up the enemy array for each encounter
	public void createMobs(ArrayList<creature> enemies, double targetCR) {
		double levelRoll = 0;
		int encounterLoop = 0;

		// loop for filling up the enemy array iteratively
		System.out.println("Encounter total CR" + targetCR);
		while (targetCR > 0 && encounterLoop < 50) {
			System.out.println("Encounter generation loop: " + encounterLoop);
			if (targetCR >= 1 && Roller.rollTotal(1f, (float) targetCR + 1) == targetCR + 1) {
				System.out.println("entering less than 1 branch");
				switch (Roller.rollTotal(1, 3)) {
				case 1:
					levelRoll = .25;
					break;
				case 2:
					levelRoll = .5;
					break;
				case 3:
					levelRoll = .75;
					break;

				}

			} else {
				levelRoll = Roller.rollTotal(1f, (float) targetCR);

			}

			System.out.println("Level Roll: " + levelRoll);

			// sets the minimum in case it would go below .25
			if (levelRoll < .25) {
				levelRoll = .25;
			}

			// sets the maximum if the level roll would exceed the target CR
			if (levelRoll > targetCR) {
				levelRoll = targetCR;
			}

			enemies.add(encounterLoop, addMob(levelRoll));

			targetCR -= enemies.get(encounterLoop).getCR();

			encounterLoop++;
			System.out.println("Remaining CR: " + targetCR);
			if (encounterLoop > 49) {
				System.out.println("Emergency Break");
			}

		}

	}

	// calculated the encounter's CR
	public double calcEncounterCR(ArrayList<creature> enemies) {
		double CR = 0;
		try {
			for (int i = 0; i < enemies.size(); i++) {
				CR += enemies.get(0).getCR();
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Soemthing went wrong with the array.");
		}

		return CR;
	}

	// returns a randomly selected creature based on the level rolled value passed
	// in
	private creature addMob(double CR) {
		// Fun fact: switched don't like doubles or floats
		System.out.println("Addmob CR: " + CR);

		if (CR == .25) {
			return new TrainingDummy();
		}
		if (CR == .5) {
			return new TrainingDoggo();
		}
		if (CR == .75) {
			return new TrainingZebra();
		}

		switch ((int) CR) {
		case 1:

			return new TrainingGobbo();

		case 2:
			return new TrainingOggo();
		case 3:
			return new TrainingDragon();
		}

		return new MobError();
	}

	public void battle(ArrayList<creature> alliedCreatures) {

	}

}
