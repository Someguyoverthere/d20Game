package engine;

import common.actor.*;
import common.actor.objects.weapons.*;
import common.actor.player.Player;

import java.util.ArrayList;

import common.Roller;
import common.actor.creature.*;

public class CoreEngine {
	

	public Player initializeGame() {
		Player player = new Player(false);
		ArmingSword armingsword = new ArmingSword();
		player.addItem(armingsword);
		player.equipMainHand(0);
		return player;
		
		
		
	}
	
	
	public int meleeAttackAction(creature creature1, creature creature2) {
		if(creature1.isWieldingMainhand() == true) {
			if((creature1.getAttackBonus()) + Roller.rollTotal(1, 20) > creature2.getAC()) {
				int damage = creature1.rollDamageDice() + creature1.getMod(creature1.getStr());
				creature2.takeDamage(damage);
				return damage;
				
			}
			
		}
		
		return 0;
		
	}
	
	public void battle(ArrayList<creature> alliedCreatures) {
		
		
	}
	
	
	
	
	

}
