package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class ModelAPI {
	//Info principal
	private Baralho baralho;
	private List<Jogador> jogadores;
	private Dealer dealer;
	//Info secundaria
	Jogador jogadorAtual; 
	int jogada;
	int rodada;
	ArrayList<String> log;
	
	
	//
	//Main loop
	//
	
	String proximaJogada() {
		
		
		
		
		
		
		jogadorAtual = jogadores.get(avancarJogada);
		return jogadorAtual.getNomeJogador();
	}
	
	
	
	
	//
	//Metodos de informacao [obtem informaçao dos jogadores e da partida]
	//

	
	
	
	//
	//Metodos de controle [controla a partida]
	//
	
	//Passa para a proxima jogada
	private int avancarJogada() {
		if(this.jogada == this.jogadores.size())
			return 0;
		
		return this.jogada++;
		
	}
	
	
	//Adiciona um jogador a partida
	public void adicionarJogador(Jogador j) throws Exception {
		if(jogadores.size()<4) {
			jogadores.add(j);
		}else
			throw new Exception("Impossivel adicionar mais um jogador. A mesa ja esta cheia !");
	}
	
	//Remove um jogador especifico da partida
	public void removerJogadorNome(String nome) {
		List<Jogador> copy = new ArrayList<Jogador>();
		
		for (Jogador j : this.jogadores) {
		  if (!nome.equals(j.getNomeJogador()))
		    copy.add(j);
		}
		
		this.jogadores = copy;
	}
	
	//Remove o jogador atual da partida
	void removerJogador() {
		removerJogadorNome(this.jogadorAtual);
	}
	
	
	//Reinicia o baralho
	void reiniciarBaralho() {
		this.baralho = new Baralho(4);
	}
	
	
	
	//
	//Implementa modelo singleton
	//
	private static ModelAPI instanciaUnica;
	
	private ModelAPI() {
		
	}
	
	public static synchronized ModelAPI iniciar() {
		if(instanciaUnica == null)
			instanciaUnica = new ModelAPI();
		return instanciaUnica;
	}
	
	
	
	
}
