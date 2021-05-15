package blackjack.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;


public class TelaJogador extends JFrame implements Observador {
	String nomeJogador;
	private int valor;
	List<String> cartas = new ArrayList<>();
	
	public TelaJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
		initialize();
	}
	
	private void initialize() {
		System.out.println("Jogador incializado:" + nomeJogador);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		
	
		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 713, 641);
		this.getContentPane().add(panel);
		this.setBounds(0, 50, 950, 700);
		panel.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel1 = new Panel();
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(729, 10, 195, 133);
		this.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		Panel panel2 = new Panel();
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(729, 518, 195, 133);
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
		
		Label label_1_1 = new Label("Dinheiro Total: ");
		label_1_1.setAlignment(Label.CENTER);
		label_1_1.setBounds(10, 66, 98, 22);
		panel1.add(label_1_1);
		
		Label label_1_1_1 = new Label("Valor da mao: " + valor);
		label_1_1_1.setAlignment(Label.CENTER);
		label_1_1_1.setBounds(45, 10, 98, 22);
		panel2.add(label_1_1_1);
		
		JButton jb = new JButton("HIT");
		jb.setBounds(118,597,100,33);
		jb.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb.setForeground(Color.DARK_GRAY);
		jb.setBackground(Color.LIGHT_GRAY);
		panel.add(jb);
		
		JButton jb2 = new JButton("STAND");
		jb2.setBounds(238,597,100,33);
		jb2.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb2.setForeground(Color.DARK_GRAY);
		jb2.setBackground(Color.LIGHT_GRAY);
		panel.add(jb2);
		
		JButton jb3 = new JButton("DOUBLE");
		jb3.setBounds(358,597,100,33);
		jb3.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb3.setForeground(Color.DARK_GRAY);
		jb3.setBackground(Color.LIGHT_GRAY);
		panel.add(jb3);
		
		JButton jb4 = new JButton("SPLIT");
		jb4.setBounds(478,597,100,33);
		jb4.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb4.setForeground(Color.DARK_GRAY);
		jb4.setBackground(Color.LIGHT_GRAY);
		panel.add(jb4);
		
	}
	
	//Observador
	@Override
	public void executar(Object obj,int ID) {
		switch (ID)
		{
		     case 2:
		    	 if(obj.getClass().equals(HashMap.class)) {
		    		 HashMap<String,List<String>> maoDosJogadores = (HashMap<String, List<String>>) obj;
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
		     
		}
		
	}
	
	//Metodos de execucao observer
	private void alterarMao(List<String> novaMao) {
		System.out.println(novaMao);

	}
	
	private void atualizarValorDaMao(Integer novoValor) {
		System.out.println("Novo valor "+novoValor);
	}
	
	public int valorDaMaoInterface(int valor) {
		 return valor;
	}
	
	
}