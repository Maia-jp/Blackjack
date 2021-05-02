package blackjack.model;

import java.util.ArrayList;

class Dealer extends Jogador{
	private ArrayList<Carta> cartasDealer;
	
	public Dealer(String nome) {
		super(nome);
		super.setNomeJogador(nome);
		this.cartasDealer = new ArrayList<>();
		//TESTADO
	}
	
	public String getNomeJogador() {
		return super.getNomeJogador();
		//TESTADO
	}
	
	//adiciona cartas na m�o do Dealer
	void receberCarta(Carta a) {
		this.cartasDealer.add(a);
		//TESTADO
	}
	
	//Retorna quantas cartas o delaer possui.
	int qtdCartasDealer() {
		return this.cartasDealer.size();
		//TESTADO
	}
	
	//remove as cartas da m�o
	void limpaMao() {
		this.cartasDealer.clear();
		//TESTADO
	}
	
	//retorna pra mesa as cartas do Dealer
	ArrayList<Carta> verificarMao(){
		return this.cartasDealer;
		//TESTADO
	}
	
	int valorMao() {
		int val = contagem();
		return val;
		//TESTADO
	}
	
	//verifica qual valor o dealer vai escolher do �s
	private int verificaValorAs(int to)	
	/*As ESCOLHAS DO DEALER SOBRE O �S AINDA EST�O SIMPLES 
	 * sera implementado uma estrategia 
	melhor na proxima itera��o */
	{
		//verifica se o total vai ser maior ou menor que 21 com a escolha do �s
		if(to + 11 > 21) {
			 to += 1;
		}
		else {
			to += 11;
		}
		return to;
	//TESTADO
	}
	
	//conta as cartas na m�o do Dealer
	private int contagem() {
		int total = 0, contador = 0, flag = 0;
		//conta as cartas na m�o do dealer, para bolar a estrategia
		for(int i = 0; i < this.cartasDealer.size(); i++) {
			contador = this.cartasDealer.get(i).getValor();
			
			if (contador != -1){
				total += contador;
			}
			else {
				flag += 1;
			}
		}
		if(flag != 0) {
			for(int i = 0; i < flag; i++) {
				total = verificaValorAs(total);
			}
		}
		
		return total;
		//TESTADO
	}
	
	//vai definir a estrategia do Dealer______DEVE SER CHAMADO PELA MESA_________
	private int estrategia() {
		int total = contagem();
		
		if(total >= 17) {
			//significa que n�o pode pegar mais cartas
			return 1;
		}
		else{
			// significa que mais uma Carta
			return 2;
		}
		//TESTADO
	}
	
	boolean blackJackDealer() {
		boolean blackjack = false;
		if (this.cartasDealer.size() == 2 && contagem() == 21) {
			blackjack = true;
		}
		return blackjack;
		//TESTADO
	}
	
	int checkEstrategia() {
		return estrategia();
		//TESTADO
	}
}

