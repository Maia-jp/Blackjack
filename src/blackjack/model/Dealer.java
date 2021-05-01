package blackjack.model;

import java.util.ArrayList;

class Dealer extends Jogador{
	private ArrayList<Carta> cartasDealer;
	
	public Dealer(String nome) {
		super(nome);
		super.setNomeJogador(nome);
		this.cartasDealer = new ArrayList<>();
		
	}
	
	public String getNomeJogador() {
		return super.getNomeJogador();
	}
	
	//adiciona cartas na mão do Dealer
	void receberCarta(Carta a) {
		this.cartasDealer.add(a);
	}
	
	//remove as cartas da mão
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
	
	//verifica qual valor o dealer vai escolher do Ás
	private int verificaValorAs(int to) {
		//verifica se o total vai ser maior ou menor que 21 com a escolha do Ás
		if(to + 11 > 21) {
			 to += 1;
		}
		else {
			to += 11;
		}
		return to;
	}
	
	//conta as cartas na mão do Dealer
	private int contagem() {
		int total = 0, contador = 0;
		//conta as cartas na mão do dealer, para bolar a estrategia
		for(int i = 0; i <= this.cartasDealer.size(); i++) {
			contador = this.cartasDealer.get(i).getValor();
			
			if (contador != -1){
				total += contador;
			}
			
			else {
				total = verificaValorAs(total);
			}
		}
		return total;
	}
	
	//vai definir a estrategia do Dealer______DEVE SER CHAMADO PELA MESA_________
	private int estrategia() {
		int total = contagem();
		
		if(total >= 17) {
			//significa que não pode pegar mais cartas
			return 1;
		}
		else{
			// significa que mais uma Carta
			return 2;
		}
	}
	
	boolean veBlackJackDealer() {
		boolean blackjack = false;
		if (this.cartasDealer.size() == 2 && contagem() == 21) {
			blackjack = true;
		}
		return blackjack;
	}
	
	int checkEstrategia() {
		return estrategia();
	}
}

