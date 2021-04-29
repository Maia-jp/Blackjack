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
	void setNomeJogador(String n);
	
	//Recebe o nome do jogador (dealer tera nome "0x00")
	String getNomeJogador();
	
	
	
	//
	//Metodos relacionados a cartas
	//
	
	//Recebe 1 carta
	void receberCarta(Carta c);
	
	//Retira todas as cartas da mão
	void limpaMao();
	
	//Olha a mão que esta na carta dos jogadores
	ArrayList<Carta> verificarMao();
	
	//Verifica o valor da mao do jogador
	int valorMao();
	
	//
	//Metodos relacionados a fichas
	//
	void pagar(int n);
	
	void receber(int n);
	
	//Retorna o numero de fichas;
	int fichas();
	
	//
	//Metodos relacionados a jogadas possiveis
	//
	
	//Coloca em Slipt
	void putSlipt();
	
	//Tira do split
	void clearSlipt();
	
	//Coloca o Stand
	void putStand();
	
	//Tira do Stand
	void clearSlipt();
	
	//Verifica se o jogador esta em slipt
	boolean checkSlipt();
	
	//Verificar so o jogador esta em stand nessa jogada
	boolean checkStand();
	
	
	
}
