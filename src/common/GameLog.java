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
		gameLog.add(0, message + "\n");

	}

	public ArrayList<String> getLastMessages() {

		ArrayList<String> tempLog = new ArrayList<String>();

		if (gameLog.size() < 10) {
			System.out.println("Log size less than 10");

			for (int i = gameLog.size(); (i >= 0); i--) {

				tempLog.add(0, gameLog.get(i));

			}

		} else {
			System.out.println("Log size greater than 10" + gameLog.size());

			for (int i = gameLog.size() - 1; (i >= 0); i--) {

				tempLog.add(0, gameLog.get(i));

			}

		}

		return tempLog;

	}

	public int getSize() {
		return gameLog.size();
	}

}
