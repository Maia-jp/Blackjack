/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
package blackjack.view;

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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import blackjack.controller.CodigosObservadorView;
import blackjack.model.Observado;

class TelaCarregamento extends JFrame implements Observado{
	
	private String diretorio;
	
	JButton btnSelecionar;
	JButton btnCarregar;
	JLabel lblCaminho;
	
	
	
	/* 
	 * Interface Grafica
	 */
	private void initialize() {
		this.setResizable(false);
		this.setTitle("Carregar");
		this.setBounds(100, 100, 486, 192);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 450, 131);
		this.getContentPane().add(panel);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setLayout(null);
		
		this.btnSelecionar = new JButton("Selecionar Arquivo");
		btnSelecionar.setBounds(10, 45, 430, 23);
		panel.add(btnSelecionar);
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirSelecaoArquivo();
			}
		});
		
		this.btnCarregar = new JButton("Carregar");
		btnCarregar.setEnabled(false);
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregar();
			}
		});
		btnCarregar.setBounds(10, 97, 430, 23);
		panel.add(btnCarregar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 86, 430, 18);
		panel.add(separator);
		
		this.lblCaminho = new JLabel("Nenhum Arquivo selecionado");
		lblCaminho.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaminho.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCaminho.setBounds(10, 20, 430, 14);
		panel.add(lblCaminho);
	}
	
	
	
	/* 
	 * Callbacks
	 */
	private void abrirSelecaoArquivo() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int option = fileChooser.showOpenDialog(this);
		if(option == JFileChooser.APPROVE_OPTION){
			File file = fileChooser.getSelectedFile();
			diretorio = file.getAbsolutePath();
			lblCaminho.setText("Arquivo: "+diretorio);
			btnCarregar.setEnabled(true);
		}else{
			return;
    	}
	}
	
	private void carregar() {
		notificar(diretorio,
				CodigosObservadorView.BOTAO_CARREGAR_TELA_OPCOES.valor);
	}
	
	
	
	
	/* 
	 * Modelo Singleton
	 */
	private static TelaCarregamento instanciaUnica;
	
	private TelaCarregamento() {
		initialize();
	}
	
	public static synchronized TelaCarregamento iniciar(){
		if(instanciaUnica == null)
			instanciaUnica = new TelaCarregamento();
		return instanciaUnica;
	}
	
	
	/* 
	 * Metodos de Observacao
	 */
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
