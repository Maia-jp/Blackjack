package blackjack.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;

import blackjack.model.ModelAPI;
import blackjack.model.Observado;

class TelaOpcoes extends JFrame implements Observado{
	
	
	//Botoes e labels globais - Tab salvar/carregar
	JButton btnSelecionarLocal;
	JButton btnCarregar;
	JButton btnSalvar;
	JLabel lblTab0Salvar;
	JLabel  lblTab0Carregar; 

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
		
		//Tab salvar/carregar
		
		JPanel salvarTAB = new JPanel();
		tabbedPane.addTab("Salvar/Carregar", null, salvarTAB, null);
		salvarTAB.setLayout(null);
		
		this.btnCarregar = new JButton("Carregar");
		btnCarregar.setEnabled(false);
		btnCarregar.setBounds(10, 164, 389, 23);
		salvarTAB.add(btnCarregar);
		
		this.lblTab0Carregar = new JLabel("Carregar");
		lblTab0Carregar.setBounds(10, 104, 389, 14);
		salvarTAB.add(lblTab0Carregar);
		
		this.lblTab0Salvar = new JLabel("Salvar");
		lblTab0Salvar.setBounds(10, 11, 389, 14);
		salvarTAB.add(lblTab0Salvar);
		
		this.btnSelecionarLocal = new JButton("Selecionar Local");
		btnSelecionarLocal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirSelecaoDiretorio();
	         }
		});
		btnSelecionarLocal.setBounds(10, 36, 389, 23);
		salvarTAB.add(btnSelecionarLocal);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 99, 389, 19);
		salvarTAB.add(separator);
		
		this.btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(10, 65, 389, 23);
		salvarTAB.add(btnSalvar);
		
		JButton btnSelecionarArquivo = new JButton("Selecionar Arquivo");
		btnSelecionarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirSelecaoArquivo();
			}
		});
		btnSelecionarArquivo.setBounds(10, 130, 389, 23);
		salvarTAB.add(btnSelecionarArquivo);
		
		//TAB carteira
		
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
	//Metodos de salvamento/carregamento
	//
	private String diretorioSalvar;
	private String diretorioCarregar;
	
	//Abre o form de Salvamento
	private void abrirSelecaoDiretorio() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
           File file = fileChooser.getSelectedFile();
           diretorioSalvar = file.getAbsolutePath();
           btnSalvar.setEnabled(true);
           lblTab0Salvar.setText("Salvar em: "+diretorioSalvar);
        }else{
        	return;
        }
     }
	
	private void abrirSelecaoArquivo() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
           File file = fileChooser.getSelectedFile();
           diretorioCarregar = file.getAbsolutePath();
           lblTab0Carregar.setText("Carregar de: "+diretorioCarregar);
           btnCarregar.setEnabled(true);
           
        }else{
        	return;
        }
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
	
	//Modelo Singleton
	private static TelaOpcoes instanciaUnica;
	
	private TelaOpcoes() {
		initialize();
	}
	
	public static synchronized TelaOpcoes iniciar() {
		if(instanciaUnica == null)
			instanciaUnica = new TelaOpcoes();
		return instanciaUnica;
	}
}