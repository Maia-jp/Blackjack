/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
package blackjack.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;


import blackjack.controller.CodigosObservadorView;
import blackjack.model.ModelAPI;
import blackjack.model.Observado;

class TelaOpcoes extends JFrame implements Observado{
	
	List<String> Jogadores;
	
	//Botoes e labels globais - Tab salvar/carregar
	JButton btnSelecionarLocal;
	JButton btnSalvar;
	JLabel lblTab0Salvar;
	
	//Botoes e labels globais - Carteira
	JButton btnGerarCarteira;
	JSpinner spinnerCarteiraJogador;
	JTextArea txtCarteira;
	JButton btnCarregarCarteira;
	/**
	 * Inicializa conteudos da Tela
	 */
	private void initialize() {
		this.setTitle("Opcoes");
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 414, 239);
		this.getContentPane().add(tabbedPane);
		
		//Tab salvar/carregar
		
		JPanel salvarTAB = new JPanel();
		tabbedPane.addTab("Salvar", null, salvarTAB, null);
		salvarTAB.setLayout(null);
		
		
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
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notifica a controller para salvar o jogo
				salvar();
			}
		});
		salvarTAB.add(btnSalvar);
		
		
		//TAB carteira
		
		JPanel tabCarteira = new JPanel();
		tabbedPane.addTab("Carteira", null, tabCarteira, null);
		tabCarteira.setLayout(null);
		
		this.spinnerCarteiraJogador = new JSpinner();
		spinnerCarteiraJogador.setModel(new SpinnerListModel(Jogadores));
		spinnerCarteiraJogador.setBounds(10, 11, 389, 20);
		tabCarteira.add(spinnerCarteiraJogador);
		
		this.btnGerarCarteira = new JButton("Gerar Carteira Dinamica");
		btnGerarCarteira.setBounds(10, 42, 190, 23);
		tabCarteira.add(btnGerarCarteira);
		btnGerarCarteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerarCarteiraDinamica();
			}
		});
		
		txtCarteira = new JTextArea();
		txtCarteira.setToolTipText("Digite a carteira dinamica ou copie a carteira dada");
		txtCarteira.setText("Digite sua carteira Dicamica");
		txtCarteira.setBounds(10, 93, 389, 92);
		txtCarteira.setWrapStyleWord(true);
		txtCarteira.setLineWrap(true);
		tabCarteira.add(txtCarteira);
		
		this.btnCarregarCarteira = new JButton("Carregar Carteira Dinamica");
		btnCarregarCarteira.setBounds(209, 42, 190, 23);
		tabCarteira.add(btnCarregarCarteira);
		btnCarregarCarteira.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notifica a controller que uma carteira precisa ser carregada (carteira, codigo)
				carregarCarteiraDinamica("teste");
			}
		});
		
		
		//TAB creditos
		
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
		
		JLabel lblNewLabel_1 = new JLabel("Projeto da disciplina Programacao Orientada e Objetos, PUC-RIO 2021.1");
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
	private Boolean podeSalvar;
	
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
	
	
	private void salvar() {
		notificar(diretorioSalvar,
				CodigosObservadorView.BOTAO_SALVAR_TELA_OPCOES.valor); 
	}
	
	
	Boolean mudarEstadoSalvar() {
		btnSalvar.setEnabled(!btnSalvar.isEnabled());
		return btnSalvar.isEnabled();
	}
	
	//
	//Metodos Carteira Dinamica
	//
	public void exibirCarteira(String t) {
		txtCarteira.setText(t);
	}
	
	public String getCarteiraDinamica(String t) {
		return txtCarteira.getName();
	}
	
	public void gerarCarteiraDinamica() {
		String nome = (String) spinnerCarteiraJogador.getValue();
		System.out.println("Notificou com o valor:" + nome);
		notificar(nome,
				CodigosObservadorView.BOTAO_GERARCARTEIRA_TELA_OPCOES.valor); 

	}
	
	public void carregarCarteiraDinamica(String c) {

		String[] par = {txtCarteira.getText(),(String) spinnerCarteiraJogador.getValue()};
		notificar(par,CodigosObservadorView.BOTAO_GERARCARREGAR_TELA_OPCOES.valor);

		
	}
	
	void gerarCarteiraErro() {
		JOptionPane.showMessageDialog(new JFrame(), "Nao foi possivel carregar a carteira desse jogador", "Erro carregar carteira",
		        JOptionPane.ERROR_MESSAGE);
		
	}
	
	void gerarAlertaSalvo(String dir) {
		String msg = "JOGO SALVO na pasta informada com o nome: "+dir+".txt";
		
		JOptionPane.showMessageDialog(new JFrame(), msg, "Salvar",
		        JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	void gerarAlertaCarregar() {
		JOptionPane.showMessageDialog(new JFrame(), "O jogo foi carregado com sucesso", "O jogo foi carregado com sucesso",
		        JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	
	
	
	
	//
	//AUX
	//
	String removeSufix(String s) {
		int i = s.indexOf("_");
		if(i < 0)
			return s;
		s = s.substring(0, s.length()-2);
		return s;
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
	
	private TelaOpcoes(List<String> jogadores) {
		this.Jogadores = jogadores;
		initialize();
		
	}
	
	public static synchronized TelaOpcoes iniciar(List<String> jogadores) {
		if(instanciaUnica == null)
			instanciaUnica = new TelaOpcoes(jogadores);
		return instanciaUnica;
	}
}