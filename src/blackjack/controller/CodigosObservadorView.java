/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
package blackjack.controller;
import java.util.ArrayList;

//Codigos referentes as chamadas do modelo observer (IDs de 100 a 200 inclusivo)
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
	BOTAO_SALVAR_TELA_OPCOES(115, String.class),
	BOTAO_CARREGAR_TELA_OPCOES(116, String.class),
	TESTE(-1,String.class);
	
	public int valor;
	public Class<?> classe;
	
	CodigosObservadorView(int valor,Class<?> classe){
		this.valor=valor;
		this.classe=classe;
	}
}
