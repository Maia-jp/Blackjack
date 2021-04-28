package blackjack.model.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public class Jogador {
	private String nomeJogador;
	private LinkedHashMap <String, Integer> fichasJogador = new LinkedHashMap<String, Integer>();
	private List<Baralho> cartasJogador;
	
	public Jogador(String nome) {
		this.setNomeJogador(nome);
		this.setFichasJogador(fichasJogador);
	}
	
	public String getNomeJogador() {
		return nomeJogador;
	}
	
	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}
	
	public LinkedHashMap <String, Integer> getFichasJogador() {
		return fichasJogador;
	}

	public void setFichasJogador(LinkedHashMap <String, Integer> fichasJogador) {
		this.fichasJogador.put("100", 2);
		this.fichasJogador.put("50", 2);
		this.fichasJogador.put("20", 5);
		this.fichasJogador.put("10", 5);
		this.fichasJogador.put("5", 8);
		this.fichasJogador.put("1", 10);
		
	}
	
	public List<Baralho> getCartasJogador() {
		return cartasJogador;
	}

	public void setCartasJogador(List<Baralho> cartasJogador) {
		this.cartasJogador = new ArrayList<>();
	}

	public void receberFichas(int quantia) {
		for (Map.Entry<String, Integer> entry : this.fichasJogador.entrySet()) {
		    this.fichasJogador.replace(entry.getKey(),entry.getValue()+quantia/Integer.parseInt(entry.getKey()));
		    quantia=quantia%Integer.parseInt(entry.getKey());
		    if(quantia==0){
		        break;
		    }
        }
	}
	
	//aposta minima de $20 e máxima de $100
	public void pagarFichas(int aposta) {
		for (Map.Entry<String, Integer> entry : this.fichasJogador.entrySet()) {
		    this.fichasJogador.replace(entry.getKey(),entry.getValue()-aposta/Integer.parseInt(entry.getKey()));
		    aposta=aposta%Integer.parseInt(entry.getKey());
		    if(aposta==0){
		        break;
		    }
        }
	}
	
}
