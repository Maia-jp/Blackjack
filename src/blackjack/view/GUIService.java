package blackjack.view;
import javax.swing.*;
import javax.swing.event.*;

import blackjack.model.ModelAPI;

import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;
import java.time.Instant;
import java.util.*;
import java.util.List;


public class GUIService {
	
	private String ID;
	
	//bitmap -> exibindo tela inical, exibindo carregamento de partida, exibindo jogo
	private static BitSet estado = new BitSet(3);
	
	public ModelAPI api = ModelAPI.iniciar();
	
	//Telas
	TelaIncial telaInicial;
	static List<TelaJogador> telasJogador = new ArrayList<>();
	
	//
	CarregaImagens cI = new CarregaImagens();
	
	TelaBanca telaBanca = new TelaBanca(cI);
	//
	//Singleton
	//
	private static GUIService instanciaUnica;
	
	private GUIService() {
		this.ID = gerarID();
		estado.clear();
		estado.set(0); //Estado iniciar é exibir a tela inicial
	}
	
	private String gerarID() {
		return ""+Instant.now().getEpochSecond();
	}
	
	public static synchronized GUIService iniciar() {
		if(instanciaUnica == null)
			instanciaUnica = new GUIService();
		return instanciaUnica;
	}
		
	//
	//Janelas
	//
	private void exibirTelaInicial() {
		
		this.telaInicial = new TelaIncial();
		telaInicial.setVisible(true);
		
		telaInicial.addWindowListener(wAListner);
		telaInicial.btnComecarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaInicialComecarCallback();
			}});
		
		telaInicial.btnComecarPartida.
			addActionListener(telaInicial.btnPartidaAction);
		
	}
	
	private void exibirTelaJogador() {
		
		telasJogador.forEach((tela) -> tela.setTitle(tela.nomeJogador));
		telasJogador.forEach((tela) -> tela.setVisible(true));
		telasJogador.forEach((tela) -> tela.addWindowListener(wAListner));
	}
	
	private void exibirTelaBanca() {
		api.adicionarObservador(telaBanca);
		telaBanca.setVisible(true);
		telaBanca.addWindowListener(wAListner);
	}
	
	//
	//Exibir
	//
	public void exibir() throws Exception {
		if(estado.get(0) && estado.get(1) && estado.get(2)) {
			throw new Exception("Mais um de estado se encontra ativo");
		}
		
		if(estado.get(0)) {
			exibirTelaInicial();
		}
		if(estado.get(1)) {
			exibirTelaJogador();
			exibirTelaBanca();
			//DISTRIBUIR AS CARTAS
			api.distribuirCartas();
			telaBanca.repaint();
		}
	}
	
	//
	//Listners
	//
	WindowAdapter wAListner = new WindowAdapter() {
		public void windowClosing(WindowEvent arg0) {
        	telaInicial.setVisible(false);
        	telaInicial.dispose();
        	
        	
        	telasJogador.forEach((tela) -> tela.setVisible(false));
    		telasJogador.forEach((tela) -> tela.setVisible(false));
        	
        	telaBanca.setVisible(false);
        	telaBanca.disable();
        	
            System.exit(0);
        }
	};
	
	
	//
	//Callbacks e observers
	private void telaInicialComecarCallback(){
		System.out.print("Tela inicial callback");
		 List<String> jogadores = new ArrayList<>();
		try {
			jogadores = telaInicial.getJogadores();
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(1);
		}
		 jogadores.forEach((j) -> api.adicionarJogador(j));
		 jogadores.forEach((j) -> telasJogador.add(new TelaJogador(j)));
		 telasJogador.forEach((j) -> api.adicionarObservador(j));
		 telaInicial.dispose();
		 
		 estado.flip(0);
		 estado.flip(1);
		 System.out.print(jogadores);
		 try {
			exibir();
		} catch (Exception e) {
			System.out.println("Erro[telaInicialComecarCallback] ao chamar exibir()");
			e.printStackTrace();
		}
	}

	
}
