package blackjack.model;

import java.util.ArrayList;

public class Dealer extends Jogador {
	private ArrayList<Carta> cartasDealer;
	private int flag;
	
	public Dealer() {
		super("dealer");
		this.cartasDealer = new ArrayList<>();
	}
	
	//adiciona cartas na mão do Dealer ______DEVE SER CHAMADO PELA MESA_________
	public void setCartaDealer(Carta a) {
		this.cartasDealer.add(a);
	}
	
	//retorna pra mesa as cartas do Dealer ____NÂO SEI SE VAI SER NECESSARIO_________
	public ArrayList<Carta> getListaCartas(){
		return this.cartasDealer;
	}
	
	//conta as cartas na mão do Dealer
	public int contagem() {
		int total = 0, contador = 0;
		flag = 0;
		//conta as cartas na mão do dealer, para bolar a estrategia
		for(int i = 0; i <= this.cartasDealer.size(); i++) {
			contador = this.cartasDealer.get(i).getValor();
			
			if (contador != -1){
				total += contador;
			}
			
			else {
				flag = contador;
			}
		}
		return total;
	}
	
	//vai definir a estrategia do Dealer______DEVE SER CHAMADO PELA MESA_________
	public String estrategia() {
		int total = contagem();
		
		if(total > 17 && flag == 0) {
			//significa que não pode pegar mais cartas
			return "Stand";
		}
		
		else if (flag == 0 && total <= 17 ) {
			//Hit significa que mais uma Carta
			return "Hit";
		}	
		return "Stand";
	}	
}

