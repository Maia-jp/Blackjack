/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
package blackjack.model;

import java.util.ArrayList;

class Dealer extends Jogador{
	private ArrayList<Carta> cartasDealer;
	
	 Dealer(String nome) {
		super(nome);
		super.setNomeJogador(nome);
		this.cartasDealer = new ArrayList<>();
	}
	
	 String getNomeJogador() {
		return super.getNomeJogador();
	}
	
	//adiciona cartas na mao do Dealer
	void receberCarta(Carta a) {
		this.cartasDealer.add(a);
	}
	
	//Retorna quantas cartas o delaer possui.
	int qtdCartasDealer() {
		return this.cartasDealer.size();
	}
	
	//remove as cartas da mao
	void limpaMao() {
		this.cartasDealer.clear();
	}
	
	//retorna pra mesa as cartas do Dealer
	ArrayList<Carta> verificarMao(){
		return this.cartasDealer;
	}
	
	int valorMao() {
		int val = contagem();
		return val;
	}
	
	//verifica qual valor o dealer vai escolher do as
	private int verificaValorAs(int to){
		//verifica se o total vai ser maior ou menor que 21 com a escolha do �s
		if(to + 11 > 21) {
			 to += 1;
		}
		else {
			to += 11;
		}
		return to;
	}
	

	private int contagem() {
		int total = 0, contador = 0, flag = 0;
		//conta as cartas na mao do dealer, para bolar a estrategia
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

	}
	
	
	private int estrategia() {
		int total = contagem();
		
		if(total >= 17) {
			//significa que nao pode pegar mais cartas
			return 1;
		}
		else{
			// significa que mais uma Carta
			return 2;
		}
	}
	
	boolean blackJackDealer() {
		if((this.qtdCartasDealer() == 2 && this.valorMao() == 21)){
			return true;
		}
		return false;
	}
	
	int checkEstrategia() {
		return estrategia();
	}
}

