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
	TelaJogador telaJogador;
	
	
	
	
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
		telaInicial.btnComeçarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaInicialComeçarCallback();
			}});
		
	}
	
	private void exibirTelaJogador() {
		this.telaJogador = new TelaJogador();
		telaJogador.setVisible(true);
		telaJogador.addWindowListener(wAListner);
	}
	
	
	//
	//Exibir
	//
	public void exibir() throws Exception {
		if(estado.get(0) && estado.get(1)&& estado.get(2)) {
			throw new Exception("Mais um de estado se encontra ativo");
		}
		
		if(estado.get(0)) {
			exibirTelaInicial();
		}
		if(estado.get(1)) {
			exibirTelaJogador();
		}
		
	}
	
	//
	//Listners
	//
	WindowAdapter wAListner = new WindowAdapter() {
        
		public void windowClosing(WindowEvent arg0) {
            	telaInicial.setVisible(false);
            	telaInicial.dispose();
            	
            	telaJogador.setVisible(false);
            	telaJogador.disable();
            	
                System.exit(0);
        }

	};
	
	
	//
	//Callbacks e observers
	private void telaInicialComeçarCallback(){
		System.out.print("Tela inicial callback");
		 List<String> jogadores = telaInicial.getJogadores();
		 jogadores.forEach((j) -> api.adicionarJogador(j));
		 telaInicial.dispose();
		 
			//Nova intancia da janela do jogador
			//Nova instancia da janela do Dealer
		 
		 estado.flip(0);
		 estado.flip(1);
		 
		 try {
			exibir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
