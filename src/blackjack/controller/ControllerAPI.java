package blackjack.controller;

import java.util.ArrayList;
import java.util.List;

import blackjack.model.ModelAPI;
import blackjack.view.GUIService;
import blackjack.view.Observador;
import blackjack.view.TelaJogador;

public class ControllerAPI implements Observador{
	private ModelAPI api = ModelAPI.iniciar();
	private GUIService view = GUIService.iniciar(api);

	
	public void start() throws Exception {
		view.adicionarObservador(this);
		view.exibir();
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
				telaJogadorHit(obj);
		}
		if(CodigosObservadorView.BOTAO_STAND_JOGADOR.valor==ID) {
			if(CodigosObservadorView.BOTAO_STAND_JOGADOR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaJogadorStand(obj);
		}
		if(CodigosObservadorView.BOTAO_DOUBLE_JOGADOR.valor==ID) {
			if(CodigosObservadorView.BOTAO_DOUBLE_JOGADOR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaJogadorDouble(null);
		}
		if(CodigosObservadorView.BOTAO_SPLIT_JOGADOR.valor==ID) {
			if(CodigosObservadorView.BOTAO_SPLIT_JOGADOR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaJogadorSplit(null);
		}
		if(CodigosObservadorView.BOTAO_APOSTA_INICIAL.valor == ID) {
			if(CodigosObservadorView.BOTAO_APOSTA_INICIAL.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaBancaAposta(obj);
		}
		if(CodigosObservadorView.BOTAO_APOSTA_INCIAL_REALIZAR.valor == ID) {
			if(CodigosObservadorView.BOTAO_APOSTA_INCIAL_REALIZAR.classe != obj.getClass())
				System.out.print("[ERRO][Controller] Classe passada no metodo executar nao corresponde ao correto, foi passado:"+obj.getClass());
			else
				telaBancaApostainicialrealizar(obj);
		}
		
	}
	
	
	//CALLBACKS de Botoes VIEW
	private void telaInicialComecarCallback(ArrayList<String> jogObj){
		 List<String> jogadores = new ArrayList<>();
		try {
			jogadores = jogObj;
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(1);
		}
		System.out.print("Passa 3; "+jogadores);
		view.telaInicialCriarJogadores(jogadores,this);
	}
	
	private void telaJogadorHit(Object nome) {
		api.pedirHit(nome);
	}
	
	private void telaJogadorStand(Object nome) {
		api.pedirStand(nome);
	}
	
	private void telaJogadorDouble(String s) {
		return;
	}
	
	private void telaJogadorSplit(String s) {
		return;
	}
	
	//Banca
	
	private void telaBancaAposta(Object s) {
		api.adicionaApostaInicial(s);
	}
	
	
	private void telaBancaApostainicialrealizar(Object s) {
		api.finalizaApostaInicial(s);
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
