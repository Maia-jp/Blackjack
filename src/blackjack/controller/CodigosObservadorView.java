package blackjack.controller;
import java.util.ArrayList;

//IDS 100-200
public enum CodigosObservadorView {
	
	BOTAO_COMECAR_TELA_INICIAL(100,ArrayList.class),
	BOTAO_HIT_JOGADOR(101,String.class),
	BOTAO_STAND_JOGADOR(102,String.class),
	BOTAO_DOUBLE_JOGADOR(103,String.class),
	BOTAO_SPLIT_JOGADOR(104,String.class),
	BOTAO_APOSTA_INICIAL(105, String.class),
	BOTAO_APOSTA_INCIAL_REALIZAR(106, Boolean.class),
	BOTAO_SALVAR_TELA_DEALER(107, Boolean.class),
	BOTAO_REMOVE_FICHA_APOSTA(108, Boolean.class),
	BOTAO_GERARCARTEIRA_TELA_OPCOES(109, String.class),
	BOTAO_SURRENDER_JOGADOR(110, String.class),
	BOTAO_QUIT_JOGADOR(111, String.class),
	BOTAO_CLEAR(112,Boolean.class),
	BOTAO_NOVA_RODADA(113, Boolean.class),
	BOTAO_GERARCARREGAR_TELA_OPCOES(114, String[].class),
	TESTE(-1,String.class);
	
	public int valor;
	public Class<?> classe;
	
	CodigosObservadorView(int valor,Class<?> classe){
		this.valor=valor;
		this.classe=classe;
	}
}
