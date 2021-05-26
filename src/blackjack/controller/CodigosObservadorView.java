package blackjack.controller;
import java.util.ArrayList;

//IDS 100-200
public enum CodigosObservadorView {
	
	BOTAO_COMECAR_TELA_INICIAL(100,ArrayList.class),
	BOTAO_HIT_JOGADOR(101,String.class),
	BOTAO_STAND_JOGADOR(102,String.class),
	BOTAO_DOUBLE_JOGADOR(103,String.class),
	BOTAO_SPLIT_JOGADOR(104,String.class),
	TESTE(-1,String.class);
	
	public int valor;
	public Class<?> classe;
	
	CodigosObservadorView(int valor,Class<?> classe){
		this.valor=valor;
		this.classe=classe;
	}
}
