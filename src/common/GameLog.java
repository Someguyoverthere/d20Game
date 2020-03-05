package common;

import java.util.ArrayList;

public class GameLog {
	ArrayList<String> gameLog = new ArrayList<String>();
	
	private void initArray() {
		int i = 0; while (i < 11); i++;
		{
			gameLog.add(0,"" + i);
		}
		
	}
	
	public GameLog() {
		initArray();
		
		
	}
	
	public void append(String message) {
		gameLog.add(0,message + "\n");
		
	}
	
	public ArrayList<String> getLastMessages() {
		
		ArrayList<String> tempLog = new ArrayList<String>();
		
		int i = gameLog.size() - 10; while (i < gameLog.size()); i++;
		{
			System.out.println("Element:" + i + gameLog.get(0));
			
			
			//tempLog.add(0,gameLog.get(i));
			
			
		}
		
		return tempLog;
		
		
	}
	
	
	
	public int getSize() {
		return gameLog.size();
	}
	
	
	
	
	

}
