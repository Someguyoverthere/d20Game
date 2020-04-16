package common.actor.creatures;

import common.actor.creature;

public class TrainingGobbo extends creature {
	public TrainingGobbo(){
		super(true, 4, 10);
		name = "Training Gobbo";
		CR = 1;
		super.setAC(-5);
	}

	@Override
	public String AI() {
		// TODO Auto-generated method stub
		return "tesT";
	}

}
