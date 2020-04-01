package common.actor;

import common.Roller;
import java.util.ArrayList;

public abstract class creature {
	
	ArrayList<object> Inventory = new ArrayList<object>();
	ArrayList<object> equippedWeapons = new ArrayList<object>();
	ArrayList<object> naturalWeapons;
	
	
	int handSlots = 0;
	object equippedArmor = null;
	
	
	protected boolean isWieldingMainhand = false;
	protected boolean isWieldingOffHand = false;
	protected String name = "";
	protected boolean isDead = false;
	protected boolean isIncapacitated = false;
	
	protected double CR;
	
	protected int EXP = 0;

	protected int str;
	protected int dex;
	protected int con;
	protected int intel;
	protected int wis;
	protected int cha;

	protected int tempStr;
	protected int tempDex;
	protected int tempCon;
	protected int tempIntel;
	protected int tempWis;
	protected int tempCha;

	protected int hpMax;
	protected int hpCurrent;
	protected int[] hitDice;
	protected int[] rolledHP;
	
	protected String attackBonusAttribute = "str";

	protected int AC = 10;
	boolean infiniteHP = false;

	// ========================================
	// Elemental Resistances
	// ========================================
	protected int fireResistance;
	protected int coldResistance;
	protected int electricResistance;
	protected int forceResistance;
	protected int sonicResistance;
	protected int positiveResistance;
	protected int negativeResistance;

	// ========================================
	// Physical Resistances
	// ========================================
	protected int slashingResistance;
	protected int piercingResistance;
	protected int blundgeoningResistance;
	protected int ballisticResistance;

	// ========================================
	// Material Resistances
	// ========================================

	// ========================================
	// Constructors
	// ========================================
	public creature() {

	}

	public creature(boolean randomStats, int hdNumber, int hdSize) {
		this.str = Roller.rollTotal(3, 6);
		this.dex = Roller.rollTotal(3, 6);
		this.con = Roller.rollTotal(3, 6);
		this.intel = Roller.rollTotal(3, 6);
		this.wis = Roller.rollTotal(3, 6);
		this.cha = Roller.rollTotal(3, 6);
		hitDice = new int[hdNumber];
		for(int i = 0; i > hitDice.length; i++) {
			hitDice[i] = hdSize;
		}
		rolledHP = Roller.rollSet(hdNumber, hdSize);
		for(int i = 0; i < rolledHP.length; i++) {
			hpMax += rolledHP[i];
		}
		hpMax += rolledHP.length * hdNumber; 
		this.hpCurrent = hpMax;
		

	}

	// Set stats
	public creature(int str, int dex, int con, int intel, int wis, int cha, int hpMax, boolean infiniteHP) {
		this.str = str;
		this.dex = dex;
		this.con = con;
		this.intel = intel;
		this.wis = wis;
		this.cha = cha;
		this.hpMax = hpMax;
		this.hpCurrent = hpMax;
		this.infiniteHP = infiniteHP;

	}
	
	public double getCR() {
		return CR;
	}

	public void setCR(int cR) {
		CR = cR;
	}

	public int[] getDamageDice() {
		int[] damage = new int[2*equippedWeapons.size()];
		damage[0] = equippedWeapons.get(0).getNumOfDamageDice();
		damage[1] = equippedWeapons.get(0).getSizeOfDamageDice();
		
		
		
		return damage;
	}
	
	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isIncapacitated() {
		return isIncapacitated;
	}

	public void setIncapacitated(boolean isIncapacitated) {
		this.isIncapacitated = isIncapacitated;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
		
	}
	
	
	
	public int rollDamageDice() {
		
		return Roller.rollTotal(equippedWeapons.get(0).getNumOfDamageDice(), equippedWeapons.get(0).getSizeOfDamageDice());
	}
	
	public void addItem(object item) {
		Inventory.add(item);
	}
	
	public void equipMainHand(int itemNumber) {
		equippedWeapons.add(0, Inventory.get(itemNumber));
		
	}
	
	

	public boolean isWieldingMainhand() {
		return isWieldingMainhand;
	}

	public void setWieldingMainhand(boolean isWieldingMainhand) {
		this.isWieldingMainhand = isWieldingMainhand;
	}

	public boolean isWieldingOffHand() {
		return isWieldingOffHand;
	}

	public void setWieldingOffHand(boolean isWieldingOffHand) {
		this.isWieldingOffHand = isWieldingOffHand;
	}

