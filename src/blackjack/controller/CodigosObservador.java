package blackjack.controller;

import java.util.HashMap;
import java.util.List;

public enum CodigosObservador {

	CARTAS_DO_DEALER(1,List.class),
	MAO_DOS_JOGADORES(2,HashMap.class),
	MAO_VALOR_DOS_JOGADORES(3,HashMap.class),
	DINHEIRO_DOS_JOGADORES(4,HashMap.class),
	TESTE(-1,String.class);
	
	
	
	
	public int valor;
	public Class<?> classe;
	
	CodigosObservador(int valor,Class<?> classe){
		this.valor=valor;
		this.classe=classe;
	}

  
}