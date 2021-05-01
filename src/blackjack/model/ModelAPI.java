package blackjack.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModelAPI {
	//Info principal
	private Baralho baralho;
	private List<Jogador> jogadores;
	private Dealer dealer;
	//Info secundaria
	static Map<String, Integer> jogadorAposta = new HashMap<String, Integer>();
	int jogada;
	int rodada;
	
	
	//
	//Funçoes principais de controle de partida
	//
	
	public void confereGanhadores() {
		//@ Ze confere black jack
		 
		//CASO UM OUTRO JOGADOR TENHA BLACK JACK DA EMPATE --> verificar o que acontece no empate. <--
		if (dealer.veBlackJackDealer() == true /*  && Jogador n�o possui Black Jack */) {
			//possui um BLACKJACK
			zerarMontade();
			novaRodada();//Chama uma nova rodada;
		}
		
		for(Jogador j: jogadores) {
			//if(j.blackjack())
		//		j.receberFichas(jogadorAposta.get(j.getNomeJogador())*1.5 +j.getNomeJogador()));
	//		if(j.valorMao(0) > dealer.valorMao())
				j.receberFichas(jogadorAposta.get(j.getNomeJogador()));
//		@Ze	if(j.checkRendicao())
//				j.receberFichas(jogadorAposta.get(j.getNomeJogador())/2);
			
		}
		zerarMontade(); // zera o montante
		novaRodada();//Chama uma nova rodada;
	}
	
	//Começa uma rodada
	public void novaRodada() {
		//Incrimenta em 1 a rodada
		rodada++;
		
		//Coloca em 0 a jogada
		jogada = 0;
		
		for(Jogador j :jogadores ) {
			//Tira carta da mão de todos os jogadores
			j.limparMaoJogador(0);;
			
			//Tira do double
			j.clearDobrar();
			
			//Tira do stand
			j.clearStand();
			
			//Tira do split
			j.limparMaoJogador(1);
			
			//remove o jogador caso nao tenha mais dinheiro
			if(j.fichasTotalJogador() == 0)
				removerJogadorNome(j.getNomeJogador());	
		}
		
		//Limpa a m�o do dealer
		dealer.limpaMao();
		
	}
	
	//limpa todos os stands
	public void clearStand(){
		for(Jogador j : jogadores) {
			j.clearStand();
		}
		dealer.clearStand();
	}
	
	//Verifica se existem jogadores que podem pedir cartas
	boolean checkJogadoresDisponiveis() {
		for(Jogador j :jogadores ) {
			if(!j.checkStand())
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
			j.putDobrar();
		}
		 dealer.receberCarta(baralho.pegarCarta());
		 dealer.receberCarta(baralho.pegarCarta());
	}
	
	//Pula para o proximo jogador (considera dinheiro e stand)
	public void proximoJogador() {
		//todo ?
		proximaJogada();
	}
	
	//
	//Metodos de informacao [obtem informaçao dos jogadores e da partida]
	//
	
	public String jogadorAtualNome() {
		return jogadores.get(jogada).getNomeJogador();
	}
	
	public List<String> jogadorAtualMao() {
		List<String> cartasString =new ArrayList<>();
		for(Carta c: jogadores.get(jogada).getMaoJogador(0)) {
			cartasString.add(c.getInfo());
		}
		return cartasString;
	}
	
	public LinkedHashMap <String, Integer> jogadorAtualCarteira() {
		return jogadores.get(jogada).getFichasJogador();
	}
	
	public boolean jogadorAtualCheckStand() {
		return jogadores.get(jogada).checkStand();
	}
	
	public int numeroDeJogadores() {
		return jogadores.size();
	}
	
	public String jogadorNome(int n) {
		return jogadores.get(n).getNomeJogador();
	}
	
	public List<String> jogadorMao(int n) {
		List<String> cartasString =new ArrayList<>();
		for(Carta c: jogadores.get(n).getMaoJogador(0)) {
			cartasString.add(c.getInfo());
		}
		return cartasString;
	}
	
	
	public List<String> dealerMao() {
		List<String> cartasString =new ArrayList<>();
		for(Carta c: dealer.verificarMao()) {
			cartasString.add(c.getInfo());
		}
		return cartasString;
	}
	
	public int totalMontante() {
		int i=0;
		for (Map.Entry<String,Integer> pair : jogadorAposta.entrySet()) {
		    i=i+pair.getValue();
		}
		return i;
	}

	
	//
	//Metodos de controle jogador  [controla o jogador e o dealer]
	//

	
	//Dealer age conforme as regras
	public void dealerAcao() {
		if(dealer.checkEstrategia() == 2) {
			dealer.receberCarta(baralho.pegarCarta());
		}
		else {
			//verificar cartas para, fim da rodada, ou proximo jogador.
			proximoJogador();// <-------------------- em duvida?
		}
	}
	
	//Jogador atual faz uma aposta
	public void apostar(int n) {
		jogadores.get(jogada).pagarFichas(n);
		adicionarAMontante(jogadores.get(jogada),n);
	}
	
	//Jogador atual recebe
	public void receber(int n) {
		jogadores.get(jogada).receberFichas(n);
	}
	
	//Jogador especifico recebe
	public void receberJogador(Jogador j,int n) {
		j.receberFichas(n);
	}
	
	public void pedirStand() {
		jogadores.get(jogada).putStand();
	}
	
	public void pedirHit() {
		jogadores.get(jogada).recebeCarta(baralho.pegarCarta(),0);
	}
	
	// .... Metodos para cada possivel interação
	
	
	//
	//Metodos de controle partida  [controla a partida]
	//
	
	
	//Zera o montade
	private void zerarMontade() {
		jogadorAposta.forEach((key, value) -> {
			   value = 0;
			});
	}
	
	//adiciona um valor da aposta de determinado montante do jogador
	private void adicionarAMontante(Jogador j, int valor){
		if(!jogadorAposta.containsKey(j.getNomeJogador())) {
			jogadorAposta.put(j.getNomeJogador(), valor);
			
		}else {
			jogadorAposta.computeIfPresent(j.getNomeJogador(), (k, v) -> v + valor);
		}
	}
	
	//remove um valor da aposta de determinado montante do jogador
	@SuppressWarnings("unused")
	private void removerAMontante(Jogador j, int valor){
		jogadorAposta.computeIfPresent(j.getNomeJogador(), (k, v) -> v - valor);
	}
	
	//Passa para a proxima jogada
	public void  proximaJogada() {
		jogada = jogada+1;
		if(jogada == jogadores.size())
			jogada = 0;
	}
	
	
	//Adiciona um jogador a partida
	public void adicionarJogador(String nome){
		if(jogadores.size()<=4) {
			jogadores.add(new Jogador(nome));
		}
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
		removerJogadorNome(jogadores.get(jogada).getNomeJogador());
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
		this.dealer = new Dealer("0x00");
		this.jogadores = new ArrayList<>();
		this.jogada = 0;
	}
	
	public static synchronized ModelAPI iniciar() {
		if(instanciaUnica == null)
			instanciaUnica = new ModelAPI();
		return instanciaUnica;
	}
	
}
