package blackjack.view;


import blackjack.controller.CodigosObservador;
import blackjack.model.Observado;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.ImageIcon;

import blackjack.controller.CodigosObservadorView;

public class TelaJogador extends JFrame implements Observado,Observador{
	String nomeJogador;
	Integer indiceJogador;
	HashMap<String,List<String>> maoDosJogadores;
	CarregaImagens cI;
	Integer maoJogador;
	String infoJogador;
	ImageIcon fundo = new ImageIcon(getClass().getResource("/blackjackBKG.png"));
	
	private JLabel labelValor;
	private JLabel labelDinheiro;
	private JButton hit;
	private JButton stand;
	private JButton dobrar;
	private JButton split;
	private JButton surrender;
	private JButton quit;
	
	public TelaJogador(String nomeJogador,CarregaImagens cI, Integer mao,Integer indiceJogador) {
		this.cI = cI;
		this.nomeJogador = nomeJogador;
		this.indiceJogador=indiceJogador;
		this.maoJogador=mao;
		this.infoJogador=Integer.toString(this.indiceJogador);
		this.infoJogador=this.infoJogador+Integer.toString(this.maoJogador);
		initialize();
	}
	
	private void initialize() {
		System.out.println("Jogador incializado:" + nomeJogador);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		this.setBounds(0, 50, 1120, 700);

		//vamo ver
		List<String> lista = new ArrayList<String>();  
		lista.add("C9");
		lista.add("Dj");
		
		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 880, 641);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel1 = new Panel();
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(901, 10, 195, 133);
		this.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		Panel panel2 = new Panel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(901, 518, 195, 133);
		this.getContentPane().add(panel2);
		panel2.setLayout(null);
		
		Label label = new Label(nomeJogador);
		label.setAlignment(Label.CENTER);
		label.setBounds(45, 10, 98, 22);
		panel1.add(label);
		
		Label label_1 = new Label("Aposta total: ");
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(10, 38, 98, 22);
		panel1.add(label_1);
		
		this.labelDinheiro = new JLabel();
		this.labelDinheiro.setAlignmentX(CENTER_ALIGNMENT);
		this.labelDinheiro.setBounds(10, 66, 98, 22);
		panel1.add(this.labelDinheiro);
		
		this.labelValor = new JLabel();
		this.labelValor.setAlignmentX(CENTER_ALIGNMENT);
		this.labelValor.setBounds(45, 10, 98, 22);
		panel2.add(this.labelValor);
		
		this.hit = new JButton("HIT");
		this.hit.setBounds(92,597,130,36);
		this.hit.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.hit.setForeground(Color.DARK_GRAY);
		this.hit.setBackground(Color.LIGHT_GRAY);
		this.hit.setEnabled(false);
		panel.add(this.hit);
		this.hit.addActionListener(btnAcionarHit);
		
		this.stand = new JButton("STAND");
		this.stand.setBounds(234,597,130,36);
		this.stand.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.stand.setForeground(Color.DARK_GRAY);
		this.stand.setBackground(Color.LIGHT_GRAY);
		this.stand.setEnabled(false);
		panel.add(this.stand);
		this.stand.addActionListener(btnAcionarStand);

		
		this.dobrar = new JButton("DOUBLE");
		this.dobrar.setBounds(374,597,130,36);
		this.dobrar.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.dobrar.setForeground(Color.DARK_GRAY);
		this.dobrar.setBackground(Color.LIGHT_GRAY);
		this.dobrar.setEnabled(false);
		panel.add(this.dobrar);
		this.dobrar.addActionListener(btnAcionarDouble);
		
		this.split = new JButton("SPLIT");
		this.split.setBounds(515,597,130,36);
		this.split.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.split.setForeground(Color.DARK_GRAY);
		this.split.setBackground(Color.LIGHT_GRAY);
		this.split.setEnabled(false);
		panel.add(this.split);
		this.split.addActionListener(btnAcionarSplit);
		
		this.surrender = new JButton("SURRENDER");
		this.surrender.setBounds(656,597,130,36);
		this.surrender.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.surrender.setForeground(Color.DARK_GRAY);
		this.surrender.setBackground(Color.LIGHT_GRAY);
		this.surrender.setEnabled(false);
		panel.add(this.surrender);
		this.surrender.addActionListener(btnAcionarSurrender);
		
