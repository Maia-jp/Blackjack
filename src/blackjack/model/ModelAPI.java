package blackjack.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelAPI {
	//Info principal
	private Baralho baralho;
	private List<Jogador> jogadores;
	private Dealer dealer;
	//Info secundaria
	Map<String, Integer> jogadorAposta = new HashMap<String, Integer>();
	Jogador jogadorAtual; 
	int jogada;
	int rodada;
	
	
	//
	//Main loop
	//
	
	//Começa uma rodada
	void novaRodada() {
		//Incrimenta em 1 a rodada
		rodada++;
		
		//Coloca em 0 a jogada
		jogada = 0;
		
		for(Jogador j :jogadores ) {
			//Tira carta da mão de todos os jogadores
			j.limparMaoJogador(0);;
			
			//Tira do stand
			j.clearStand();
			
			//Tira do split
			j.limparMaoJogador(1);
			
			//remove o jogador caso nao tenha mais dinheiro
			if(j.fichasTotalJogador() == 0)
				removerJogadorNome(j.getNomeJogador());	
		}
		
		//Primeiro jogador sera o jogador atual
		jogadorAtual = jogadores.get(0);
	}
	
	//Verifica se existem jogadores que podem pedir cartas
	boolean checkJogadoresDisponiveis() {
		for(Jogador j :jogadores ) {
			if(j.checkStand())
				return true;
		}
		return false;
	}
	
	//verifica de precisa de um novo baralho
	void checkNovoBaralho() {
		if(baralho.getNumeroDeCartas() < ((52*4) - ((52*4)*0.10))){
			reiniciarBaralho();
		}
		
	}
	
	//distribui cartas
	void distribuirCartas() {
		for(Jogador j : jogadores) {
			j.recebeCarta(baralho.pegarCarta(),0);
			j.recebeCarta(baralho.pegarCarta(),0);
		}
		// @ALE dealer.receberCarta(baralho.pegarCarta());
		// @ALE dealer.receberCarta(baralho.pegarCarta());
	}
	
	//Pula para o proximo jogador (considera dinheiro e stand)
	public String proximoJogador() {
		jogada = avancarJogada();
		jogadorAtual = jogadores.get(jogada);
		return jogadorAtual.getNomeJogador();
	}
	
	//
	//Metodos de informacao [obtem informaçao dos jogadores e da partida]
	//

	
	//
	//Metodos de controle jogador  [controla o jogador e o dealer]
	//

	
	//Dealer age conforme as regras
	public void dealerAcao() {
		// @Ale
	}
	
	//Jogador atual faz uma aposta
	public void apostar(int n) {
		jogadorAtual.pagarFichas(n);
		adicionarAMontante(jogadorAtual,n);
	}
	
	//Jogador atual recebe
	public void receber(int n) {
		jogadorAtual.receberFichas(n);
	}
	
	//Jogador especifico recebe
	public void receberJogador(Jogador j,int n) {
		j.receberFichas(n);
	}
	
	public void pedirStand() {
		jogadorAtual.putStand();
	}
	
	// .... Metodos para cada possivel interação
	
	
	//
	//Metodos de controle partida  [controla a partida]
	//
	
	//adiciona um valor da aposta de determinado montante do jogador
	private void adicionarAMontante(Jogador j, int valor){
		if(!jogadorAposta.containsKey(j.getNomeJogador())) {
			jogadorAposta.put(j.getNomeJogador(), valor);
			
		}else {
			jogadorAposta.computeIfPresent(j.getNomeJogador(), (k, v) -> v + valor);
		}
	}
	
	//remove um valor da aposta de determinado montante do jogador
	private void removerAMontante(Jogador j, int valor){
		jogadorAposta.computeIfPresent(j.getNomeJogador(), (k, v) -> v - valor);
	}
	
	//Passa para a proxima jogada
	private int avancarJogada() {
		if(this.jogada == this.jogadores.size())
			return 0;
		
		return this.jogada++;
		
	}
	
	
	//Adiciona um jogador a partida
	public void adicionarJogador(String nome) throws Exception {
		if(jogadores.size()<4) {
			jogadores.add(new Jogador(nome));
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
	public void removerJogador() {
		removerJogadorNome(this.jogadorAtual.getNomeJogador());
	}
	
	
	//Reinicia o baralho
	public void reiniciarBaralho() {
		this.baralho = new Baralho(4);
	}
	
	
	
	//
	//Implementa modelo singleton
	//
	private static ModelAPI instanciaUnica;
	
	private ModelAPI() {
		reiniciarBaralho();
		this.dealer = new Dealer();
	}
	
	public static synchronized ModelAPI iniciar() {
		if(instanciaUnica == null)
			instanciaUnica = new ModelAPI();
		return instanciaUnica;
	}
	
	
	
	
}
