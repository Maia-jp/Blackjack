package blackjack.model;

import blackjack.controller.CodigosObservador;
import blackjack.controller.SaveDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import blackjack.controller.CodigosObservador;
import blackjack.view.Observador;
import blackjack.view.TelaJogador;

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
	
	//Clear nÃ£o participa da rodada
	private boolean[] clear = {true, true, true, true};
	private boolean[] teveBlackJack = {false, false, false, false};
	private boolean[] quit = {true, true, true, true};
	private int[] apostaAdicional = {0,0,0,0};
	//Variaveis necessarias para aposta incial
	private boolean ifOkApostaInicial;
	private Map<String, Integer> carteiraJogadorApostaInicial = new HashMap<String, Integer>();
	private int valorApostaInicial = 0;
	private Stack<String> pilhaApostaInicial = new Stack<String>();
	
	public void confereGanhadores() {
		List<String[]> resultadosFinais = new ArrayList<String[]>();
		String[] resultadosJogador;
		String result =  new String();
		String lucro =  new String();
		for(Jogador j: jogadores) {
			if(clear[jogadores.indexOf(j)] && quit[jogadores.indexOf(j)]) {
				if(j.getMaoJogador(0).isEmpty()) {
					if(dealer.blackJackDealer() == false) {
						result = "RESULTADO FOI DE UMA RENDICAO PARA O JOGADOR: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI‰ DE: " + apostaDoMontante(jogadores.indexOf(j))/2;
						System.out.println(result);
						System.out.println(lucro);
						j.receberAposta(apostaDoMontante(jogadores.indexOf(j))/2);
					}else if (dealer.blackJackDealer() == true) {
						result = "DEALER POSSUI BLACKJACK, JOGADOR NAO PODE SE RENDER:" + j.getNomeJogador();
						lucro = "SEU LUCRO FOI ZERO";
						System.out.println(result);
						System.out.println(lucro);
					}
					resultadosJogador = new String[] {result, lucro};
				}else if(!j.getMaoJogador(1).isEmpty()) {
					if(j.valorMao(0)>21 && (j.valorMao(1)>21) ) {
						result = "VOCE QUEBROU A MAO: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI ZERO";
						System.out.println(result);
						System.out.println(lucro);
					}else if(dealer.valorMao()>21 && (j.valorMao(0)<=21 || j.valorMao(1)<=21)) {
						result = "DEALER QUEBROU A MAO: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI DE: " + (apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1;
						System.out.println(result);
						System.out.println(lucro);
						j.receberAposta((apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1);		
					}else if((j.valorMao(0)>dealer.valorMao()) && dealer.valorMao()<=21 && j.valorMao(0)<=21) {
						result = "RESULTADO FOI UMA VITORIA ORDINARIA PARA O JOGADOR: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI DE: " + (apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1;
						System.out.println(result);
						System.out.println(lucro);
						j.receberAposta((apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1);
					}else if((j.valorMao(1)>dealer.valorMao()) && dealer.valorMao()<=21 && j.valorMao(1)<=21) {
						result = "RESULTADO FOI UMA VITORIA ORDINARIA PARA O JOGADOR: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI DE: " + (apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1;
						System.out.println(result);
						System.out.println(lucro);
						j.receberAposta((apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1);
					}else if((dealer.valorMao()>j.valorMao(0)) && dealer.valorMao()<=21 && j.valorMao(0)<=21) {
						result = "TOTAL DE PONTOS DO DEALER FOI MAIOR DO QUE DO JOGADOR: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI ZERO";
						System.out.println(result);
						System.out.println(lucro);
					}else if((dealer.valorMao()>j.valorMao(1)) && dealer.valorMao()<=21 && j.valorMao(1)<=21) {
						result = "TOTAL DE PONTOS DO DEALER FOI MAIOR DO QUE DO JOGADOR: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI ZERO";
						System.out.println(result);
						System.out.println(lucro);
					}else if(j.valorMao(0)==dealer.valorMao() || j.valorMao(1)==dealer.valorMao()) {
						result = "RESULTADO FOI UM PUSH PARA O JOGADOR: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI ZERO";
						System.out.println(result);
						System.out.println(lucro);
					}
					resultadosJogador = new String[] {result, lucro};
				}else {
					if(j.valorMao(0)>21) {
						result = "VOCÃŠ QUEBROU A MAO: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI ZERO";
						System.out.println(result);
						System.out.println(lucro);
					}else if((j.blackjack()) && (dealer.blackJackDealer() == false)) {
						result = "RESULTADO FOI UM BLACKJACK PARA O JOGADOR: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI DE: " + (int)(apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1.5;
						System.out.println(result);
						System.out.println(lucro);
						double tmp = (apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1.5;
						j.receberAposta((int)tmp);
					}else if(dealer.valorMao()>21 && j.valorMao(0)<=21 ) {
						result = "DEALER QUEBROU A MAO: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI DE: " + (apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1;
						System.out.println(result);
						System.out.println(lucro);
						j.receberAposta((apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1);
					}else if((j.valorMao(0)>dealer.valorMao()) && dealer.valorMao()<=21) {
						result = "RESULTADO FOI UMA VITORIA ORDINARIA PARA O JOGADOR: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI DE: " + (apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1;
						System.out.println(result);
						System.out.println(lucro);
						j.receberAposta((apostaDoMontante(jogadores.indexOf(j))+apostaAdicional[jogadores.indexOf(j)])*1);
					}else if((dealer.valorMao()>j.valorMao(0)) && dealer.valorMao()<=21) {
						result = "TOTAL DE PONTOS DO DEALER FOI MAIOR DO QUE DO JOGADOR: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI ZERO";
						System.out.println(result);
						System.out.println(lucro);
					}else if(j.valorMao(0)==dealer.valorMao() || (j.blackjack() && dealer.blackJackDealer())) {
						result = "RESULTADO FOI UM PUSH PARA O JOGADOR: " + j.getNomeJogador();
						lucro = "SEU LUCRO FOI ZERO";
						System.out.println(result);
						System.out.println(lucro);
					}
					resultadosJogador = new String[] {result, lucro};
				}
				resultadosFinais.add(resultadosJogador);
			}
		}
		setClear();
		notificar(resultadosFinais, CodigosObservador.INFO_RESULTADO_FINAL.valor);
		zerarMontante();
		notificar(true, CodigosObservador.BOTAO_NOVA_RODADA_OK.valor);
		
	}
	
	private void confereBlackJackGeral() {
		//Fazer Testes Unitarios
		int i = 0;
		for(Jogador j: jogadores) {
			if(j.blackjack()) {
				teveBlackJack[i] = true;
			}
			i++;
		}
	}
	
	//ComeÃƒÆ’Ã‚Â§a uma rodada
	public void novaRodada() {

		//verificarSaldoJogador();
		
        //Incrimenta em 1 a rodada
        rodada++;
        
        //Coloca em 0 a jogada
        jogada = 0;
        //Jogada Dealer = 0
        jogadaDealer = 0;
        for(Jogador j :jogadores ) {
            //Tira carta da mÃƒÂ¯Ã‚Â¿Ã‚Â½o de todos os jogadores
            j.limparMaoJogador(0);
            j.limparMaoJogador(1);
            j.clearDobrar(0);
            j.clearDobrar(1);
            j.clearHit(0);
            j.clearHit(1);
            j.clearStand(0);
            j.clearStand(1);
            j.clearSplit();
            j.clearSurrender();
            if(j.fichasTotalJogador() < 20) {
                quit[jogadores.indexOf(j)]=false;
            	notificar(j.getNomeJogador(),CodigosObservador.DINHEIRO_ZERO.valor);
            }
        }
        ifOkApostaInicial = true;
        valorApostaInicial = 0;
        setTeveBlackJack();
        setClear();
        setApostaAdicional();
		carteiraJogadorApostaInicial.clear();
		geracarteiraJogadorApostaInicial();
		pilhaApostaInicial.clear();
        dealer.limpaMao();
        zerarMontante();
        while(clear[jogada]==false) {
        	jogada=jogada + 1;
        }
        exibeNomeJogadores();
        notificaViewInfoJogadores();
        exibeCartasDealerJogadores();
        notificar(false, CodigosObservador.BOTAO_NOVA_RODADA_OK.valor);
        List<String[]> resultadosFinais = new ArrayList<String[]>();
        notificar(resultadosFinais, CodigosObservador.INFO_RESULTADO_FINAL.valor);
    }
	
	private void setTeveBlackJack() {
		teveBlackJack[0] = false;
		teveBlackJack[1] = false;
		teveBlackJack[2] = false;
		teveBlackJack[3] = false;
	}
	
	private void setClear() {
		clear[0] = quit[0];
        clear[1] = quit[1];
        clear[2] = quit[2];
        clear[3] = quit[3];
	}
	
	private void setApostaAdicional() {
		apostaAdicional[0] = 0;
		apostaAdicional[1] = 0;
		apostaAdicional[2] = 0;
		apostaAdicional[3] = 0;
	}
	
	
	//Verifica se existem jogadores que podem pedir cartas
	boolean checkJogadoresDisponiveis() {
		for(Jogador j :jogadores ) {
			if(!j.checkStand(0))
				return true;
			if(!j.checkStand(1))
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
		int i = 0;
		for(Jogador j : jogadores) {
			if(clear[i]) {
				j.recebeCarta(baralho.pegarCarta(),0);
				j.recebeCarta(baralho.pegarCarta(),0);
			}
			i++;
		}
		 dealer.receberCarta(baralho.pegarCarta());
		 dealer.receberCarta(baralho.pegarCarta());
		 
		 exibeCartasDealerJogadores();
		 exibeNomeJogadores();
		 confereBlackJackGeral();
	}
	
	private int dinheiroTotalJogadorAtual() {
		//FazerTesteUnitario
		int money = 0;
		for(Jogador j: jogadores) {
			if(j.getNomeJogador() == jogadorNome(jogada)) {
				money = j.fichasTotalJogador();
			}
		}
		return money;
	}
	
	private void exibeCartasDealerJogadores(){
		int []tCartasRoda = new int[2];
		 tCartasRoda[0] = valorDealerMao();
		 tCartasRoda[1] = this.jogadaDealer;
		 notificar(tCartasRoda,CodigosObservador.INFOS_DEALER.valor); //@ Ale , colocar padrÃƒÆ’Ã‚Â£o enum
		 
		 List<String> cartasDealer = dealerMao();
		 notificar(cartasDealer,CodigosObservador.CARTAS_DO_DEALER.valor);
		 
		 enviarInfoMaoJogador();
		 enviarInfoDinheiroJogador();
	}

	public void pedirHit(String infoJogador) {
		if(jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).checkStand(Integer.parseInt(String.valueOf(infoJogador.charAt(1))))==false && jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).valorMao(Integer.parseInt(String.valueOf(infoJogador.charAt(1))))<21){
			jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).hit(baralho.pegarCarta(),Integer.parseInt(String.valueOf(infoJogador.charAt(1))));
			jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).putDobrar(Integer.parseInt(String.valueOf(infoJogador.charAt(1))));
			jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).putSurrender();
			ativarBotoes(Integer.parseInt(String.valueOf(infoJogador.charAt(0))));
		}
		if(jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).valorMao(Integer.parseInt(String.valueOf(infoJogador.charAt(1))))>=21) {
			jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).putStand(Integer.parseInt(String.valueOf(infoJogador.charAt(1))));
			ativarBotoes(Integer.parseInt(String.valueOf(infoJogador.charAt(0))));
			if(!jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).getMaoJogador(1).isEmpty()) {
				if(jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).checkStand(0) && jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).checkStand(1)) {
					proximoJogador();
				}
			}else {
				proximoJogador();

			}
		}
		enviarInfoMaoJogador();
		enviarInfoMaoJogadorSplit();
	}

	public void pedirStand(String infoJogador) {
		jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).putStand(Integer.parseInt(String.valueOf(infoJogador.charAt(1))));
		jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).putDobrar(0);
		jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).putSurrender();
		ativarBotoes(Integer.parseInt(String.valueOf(infoJogador.charAt(0))));
		if(!jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).getMaoJogador(1).isEmpty()) {
			if(jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).checkStand(0) && jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).checkStand(1)) {
				proximoJogador();
			}
		}else {
			System.out.println("O VALOR DE JOGADA EH DE " + jogada);
			proximoJogador();

		}
	}
	
	public void pedirDouble(String infoJogador) {
		int total=apostaDoMontante(Integer.parseInt(String.valueOf(infoJogador.charAt(0))));
		if((jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).fichasTotalJogador()>=total && jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).checkDobrar(Integer.parseInt(String.valueOf(infoJogador.charAt(1))))==false) ) {
			jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).dobrar(total,Integer.parseInt(String.valueOf(infoJogador.charAt(1))));
			jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).hit(baralho.pegarCarta(), Integer.parseInt(String.valueOf(infoJogador.charAt(1))));
			jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).putSurrender();
			ativarBotoes(Integer.parseInt(String.valueOf(infoJogador.charAt(0))));
			apostaAdicional[Integer.parseInt(String.valueOf(infoJogador.charAt(0)))]=total+apostaAdicional[Integer.parseInt(String.valueOf(infoJogador.charAt(0)))];
			enviarInfoDinheiroJogador();
			enviarInfoMaoJogador();
			enviarInfoMaoJogadorSplit();
			if(!jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).getMaoJogador(1).isEmpty()) {
				if(jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).checkStand(0) && jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).checkStand(1)) {
					proximoJogador();
				}
			}else {
				proximoJogador();

			}
		}else {
			jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).putDobrar(0);
			jogadores.get(Integer.parseInt(String.valueOf(infoJogador.charAt(0)))).putDobrar(1);
			ativarBotoes(Integer.parseInt(String.valueOf(infoJogador.charAt(0))));
		}
	}
	
	public boolean pedirSplit(int indiceJogador) {
		int total=apostaDoMontante(indiceJogador);
		if(indiceJogador==-1) {
			return false;
		}
		if(jogadores.get(indiceJogador).fichasTotalJogador()>=total) {
			if(jogadores.get(indiceJogador).split()) {
				if(jogadores.get(indiceJogador).valorMao(0)+jogadores.get(indiceJogador).valorMao(1)==22) {
					jogadores.get(indiceJogador).hit(baralho.pegarCarta(), 0);
					jogadores.get(indiceJogador).hit(baralho.pegarCarta(), 1);
					jogadores.get(indiceJogador).putStand(0);
					jogadores.get(indiceJogador).putStand(1);
					jogadores.get(indiceJogador).putDobrar(0);
					jogadores.get(indiceJogador).putDobrar(1);
					jogadores.get(indiceJogador).putSurrender();
					jogadores.get(indiceJogador).apostar(total);
					apostaAdicional[indiceJogador]=total+apostaAdicional[indiceJogador];
					ativarBotoes(indiceJogador);
					enviarInfoDinheiroJogador();
					enviarInfoMaoJogador();
					enviarInfoMaoJogadorSplit();
					proximoJogador();
					return true;
				}else {
					jogadores.get(indiceJogador).hit(baralho.pegarCarta(), 0);
					jogadores.get(indiceJogador).hit(baralho.pegarCarta(), 1);
					jogadores.get(indiceJogador).apostar(total);
					apostaAdicional[indiceJogador]=total+apostaAdicional[indiceJogador];
					jogadores.get(indiceJogador).putSurrender();
					ativarBotoes(indiceJogador);
					enviarInfoDinheiroJogador();
					enviarInfoMaoJogador();
					enviarInfoMaoJogadorSplit();
					return true;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public void pedirSurrender(int indiceJogador) {
		jogadores.get(indiceJogador).surrender();
		ativarBotoes(indiceJogador);
		enviarInfoMaoJogador();
		proximoJogador();

	}
	
	public void pedirQuit(int indiceJogador) {			
		quit[indiceJogador]=false;
		proximoJogador();
	}
	
	public ArrayList<Integer> dinheiroJogadores(){
		ArrayList<Integer> listaDin = new ArrayList<Integer>();
		for(Jogador j : jogadores) {
			listaDin.add(j.fichasTotalJogador());
		}
		return listaDin;
	}
	
	
	public HashMap<String,Integer> dinheiroJogadoresComNome(){
		HashMap<String,Integer> jogadoresDinheiro = new HashMap<>();
		jogadores.forEach(j-> jogadoresDinheiro.put(j.getNomeJogador(), j.getTotalFichasJogador()));
		
		return jogadoresDinheiro;
	}
	
	
	public int apostaDoMontante(int indiceJogador) {
		int total=0;
		Set<String> chaves = jogadorAposta.get(jogadores.get(indiceJogador).getNomeJogador()).keySet();
		for(String chave : chaves) {
			total=jogadorAposta.get(jogadores.get(indiceJogador).getNomeJogador()).get(chave)*Integer.parseInt(chave)+total;
		}
		return total;
	}

	
	private void enviarInfoMaoJogador() {
		
		//-Envia mao para cada jogador
		 Map<String,List<String>> maoDosJogadores = new HashMap<String,List<String>>();
		 jogadores.forEach((j) -> maoDosJogadores.put(j.getNomeJogador(), jogadorMao(jogadores.indexOf(j),0)));
		 notificar(maoDosJogadores,CodigosObservador.MAO_DOS_JOGADORES.valor);
		 
		 //Envia valor da mao para cada jogador
		 Map<String,Integer> maoValorDosJogadores = new HashMap<String,Integer>();
		 jogadores.forEach((j) -> maoValorDosJogadores.put(j.getNomeJogador(),j.valorMao(0)));
		 notificar(maoValorDosJogadores,CodigosObservador.MAO_VALOR_DOS_JOGADORES.valor);
	}
	
	private void enviarInfoMaoJogadorSplit() {
		
		//-Envia mao para cada jogador
		 Map<String,List<String>> maoDosJogadores = new HashMap<String,List<String>>();
		 jogadores.forEach((j) -> maoDosJogadores.put(j.getNomeJogador(), jogadorMao(jogadores.indexOf(j),1)));
		 notificar(maoDosJogadores,CodigosObservador.MAO_DOS_JOGADORES_SPLIT.valor);
		 
		 //Envia valor da mao para cada jogador
		 Map<String,Integer> maoValorDosJogadores = new HashMap<String,Integer>();
		 jogadores.forEach((j) -> maoValorDosJogadores.put(j.getNomeJogador(),j.valorMao(1)));
		 notificar(maoValorDosJogadores,CodigosObservador.MAO_VALOR_DOS_JOGADORES_SPLIT.valor);
	}
	
	private void enviarInfoDinheiroJogador() {
		
		//Envia a quantia de dinheiro para cada jogador
		 Map<String,Integer> dinheiroJogador = new HashMap<String,Integer>();
		 jogadores.forEach((j) -> dinheiroJogador.put(j.getNomeJogador(),j.fichasTotalJogador()));
		 
		 notificar(dinheiroJogador,CodigosObservador.DINHEIRO_DOS_JOGADORES.valor);
	}
	
	
	//Pula para o proximo jogador 
	public void proximoJogador() {
		proximaJogada();
	}
	
	public void verificarSaldoJogador() {
		for(Jogador j : jogadores) {
			if(j.fichasTotalJogador()==0) {
				jogadores.remove(j);
			}
		}
	}
	
	//
	//Metodos de informacao [obtem informaÃƒÆ’Ã‚Â§ao dos jogadores e da partida]
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
	
	public int jogadorNomeCarteiraTotal(String s) {
		int index = 0;
		for(Jogador j: jogadores) {
			if(s.equals(j.getNomeJogador())) {
				return jogadorEspecificoCarteiraTotal(index);
			}
			index++;
		}
		return -1;
	}
	
	public Map<String, Integer> jogadorEspecificoCarteira(int n){
		return jogadores.get(n).getFichasJogador();

	}
	
	public boolean jogadorAtualCheckStand(int mao) {
		return jogadores.get(jogada).checkStand(mao);
	}
	
	public int numeroDeJogadores() {
		return jogadores.size();
	}
	
	public String jogadorNome(int n) {
		return jogadores.get(n).getNomeJogador();
	}
	
	public List<String> jogadorMao(int n,int mao) {
		List<String> cartasString =new ArrayList<>();
		for(Carta c: jogadores.get(n).getMaoJogador(mao)) {
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
	
	public List<String> listaNomeJogadores(){
		List<String> nomes = new ArrayList<>();
		jogadores.forEach(j-> nomes.add(j.getNomeJogador()));
		
		return nomes;
	}
	
	public int jogadorId(String nome) {
		int i =0;
		for(Jogador j: jogadores) {
			if(j.getNomeJogador()==nome)
				return i;
			i++;
		}
		return -1;
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
	
	public int getRodada() {
		return this.rodada;
	}
	

	
	//
	//Metodos de controle jogador  [controla o jogador e o dealer]
	//

	
	//Dealer age conforme as regras
	public void dealerAcao() {
		jogadaDealer++;
		
		int []tCartasRoda = new int[2];
		tCartasRoda[0] = valorDealerMao();
		tCartasRoda[1] = this.jogadaDealer;
		notificar(tCartasRoda,CodigosObservador.INFOS_DEALER.valor);
		
		if(dealer.checkEstrategia() == 2) {
			dealer.receberCarta(baralho.pegarCarta());
			
			tCartasRoda[0] = valorDealerMao();
			tCartasRoda[1] = this.jogadaDealer;
			notificar(tCartasRoda,CodigosObservador.INFOS_DEALER.valor);
			
			List<String> cartasDealer = dealerMao();
			notificar(cartasDealer,CodigosObservador.CARTAS_DO_DEALER.valor);
			dealerAcao();
		}
		else {
			confereGanhadores();
			}
	}
	
	//Jogador atual faz uma aposta
	public void apostar(String ficha, int quantidade,String nomeJogador) {
		for(Jogador j : jogadores) {
			if(j.getNomeJogador()==nomeJogador) {
				j.pagarFichas(ficha,quantidade); 
				adicionarAMontante(j,ficha,quantidade);			
			}
		}
	}
	
	//Jogador atual recebe
	public void receber(String ficha, int quantidade) {
		jogadores.get(jogada).receberFichas(ficha,quantidade);
	}
	
	//Jogador especifico recebe
	public void receberJogador(Jogador j,String ficha, int quantidade) {
		j.receberFichas(ficha,quantidade);
	}
		
	// .... Metodos para cada possivel interaÃƒÆ’Ã‚Â§ÃƒÆ’Ã‚Â£o
	
	
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
			jogadorAposta.get(j.getNomeJogador()).put(ficha, quantidade);
		}
	}
	
	private void verificaJogadaApostaInicial() {
		//Fazer Teste unitario
		if(this.jogada == numeroDeJogadores()) {	
			this.ifOkApostaInicial = false;
			carteiraJogadorApostaInicial.clear();
			pilhaApostaInicial.clear();
			geracarteiraJogadorApostaInicial();
			distribuirCartas();
			jogada = -1;
			proximoJogador();
			notificar(false, CodigosObservador.VERIFICA_APOSTA_INICAL_EFETUADA.valor);
		}
	}
	
	private void realizaApostaInicial() {
		//Fazer Teste Unitario
		Set<String> chaves = carteiraJogadorApostaInicial.keySet();
		for(Jogador j: jogadores ) {
			if(j.getNomeJogador() == jogadorNome(jogada) && clear[jogadores.indexOf(j)]) {
				for(String chave : chaves) {
					if(carteiraJogadorApostaInicial.get(chave) != 0) {
						apostar(chave, carteiraJogadorApostaInicial.get(chave)*(-1),j.getNomeJogador());
					}
				}
			}
		}
	}
	
	public void ativarBotoes(int indiceJogador) {
		String[][] vetorBtn = new String[7][2];
		for(Jogador j: jogadores ) {
			if(j.getNomeJogador() == jogadorNome(indiceJogador)) {
				vetorBtn[0][0]=String.valueOf(!j.checkHit(0));
				vetorBtn[1][0]=String.valueOf(!j.checkStand(0));
				if(j.getMaoJogador(0).size()==2 && j.fichasTotalJogador()>=apostaDoMontante(jogadores.indexOf(j))) {
					vetorBtn[2][0]=String.valueOf(!j.checkDobrar(0));
				}else if(j.getMaoJogador(0).size()>2) {
					vetorBtn[2][0]=String.valueOf(!j.checkDobrar(0));
				}else {
					System.out.println(j.checkDobrar(0));
					vetorBtn[2][0]=String.valueOf(j.checkDobrar(0));
				}
				if(j.checkSurrender()==false && j.getMaoJogador(0).get(0).getValor()==j.getMaoJogador(0).get(1).getValor() && j.getMaoJogador(0).size()==2 && j.fichasTotalJogador()>=apostaDoMontante(jogadores.indexOf(j))) {
					vetorBtn[3][0]=String.valueOf(!j.checkSplit());
				}else {
					vetorBtn[3][0]=String.valueOf(j.checkSplit());
				}
				vetorBtn[4][0]=String.valueOf(!j.checkSurrender());
				vetorBtn[5][0]=String.valueOf(indiceJogador);
				vetorBtn[6][0]=String.valueOf(!j.checkQuit());
				vetorBtn[0][1]=String.valueOf(!j.checkHit(1));
				vetorBtn[1][1]=String.valueOf(!j.checkStand(1));
				if(j.getMaoJogador(1).size()==2 && j.fichasTotalJogador()>=apostaDoMontante(jogadores.indexOf(j))) {
					vetorBtn[2][1]=String.valueOf(!j.checkDobrar(1));
				}else if(j.getMaoJogador(1).size()>2) {
					vetorBtn[2][1]=String.valueOf(!j.checkDobrar(1));
				}else {
					vetorBtn[2][1]=String.valueOf(j.checkDobrar(1));
				}
				if(j.checkSurrender()==false && j.getMaoJogador(0).get(0).getValor()== j.getMaoJogador(0).get(1).getValor() && j.getMaoJogador(0).size()==2 && j.fichasTotalJogador()>=apostaDoMontante(jogadores.indexOf(j))) {
					vetorBtn[3][1]=String.valueOf(!j.checkSplit());
				}else {
					vetorBtn[3][1]=String.valueOf(j.checkSplit());
				}
				vetorBtn[4][1]=String.valueOf(!j.checkSurrender());
				vetorBtn[5][1]=String.valueOf(indiceJogador);
				vetorBtn[6][1]=String.valueOf(!j.checkQuit());
			}
		}
		notificar(vetorBtn, CodigosObservador.BOTOES_JOGADORES.valor);
	}
	
	public void finalizaApostaInicial() {
		//Fazer Teste Unitario
		if(this.valorApostaInicial >= 20 && this.ifOkApostaInicial == true) {
			realizaApostaInicial();
			this.valorApostaInicial = 0;
			carteiraJogadorApostaInicial.clear();
			geracarteiraJogadorApostaInicial();
			notificar(false, CodigosObservador.VERIFICA_APOSTA_INICAL_EFETUADA.valor);
			if(clear[jogada]) {
				jogada=jogada+1;
			}
			verificaJogadaApostaInicial();
			while(jogada<jogadores.size() && clear[jogada]==false) {
	        	jogada=jogada + 1;
	        }
			verificaJogadaApostaInicial();
			exibeNomeJogadores();
			notificaViewInfoJogadores();
		}
	}
	
	private void notificaViewApostaInicial(String s) {
		//Fazer Teste Unitario
		String[] infoForTelaBanca = new String[] {s, String.valueOf(valorApostaInicial)};
		notificar(infoForTelaBanca, CodigosObservador.VERIFICA_APOSTA_INICIAL_OK_REPAINT.valor);
	}
	
	private void notificaViewNaoPodeApostar() {
		notificar(false, CodigosObservador.VERIFICA_APOSTA_INICIAL_OK_BOTAO_APOSTAR.valor);
	}
	
	public void clearJogadorRodada() {
		clear[jogada] = false;
		this.valorApostaInicial = 0;
		carteiraJogadorApostaInicial.clear();
		geracarteiraJogadorApostaInicial();
		notificar(false, CodigosObservador.VERIFICA_APOSTA_INICAL_EFETUADA.valor);
		this.jogada += 1;
		verificaJogadaApostaInicial();
		notificaViewNaoPodeApostar();
		exibeNomeJogadores();
		notificaViewApostaInicial(null);
	}
	
	
	public void notificaViewInfoJogadores() {
		int i = 0;
		List<String[]> infosJogadores = new ArrayList<String[]>();
		for(Jogador j: jogadores) {
			String[] infoJogador = new String[]{j.getNomeJogador(), String.valueOf(j.fichasTotalJogador())};
			infosJogadores.add(infoJogador);
		}
		notificar(infosJogadores, CodigosObservador.INFOS_JOGADORES.valor);
	}
	
	public void removeFichaPilha() {
		//Fazer Teste Unitario
		if(this.ifOkApostaInicial == true && !(pilhaApostaInicial.isEmpty())) {
			String vFicha = pilhaApostaInicial.pop();
			carteiraJogadorApostaInicial.replace(vFicha, carteiraJogadorApostaInicial.get(vFicha)+1);
			if(this.valorApostaInicial - Integer.parseInt(vFicha) >= 0) {
				this.valorApostaInicial -= Integer.parseInt(vFicha);
			}
			if(!(pilhaApostaInicial.isEmpty())) {
				vFicha = pilhaApostaInicial.pop();
				notificaViewApostaInicial(vFicha);
				pilhaApostaInicial.push(vFicha);
			}
			if (valorApostaInicial == 0) {
				notificaViewApostaInicial(null);
			}
			if(this.valorApostaInicial < 20) {
				
				notificaViewNaoPodeApostar();
			}
			notificaViewInfoJogadores();
		}
	}
	
	public void adicionaApostaInicial(String s) {
		//Fazer Teste Unitario
		if(this.ifOkApostaInicial == true) {
			System.out.println(dinheiroTotalJogadorAtual() - (this.valorApostaInicial+Integer.parseInt(s)));
			if((this.valorApostaInicial+Integer.parseInt(s) <= 100) && (dinheiroTotalJogadorAtual() - (this.valorApostaInicial+Integer.parseInt(s)) >= 0 )) {
				carteiraJogadorApostaInicial.replace(s, carteiraJogadorApostaInicial.get(s)-1);
				this.valorApostaInicial += Integer.parseInt(s);
				pilhaApostaInicial.push(s);
				notificaViewApostaInicial(s);
			}
			if(this.valorApostaInicial >= 20) {
				
				notificar(true, CodigosObservador.VERIFICA_APOSTA_INICIAL_OK_BOTAO_APOSTAR.valor);
			}
			if(this.valorApostaInicial < 20) {
				
				notificaViewNaoPodeApostar();
			}
			verificaJogadaApostaInicial();
			notificaViewInfoJogadores();
		}
	}
	
	public void exibeNomeJogadores() {
		if(jogada <= jogadores.size()-1) {
			notificar(jogadorNome(jogada), CodigosObservador.NOME_JOGADOR_ATUAL_APOSTA_INICIAL.valor);
		}
		else {
			notificar("DEALER", CodigosObservador.NOME_JOGADOR_ATUAL_APOSTA_INICIAL.valor);
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
		if(jogada<=jogadores.size()-1) {
			while(!(clear[jogada]) || teveBlackJack[jogada]) {
				jogada = jogada+1;
				if(jogada>jogadores.size()-1) {
					break;
				}
			}
		}
		if(jogada<=jogadores.size()-1) {
			System.out.println("ATIVOU O BOTÃO LEGAL");
			System.out.println("O VALOR DE JOGADA É " + jogada);
			ativarBotoes(jogada);
		}
		exibeNomeJogadores();
		if(jogada >= jogadores.size()) {
			jogada = 0;
			dealerAcao();
		}
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
	
	//Carrega o jogo
	public void carregarSalvamento(SaveDTO dto) {
		this.rodada = dto.rodada;
		List<String> novosJogadores = dto.jogadores;
		
		
		reinicar();
		novosJogadores.forEach(j-> adicionarJogador(j));
		
		for(Jogador j: jogadores) {
			j.setTotalFichasJogador(dto.dinheiro.get(j.getNomeJogador()));
		}
		
		
	}
	
	//Carrega uma carteira
	public void carregarCarteira(String nome, int carteira) {
		// @Ale @Ze , atualizar o dealer das novas infos referente ao jogador
		for(Jogador j: jogadores) {
			if(j.getNomeJogador()==nome) {
				j.setTotalFichasJogador(carteira);
				System.out.println(j.fichasTotalJogador());
			}
		}
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
