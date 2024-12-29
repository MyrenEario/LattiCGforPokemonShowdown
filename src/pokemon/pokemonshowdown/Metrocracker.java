package pokemonshowdown;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Metrocracker extends JFrame {

	private JPanel contentPane;
	private JTextField txtGen;
	private JSpinner genSpinner;
	private JTable table;
	private JTextPane txtpnMetronomeResults;
	private JTextPane txtpnRngCalls;
	private JTextPane txtpnResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Metrocracker frame = new Metrocracker();
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
	public Metrocracker() {
		super("LattiCG Metrocracker");
		
		System.out.println(Metromovelist.gen3.length);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		genSpinner = new JSpinner();
		genSpinner.setModel(new SpinnerNumberModel(3, 1, 9, 1));
		genSpinner.setBounds(54, 11, 30, 20);
		contentPane.add(genSpinner);
		
		txtGen = new JTextField();
		txtGen.setText("Gen");
		txtGen.setEditable(false);
		txtGen.setBounds(10, 11, 30, 20);
		contentPane.add(txtGen);
		txtGen.setColumns(10);
		
		JButton btnNewButton = new JButton("Run");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		btnNewButton.setBounds(20, 230, 89, 23);
		contentPane.add(btnNewButton);
		
		JTextPane metronomesNeeded = new JTextPane();
		metronomesNeeded.setEditable(false);
		metronomesNeeded.setBackground(UIManager.getColor("Button.background"));
		metronomesNeeded.setText("Gen2, Gen4 dont work yet");
		metronomesNeeded.setBounds(94, 11, 158, 20);
		contentPane.add(metronomesNeeded);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Metromoves", "RNG Calls"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setMinWidth(60);
		table.getColumnModel().getColumn(1).setMaxWidth(60);
		table.setBounds(10, 59, 350, 160);
		contentPane.add(table);
		
		txtpnMetronomeResults = new JTextPane();
		txtpnMetronomeResults.setEditable(false);
		txtpnMetronomeResults.setText("Metronome Results");
		txtpnMetronomeResults.setBounds(20, 40, 124, 20);
		contentPane.add(txtpnMetronomeResults);
		
		txtpnRngCalls = new JTextPane();
		txtpnRngCalls.setEditable(false);
		txtpnRngCalls.setText("RNG Calls");
		txtpnRngCalls.setBounds(299, 40, 61, 20);
		contentPane.add(txtpnRngCalls);
		
		txtpnResult = new JTextPane();
		txtpnResult.setEditable(false);
		txtpnResult.setText("Result:");
		txtpnResult.setBounds(137, 233, 223, 20);
		contentPane.add(txtpnResult);
	}
	
	
	
	public void run() {
		int gen = (int)genSpinner.getValue();
		if(gen == 2 || gen == 4) {
			txtpnResult.setText("Gen2, Gen4 dont work yet");
			return;
		}
		
		
		txtpnResult.setText("Calculating");
		
		
		int[] metromoveids = new int[10];
		int[] rngcalls = new int[10];
		int[] metrolengths = new int[10];
		TableModel model = table.getModel();
		for(int i = 0 ; i < metromoveids.length;i++) {
			String metromove = (String)model.getValueAt(i, 0);
			if(metromove != null && !metromove.equals("")) {
				metromoveids[i] = Metromovelist.getIndex(metromove, gen);
				if(metromoveids[i] == -1) {
					txtpnResult.setText("Couldnt read move "+ metromove);
					return;
				}
			}else {
				metromoveids[i] = -1;
			}
			
			String rngcall = (String)model.getValueAt(i, 1);
			if(rngcall != null && !rngcall.equals("")) {
				try {
					rngcalls[i] = Integer.parseInt((String)model.getValueAt(i, 1));
				}catch(NumberFormatException e) {
					txtpnResult.setText("Couldnt read rng call amount "+ (String)model.getValueAt(i, 1));
					return;
				}
			}else {
				rngcalls[i] = -1;
			}
			
//			if(gen == 2 || gen == 4) {
//				String slength = (String)model.getValueAt(i, 2);
//				try {
//					metrolengths[i] = Integer.parseInt(slength);
//				}catch(NumberFormatException e) {
//					metromoveids[i] = Metromovelist.getMetromoves(gen).length - 3;
//				}
//			}else {
				metrolengths[i] = Metromovelist.getMetromoves(gen).length;
//			}
			
		}
		
		
		PokemonRandomReverser device = new PokemonRandomReverser(new ArrayList<>());
		
		for(int i = 0 ; i < metromoveids.length;i++) {
			if(metromoveids[i] != -1) {
				System.out.println("Adding constraint: " + metromoveids[i]);
				device.addNextIntCall(metrolengths[i] , metromoveids[i]);
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
		
		device.setVerbose(false);
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
                CSVGenerator.generateCSV(results[i].longValue(), metromoveids, rngcalls, gen, i);
            }
        }
        
    
        
	}
}
