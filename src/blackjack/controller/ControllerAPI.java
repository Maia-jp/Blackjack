package blackjack.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackjack.model.ModelAPI;
import blackjack.view.GUIService;
import blackjack.view.Observador;
import blackjack.view.TelaJogador;

public class ControllerAPI implements Observador{
	private ModelAPI api = ModelAPI.iniciar();
	private GUIService view = GUIService.iniciar(api);
	private Carteira carteira = new Carteira();
	List<String> jogadores=new ArrayList<>();
	
	public void start() throws Exception {
		view.adicionarObservador(this);
		view.exibir();
		//view.exibirOpcoes();
	}
	
	
	//LOGICA OBSERVADOR
	public void executar(Object obj, int ID) {
		
		if(CodigosObservadorView.BOTAO_COMECAR_TELA_INICIAL.valor==ID) {
			if(CodigosObservadorView.BOTAO_COMECAR_TELA_INICIAL.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaInicialComecarCallback((ArrayList<String>)obj);
		}
		if(CodigosObservadorView.BOTAO_HIT_JOGADOR.valor==ID) {
			if(CodigosObservadorView.BOTAO_HIT_JOGADOR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaJogadorHit((String)obj);
		}
		if(CodigosObservadorView.BOTAO_STAND_JOGADOR.valor==ID) {
			if(CodigosObservadorView.BOTAO_STAND_JOGADOR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaJogadorStand((String)obj);
		}
		if(CodigosObservadorView.BOTAO_DOUBLE_JOGADOR.valor==ID) {
			if(CodigosObservadorView.BOTAO_DOUBLE_JOGADOR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaJogadorDouble((String)obj);
		}
		if(CodigosObservadorView.BOTAO_SPLIT_JOGADOR.valor==ID) {
			if(CodigosObservadorView.BOTAO_SPLIT_JOGADOR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaJogadorSplit((String)obj);
		}
		if(CodigosObservadorView.BOTAO_APOSTA_INICIAL.valor == ID) {
			if(CodigosObservadorView.BOTAO_APOSTA_INICIAL.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaBancaAposta((String)obj);
		}
		if(CodigosObservadorView.BOTAO_APOSTA_INCIAL_REALIZAR.valor == ID) {
			if(CodigosObservadorView.BOTAO_APOSTA_INCIAL_REALIZAR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaBancaApostainicialrealizar(obj);
		}
		if(CodigosObservadorView.BOTAO_SALVAR_TELA_DEALER.valor == ID) {
			if(CodigosObservadorView.BOTAO_SALVAR_TELA_DEALER.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				view.exibirOpcoes();
		}
		if(CodigosObservadorView.BOTAO_GERARCARTEIRA_TELA_OPCOES.valor == ID) {
			if(CodigosObservadorView.BOTAO_GERARCARTEIRA_TELA_OPCOES.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				opcoesGerarCarteira((String)obj);
		}
		if(CodigosObservadorView.BOTAO_REMOVE_FICHA_APOSTA.valor == ID) {
			if(CodigosObservadorView.BOTAO_REMOVE_FICHA_APOSTA.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaBancaRemoveFichaPilha(obj);
		}
    
		if(CodigosObservadorView.BOTAO_SURRENDER_JOGADOR.valor == ID) {
			if(CodigosObservadorView.BOTAO_SURRENDER_JOGADOR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaJogadorSurrender((String)obj);
		}
		if(CodigosObservadorView.BOTAO_QUIT_JOGADOR.valor == ID) {
			if(CodigosObservadorView.BOTAO_QUIT_JOGADOR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaJogadorQuit((String)obj);
		}
		if(CodigosObservadorView.BOTAO_CLEAR.valor == ID) {
			if(CodigosObservadorView.BOTAO_CLEAR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaBancaClear((Boolean)obj);
		}
		if(CodigosObservadorView.BOTAO_NOVA_RODADA.valor == ID) {
			if(CodigosObservadorView.BOTAO_NOVA_RODADA.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				novaRodadaAcionada();
		}
	
		if(CodigosObservadorView.BOTAO_GERARCARREGAR_TELA_OPCOES.valor== ID) {
			if(CodigosObservadorView.BOTAO_GERARCARREGAR_TELA_OPCOES.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				carregarCarteira((String[]) obj);
		}
		
		if(CodigosObservadorView.BOTAO_SALVAR_TELA_OPCOES.valor== ID) {
			if(CodigosObservadorView.BOTAO_SALVAR_TELA_OPCOES.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				salvar(((String) obj));
		}
	} 
	
	
	//CALLBACKS de Botoes VIEW
	private void telaInicialComecarCallback(ArrayList<String> jogObj){
		try {
			jogadores = jogObj;
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(1);
		}
		System.out.print("Passa 3; "+jogadores);
		view.telaInicialCriarJogadores(jogadores,this);
		api.exibeNomeJogadores();
		api.notificaViewInfoJogadores();
	}
	
	private void telaJogadorHit(String infoJogador) {
		api.pedirHit(infoJogador);
	}
	
	private void telaJogadorStand(String infoJogador) {
		System.out.println("CHEGOU AQUI " + infoJogador);
		api.pedirStand(infoJogador);
	}
	
	private void telaJogadorDouble(String infoJogador) {
		api.pedirDouble(infoJogador);
	}
	
	private void telaJogadorSplit(String indiceJogador) {
		if(api.pedirSplit(Integer.parseInt(indiceJogador))) {
			view.telaSplitVisivel(Integer.parseInt(indiceJogador));
		}else {
			System.out.println("N�O FOI POSSIVEL ACIONAR O SPLIT");
		}
	}
	
	private void telaJogadorSurrender(String indiceJogador) {
		api.pedirSurrender(Integer.parseInt(indiceJogador));
	}
	
	private void telaJogadorQuit(String indiceJogador) {
		  view.telaQuitVisivel(Integer.parseInt(indiceJogador));
		  api.pedirQuit(Integer.parseInt(indiceJogador));
		  
	}
	
	//Banca
	
	private void telaBancaAposta(String s) {
		api.adicionaApostaInicial(s);
	}
	
	
	private void telaBancaApostainicialrealizar(Object s) {
		api.finalizaApostaInicial();
	}
	
	private void telaBancaClear(Boolean clear) {
		api.clearJogadorRodada();
	}
	
	//Opcoes
	private void opcoesGerarCarteira(String jogador) {
		view.opcoesGerarCarteira(carteira.gerarCarteira(
				api.jogadorNomeCarteiraTotal(jogador)
				, jogador));
	}
	
	private void telaBancaRemoveFichaPilha(Object s) {
		api.removeFichaPilha();
	}
	
	private void novaRodadaAcionada() {
		api.novaRodada();
		view.telaSplitInvisivel();
	}

	private void carregarCarteira(String[] s) {
		int c =  carteira.validarCarteira(s[0],s[1]);
		if(c != -1) {
			api.carregarCarteira(s[1],c);
		}else {
			view.opcoesErroGerarCarteira();
		}
	}
	
	private void salvar(String dir) {
		List<String> jogadores = api.listaNomeJogadores();
		HashMap<String,Integer> dinheiro = api.dinheiroJogadoresComNome();
		int rodada = api.getRodada();
		
		SavingUtilities saveUtil = new SavingUtilities();
		saveUtil.gerarModeloSalvar(jogadores, dinheiro, rodada);
		saveUtil.salvar(dir, 
				String.valueOf(Instant.now().getEpochSecond()));
		
	}
	
	//
	//Singleton
	//
	private static ControllerAPI instanciaUnica;
	
	private ControllerAPI() {
		}
	
	public static synchronized ControllerAPI iniciar() {
		if(instanciaUnica == null)
			instanciaUnica = new ControllerAPI();
	
		return instanciaUnica;
	}
	
}
