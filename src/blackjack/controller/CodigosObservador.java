/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
package blackjack.controller;

import java.util.HashMap;
import java.util.List;

//Codigos referentes as chamadas do modelo observer (IDs de 0 a 99 inclusivo)
public enum CodigosObservador {

	CARTAS_DO_DEALER(1,List.class),
	MAO_DOS_JOGADORES(2,HashMap.class),
	MAO_VALOR_DOS_JOGADORES(3,HashMap.class),
	DINHEIRO_DOS_JOGADORES(4,HashMap.class),
	INFOS_DEALER(10, int[].class),
	VERIFICA_APOSTA_INICIAL_OK_BOTAO_APOSTAR(11, Boolean.class),
	VERIFICA_APOSTA_INICIAL_OK_REPAINT(12, String[].class),
	VERIFICA_APOSTA_INICAL_EFETUADA(13, Boolean.class),
	MAO_DOS_JOGADORES_SPLIT(14,HashMap.class),
	MAO_VALOR_DOS_JOGADORES_SPLIT(15,HashMap.class),
	NOME_JOGADOR_ATUAL_APOSTA_INICIAL(16, String.class),
	INFOS_JOGADORES(17, List.class),
	BOTOES_JOGADORES(18, List.class),
	BOTAO_NOVA_RODADA_OK(19, Boolean.class),
	INFO_RESULTADO_FINAL(20, List.class),
	DINHEIRO_ZERO(21,String.class),
	BOTAO_CLEAR_VISIBLE_NOTVISIBLE(22, Boolean.class),
	TESTE(-1,String.class);

	
	
	public int valor;
	public Class<?> classe;
	
	CodigosObservador(int valor,Class<?> classe){
		this.valor=valor;
		this.classe=classe;
	}

  
}
