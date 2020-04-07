/*
 * Contains a roll function and other supporting functions
 */
package common;

public class Roller {
	
	/**
	 * Rolls dice and places the values into an array.
	 *
	 * @param diceNum Number of dice rolled
	 * @param diceSize the maximum roll possible on each die
	 * @return The dice rolls in the form of an array
	 */
	public static int[] rollSet(int diceNum, int diceSize) {
		int[] rolls = new int[diceNum];
		for(int i = 0; i < diceNum; i++) {
			rolls[i] = (int) (Math.random() * (diceSize - 1)) + 1;
		}
			
		
		return rolls;
		
	}
	
	/**
	 * Rolls dice and returns the cumulative value of the rolls.
	 *
	 * @param diceNum Number of dice rolled
	 * @param diceSize the maximum roll possible on each die
	 * @return returns the cumulative value of the rolls
	 */
	public static int rollTotal(int diceNum, int diceSize) {
		int total = 0;
		for(int i = 0; i < diceNum; i++) {
			total += (int) (Math.random() * (diceSize - 1)) + 1;
		}
			
		
		return total;
		
	}
	
	public static int rollTotalDropOne(int diceNum, int diceSize) {
		int total = 0;
		int roll;
		int lowestNum = Integer.MAX_VALUE;
		
		
		for(int i = 0; i < diceNum; i++) {
			roll = (int) (Math.random() * (diceSize - 1)) + 1;
			total += roll;
			
			if(roll <= lowestNum) {
				lowestNum = roll;
			}
			
		}
		
		total -= lowestNum;
		
			
		
		return total;
		
	}
	
	public static float rollTotal(float diceNum, float diceSize) {
		float total = 0;
		for(int i = 0; i < diceNum; i++) {
			total += (int) (Math.random() * (diceSize - 1)) + 1;
		}
			
		
		return total;
		
	}
	
	public static int rollTotal(int[] diceNumAndSize) {
		int total = 0;
		for(int i = 0; i < diceNumAndSize[0]; i++) {
			total += (int) (Math.random() * (diceNumAndSize[1] - 1)) + 1;
		}
			
		
		return total;
		
	}
	
	
	
	/**
	 * This function takes an array and changes a specific number within the array into another number.
	 *
	 * @param rolls is an array that contains dice rolls
	 * @param oldNum is the number that you want to change
	 * @param newNum is the number oldNum will become
	 * @return the int[] after being modified
	 */
	public static int[] rollConverter(int[] rolls, int[] oldNums, int newNums) {
		for(int i = 0; i < rolls.length; i++) {
			for(int j = 0; i < rolls.length; j++) {
				if(rolls[i] == oldNums[j]) {
					rolls[i] = oldNums[j];
				}
				
			}
		}
		
		return rolls;
	}
}
