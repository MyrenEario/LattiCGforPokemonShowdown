package pokemonshowdown;

public class Move {
	public static Move Earthquake = new Move("Earthquake", 100, true, false);
	public static Move Struggle = new Move("Struggle", 50, false, false);
	public static Move AerialAce = new Move("Aerial Ace", 60, false, false);
	public static Move HPBug = new Move("HP Bug", 70, false, false);
	public static Move HPFight = new Move("HP Fight", 70, false, true);
	public static Move RockSlide = new Move("Rock Slide", 75, false, false);
	
	
	
	public static Move getMove(String name) {
		if(name.equals("Earthquake")) {
			return Earthquake;
		}

		if(name.equals("Struggle")) {
			return Struggle;
		}

		if(name.equals("AerialAce")) {
			return AerialAce;
		}

		if(name.equals("HPBug")) {
			return HPBug;
		}

		if(name.equals("HPFight")) {
			return HPFight;
		}

		if(name.equals("RockSlide")) {
			return RockSlide;
		}
		
		return null;
	}
	
	
	public String name;
	public int baseDamage;
	boolean groundStab;
	boolean supereffective;
	
	public Move(String name, int baseDamage, boolean groundStab, boolean supereffective) {
		this.name=name;
		this.baseDamage = baseDamage;
		this.groundStab = groundStab;
		this.supereffective = supereffective;
	}

	public static float getModifierFromBoost(int boost) {
		switch(boost) {
		case -6: return 2/(float)8;
		case -5: return 2/(float)7;
		case -4: return 2/(float)6;
		case -3: return 2/(float)5;
		case -2: return 2/(float)4;
		case -1: return 2/(float)3;
		case 0: return 1;
		case 1: return 3/(float)2;
		case 2: return 4/(float)2;
		case 3: return 5/(float)2;
		case 4: return 6/(float)2;
		case 5: return 7/(float)2;
		case 6: return 8/(float)2;
		}
		throw new NullPointerException();
	}
	
	public static float toPercentage(int damageAmount, int health_points) {
		return ( (1000*damageAmount + (health_points/2) )/ health_points) / (float)10;
	}
	
	
	public int[] advDamage(int attack, int defense, boolean crit, boolean choiceBanded, boolean burned, int level, int attackBoost, int defenseBoost) {
		int res = baseDamage;
		
		int actualAttack = attack;
		if(choiceBanded) {
			actualAttack = (actualAttack * 3)/2; 
		}
		if(attackBoost >= 0 || (attackBoost < 0 && !crit)) {
			actualAttack = (int)(actualAttack * getModifierFromBoost(attackBoost));
		}
		
		int actualDefense = defense;
		if(defenseBoost <= 0 || (defenseBoost > 0 && !crit)) {
			actualDefense = (int)(actualDefense * getModifierFromBoost(defenseBoost));
		}
		
		res = (int) Math.floor(Math.floor((Math.floor( (2*(float)level) / 5 + 2) * (float)actualAttack * res) / (float)actualDefense) / (float)50 );
		

		
		if(burned) {
			res = res/2;
		}
		
		res = res + 2;
		
		if(crit) {
			res *= 2;
		}
		if(groundStab) {
			res = (res * 3)/2;
		}
		if(supereffective) {
			res *= 2;
		}

		
		int[] resArray = new int[16];
		for(int i=0;i<resArray.length;i++) {
			resArray[i] = (int)((res * (85+i)) / (float)100);
		}
		return resArray;
	}
	
	
//	public static void main(String[] args) {
//		
//		
//		int[] advDamage = Earthquake.advDamage(258, 292, false, true, false, 100, 0, 0);
//		for(int i: advDamage) {
//			System.out.println(Move.toPercentage(i,374));
//		}
//		
//
//		advDamage = Earthquake.advDamage(259, 292, false, true, false, 100, 0, 0);
//		for(int i: advDamage) {
//			System.out.println(Move.toPercentage(i,374));
//		}
//	}
	
	
}