	public int getMod(int stat) {
		
		double mod = (10 - stat)/2;

		if (mod > 0) { // if attribute modifier is positive, we can cast as an int and return it since
						// we don't care if the decimal gets cut off
			return (int) mod;
		} else { // in the attribute modifier is negative, we want to make sure any decimals get
					// rolled over to the next whole number.
			mod -= 0.5;
		}
		return (int) mod;
	}
	
	public int getAttackBonus() {
		switch(attackBonusAttribute) {
		case "Dex": 
			if(this.dex > this.str) {
				return getMod(dex);
			}
		
		}
		
		return getMod(str);
		
	}
	
	public String AI() {
		
		return "test";
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getDex() {
		return dex;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public int getCon() {
		return con;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public int getIntel() {
		return intel;
	}

	public void setIntel(int intel) {
		this.intel = intel;
	}

	public int getWis() {
		return wis;
	}

	public void setWis(int wis) {
		this.wis = wis;
	}

	public int getCha() {
		return cha;
	}

	public void setCha(int cha) {
		this.cha = cha;
	}

	public int getTempStr() {
		return tempStr;
	}

	public void setTempStr(int tempStr) {
		this.tempStr = tempStr;
	}

	public int getTempDex() {
		return tempDex;
	}

	public void setTempDex(int tempDex) {
		this.tempDex = tempDex;
	}

	public int getTempCon() {
		return tempCon;
	}

	public void setTempCon(int tempCon) {
		this.tempCon = tempCon;
	}

	public int getTempIntel() {
		return tempIntel;
	}

	public void setTempIntel(int tempIntel) {
		this.tempIntel = tempIntel;
	}

	public int getTempWis() {
		return tempWis;
	}

	public void setTempWis(int tempWis) {
		this.tempWis = tempWis;
	}

	public int getTempCha() {
		return tempCha;
	}

	public void setTempCha(int tempCha) {
		this.tempCha = tempCha;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getHpCurrent() {
		return hpCurrent;
	}
	
	public int getHPRemainingPercent() {
		float whatever = (float) hpCurrent/(float) hpMax;
		return (int) (whatever*100);
	}

	public void setHpCurrent(int hpCurrent) {
		this.hpCurrent = hpCurrent;
	}
	
	public void takeDamage(int damage) {
		hpCurrent -= damage;
	}

	public int getAC() {
		return AC;
	}

	public void setAC(int aC) {
		AC = aC;
	}

	public boolean isInfiniteHP() {
		return infiniteHP;
	}

	public void setInfiniteHP(boolean infiniteHP) {
		this.infiniteHP = infiniteHP;
	}

	public int getFireResistance() {
		return fireResistance;
	}

	public void setFireResistance(int fireResistance) {
		this.fireResistance = fireResistance;
	}

	public int getColdResistance() {
		return coldResistance;
	}

	public void setColdResistance(int coldResistance) {
		this.coldResistance = coldResistance;
	}

	public int getElectricResistance() {
		return electricResistance;
	}

	public void setElectricResistance(int electricResistance) {
		this.electricResistance = electricResistance;
	}

	public int getForceResistance() {
		return forceResistance;
	}

	public void setForceResistance(int forceResistance) {
		this.forceResistance = forceResistance;
	}

	public int getSonicResistance() {
		return sonicResistance;
	}

	public void setSonicResistance(int sonicResistance) {
		this.sonicResistance = sonicResistance;
	}

	public int getPositiveResistance() {
		return positiveResistance;
	}

	public void setPositiveResistance(int positiveResistance) {
		this.positiveResistance = positiveResistance;
	}

	public int getNegativeResistance() {
		return negativeResistance;
	}

	public void setNegativeResistance(int negativeResistance) {
		this.negativeResistance = negativeResistance;
	}

	public int getSlashingResistance() {
		return slashingResistance;
	}

	public void setSlashingResistance(int slashingResistance) {
		this.slashingResistance = slashingResistance;
	}

	public int getPiercingResistance() {
		return piercingResistance;
	}

	public void setPiercingResistance(int piercingResistance) {
		this.piercingResistance = piercingResistance;
	}

	public int getBlundgeoningResistance() {
		return blundgeoningResistance;
	}

	public void setBlundgeoningResistance(int blundgeoningResistance) {
		this.blundgeoningResistance = blundgeoningResistance;
	}

	public int getBallisticResistance() {
		return ballisticResistance;
	}

	public void setBallisticResistance(int ballisticResistance) {
		this.ballisticResistance = ballisticResistance;
	}

}
