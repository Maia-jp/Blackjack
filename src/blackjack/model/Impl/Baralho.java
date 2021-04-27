package blackjack.model.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;





public class Baralho{
	private int numeroDeCartas;
	private List<Carta> cartas;
	
	
	public Baralho(int nBaralhos) {
		this.cartas = new ArrayList<>();
		for (int i = 0; i < nBaralhos; i++) {
			 this.cartas.addAll(adicionarBaralho());
			}
		this.numeroDeCartas = this.cartas.size();
		embaralhar();
		
	}
	
	
	//Embaralha as cartas
	public void embaralhar() {
		Collections.shuffle(this.cartas);
	}
	
	
	public int getNumeroDeCartas() {
		return numeroDeCartas;
	}
	
	public Carta pegarCarta() {
		this.numeroDeCartas--; 
		return this.cartas.remove(0);
	}
	
	
	
	//Adiciona um baralho
	private List<Carta> adicionarBaralho(){

		String[] naipes = {"H","S","C","D"};
		String[] valores = {"2","3","4","5",
				"6","7","8","9","10","j","q",
				"k","a"};
		
		List<Carta> cartas = new ArrayList<>();
		
		for(String naipe: naipes) {
			for(String valor: valores) {
				Carta c = new Carta(naipe+valor);
				cartas.add(c);
			}
		}
		
		
		return cartas;
		
	}
	
}
