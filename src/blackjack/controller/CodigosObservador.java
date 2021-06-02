package blackjack.controller;

import java.util.HashMap;
import java.util.List;

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
	TESTE(-1,String.class);
	
	
	
	
	public int valor;
	public Class<?> classe;
	
	CodigosObservador(int valor,Class<?> classe){
		this.valor=valor;
		this.classe=classe;
	}

  
}
