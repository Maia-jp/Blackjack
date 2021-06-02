package blackjack.view;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import blackjack.controller.CodigosObservadorView;
import blackjack.model.Observado;

public class TelaBanca extends JFrame implements Observado,Observador, MouseListener{
	
	private final int LARG_DEFAULT=891;
	private final int ALT_DEFAULT=700;
	private ImagensTela telaI;
	private CarregaImagens cI;
	private JButton novaRodada;
	private JButton encerrrar;
	private JButton salvar;
	
	public TelaBanca(CarregaImagens cI) {
		super("Banca");
		this.cI = cI;
		addMouseListener(this);
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
		
		salvar = new JButton("Abrir Opcoes");
		salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			notificar(true,CodigosObservadorView.BOTAO_SALVAR_TELA_DEALER.valor);
			}
		});
		telaI.add(salvar);
	}
    
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
		    	 break;
			case 10:
				if(obj.getClass().equals(int[].class)) {
					int [] conteudoDealer = (int[]) obj;
					this.telaI.redesenhar(conteudoDealer);
				}else {
		    		 System.out.println("[ERRO][Tela Banca][Observer] ID 10 deve receber um int[], foi recebido:" + obj.getClass());
		    	}
				break;
			case 11:
				if(obj.getClass().equals(Boolean.class)) {
					boolean valorFicha = (boolean) obj;
					this.telaI.redesenhar(valorFicha);
				}else {
		    		 System.out.println("[ERRO][Tela Banca][Observer] ID 11 deve receber um Boolean, foi recebido:" + obj.getClass());
		    	}
				break;
			case 12:
				if(obj.getClass().equals(String[].class)) {
					String[] valorFicha = (String[]) obj;
					this.telaI.redesenhar(valorFicha);
				}else {
		    		 System.out.println("[ERRO][Tela Banca][Observer] ID 12 deve receber um String[], foi recebido:" + obj.getClass());
		    	}
				break;
			case 13:
				if(obj.getClass().equals(Boolean.class)) {
					this.telaI.redesenhar();
				}else {
		    		 System.out.println("[ERRO][Tela Banca][Observer] ID 13 deve receber um Boolean, foi recebido:" + obj.getClass());
		    	}
				break;
			case 16:
				if(obj.getClass().equals(String.class)) {
					String nomeJogador = (String) obj;
					this.telaI.redesenhar(nomeJogador);
				}else {
		    		 System.out.println("[ERRO][Tela Banca][Observer] ID 14 deve receber um String, foi recebido:" + obj.getClass());
		    	}
				break;
			case 17:
				if(obj.getClass().equals(ArrayList.class)) {
					ArrayList<String[]> infosJogadores= (ArrayList<String[]>) obj;
					this.telaI.redesenhar2(infosJogadores);
				}else {
		    		 System.out.println("[ERRO][Tela Banca][Observer] ID 15 deve receber um ArrayList, foi recebido:" + obj.getClass());
		    	}
				break;
		}
				
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX() >= 60 && e.getX() <= 119 && e.getY() >= 500 && e.getY() <=  559) {
			notificar("1",CodigosObservadorView.BOTAO_APOSTA_INICIAL.valor);
		}
		
		else if (e.getX() >= 190 && e.getX() <= 249 && e.getY() >= 550 && e.getY() <=  609) {
			notificar("5",CodigosObservadorView.BOTAO_APOSTA_INICIAL.valor);
		}
		
		else if (e.getX() >= 320 && e.getX() <= 379 && e.getY() >= 590 && e.getY() <=  649) {
			notificar("10",CodigosObservadorView.BOTAO_APOSTA_INICIAL.valor);
		}
		
		else if (e.getX() >= 460 && e.getX() <= 519 && e.getY() >= 590 && e.getY() <=  649) {
			notificar("20",CodigosObservadorView.BOTAO_APOSTA_INICIAL.valor);
		}
		
		else if (e.getX() >= 590 && e.getX() <= 649 && e.getY() >= 560 && e.getY() <=  619) {
			notificar("50",CodigosObservadorView.BOTAO_APOSTA_INICIAL.valor);
		}
		
		else if (e.getX() >= 720 && e.getX() <= 779 && e.getY() >= 520 && e.getY() <=  579) {
			notificar("100",CodigosObservadorView.BOTAO_APOSTA_INICIAL.valor);
		}
		else if (e.getX() >= 700 && e.getX() <= 891 && e.getY() >= 290 && e.getY() <=  320) {
			notificar(true,CodigosObservadorView.BOTAO_APOSTA_INCIAL_REALIZAR.valor);
		}
		else if (e.getX() >= 750 && e.getX() <= 809 && e.getY() >= 200 && e.getY() <=  259) {
			notificar(true,CodigosObservadorView.BOTAO_REMOVE_FICHA_APOSTA.valor);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
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
