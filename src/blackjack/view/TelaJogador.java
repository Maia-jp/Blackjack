package blackjack.view;


import blackjack.controller.CodigosObservador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class TelaJogador extends JFrame implements Observador{
	String nomeJogador;
	HashMap<String,List<String>> maoDosJogadores;
	Label labelValor;
	Label labelDinheiro;
	CarregaImagens cI;
	ImageIcon fundo = new ImageIcon(getClass().getResource("/blackjackBKG.png"));
	
	public TelaJogador(String nomeJogador,CarregaImagens cI) {
		this.cI = cI;
		this.nomeJogador = nomeJogador;
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
		this.getContentPane().add(panel1).setBackground(getBackground());;;
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
		
		this.labelDinheiro = new Label();
		this.labelDinheiro.setAlignment(Label.CENTER);
		this.labelDinheiro.setBounds(10, 66, 98, 22);
		panel1.add(this.labelDinheiro);
		
		this.labelValor = new Label();
		this.labelValor.setAlignment(Label.CENTER);
		this.labelValor.setBounds(45, 10, 98, 22);
		panel2.add(this.labelValor);
		
		JButton jb = new JButton("HIT");
		jb.setBounds(209,597,100,36);
		jb.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb.setForeground(Color.DARK_GRAY);
		jb.setBackground(Color.LIGHT_GRAY);
		panel.add(jb);
		
		JButton jb2 = new JButton("STAND");
		jb2.setBounds(329,597,100,36);
		jb2.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb2.setForeground(Color.DARK_GRAY);
		jb2.setBackground(Color.LIGHT_GRAY);
		panel.add(jb2);
		
		JButton jb3 = new JButton("DOUBLE");
		jb3.setBounds(449,597,100,36);
		jb3.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb3.setForeground(Color.DARK_GRAY);
		jb3.setBackground(Color.LIGHT_GRAY);
		panel.add(jb3);
		
		JButton jb4 = new JButton("SPLIT");
		jb4.setBounds(569,597,100,36);
		jb4.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb4.setForeground(Color.DARK_GRAY);
		jb4.setBackground(Color.LIGHT_GRAY);
		panel.add(jb4);
		
	}
	
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
	
	
}