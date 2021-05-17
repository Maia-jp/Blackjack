package blackjack.view;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaBanca extends JFrame implements Observador{
	
	private final int LARG_DEFAULT=891;
	private final int ALT_DEFAULT=700;
	private ImagensTela telaI;
	private CarregaImagens cI;
	JButton novaRodada;
	JButton encerrrar;
	JButton salvar;
	
	public TelaBanca(CarregaImagens cI) {
		super("Banca");
		this.cI = cI;
		inicializar();
	}
	
	private void inicializar() {
		this.telaI = new ImagensTela(cI);
		getContentPane().add(telaI);
		setResizable(false);
		setBounds(450,40,LARG_DEFAULT,ALT_DEFAULT);

		novaRodada = new JButton("Nova Rodada");
		telaI.add(novaRodada);
		novaRodada.addActionListener(null);
		
		encerrrar = new JButton("Encerrar Partida");
		telaI.add(encerrrar);
		
		salvar = new JButton("Salvar Partida");
		telaI.add(salvar);
	}
    
	//Metodos Observador
	@Override
	public void executar(Object obj,int ID) {
		switch (ID)
		{
			case 1:
		    	 if(obj.getClass().equals(ArrayList.class)) {
		    		 ArrayList<String> maoDealer = (ArrayList<String>) obj;
		    		 this.telaI.redesenhar(maoDealer);
		    		 System.out.println("DEALER:"+maoDealer);
		    
		    	 }else {
		    		 System.out.println("[ERRO][Tela Banca][Observer] ID 1 deve receber um ArrayList, foi recebido:" + obj.getClass());
		    	 }
			case 10:
				if(obj.getClass().equals(int[].class)) {
					int [] conteudoDealer = (int[]) obj;
					this.telaI.redesenhar(conteudoDealer);
				}
		}
				
	}
	
}
