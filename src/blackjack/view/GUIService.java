package blackjack.view;
import javax.swing.*;
import javax.swing.event.*;

import blackjack.model.ModelAPI;

import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;
import java.time.Instant;
import java.util.*;


public class GUIService {
	private String ID;
	//bitmap -> exibindo tela inical, exibindo carregamento de partida, exibindo jogo
	private static BitSet estado = new BitSet(3);
	

	
	//Singleton
	
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
	
	//Janela inicial
	
	private void exibirTelaInicial() {
		TelaIncial telaInicial = new TelaIncial();
		telaInicial.setVisible(true);
		
		telaInicial.addWindowListener(new WindowAdapter() {

	        @Override
	        public void windowClosing(WindowEvent arg0) {
	            	telaInicial.setVisible(false);
	                System.out.println("closed");
	        }

	    });
		
		
	}
	
	
	
	
	//Exibir
	public void exibir() throws Exception {
		if(estado.get(0) && estado.get(1)&& estado.get(2)) {
			throw new Exception("Mais um de estado se encontra ativo");
		}
		
		if(estado.get(0)) {
			exibirTelaInicial();
		}
		
		
	}
	

	
	
}
