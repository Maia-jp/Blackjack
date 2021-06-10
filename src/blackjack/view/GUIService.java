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
import blackjack.model.Observado;


public class GUIService{
	
	
	private String ID;
	
	//Model API para opera��es simples
	public ModelAPI api; 
	
	//bitmap -> exibindo tela inical, exibindo carregamento de partida, exibindo jogo
	private static BitSet estado = new BitSet(3);
	
	//Telas
	CarregaImagens cI = new CarregaImagens();
	TelaIncial telaInicial;
	static List<TelaJogador> telasJogador = new ArrayList<>();
	static List<TelaJogador> telasJogadorSplit = new ArrayList<>();
	TelaBanca telaBanca = new TelaBanca(cI);
	TelaOpcoes opcoes;
	
	//
	//
	//Singleton
	//
	private static GUIService instanciaUnica;
	
	private GUIService(ModelAPI api) {
		this.api = api;
		this.ID = gerarID();
		estado.clear();
		estado.set(0); //Estado iniciar é exibir a tela inicial
	}
	
	private String gerarID() {
		return ""+Instant.now().getEpochSecond();
	}
	
	public static synchronized GUIService iniciar(ModelAPI api) {
		if(instanciaUnica == null)
			instanciaUnica = new GUIService(api);
		return instanciaUnica;
	}
		
	//
	//Janelas
	//
	private void exibirTelaInicial() {
		
		this.telaInicial = new TelaIncial();
		observadores.forEach(o->this.telaInicial.adicionarObservador(o));
		telaInicial.setVisible(true);
		telaInicial.addWindowListener(wAListner);
		
	}
	
	private void exibirTelaJogador() {
		
		telasJogador.forEach((tela) -> tela.setTitle(tela.nomeJogador));
		telasJogador.forEach((tela) -> tela.setVisible(true));
		telasJogador.forEach((tela) -> tela.addWindowListener(wAListner));
	}
	
	private void exibirTelaJogadorSplit() {
		telasJogadorSplit.forEach((tela) -> tela.setTitle(tela.nomeJogador));
		telasJogadorSplit.forEach((tela) -> tela.setVisible(false));
		telasJogadorSplit.forEach((tela) -> tela.addWindowListener(wAListner));
	}
	
	
	
	private void exibirTelaBanca() {
		api.adicionarObservador(telaBanca);
		observadores.forEach((o) -> telaBanca.adicionarObservador(o));
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
			exibirTelaJogadorSplit();
			//DISTRIBUIR AS CARTAS
			//api.distribuirCartas();
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
	
	public void telaInicialCriarJogadores(List<String> jogadores, Observador o){
		 jogadores.forEach((j) -> api.adicionarJogador(j));
		 jogadores.forEach((j) -> telasJogador.add(new TelaJogador(j,cI,0,jogadores.indexOf(j))));
		 telasJogador.forEach((j) -> j.adicionarObservador(o));
		 telasJogador.forEach((j) -> api.adicionarObservador(j));
		 
		 jogadores.forEach((j) -> telasJogadorSplit.add(new TelaJogador(j,cI,1,jogadores.indexOf(j))));
		 telasJogadorSplit.forEach((j) -> j.adicionarObservador(o));
		 telasJogadorSplit.forEach((j) -> api.adicionarObservador(j));
	
		 telaInicial.dispose();
		 
		 
		 estado.flip(0);
		 estado.flip(1);
		 System.out.println("Jogadores adicionados com sucesso");
		 try {
			exibir();
		} catch (Exception e) {
			System.out.println("Erro[telaInicialComecarCallback] ao chamar exibir()");
			e.printStackTrace();
		}
	}
	
	public void telaSplitVisivel(int indiceJogador) {
		telasJogadorSplit.get(indiceJogador).setVisible(true);
	}
	
	public void telaSplitInvisivel() {
		telasJogadorSplit.forEach((tela) -> tela.setVisible(false));
	}
	
	public void telaQuitVisivel(int indiceJogador) {
		telasJogador.get(indiceJogador).setVisible(false);
	}
	
	public void exibirOpcoes() {
		this.opcoes = TelaOpcoes.iniciar(api.listaNomeJogadores());
		observadores.forEach(o->this.opcoes.adicionarObservador(o));
		this.opcoes.setVisible(true);
		this.opcoes.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent evt) {
		    	opcoes.setVisible(false);
		    }
		  });
	}
	
	public void opcoesGerarCarteira(String carteira) {
		opcoes.exibirCarteira(carteira);
		
	}
	
	public void opcoesErroGerarCarteira() {
		opcoes.gerarCarteiraErro();
	}
	public void opcoesInfoSalvar() {
		opcoes.gerarAlertaSalvo();
	}
	public void opcoesInfoCarregar() {
		opcoes.gerarAlertaCarregar();
	}
	
	public Boolean opcoesAlterarSalvamento() {
		return opcoes.mudarEstadoSalvar();
	}
	
	//
	// OBSERVADO metodos
	//
	public List<Observador> observadores = new ArrayList<>();
	
	public void adicionarObservador(Observador o) {
		observadores.add(o);
	}

	
}
