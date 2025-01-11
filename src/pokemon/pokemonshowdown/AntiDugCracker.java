package pokemonshowdown;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class AntiDugCracker extends JFrame {
	
	public static final int GEN = 3;

	private JPanel contentPane;
	private JTextField healthTextField;
	private JTextField defenseTextField;
	private JTextField attackTextField;
	
	private final int ROWS = 20;
	
	private JTextPane[] turn_panes = new JTextPane[ROWS];
	private JTextField[] damage_fields = new JTextField[ROWS];
	private JComboBox[] attack_boxes = new JComboBox[ROWS];
	private JCheckBox[] crit_boxes = new JCheckBox[ROWS];
	private JCheckBox[] rock_slide_miss_boxes = new JCheckBox[ROWS];
	private JTextField level_text_field;
	
	private JTextPane attack_result_pane;
	private JTextPane rng_result_pane;
	private JCheckBox choice_band_box;
	private JCheckBox burned_box;
	private JSpinner attack_boost_spinner;
	private JSpinner defense_boost_spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AntiDugCracker frame = new AntiDugCracker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AntiDugCracker() {
		super("Porygon2's Anti-Dug RNG Cracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		healthTextField = new JTextField();
		healthTextField.setText("374");
		healthTextField.setBounds(80, 11, 47, 20);
		contentPane.add(healthTextField);
		healthTextField.setColumns(10);
		
		JTextPane txtpnPHealth = new JTextPane();
		txtpnPHealth.setText("P2 MaxHP:");
		txtpnPHealth.setEditable(false);
		txtpnPHealth.setBounds(10, 11, 76, 20);
		contentPane.add(txtpnPHealth);
		
		JTextPane txtpnPDefense = new JTextPane();
		txtpnPDefense.setEditable(false);
		txtpnPDefense.setText("P2 Def:");
		txtpnPDefense.setBounds(10, 32, 61, 20);
		contentPane.add(txtpnPDefense);
		
		defenseTextField = new JTextField();
		defenseTextField.setText("292");
		defenseTextField.setBounds(80, 32, 47, 20);
		contentPane.add(defenseTextField);
		defenseTextField.setColumns(10);
		
		JTextPane txtpnDugAttack = new JTextPane();
		txtpnDugAttack.setEditable(false);
		txtpnDugAttack.setText("Dug Attack:");
		txtpnDugAttack.setBounds(171, 11, 76, 20);
		contentPane.add(txtpnDugAttack);
		
		attackTextField = new JTextField();
		attackTextField.setText("258");
		attackTextField.setBounds(247, 11, 47, 20);
		contentPane.add(attackTextField);
		attackTextField.setColumns(10);
		
		
		
		choice_band_box = new JCheckBox("Choice Band");
		choice_band_box.setSelected(true);
		choice_band_box.setBounds(171, 32, 118, 23);
		contentPane.add(choice_band_box);
		
		JButton btnNewButton = new JButton("Guess Attack");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					guessAttack();
				}catch(NumberFormatException NFE) {
					NFE.printStackTrace();
					attack_result_pane.setText("Cant read inputs");
				}
			}
		});
		btnNewButton.setBounds(424, 32, 124, 23);
		contentPane.add(btnNewButton);
		
		attack_result_pane = new JTextPane();
		attack_result_pane.setEditable(false);
		attack_result_pane.setBounds(380, 66, 196, 20);
		contentPane.add(attack_result_pane);
		
		JButton btnNewButton_1 = new JButton("Crack RNG");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crackRNG();
				}catch(NumberFormatException NFE) {
					NFE.printStackTrace();
					attack_result_pane.setText("Cant read inputs");
				}
			}
		});
		btnNewButton_1.setBounds(424, 140, 124, 23);
		contentPane.add(btnNewButton_1);
		
		rng_result_pane = new JTextPane();
		rng_result_pane.setEditable(false);
		rng_result_pane.setBounds(380, 172, 196, 20);
		contentPane.add(rng_result_pane);
		
		burned_box = new JCheckBox("Burned");
		burned_box.setBounds(291, 31, 99, 23);
		contentPane.add(burned_box);
		
		attack_boost_spinner = new JSpinner();
		attack_boost_spinner.setModel(new SpinnerNumberModel(0, -6, 6, 1));
		attack_boost_spinner.setBounds(259, 55, 30, 20);
		contentPane.add(attack_boost_spinner);
		
		JTextPane txtpnAtkBoost = new JTextPane();
		txtpnAtkBoost.setText("Atk Boost");
		txtpnAtkBoost.setEditable(false);
		txtpnAtkBoost.setBounds(176, 55, 76, 20);
		contentPane.add(txtpnAtkBoost);
		
		JTextPane txtpnDefBoost = new JTextPane();
		txtpnDefBoost.setText("Def Boost");
		txtpnDefBoost.setBounds(10, 55, 61, 20);
		contentPane.add(txtpnDefBoost);
		
		defense_boost_spinner = new JSpinner();
		defense_boost_spinner.setModel(new SpinnerNumberModel(0, -6, 6, 1));
		defense_boost_spinner.setBounds(80, 55, 30, 20);
		contentPane.add(defense_boost_spinner);
		
		JTextPane txtpnLevel = new JTextPane();
		txtpnLevel.setEditable(false);
		txtpnLevel.setText("Level:");
		txtpnLevel.setBounds(291, 11, 47, 20);
		contentPane.add(txtpnLevel);
		
		level_text_field = new JTextField();
		level_text_field.setText("100");
		level_text_field.setBounds(339, 11, 40, 20);
		contentPane.add(level_text_field);
		level_text_field.setColumns(10);
		
		
		int offset = 20;
		int total_offset=30;
		for(int i=0; i<ROWS; i++) {
			JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Crit");
			chckbxNewCheckBox_1.setBounds(187, total_offset+65+offset*i, 52, 23);
			crit_boxes[i] = chckbxNewCheckBox_1;
			contentPane.add(chckbxNewCheckBox_1);
			
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setText((i+1)+":");
			textPane.setBounds(10, total_offset+66 + offset*i, 21, 20);
			turn_panes[i] = textPane;
			contentPane.add(textPane);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Earthquake", "Struggle", "Aerial Ace", "Rock Slide", "HP Bug", "HP Fight"}));
			comboBox.setBounds(80, total_offset+65+offset*i, 99, 22);
			attack_boxes[i] = comboBox;
			contentPane.add(comboBox);
			
			JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Rock Slide Miss");
			chckbxNewCheckBox_2.setBounds(241, total_offset+65+offset*i, 129, 23);
			rock_slide_miss_boxes[i] = chckbxNewCheckBox_2;
			contentPane.add(chckbxNewCheckBox_2);
			
			JTextField textField_3 = new JTextField();
			textField_3.setBounds(30, total_offset+66+offset*i, 47, 20);
			contentPane.add(textField_3);
			damage_fields[i] = textField_3;
			textField_3.setColumns(10);
		}
	}
	
	
	public static final int minLvl100DugAttack = 148;
	public static final int maxDugAttack = 284;
	
	
	
	public int getLevel() {
		return Integer.parseInt(level_text_field.getText());
	}
	public int getHealth() {
		return Integer.parseInt(healthTextField.getText());
	}
	public int getDefense() {
		return Integer.parseInt(defenseTextField.getText());
	}
	public int getAttack() {
		return Integer.parseInt(attackTextField.getText());
	}
	
	public int getDefenseBoost() {
		return (int)defense_boost_spinner.getValue();
	}
	public int getAttackBoost() {
		return (int)attack_boost_spinner.getValue();
	}
	
	public boolean choice_banded() {
		return choice_band_box.isSelected();
	}
	public boolean burned() {
		return burned_box.isSelected();
	}
	
	
	public static boolean stringValid(String s) {
		return !(s == null || s.equals(""));
	}
	
	
	public ArrayList<Float> readDamagePercentages(){
		ArrayList<Float> result = new ArrayList<Float>();
		for(int i=0;i < ROWS; i++) {
			String damage = damage_fields[i].getText();
			if(rock_slide_miss_boxes[i].isSelected()) {
				result.add((float)0);
			}else if(stringValid(damage)) {
				result.add(Float.parseFloat(damage_fields[i].getText()));
			}else {
				break;
			}
		}
		return result;
	}
	
	public ArrayList<Boolean> readCrits(){
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		for(int i=0;i<ROWS;i++) {
			result.add(crit_boxes[i].isSelected());
			
		}
		return result;
	}
	
	public ArrayList<Boolean> readRockSlideMisses(){
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		for(int i=0;i<ROWS;i++) {
			result.add(rock_slide_miss_boxes[i].isSelected());
			
		}
		return result;
	}
	
	public ArrayList<Move> readMoves(){
		ArrayList<Move> result = new ArrayList<Move>();
		for(int i=0;i<ROWS;i++) {
			result.add(Move.getMove((String)attack_boxes[i].getSelectedItem()));
		}
		return result;
	}
	

	public static float minimum(ArrayList<Float> array) {
		float res = Float.POSITIVE_INFINITY;
		for(Float candidate : array) {
			if(candidate < res) {
				res = candidate;
			}
		}
		return res;
	}
	public static float maximum(ArrayList<Float> array) {
		float res = Float.NEGATIVE_INFINITY;
		for(Float candidate : array) {
			if(candidate > res) {
				res = candidate;
			}
		}
		return res;
	}
	
	public static final float epsilon = 0.09f;

	public static boolean approxEqual(float x, float y) {
		return Math.abs(x-y) < epsilon;
	}
	

	public static int minimalIndexOf(float x, float[] array) {
		for(int i=0;i<array.length;i++) {
			if(approxEqual(x,array[i])) {
				return i;
			}
		}
		return -1;
	}
	public static int maximalIndexOf(float x, float[] array) {
		for(int i=array.length-1;i>=0;i--) {
			if(approxEqual(x,array[i])) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	public void guessAttack() {
		System.out.println("Starting attack guess");
		int level = getLevel();
		int minAttack = level == 100 ? minLvl100DugAttack : 5;
		
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		ArrayList<Float> percentages = readDamagePercentages();
		ArrayList<Move> moves = readMoves();
		ArrayList<Boolean> crits = readCrits();
		ArrayList<Boolean> rs_misses = readRockSlideMisses();
		
		float minimumPercentage = minimum(percentages);
		float maximumPercentage = maximum(percentages);
		
		
		int length = percentages.size();
		
		int health = getHealth();
		
		for(int i= minAttack; i <= maxDugAttack; i++) {
			System.out.println("Validating " +i );
			if(validateAttackStat(i, percentages,moves,crits,rs_misses,minimumPercentage,maximumPercentage,length,health)) {
				results.add(i);
				System.out.println(i +" is valid");
			}
		}
		String resultString = "";
		for(Integer i : results) {
			resultString = resultString + ", " + i;
		}
		attack_result_pane.setText(resultString);
	}
	
	
	
	
	//Returns true if the attackStat is possible given the damage percentages.
	public boolean validateAttackStat(int attackStat, ArrayList<Float> percentages, ArrayList<Move> moves, ArrayList<Boolean> crits, ArrayList<Boolean> rs_misses, float minimumPercentage, float maximumPercentage, int length, int health) {
		for(int i = 0; i < length; i++) {
			if(rs_misses.get(i)) {
				continue;
			}
			int[] advDamage = moves.get(i).advDamage(attackStat, getDefense(), crits.get(i), choice_banded(), burned(), getLevel(), getAttackBoost(), getDefenseBoost());
			
			if(Move.toPercentage(advDamage[0],health) > maximumPercentage + epsilon || 
			   Move.toPercentage(advDamage[advDamage.length-1], health) < minimumPercentage - epsilon	) {
				System.out.println("Completely out of range.");
				return false;
			}
			boolean damagePossible = false;
			for(int j=0; j < advDamage.length;j++) {
				float diff = percentages.get(i) - Move.toPercentage(advDamage[j],health);
				
				if(Math.abs(diff) < epsilon) {
					damagePossible = true;
					System.out.println("equal");
					break;
				}else {
					System.out.println("unequal");
				}
				
				if(diff < 0) {
					System.out.println("no need to check further");
					break;
				}
				
			}
			if(!damagePossible) {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	public void crackRNG() {

		System.out.println("Starting attack guess");
		int level = getLevel();
		int attack = getAttack();
		
		ArrayList<Float> percentages = readDamagePercentages();
		ArrayList<Move> moves = readMoves();
		ArrayList<Boolean> crits = readCrits();
		ArrayList<Boolean> rs_misses = readRockSlideMisses();
		
		boolean choice_banded = choice_banded();
		boolean burned = burned();
		int attackBoost = getAttackBoost();
		int defenseBoost = getDefenseBoost();
		int health = getHealth();
		int defense = getDefense();
		
		
		int length = percentages.size();
		float[][] damage_rolls = new float[length][16];
		int[] minIndices= new int[length], maxIndices = new int[length];
		
		for(int i=0;i<length;i++) {
			int[] intDamage = moves.get(i).advDamage(attack, defense, crits.get(i), choice_banded, burned, level, attackBoost, defenseBoost);
			for(int j=0;j<16;j++) {
				damage_rolls[i][j] = Move.toPercentage(intDamage[j], health);
			}
			minIndices[i] = minimalIndexOf(percentages.get(i),damage_rolls[i]);
			maxIndices[i] = maximalIndexOf(percentages.get(i),damage_rolls[i]);
			
			
			//Higher random calls correspond to lower damage rolls.
			int minIndex = minIndices[i];
			minIndices[i] = 15 - maxIndices[i];
			maxIndices[i] = 15 - minIndex;			
		}

		PokemonRandomReverser device = new PokemonRandomReverser(new ArrayList<>());
		ArrayList<String> comments = new ArrayList<String>();
		
		
		for(int i=0;i<length;i++) {
			if(!rs_misses.get(i)) {
				if(!crits.get(i)) {
					device.consumeNextIntCall(1);
				}else {
					device.addNextIntCall(20, 0);
				}
				comments.add("Crit Check");
				
				device.addNextIntCall(16, minIndices[i], maxIndices[i]+1);
				comments.add("Damage Roll "+(i+1));
			}
			
			device.consumeNextIntCall(2);
			comments.add("Quick Claw");
			comments.add("Accuracy Check");
		}
		
		final int maxResults = 5;
		AtomicLong[] results = new AtomicLong[maxResults];
		
		device.setVerbose(true);
		long start = System.nanoTime();
        AtomicInteger count = new AtomicInteger(0);
        device.findAllValidSeeds().forEach(s -> {
            int value = count.incrementAndGet();
            if(value <= maxResults) {
                results[value-1] = new AtomicLong(s);
            }
        });
        
        if(count.intValue() == 1) {
        	rng_result_pane.setText("Result: "+  Long.toHexString(results[0].longValue()));
        }else {
            rng_result_pane.setText("Result: "+ count + " possible seeds.");
        }
        
        if(count.intValue() < maxResults) {
            for(int i=0; i < count.intValue() ; i++) {
                CSVGenerator.generateCSVWithComments(results[i].longValue(), comments , GEN, i);
            }
        }
        
		
	}
}
