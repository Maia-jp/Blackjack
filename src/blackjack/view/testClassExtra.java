package blackjack.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JSlider;
import javax.swing.JRadioButton;

public class testClassExtra {

	private JFrame frmOpoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testClassExtra window = new testClassExtra();
					window.frmOpoes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testClassExtra() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOpoes = new JFrame();
		frmOpoes.addKeyListener(new CustomKeyListener());
		frmOpoes.setTitle("Opçoes");
		frmOpoes.setBounds(100, 100, 450, 300);
		frmOpoes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOpoes.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 414, 239);
		frmOpoes.getContentPane().add(tabbedPane);
		
		JPanel tabCarteira = new JPanel();
		tabbedPane.addTab("Carteira", null, tabCarteira, null);
		tabCarteira.setLayout(null);
		
		JSpinner spinnerCarteiraJogador = new JSpinner();
		spinnerCarteiraJogador.setModel(new SpinnerListModel(new String[] {"Jogador 1", "Jogador 2", "Jogador 3", "Jogador 4"}));
		spinnerCarteiraJogador.setBounds(10, 11, 389, 20);
		tabCarteira.add(spinnerCarteiraJogador);
		
		JButton btnGerarCarteira = new JButton("Gerar Carteira Dinamica");
		btnGerarCarteira.setBounds(10, 42, 190, 23);
		tabCarteira.add(btnGerarCarteira);
		
		JTextPane txtpnNone = new JTextPane();
		txtpnNone.setToolTipText("Digite a carteira dinamica ou copie a carteira dada");
		txtpnNone.setText("Digite sua carteira Dicamica");
		txtpnNone.setBounds(10, 76, 389, 124);
		tabCarteira.add(txtpnNone);
		
		JButton btnCarregarCarteira = new JButton("Carregar Carteira Dinamica");
		btnCarregarCarteira.setBounds(209, 42, 190, 23);
		tabCarteira.add(btnCarregarCarteira);
		
		JPanel tabRadio = new JPanel();
		tabbedPane.addTab("Radio", null, tabRadio, null);
		tabRadio.setLayout(null);
		
		JRadioButton rdbtnParar = new JRadioButton("Parar");
		rdbtnParar.setSelected(true);
		rdbtnParar.setBounds(6, 7, 109, 23);
		tabRadio.add(rdbtnParar);
		
		JRadioButton rdbtnJazz = new JRadioButton("Jazz");
		rdbtnJazz.setBounds(6, 33, 109, 23);
		tabRadio.add(rdbtnJazz);
		
		JRadioButton rdbtnBlues = new JRadioButton("Blues");
		rdbtnBlues.setBounds(6, 59, 109, 23);
		tabRadio.add(rdbtnBlues);
		
		JRadioButton rdbtnClassica = new JRadioButton("Classica");
		rdbtnClassica.setBounds(6, 85, 109, 23);
		tabRadio.add(rdbtnClassica);
		
		JPanel salvarTAB = new JPanel();
		tabbedPane.addTab("Salvar", null, salvarTAB, null);
		salvarTAB.setLayout(null);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(10, 103, 389, 23);
		salvarTAB.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Local: SELECIONE");
		lblNewLabel_2.setBounds(10, 78, 389, 14);
		salvarTAB.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Selecione o Local");
		lblNewLabel_3.setBounds(10, 11, 91, 14);
		salvarTAB.add(lblNewLabel_3);
		
		JButton btnSelecionarLocal = new JButton("Selecionar Local");
		btnSelecionarLocal.setBounds(10, 36, 389, 23);
		salvarTAB.add(btnSelecionarLocal);
		
		JPanel creditosTab = new JPanel();
		tabbedPane.addTab("Creditos", null, creditosTab, null);
		creditosTab.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BLACKJACK");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel.setBounds(137, 11, 123, 26);
		creditosTab.add(lblNewLabel);
		
		JLabel lblAlexandreBomfim = new JLabel("Alexandre Bomfim");
		lblAlexandreBomfim.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAlexandreBomfim.setBounds(10, 53, 123, 26);
		creditosTab.add(lblAlexandreBomfim);
		
		JLabel lblJoseLucasTeixeira = new JLabel("Jose Lucas Teixeira");
		lblJoseLucasTeixeira.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblJoseLucasTeixeira.setBounds(143, 53, 123, 26);
		creditosTab.add(lblJoseLucasTeixeira);
		
		JLabel lblAlexandreBomfim_1_1 = new JLabel("Joao Pedro Maia");
		lblAlexandreBomfim_1_1.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblAlexandreBomfim_1_1.setBounds(276, 53, 123, 26);
		creditosTab.add(lblAlexandreBomfim_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Projeto da disciplina Programaçao Orientada e Objetos, PUC-RIO 2021.1");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(27, 179, 372, 21);
		creditosTab.add(lblNewLabel_1);
	}
}

class CustomKeyListener implements KeyListener{
    public void keyTyped(KeyEvent e) {
    	System.out.println(e.getKeyCode());
    }
    public void keyPressed(KeyEvent e) {
       System.out.println(e.getKeyCode());
    }
    public void keyReleased(KeyEvent e) {
    	System.out.println(e.getKeyCode());
    }   
 }
