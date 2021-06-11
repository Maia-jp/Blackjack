/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
package blackjack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//DTO -> Data transfer object
public class SaveDTO {
	public List<String> jogadores;
	public HashMap<String,Integer> dinheiro;
	public int rodada;
	
	
	public SaveDTO() {
		this.jogadores = new ArrayList<String>();
		this.dinheiro = new HashMap<String,Integer>();
	}
	
	public void adicionarJogador(String jogador) {
		jogadores.add(jogador);
	}
	
	
	public void adicionarDinheiro(String dinheiroStr, int jogador) {
		int temp = Integer.valueOf(dinheiroStr);
		dinheiro.put(jogadores.get(jogador), temp);
	}
	
	public void adicionarRodada(String rodada) {
		this.rodada = Integer.valueOf(rodada);
	}
	
}
