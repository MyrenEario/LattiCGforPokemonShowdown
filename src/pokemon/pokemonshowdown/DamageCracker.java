package pokemonshowdown;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DamageCracker extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JSpinner spinner;
	private JTextPane txtpnResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DamageCracker frame = new DamageCracker();
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
	public DamageCracker() {
		super("LattiCG Damage Roll Cracker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnActual = new JTextPane();
		txtpnActual.setEditable(false);
		txtpnActual.setText("Actual");
		txtpnActual.setBounds(10, 22, 49, 20);
		contentPane.add(txtpnActual);
		
		JTextPane txtpnPossible = new JTextPane();
		txtpnPossible.setEditable(false);
		txtpnPossible.setText("Possible");
		txtpnPossible.setBounds(95, 22, 68, 20);
		contentPane.add(txtpnPossible);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Actual", "Possible", "RNG Calls"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(250);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(20);
		table_1.setBounds(10, 44, 403, 400);
		contentPane.add(table_1);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		btnNewButton.setBounds(10, 454, 89, 23);
		contentPane.add(btnNewButton);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		spinner.setBounds(255, 11, 30, 20);
		contentPane.add(spinner);
		
		JTextPane txtpnGen = new JTextPane();
		txtpnGen.setEditable(false);
		txtpnGen.setText("Gen");
		txtpnGen.setBounds(229, 11, 28, 20);
		contentPane.add(txtpnGen);
		
		txtpnResult = new JTextPane();
		txtpnResult.setEditable(false);
		txtpnResult.setText("Result:");
		txtpnResult.setBounds(128, 455, 230, 20);
		contentPane.add(txtpnResult);
		
		JTextPane txtpnRngCalls = new JTextPane();
		txtpnRngCalls.setText("RNG Calls");
		txtpnRngCalls.setEditable(false);
		txtpnRngCalls.setBounds(345, 22, 68, 20);
		contentPane.add(txtpnRngCalls);
	}
	
	
	public void run() {
		int gen = (int)spinner.getValue();
		
		
		txtpnResult.setText("Calculating");
		
		
		//In Gen1 there are 39 possible damage rolls, in other Gens only 16.
		//In Gen1 higher damage rolls correspond to higher damage.
		//In other gens higher damage rolls correspond to lower damage.
		int possibleRolls = gen == 1 ? 39 : 16;
		
		int columnLength = 25;
		
		
		
		int[] actualOutcomes = new int[columnLength];
		int[][] possibleOutcomes = new int[columnLength][possibleRolls];
		int[] rngcalls = new int[columnLength];
		TableModel model = table_1.getModel();
		
		for(int i = 0 ; i < columnLength;i++) {
			String actualOutcome = (String)model.getValueAt(i, 0);
			if(actualOutcome != null && !actualOutcome.equals("")) {
				try {
					actualOutcomes[i] = Integer.parseInt(actualOutcome);
				}catch(NumberFormatException e) {
					txtpnResult.setText("Cant read "+ actualOutcome +" at line "+i);
					return;
				}
			}else {
				actualOutcomes[i] = -1;
			}
			
			String possibleOutcomeString = (String)model.getValueAt(i, 1);
			
			if(possibleOutcomeString != null && !possibleOutcomeString.equals("")) {

				possibleOutcomeString = possibleOutcomeString.replaceAll("[()]", "");
				possibleOutcomeString = possibleOutcomeString.replaceAll(" ", "");
				
				String[] possibleOutcome = possibleOutcomeString.split(",");
				if(possibleOutcome.length == possibleRolls){
					for(int j=0;j < possibleRolls;j++ ) {
						try {
							possibleOutcomes[i][j] = Integer.parseInt(possibleOutcome[j]);
						}catch(NumberFormatException e) {
							txtpnResult.setText("Cant read "+ possibleOutcome[j] + "at line" +i);
							return;
						}
					}
				}else {
					txtpnResult.setText("Wrong possible outcome length "+possibleOutcome.length +" at line "+i);
					return;
				}
			}else {
				possibleOutcomes[i] = null;
			}
			
			
			String rngcall = (String)model.getValueAt(i, 2);
			if(rngcall != null && !rngcall.equals("")) {
				try {
					rngcalls[i] = Integer.parseInt(rngcall);
				}catch(NumberFormatException e) {
					txtpnResult.setText("Couldnt read rng call amount "+ (String)model.getValueAt(i, 1));
					return;
				}
			}else {
				rngcalls[i] = -1;
			}	
		}
		
		int[] minRolls = new int[columnLength];
		int[] maxRolls = new int[columnLength];
		
		for(int i=0; i < columnLength;i++ ) {
			if(actualOutcomes[i] == -1) break;
			
			boolean possibleRollFound = false;
			
			for(int j=0;j < possibleRolls;j++ ) {
				if(actualOutcomes[i] == possibleOutcomes[i][j]) {
					if(!possibleRollFound) {
						possibleRollFound = true;
						minRolls[i] = j;
					}
					maxRolls[i] = j;
				}
			}
			if(!possibleRollFound) {
				txtpnResult.setText("Line "+i+" is impossible.");
				return;
			}
			if(gen != 1) {
				int minRoll = minRolls[i];
				minRolls[i] = (possibleRolls - 1) - maxRolls[i];
				maxRolls[i] = (possibleRolls - 1) - minRoll;
			}
		}
		
		
		PokemonRandomReverser device = new PokemonRandomReverser(new ArrayList<>());


		for(int i=0; i < columnLength;i++ ) {
			if(actualOutcomes[i] != -1) {
				System.out.println("Adding constraint: " + minRolls[i] + " to" + maxRolls[i]);
				device.addNextIntCall(possibleRolls , minRolls[i], maxRolls[i]+1);
			}else {
				break;
			}
			if(rngcalls[i] != -1) {
				System.out.println("Adding " +rngcalls[i] + " unmeasured calls");
				device.addUnmeasuredSeeds(rngcalls[i]);
			}else {
				break;
			}
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
            txtpnResult.setText("Result: "+  Long.toHexString(results[0].longValue()));
        }else {
            txtpnResult.setText("Result: "+ count + " possible seeds.");
        }
        
        if(count.intValue() < maxResults) {
            for(int i=0; i < count.intValue() ; i++) {
                CSVGenerator.generateCSV(results[i].longValue(), actualOutcomes, rngcalls, gen, i);
            }
        }
        
    
        
	}
}
