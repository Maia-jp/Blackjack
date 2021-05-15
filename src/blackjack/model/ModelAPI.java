package blackjack.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import blackjack.view.Observador;

public class ModelAPI implements Observado {
	//Info principal
	private Baralho baralho;
	private List<Jogador> jogadores;
	private Dealer dealer;
	//Info secundaria
	static private Map<String,Map<String, Integer>> jogadorAposta = 
			new HashMap<String, Map<String, Integer>>();
	private String ID;
	private int jogada;
	private int rodada;
	
	
	//
	//Funçoes principais de controle de partida
	//
	
	public void confereGanhadores() {
		
		for(Jogador j: jogadores) {
			if((j.blackjack()) && (dealer.blackJackDealer() == false)) {
				//j.receberFichas((int)(jogadorAposta.get(j.getNomeJogador())*1.5 + jogadorAposta.get(j.getNomeJogador())));
			}
			if((j.valorMao(0) > dealer.valorMao() && dealer.valorMao() < 21) || (j.blackjack() && dealer.blackJackDealer() == true)) {
				//j.receberFichas(jogadorAposta.get(j.getNomeJogador()));
			}
//			Proxima itera��o
//			if(j.checkRendicao() && dealer.blackJackDealer() == false)
//				j.receberFichas(jogadorAposta.get(j.getNomeJogador())/2);
		}
		
		zerarMontante();
		novaRodada();
	}
	
