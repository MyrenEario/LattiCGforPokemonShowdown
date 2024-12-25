package pokemonshowdown;

public class PRNG {
	
	
	public static boolean randomChance(long seed, int numerator, int denominator) {
		return next(seed, denominator) < numerator;
	}
	
	public static float next(long seed) {
		long upper32bits = seed >>> 32;
		return ((float) upper32bits / (float) (1L << 32));
	}
	
	
	public static int next(long seed, int n) {
		long upper32bits = seed >>> 32;
		return (int) ((upper32bits * n) / (1L << 32)  );
	}
	
	public static String metroMove(long seed, int gen) {
		String[] moves = Metromovelist.getMetromoves(gen);
		if(gen == 2 || gen == 4) {
			return moves[next(seed,moves.length)] + "/" + moves[next(seed,moves.length)+1];
		}
		return moves[next(seed,moves.length)];
	}
}
