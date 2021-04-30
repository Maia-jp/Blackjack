package blackjack.model;

import java.util.ArrayList;

// Todos os jogadores da mesa (Dealer e Jogador) possuem metodos em comuns que serão chamados internamente
// na ModelAPI, essa interface garante que as classes implementadas sigam o mesmo padrão
//
interface InterfaceInteracaoJogadores {
	
	//
	//Metodos relacionados ao jogador
	//

	
	//Seta um nome para o jogador (dealer tera nome "0x00")
	void setNomeJogador(String n); // foi
	
	//Recebe o nome do jogador (dealer tera nome "0x00")
	String getNomeJogador(); // foi
	
	
	
	//
	//Metodos relacionados a cartas
	//
	
	//Recebe 1 carta
	void receberCarta(Carta c); // foi
	
	//Retira todas as cartas da mão
	void limpaMao(); // foi
	
	//Olha a mão que esta na carta dos jogadores
	ArrayList<Carta> verificarMao(); // foi
	
	//Verifica o valor da mao do jogador
	int valorMao(); // foi
	
	//
	//Metodos relacionados a fichas
	//
	void pagar(int n);
	
	void receber(int n); // foi
	
	//Retorna o numero de fichas;
	int fichas(); // foi
	
	//
	//Metodos relacionados a jogadas possiveis
	//
	
	//Coloca em Slipt
	void putSlipt(); // foi
	
	//Tira do split
	void clearSlipt(); // foi
	
	//Coloca o Stand
	void putStand(); // foi
	
	//Tira do Stand
	void clearStand(); // foi
	
	//Verifica se o jogador esta em slipt
	boolean checkSlipt(); // foi
	
	//Verificar so o jogador esta em stand nessa jogada
	boolean checkStand(); // foi
	
	
	
}
