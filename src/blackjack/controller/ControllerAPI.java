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
				telaJogadorHit(null);
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
	
	private void telaJogadorHit(String s) {
		return;
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
