package blackjack.model.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Jogador {
	private String nomeJogador;
	private Map <String, Integer> fichasJogador = new HashMap<String, Integer>();
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
	
	public Map <String, Integer> getFichasJogador() {
		return fichasJogador;
	}

	public void setFichasJogador(Map <String, Integer> fichasJogador) {
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

	
}
