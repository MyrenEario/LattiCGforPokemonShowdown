package pokemonshowdown;

import java.math.BigInteger;
import java.util.List;

import com.seedfinding.latticg.RandomReverser;
import com.seedfinding.latticg.reversal.calltype.FilteredSkip;
import com.seedfinding.latticg.util.LCG;

public class PokemonRandomReverser extends RandomReverser{
	//TODO: 1L << 64 is 1. Replace everything in LCG by BigInteger
	public static final LCG PSRNG = new LCG(0x5D588B656C078965L, 0x00269EC3L, 0);
	
    public PokemonRandomReverser( List<FilteredSkip> filteredSkips) {
		super(PSRNG, filteredSkips);
	}
    
    
    
    public void addNextIntCall(int n, int value) {
    	addNextIntCall(n, value, value+1);
    }
    
    

    
    //x <= next(n) < y
    //implies
    //2^64*x <= n*seed < 2^64*y + n*2^32
    //which implies
    //2^64*x / n <= seed <= 2^64*y / n   + 2^32
    public void addNextIntCall(int n, int min, int max) {
        if (n <= 0) {
            throw new IllegalArgumentException(String.format("Bad bound for nextInt call can only be positive : %d", n));
        }
        
        BigInteger lower_bound = BigInteger.valueOf(min);
        lower_bound = lower_bound.shiftLeft(64);
        lower_bound = lower_bound.divide(BigInteger.valueOf(n));
        
        BigInteger upper_bound = BigInteger.valueOf(max);
        upper_bound = upper_bound.shiftLeft(64);
        upper_bound = upper_bound.divide(BigInteger.valueOf(n));
        upper_bound = upper_bound.add(BigInteger.valueOf(2).pow(32));
        
        addMeasuredSeed(lower_bound, upper_bound);
    }
    
    public void consumeNextIntCall(int num_calls) {
    	addUnmeasuredSeeds(num_calls);
    }
    
    
}
