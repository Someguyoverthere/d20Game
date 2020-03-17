package common;

import java.util.ArrayList;

public class GameLog {
	ArrayList<String> gameLog = new ArrayList<String>();

	private void initArray() {

		for (int i = 0; (i < 11); i++) {

			gameLog.add("");

		}
		System.out.println("Log Initialized");

	}

	public GameLog() {
		initArray();

	}

	public void append(String message) {
		gameLog.add(message + "\n");

	}

	public ArrayList<String> getLastMessages(int logLineNumber) {

		ArrayList<String> tempLog = new ArrayList<String>();

		for (int i = logLineNumber; (i < gameLog.size()); i++) {

			tempLog.add(gameLog.get(i));

		}

		return tempLog;

	}

	public int getSize() {
		return gameLog.size();
	}

	public void resetLog() {
		gameLog.clear();
		initArray();
	}

}