		this.quit = new JButton("QUIT");
		this.quit.setBounds(923,158,150,36);
		this.quit.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.quit.setForeground(Color.DARK_GRAY);
		this.quit.setBackground(Color.LIGHT_GRAY);
		this.quit.setEnabled(false);
		this.getContentPane().add(this.quit);
		this.quit.addActionListener(btnAcionarQuit);
	}
		
	public ActionListener btnAcionarHit = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
    			notificar(infoJogador,CodigosObservadorView.BOTAO_HIT_JOGADOR.valor);
				System.out.println("HIT ACIONADO PELO JOGADOR: " + nomeJogador);
			} catch (Exception e1) {
				System.out.println("Erro[btnAcionarHit] ao chamar btnComecarCallback()"+e1);
			}
		}
    };
	
    public ActionListener btnAcionarStand = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
    			notificar(infoJogador,CodigosObservadorView.BOTAO_STAND_JOGADOR.valor);
				System.out.println("STAND ACIONADO PELO JOGADOR: " + nomeJogador);
			} catch (Exception e1) {
				System.out.println("Erro[btnAcionarStand] ao chamar btnComecarCallback()"+e1);
			}
		}
    };
    
    public ActionListener btnAcionarDouble = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
    			notificar(infoJogador,CodigosObservadorView.BOTAO_DOUBLE_JOGADOR.valor);
				System.out.println("DOUBLE ACIONADO PELO JOGADOR: " + nomeJogador);
			} catch (Exception e1) {
				System.out.println("Erro[btnAcionarDouble] ao chamar btnComecarCallback()"+e1);
			}
		}
    };
    
    public ActionListener btnAcionarSplit = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
    			notificar(Integer.toString(indiceJogador),CodigosObservadorView.BOTAO_SPLIT_JOGADOR.valor);
				System.out.println("SPLIT ACIONADO PELO JOGADOR: " + nomeJogador);
			} catch (Exception e1) {
				System.out.println("Erro[btnAcionarSplit] ao chamar btnComecarCallback()"+e1);
			}
		}
    };
    
    public ActionListener btnAcionarSurrender = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
    			notificar(Integer.toString(indiceJogador),CodigosObservadorView.BOTAO_SURRENDER_JOGADOR.valor);
				System.out.println("SURRENDER ACIONADO PELO JOGADOR: " + nomeJogador);
			} catch (Exception e1) {
				System.out.println("Erro[btnAcionarSurrender] ao chamar btnComecarCallback()"+e1);
			}
		}
    };
    
    public ActionListener btnAcionarQuit = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
    			notificar(Integer.toString(indiceJogador),CodigosObservadorView.BOTAO_QUIT_JOGADOR.valor);
				System.out.println("QUIT ACIONADO PELO JOGADOR: " + nomeJogador);
			} catch (Exception e1) {
				System.out.println("Erro[btnAcionarQuit] ao chamar btnComecarCallback()"+e1);
			}
		}
    };
    
	public class Panel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(cI.getFundoBanca(), 0, 0, this);
			if(maoDosJogadores!=null) {
				List<String> tmp=maoDosJogadores.get(nomeJogador);
				int indice = 1;
		    	for(String i : tmp) {
		    		g.drawImage(cI.getmapBaralho(i), indice+400,380, null);
		    		indice+=20;
		    	}
			}
		}
	}
	
	//Observador
	public void executar(Object obj,int ID) {
		
		if(maoJogador==0) {
			switch (ID)
			{
			     case 2:
			    	 if(obj.getClass().equals(HashMap.class)) {
			    		 maoDosJogadores = (HashMap<String, List<String>>) obj;
			    		 repaint();
			    		 alterarMao(maoDosJogadores.get(nomeJogador));
			    	 }else {
			    		 System.out.println("[ERRO][Tela jogador][Observer] ID 2 deve receber um hashMap, foi recebido:" + obj.getClass());
			    	 }
			    	 break
			     ;
			     case 3:
			    	 if(obj.getClass().equals(HashMap.class)) {
			    		 Map<String,Integer> maoValorDosJogadores = (HashMap<String, Integer>) obj;
			    		 atualizarValorDaMao(maoValorDosJogadores.get(nomeJogador));
			    	 }else {
			    		 System.out.println("[ERRO][Tela jogador][Observer] ID 3 deve receber um HashMap, foi recebido:" + obj.getClass());
			    	 }
			    	 break
			     ;
			     case 4:
			    	 if(obj.getClass().equals(HashMap.class)) {
			    		 Map<String,Integer> dinheiroJogador = (HashMap<String, Integer>) obj;
			    		 dinheiroTotalJogador(dinheiroJogador.get(nomeJogador));
			    	 }else {
			    		 System.out.println("[ERRO][Tela jogador][Observer] ID 4 deve receber um HashMap, foi recebido:" + obj.getClass());
			    	 }
			    	 break
			     ; 
			     case 18:
			    	 String[][] tmp= (String[][])obj;
			    	 if(Integer.parseInt(tmp[5][0])==indiceJogador) {
			    		 this.hit.setEnabled(Boolean.valueOf(tmp[0][0]));
				    	 this.stand.setEnabled(Boolean.valueOf(tmp[1][0]));
				    	 this.dobrar.setEnabled(Boolean.valueOf(tmp[2][0]));
				    	 this.split.setEnabled(Boolean.valueOf(tmp[3][0]));
				    	 this.surrender.setEnabled(Boolean.valueOf(tmp[4][0]));
				    	 this.quit.setEnabled(Boolean.valueOf(tmp[6][0]));
			    	 }
			    	 break
			     ;		
			}
		}else {
			switch (ID)
			{
			     case 14:
			    	 if(obj.getClass().equals(HashMap.class)) {
			    		 maoDosJogadores = (HashMap<String, List<String>>) obj;
			    		 repaint();
			    		 alterarMao(maoDosJogadores.get(nomeJogador));
			    	 }else {
			    		 System.out.println("[ERRO][Tela jogador][Observer] ID 2 deve receber um hashMap, foi recebido:" + obj.getClass());
			    	 }
			    	 break
			     ;
			     case 15:
			    	 if(obj.getClass().equals(HashMap.class)) {
			    		 Map<String,Integer> maoValorDosJogadores = (HashMap<String, Integer>) obj;
			    		 atualizarValorDaMao(maoValorDosJogadores.get(nomeJogador));
			    	 }else {
			    		 System.out.println("[ERRO][Tela jogador][Observer] ID 3 deve receber um HashMap, foi recebido:" + obj.getClass());
			    	 }
			    	 break
			     ;
			     case 4:
			    	 if(obj.getClass().equals(HashMap.class)) {
			    		 Map<String,Integer> dinheiroJogador = (HashMap<String, Integer>) obj;
			    		 dinheiroTotalJogador(dinheiroJogador.get(nomeJogador));
			    	 }else {
			    		 System.out.println("[ERRO][Tela jogador][Observer] ID 4 deve receber um HashMap, foi recebido:" + obj.getClass());
			    	 }
			    	 break
			     ;
			     case 18:
			    	 String[][] tmp= (String[][])obj;
			    	 if(Integer.parseInt(tmp[5][1])==indiceJogador) {
			    		 this.hit.setEnabled(Boolean.valueOf(tmp[0][1]));
				    	 this.stand.setEnabled(Boolean.valueOf(tmp[1][1]));
				    	 this.dobrar.setEnabled(Boolean.valueOf(tmp[2][1]));
				    	 this.split.setEnabled(Boolean.valueOf(tmp[3][1]));
				    	 this.surrender.setEnabled(Boolean.valueOf(tmp[4][1]));
				    	 this.quit.setEnabled(Boolean.valueOf(tmp[6][1]));
			    	 }
			    	 break
			     ;		 
			    
			}
		}
			
	}
	
	//Metodos de execucao observer
	private void alterarMao(List<String> novaMao) {
		System.out.println(novaMao);
	}
		
	private void atualizarValorDaMao(Integer novoValor) {
		this.labelValor.setText("Novo Valor: " + novoValor);
	}
		
	private void dinheiroTotalJogador(Integer din) {
		this.labelDinheiro.setText("Novo Valor: " + din);
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