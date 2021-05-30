package blackjack.model;

import blackjack.controller.CodigosObservador;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import blackjack.controller.CodigosObservador;
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
	private int jogadaDealer;
	private int rodada;
	
	//Variaveis necessarias para aposta incial
	private boolean ifOkApostaInicial;
	private Map<String, Integer> carteiraJogadorApostaInicial = new HashMap<String, Integer>();
	private int valorApostaInicial = 0;
	
	//
	//Fun√ßoes principais de controle de partida
	//
	
	public void confereGanhadores() {
		
		for(Jogador j: jogadores) {
			if((j.blackjack()) && (dealer.blackJackDealer() == false)) {
				//j.receberFichas((int)(jogadorAposta.get(j.getNomeJogador())*1.5 + jogadorAposta.get(j.getNomeJogador())));
			}
			if((j.valorMao(0) > dealer.valorMao() && dealer.valorMao() < 21) || (j.blackjack() && dealer.blackJackDealer() == true)) {
				//j.receberFichas(jogadorAposta.get(j.getNomeJogador()));
			}
//			Proxima iteraÔøΩÔøΩo
//			if(j.checkRendicao() && dealer.blackJackDealer() == false)
//				j.receberFichas(jogadorAposta.get(j.getNomeJogador())/2);
		}
		
		zerarMontante();
		novaRodada();
	}
	
	//Come√ßa uma rodada
	public void novaRodada() {
        //Incrimenta em 1 a rodada
        rodada++;
        
        //Coloca em 0 a jogada
        jogada = 0;
        
        //Jogda Dealer = 0
        jogadaDealer = 0;
        for(Jogador j :jogadores ) {
            //Tira carta da mÔøΩo de todos os jogadores
            j.limparMaoJogador(0);;
            
            //Tira do double
            j.clearDobrar();
            
            //Tira do 
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
		 //-Envia mao do dealer para o dealer, valor total cartas e Jogada
		 
		 int []tCartasRoda = new int[2];
		 tCartasRoda[0] = valorDealerMao();
		 tCartasRoda[1] = this.jogadaDealer;
		 notificar(tCartasRoda,CodigosObservador.INFOS_DEALER.valor); //@ Ale , colocar padr√£o enum
		 
		 List<String> cartasDealer = dealerMao();
		 notificar(cartasDealer,CodigosObservador.CARTAS_DO_DEALER.valor);
		 
		 enviarInfoMaoJogador();
		 
		//Envia a quantia de dinheiro para cada jogador
		 Map<String,Integer> dinheiroJogador = new HashMap<String,Integer>();
		 jogadores.forEach((j) -> dinheiroJogador.put(j.getNomeJogador(),j.fichasTotalJogador()));
		 
		 notificar(dinheiroJogador,CodigosObservador.DINHEIRO_DOS_JOGADORES.valor);
		 
	}
	
	public void pedirHit(Object nome) {
		for(Jogador j : jogadores) {
			if(j.getNomeJogador()==nome){
				if(j.checkStand()==false) {
					j.hit(baralho.pegarCarta(),0);
				}else {
					System.out.println("STAND ATIVADO, LOGO HIT N√O PODE SER ACIONADO");
				}
			}
		}
		enviarInfoMaoJogador();
	}

	public void pedirStand(Object nome) {
		for(Jogador j : jogadores) {
			if(j.getNomeJogador()==nome) {
				j.putStand();
			}
		}
	}
	
	// fazer depois do alexandre fazer a funÁ„o para pegar a aposta inicialdos jogadores
	/*public void pedirDouble(Object nome) {
		for(Jogador j : jogadores) {
			if(j.getNomeJogador()==nome) {
				j.hit(baralho.pegarCarta(),0);
			}
		}
		enviarInfoMaoJogador();

	}*/
	
	private void enviarInfoMaoJogador() {
		
		//-Envia mao para cada jogador
		 Map<String,List<String>> maoDosJogadores = new HashMap<String,List<String>>();
		 jogadores.forEach((j) -> maoDosJogadores.put(j.getNomeJogador(), jogadorMao(jogadores.indexOf(j))));
		 notificar(maoDosJogadores,CodigosObservador.MAO_DOS_JOGADORES.valor);
		 
		 //Envia valor da mao para cada jogador
		 Map<String,Integer> maoValorDosJogadores = new HashMap<String,Integer>();
		 jogadores.forEach((j) -> maoValorDosJogadores.put(j.getNomeJogador(),j.valorMao(0)));
		 notificar(maoValorDosJogadores,CodigosObservador.MAO_VALOR_DOS_JOGADORES.valor);
	}
	
	//Pula para o proximo jogador 
	public void proximoJogador() {
		proximaJogada();
	}
	
	//
	//Metodos de informacao [obtem informa√ßao dos jogadores e da partida]
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
		jogadaDealer++;
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
	
	public void ativarDouble() {
		// @ Ze
//		for(Jogador j : jogadores) {
//			if(j.fichasTotalJogador()>=jogadorAposta.get(j.getNomeJogador())) {
//				j.putDobrar();
//			}
//		}
	}
		
	// .... Metodos para cada possivel intera√ß√£o
	
	
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
	
	//FUN«‘ES APOSTA INCIAL
	private void verificaJogadaApostaInicial() {
		//Fazer Teste Unitario
		if(this.jogada == numeroDeJogadores()) {	
			this.ifOkApostaInicial = false;
			this.jogada = 0;
			carteiraJogadorApostaInicial.clear();
			geracarteiraJogadorApostaInicial();
			notificar(false, CodigosObservador.VERIFICA_APOSTA_INICAL_EFETUADA.valor);
			distribuirCartas();
		}
	}
	
	private void realizaApostaInicial() {
		//Fazer Teste Unitario
		Set<String> chaves = carteiraJogadorApostaInicial.keySet();
		for(Jogador j: jogadores ) {
			if(j.getNomeJogador() == jogadorNome(jogada)) {
				for(String chave : chaves) {
					if(carteiraJogadorApostaInicial.get(chave) != 0) {
						adicionarAMontante(j, chave, carteiraJogadorApostaInicial.get(chave)*(-1));
						apostar(chave, carteiraJogadorApostaInicial.get(chave)*(-1));
					}
				}
			}
		}
	}
	
	public void finalizaApostaInicial(Object s) {
		//Fazer Teste Unitario
		if(this.valorApostaInicial >= 20) {
			realizaApostaInicial();
			this.valorApostaInicial = 0;
			carteiraJogadorApostaInicial.clear();
			geracarteiraJogadorApostaInicial();
			notificar(false, CodigosObservador.VERIFICA_APOSTA_INICAL_EFETUADA.valor);
			this.jogada += 1;
			verificaJogadaApostaInicial();
		}
	}
	
	public void adicionaApostaInicial(Object s) {
		//Fazer Teste Unitario
		if(this.ifOkApostaInicial == true) {
			Map<String, Integer> carteiraJogadorAtual = jogadorEspecificoCarteira(this.jogada);
			if((carteiraJogadorAtual.get(s) + carteiraJogadorApostaInicial.get(s)) > 0) {
				
				carteiraJogadorApostaInicial.replace(s.toString(), carteiraJogadorApostaInicial.get(s)-1);
				this.valorApostaInicial += Integer.parseInt(s.toString());
				notificar(s.toString(), CodigosObservador.VERIFICA_APOSTA_INICIAL_OK_REPAINT.valor);
			
			}
			if(this.valorApostaInicial >= 20) {
				
				notificar(true, CodigosObservador.VERIFICA_APOSTA_INICIAL_OK_BOTAO_APOSTAR.valor);
			}
			if(this.valorApostaInicial < 20) {
				
				notificar(false, CodigosObservador.VERIFICA_APOSTA_INICIAL_OK_BOTAO_APOSTAR.valor);
			}
			verificaJogadaApostaInicial();
		}
	}
	
	private void geracarteiraJogadorApostaInicial() {
		//Fazer Teste Unitario
		carteiraJogadorApostaInicial.put("100", 0);
		carteiraJogadorApostaInicial.put("50", 0);
		carteiraJogadorApostaInicial.put("20", 0);
		carteiraJogadorApostaInicial.put("10", 0);
		carteiraJogadorApostaInicial.put("5", 0);
		carteiraJogadorApostaInicial.put("1", 0);
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
		this.jogadaDealer = 0;
		this.jogadorAposta.clear();
		this.ifOkApostaInicial = true;
		carteiraJogadorApostaInicial.clear();
		geracarteiraJogadorApostaInicial();
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
		this.jogadaDealer = 0;
		this.ifOkApostaInicial = true;
		this.ID = gerarID();
		geracarteiraJogadorApostaInicial();
		
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
