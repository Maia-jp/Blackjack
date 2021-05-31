package blackjack.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;

import blackjack.model.Observado;

class TelaOpcoes extends JFrame implements Observado{
	private ArrayList<String> Jogadores;

	public TelaOpcoes(ArrayList<String> jogadores) {
		this.Jogadores = jogadores;
		initialize();
	
	}

	/**
	 * Inicializa conteudos da Tela
	 */
	private void initialize() {
		this.setTitle("Opçoes");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 414, 239);
		this.getContentPane().add(tabbedPane);
		
		JPanel tabCarteira = new JPanel();
		tabbedPane.addTab("Carteira", null, tabCarteira, null);
		tabCarteira.setLayout(null);
		
		JSpinner spinnerCarteiraJogador = new JSpinner();
		spinnerCarteiraJogador.setModel(new SpinnerListModel(Jogadores));
		spinnerCarteiraJogador.setBounds(10, 11, 389, 20);
		tabCarteira.add(spinnerCarteiraJogador);
		
		JButton btnGerarCarteira = new JButton("Gerar Carteira Dinamica");
		btnGerarCarteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
	
	
	//
	//Metodos de observado
	//
	public List<Observador> observadores = new ArrayList<>();
	
	@Override
	public void adicionarObservador(Observador o) {
		observadores.add(o);
	}
		
	@Override
	public void notificar(Object obj,int idAction) {
		observadores.forEach((o) -> o.executar(obj,idAction));
	}
}