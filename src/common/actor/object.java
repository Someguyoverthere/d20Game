package common.actor;

public abstract class object {
	
	protected int hpMax;
	protected int hpCurrent;
	
	protected int AC = 5;
	
	protected int damageMin;
	protected int damageMax;
	
	//========================================
	//Elemental Resistances
	//========================================
	protected int fireResistance;
	protected int coldResistance;
	protected int electricResistance;
	protected int forceResistance;
	protected int sonicResistance;
	protected int positiveResistance;
	protected int negativeResistance;
	
	
	//========================================
	//Physical Resistances
	//========================================
	protected int slashingResistance;
	protected int piercingResistance;
	protected int blundgeoningResistance;
	protected int ballisticResistance;
	
	
	//========================================
	//Material Resistances
	//========================================

}
