package pokemonshowdown;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CSVGenerator {
	public static final int csvlength = 5000;
	
	
	public static void generateCSV(long seed, int[] metromoves, int[] rngcalls, int gen, int suffix) {
		boolean advanceToCurrentTurn = true;
		
		if(advanceToCurrentTurn) {
			for(int i=0; i < metromoves.length;i++) {
				if(metromoves[i] != -1) {
					seed = PokemonRandomReverser.PSRNG.nextSeed(seed);
				}
			}
			
			for(int i=0; i < rngcalls.length;i++) {
				if(rngcalls[i] != -1) {
					for(int j=0;j < rngcalls[i];j++) {
						seed = PokemonRandomReverser.PSRNG.nextSeed(seed);
					}
				}
			}
		}
		
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File("rngoutput"+ suffix +".csv"));
        try (PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile())) {
            writer.println("Seed, Chance, Metromove, Notes");
            String nextLine;
            for (int i=0 ; i < csvlength; i++) {
                nextLine = Long.toHexString(seed) + ", "+PRNG.next(seed) + ", " + PRNG.metroMove(seed, gen);
                //TODO: Create notes for the metromoves
            	writer.println(nextLine);
                seed = PokemonRandomReverser.PSRNG.nextSeed(seed);
            }
        } catch (IOException ex) {
            System.out.println("Failed to save file " + ex.getMessage());
        }
	}
	
	
	
}
