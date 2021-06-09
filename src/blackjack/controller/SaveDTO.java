package blackjack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//DTO -> Data transfer object
public class SaveDTO {
	boolean[] info = {false,false,false,false,false,false};
	public List<String> jogadores;
	public HashMap<String,Integer> dinheiro;
	public int rodada;
	
	
	public SaveDTO() {
		this.jogadores = new ArrayList<String>();
		this.dinheiro = new HashMap<String,Integer>();
	}
	
	public void adicionarJogador(String jogador) {
		jogadores.add(jogador);
		info[0] = true; //Valida jogador adicionado
	}
	
	
	public void adicionarDinheiro(String dinheiroStr, int jogador) {
		int temp = Integer.valueOf(dinheiroStr);
		info[3] = true; //Valida Dinheiro adicionado
		dinheiro.put(jogadores.get(jogador), temp);
	}
	
	public void adicionarRodada(String rodada) {
		this.rodada = Integer.valueOf(rodada);
		info[4] = true; //Valida mao dealer
	}
	
}
