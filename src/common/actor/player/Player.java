package common.actor.player;

import common.actor.creature;

public class Player extends creature {
	
	

	public Player(boolean randomStats) {
		super(true, 1, 12);
		name = "Player";
		// TODO Auto-generated constructor stub
		CR = 1;
	}
	
	public int attack() {
		
		return 0;
	}

}
