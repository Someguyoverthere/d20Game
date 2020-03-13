package engine;

import common.actor.*;
import common.actor.player.Player;
import common.Roller;
import common.actor.creature.*;

public class CoreEngine {
	

	public Player initializeGame() {
		Player player = new Player(false);
		return player;
		
		
		
	}
	
	
	public int attack(creature creature1, creature creature2) {
		
		if((creature1.getAttackBonus()) + Roller.rollTotal(1, 20) > creature2.getAC()) {
			return 5;
			
		}
		return 0;
		
	}
	
	
	
	
	

}
