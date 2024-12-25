package com.seedfinding.latticg;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.seedfinding.latticg.util.LCG;

import pokemonshowdown.PokemonRandomReverser;

public class RandomReverserTest {
	public static void main(String[] args) {
				
		pokemon_showdown_reverser_test();	
	}
	
	public static void pokemon_showdown_reverser_test() {
		PokemonRandomReverser device = new PokemonRandomReverser(new ArrayList<>());
		
		
		//device.consumeNextIntCall(10);
		device.addNextIntCall(440, 395);
		device.consumeNextIntCall(5);
		device.addNextIntCall(440, 167);
		device.consumeNextIntCall(3);
		device.addNextIntCall(440, 252);
		device.consumeNextIntCall(2);
		device.addNextIntCall(440, 189);
		device.consumeNextIntCall(0);
		device.addNextIntCall(440, 60);
		device.consumeNextIntCall(4);
		device.addNextIntCall(440, 373);
		device.consumeNextIntCall(4);
		device.addNextIntCall(440, 139);
		device.consumeNextIntCall(4);
		device.addNextIntCall(440, 419);
		device.consumeNextIntCall(0);
		device.addNextIntCall(440, 130);
		device.consumeNextIntCall(3);
		device.addNextIntCall(440, 360);
		
		
		device.setVerbose(true);
		long start = System.nanoTime();
        AtomicInteger count = new AtomicInteger(0);
        device.findAllValidSeeds().forEach(s -> {
            count.incrementAndGet();
            String string = s + "";
            //Random r = new Random(s ^ 0x5deece66dL);
            for (int i =0; i < 5; i++) {
                s = PokemonRandomReverser.PSRNG.nextSeed(s);
                string += " " + s;
            }
            System.out.println(string);
        });
        if (count.get() == 1)
            System.out.println("Found " + count + " seed.");
        else System.out.println("Found " + count + " seeds.");

        long end = System.nanoTime();

        System.out.printf("elapsed: %.2fs%n", (end - start) * 1e-9);
		
		
		
	}
	
    public static void java_reverser_test() {
        LCG lcg = new LCG(7567025607324980273L, 5279421, 0);
        RandomReverser device = new RandomReverser(lcg, new ArrayList<>());
        final int k = 5;
        for (int i =0; i < k; i++)
            device.addMeasuredSeed(0, 20000000000L);

        device.setVerbose(true);
        long start = System.nanoTime();
        AtomicInteger count = new AtomicInteger(0);
        device.findAllValidSeeds().forEach(s -> {
            count.incrementAndGet();
            String string = s + "";
            //Random r = new Random(s ^ 0x5deece66dL);
            for (int i =0; i < k; i++) {
                s = lcg.nextSeed(s);
                string += " " + s;
            }
            System.out.println(string);
        });
        if (count.get() == 1)
            System.out.println("Found " + count + " seed.");
        else System.out.println("Found " + count + " seeds.");

        long end = System.nanoTime();

        System.out.printf("elapsed: %.2fs%n", (end - start) * 1e-9);
    }
}
