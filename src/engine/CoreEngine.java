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
	
	public void getStr(creature creature) {
		System.out.println(creature.getStr());
		
	}
	
	
	
	
	

}