	//Começa uma rodada
	public void novaRodada() {
        //Incrimenta em 1 a rodada
        rodada++;
        
        //Coloca em 0 a jogada
        jogada = 0;
        
        for(Jogador j :jogadores ) {
            //Tira carta da m�o de todos os jogadores
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
        
        //Limpa a mao do dealer
        dealer.limpaMao();
        zerarMontante();    
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
	public boolean checkNovoBaralho() {
		if(baralho.getNumeroDeCartas() < ((52*4) - ((52*4)*0.10))){
			reiniciarBaralho();
			return true;
		}

		return false;

	}
	
	//distribui cartas
	public void distribuirCartas() {
		for(Jogador j : jogadores) {
			j.recebeCarta(baralho.pegarCarta(),0);
			j.recebeCarta(baralho.pegarCarta(),0);
		}
		 dealer.receberCarta(baralho.pegarCarta());
		 dealer.receberCarta(baralho.pegarCarta());
		 
		 //Observer
		 notificar(dealer,1);
	}
	
	//Pula para o proximo jogador 
	public void proximoJogador() {
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
	
	public int jogadorAtualQuantidadeFichas(String ficha) {
		return jogadorEspecificoQuantidadeFichas(jogada,ficha);
	}
	
	public int jogadorEspecificoQuantidadeFichas(int j,String ficha) {
		return jogadores.get(j).getFichasJogador().get(ficha);
	}
	
	public int jogadorAtualCarteiraTotal() {
		int i =0;
		for(String key : jogadores.get(jogada).getFichasJogador().keySet()) {
			i = i + Integer.parseInt(key) * jogadores.get(jogada).getFichasJogador().get(key);
		}
		return i;
	}
	
	public int jogadorEspecificoCarteiraTotal(int n) {
		int i =0;
		for(String key : jogadores.get(n).getFichasJogador().keySet()) {
			i = i + Integer.parseInt(key) * jogadores.get(n).getFichasJogador().get(key);
		}
		return i;
	}
	
	public Map<String, Integer> jogadorEspecificoCarteira(int n){
		return jogadores.get(n).getFichasJogador();

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
	
	//NOVO
	public int valorDealerMao() {
		return dealer.valorMao();
	}
	
	public int totalMontante() {
		int i=0;
		for(String key : jogadorAposta.keySet()) {
			for(String key2 :jogadorAposta.get(key).keySet()) {
				i = i + Integer.parseInt(key2) * jogadorAposta.get(key).get(key2);
			}
		}
		return i;
	}
	
	public Map<String, Integer> fichasNaMesaTotal(){
		 Map<String, Integer> fichasTotaisNaMesa = new HashMap<String, Integer>();
		for(String key : jogadorAposta.keySet()) {
			for(String key2 :jogadorAposta.get(key).keySet()) {
				if(fichasTotaisNaMesa.containsKey(key2)) {
					int nvVal = jogadorAposta.get(key).get(key2);
					fichasTotaisNaMesa.computeIfPresent(key2, (k, v) -> v + nvVal);
				}else {
					fichasTotaisNaMesa.put(key2, jogadorAposta.get(key).get(key2));
				}
			}
		}
		return fichasTotaisNaMesa;
		
	}
	
	public Map<String,Map<String,Integer>> montanteTotal(){
		return jogadorAposta;
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
			//APENAS PARA VERIFICAR NOS TESTES
			//proximoJogador();// <--------------------?
			dealer.receberCarta(baralho.pegarCarta());
		}
	}
	
	//Jogador atual faz uma aposta
	public void apostar(String ficha, int quantidade) {
		jogadores.get(jogada).pagarFichas(ficha,quantidade); 
		adicionarAMontante(jogadores.get(jogada),ficha,quantidade);
	}
	
	//Jogador atual recebe
	public void receber(String ficha, int quantidade) {
		jogadores.get(jogada).receberFichas(ficha,quantidade);
	}
	
	//Jogador especifico recebe
	public void receberJogador(Jogador j,String ficha, int quantidade) {
		j.receberFichas(ficha,quantidade);
	}
	
	public void pedirStand() {
		jogadores.get(jogada).putStand();
	}
	
	public void pedirHit() {
		jogadores.get(jogada).hit(baralho.pegarCarta(),0);
	}
	
	public void ativarDouble() {
		// @ Ze
//		for(Jogador j : jogadores) {
//			if(j.fichasTotalJogador()>=jogadorAposta.get(j.getNomeJogador())) {
//				j.putDobrar();
//			}
//		}
	}
	
	public void pedirDouble() {
		// @ Ze
//		if(jogadores.get(jogada).checkDobrar()) {
//			jogadores.get(jogada).dobrar(jogadorAposta.get(jogadores.get(jogada).getNomeJogador()));
//			jogadores.get(jogada).recebeCarta(baralho.pegarCarta(),0);
//		}
	}
		
	// .... Metodos para cada possivel interação
	
	
	//
	//Metodos de controle partida  [controla a partida]
	//
	
	
	//Zera o montade
	private void zerarMontante() {
        jogadorAposta.clear();
    }
	
	//adiciona um valor da aposta de determinado montante do jogador
	private void adicionarAMontante(Jogador j, String ficha, int quantidade){
		if(!jogadorAposta.containsKey(j.getNomeJogador())) {
			Map<String, Integer> novoMapa = new HashMap<String, Integer>();
			novoMapa.put(ficha, quantidade);
	
			jogadorAposta.put(j.getNomeJogador(),novoMapa);
			jogadorAposta.get(j.getNomeJogador()).put(ficha, quantidade);
			
		}else {
			jogadorAposta.get(j.getNomeJogador()).computeIfPresent(ficha, (k, v) -> v + quantidade);
		}
	}
	
	//remove um valor da aposta de determinado montante do jogador
	@SuppressWarnings("unused")
	private void removerAMontante(Jogador j, String ficha, int quantidade){
		jogadorAposta.get(j.getNomeJogador()).computeIfPresent(ficha, (k, v) -> v + quantidade);
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
	private void reiniciarBaralho() {
		this.baralho = new Baralho(4);
	}
	
	
	
	//
	//Implementa modelo singleton
	//
	private void reinicar() {
		reiniciarBaralho();
		this.dealer = new Dealer("0x00");
		this.jogadores = new ArrayList<>();
		this.jogada = 0;
		this.rodada = 0;
		this.jogadorAposta.clear();
		distribuirCartas();
	}
	
	private String gerarID() {
		return ""+Instant.now().getEpochSecond();
	}
	
	public String conferirId() {
		return ID;
	}
	
	
	private static ModelAPI instanciaUnica;
	
	private ModelAPI() {
		reiniciarBaralho();
		this.jogadorAposta.clear();
		this.dealer = new Dealer("0x00");
		this.jogadores = new ArrayList<>();
		this.jogada = 0;
		this.ID = gerarID();
		
	}
	
	public static synchronized ModelAPI iniciar() {
		if(instanciaUnica == null)
			instanciaUnica = new ModelAPI();
		instanciaUnica.reinicar();
		return instanciaUnica;
	}
	
	//
	// OBSERVADO
	//
	public static final List<Observador> observadores = new ArrayList<>();
	
	@Override
	public void adicionarObservador(Observador o) {
		observadores.add(o);
	}
	
	@Override
	public void notificar(Object obj,int idAction) {
		observadores.forEach((o) -> o.executar(obj,idAction));
	}
	
}
