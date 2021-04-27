package blackjack.controller.Impl;

import java.util.ArrayList;

public class Dealer {
	private ArrayList<Carta> cartasDealer;
	
	public Dealer() {
		ArrayList<Carta> cartasDealer = new ArrayList<>();
	}
	
	public void setCartaDealer(Carta a) {
		this.cartasDealer.add(a);
	}
	
	//public int getCartaDealer() {
		//return this.cartaDealer;
	//}
	
}
