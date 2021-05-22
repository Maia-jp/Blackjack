package blackjack.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import blackjack.controller.CodigosObservadorView;
import blackjack.model.Observado;

public class TelaIncial extends JFrame implements Observado{
	private static List<String> jogadores = new ArrayList<>();
	
	private JTextField jogador1Nome;
	private JTextField jogador2Nome;
	private JTextField jogador3Nome;
	private JTextField jogador4Nome;
	
	private JLabel lblJogadoresSelecionados;
	
	public JButton btnComecarPartida;
	
	public TelaIncial() {
		initialize();
	}
	
	private void initialize() {
		this.setBounds(100, 100, 600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel();
		lblTitulo.setBounds(87, 11, 410, 57);
		this.getContentPane().add(lblTitulo);
		
		//Imagem no titulo
		ImageIcon tituloLogo = new ImageIcon("Resource/logo.png");
		// In init() method write this code
		lblTitulo.setIcon(tituloLogo);
		
		JPanel lblj2 = new JPanel();
		lblj2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblj2.setBounds(10, 79, 564, 112);
		this.getContentPane().add(lblj2);
		lblj2.setLayout(null);
		
		JLabel lblJ1 = new JLabel("Jogador 1");
		lblJ1.setBounds(10, 11, 85, 14);
		lblj2.add(lblJ1);
		
		JLabel lblJ2 = new JLabel("Jogador 2");
		lblJ2.setBounds(10, 36, 85, 14);
		lblj2.add(lblJ2);
		
		JLabel lblJ3 = new JLabel("Jogador 3");
		lblJ3.setBounds(10, 61, 85, 14);
		lblj2.add(lblJ3);
		
		JLabel lblJ4 = new JLabel("Jogador 4");
		lblJ4.setBounds(10, 90, 85, 14);
		lblj2.add(lblJ4);
		
		jogador1Nome = new JTextField();
		jogador1Nome.setToolTipText("Deixe em branco para nao jogar");
		jogador1Nome.setBounds(105, 8, 159, 20);
		lblj2.add(jogador1Nome);
		jogador1Nome.setColumns(10);
		
		jogador2Nome = new JTextField();
		jogador2Nome.setToolTipText("Deixe em branco para nao jogar");
		jogador2Nome.setColumns(10);
		jogador2Nome.setBounds(105, 33, 159, 20);
		lblj2.add(jogador2Nome);
		
		jogador3Nome = new JTextField();
		jogador3Nome.setToolTipText("Deixe em branco para nao jogar");
		jogador3Nome.setColumns(10);
		jogador3Nome.setBounds(105, 58, 159, 20);
		lblj2.add(jogador3Nome);
		
		jogador4Nome = new JTextField();
		jogador4Nome.setToolTipText("Deixe em branco para nao jogar");
		jogador4Nome.setColumns(10);
		jogador4Nome.setBounds(105, 87, 159, 20);
		lblj2.add(jogador4Nome);
		
		this.btnComecarPartida = new JButton("Comecar partida");
		btnComecarPartida.addActionListener(btnPartidaAction);
		this.btnComecarPartida.setBounds(351, 84, 150, 23);
		lblj2.add(this.btnComecarPartida);
		
		
		lblJogadoresSelecionados = new JLabel("x jogadores selecionados");
		lblJogadoresSelecionados.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogadoresSelecionados.setVerticalAlignment(SwingConstants.TOP);
		lblJogadoresSelecionados.setBounds(346, 9, 160, 64);
		lblj2.add(lblJogadoresSelecionados);
		
		JButton btnCarregar = new JButton("Carregar Partida");
		btnCarregar.setBounds(10, 227, 135, 23);
		this.getContentPane().add(btnCarregar);
		
		
		//Adiciona listners
		jogador1Nome.getDocument().addDocumentListener(dl);
		jogador2Nome.getDocument().addDocumentListener(dl);
		jogador3Nome.getDocument().addDocumentListener(dl);
		jogador4Nome.getDocument().addDocumentListener(dl);
		
		
	
	}
	
	
	//Metodos listner
	DocumentListener dl = new DocumentListener() {

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateFieldState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateFieldState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateFieldState();
        }

        protected void updateFieldState() {
            int numeroJogadores =0;
            
            if(jogador1Nome.getText().length()>0)
            	numeroJogadores++;
            if(jogador2Nome.getText().length()>0)
            	numeroJogadores++;
            if(jogador3Nome.getText().length()>0)
            	numeroJogadores++;
            if(jogador4Nome.getText().length()>0)
            	numeroJogadores++;
        	
            
            
            
            lblJogadoresSelecionados.setText(numeroJogadores+" jogadores selecionadados");
        }
    };
    
    public ActionListener btnPartidaAction = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
				btnComecarCallback();
			} catch (Exception e1) {
				System.out.println("Erro[btnPartidaAction] ao chamar btnComecarCallback()"+e1);
			}
		}
    };
  
    
    //Callbacks
    private void btnComecarCallback() throws Exception {
    	if(jogador1Nome.getText().length()>0)
        	jogadores.add(jogador1Nome.getText());
        if(jogador2Nome.getText().length()>0)
        	jogadores.add(jogador2Nome.getText());
        if(jogador3Nome.getText().length()>0)
        	jogadores.add(jogador3Nome.getText());
        if(jogador4Nome.getText().length()>0)
        	jogadores.add(jogador4Nome.getText());
        
        
        if(jogadores.size() < 1) {
        	throw new Exception("Lista de jogadores nao pode ser vaiza");
        }else {
        	this.setVisible(false);
        }
        System.out.print("Passa 1; ");
        notificar(this.getJogadores(),CodigosObservadorView.BOTAO_COMECAR_TELA_INICIAL.valor);

    }
    
    List<String> getJogadores() throws Exception {
    	if(jogadores.isEmpty())
    		throw new Exception("Nao existem jogadores");
    	return jogadores;
    }

    //
	// OBSERVADO pela Controller
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
