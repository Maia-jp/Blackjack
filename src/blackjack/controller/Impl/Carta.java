package blackjack.controller.Impl;


//Implementa uma carta
// H = Hearts/Copas
// S = Spades/As
// C = Clubs/Paus
// D = Diamonds/Ouros
// Segunte formato = {Naipe Maisuculo}{Valor minusculo}
// Examplo  = Hk = Rei de copas
public class Carta {
	private String info;
	private int valor;
	
	public String getInfo() {
		return info;
	}


	public int getValor() {
		return valor;
	}
	
	
	
	public Carta(String Info) {
		this.info = Info;
		this.valor = calculaValor(Info);

	}
	
	
	
	
	private int calculaValor(String info){
		String pontos = info.substring(1).toLowerCase();
		
		
		if(pontos.equals("a")) {
			return -1;// retorna valor indefinido
		}else if(pontos.equals("k")){
			return 13;
		}else if(pontos.equals("q")) {
			return 12;
		}else if(pontos.equals("j")) {
			return 11;
		}
		
		return Integer.parseInt(pontos);
		
	}
	
	
	
}
