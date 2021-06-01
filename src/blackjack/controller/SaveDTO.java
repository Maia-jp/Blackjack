package blackjack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//DTO -> Data transfer object
public class SaveDTO {
	boolean[] info = {false,false,false,false,false,false};
	public List<String> jogadores;
	public HashMap<String,HashMap<String,Integer>> dinheiro;
	public HashMap<String,List<String>>MaoJogadores;
	public List<String>MaoDealer;
	
	
	public SaveDTO() {
		this.jogadores = new ArrayList<String>();
		this.MaoJogadores = new HashMap<String,List<String>>();
		this.dinheiro = new HashMap<String,HashMap<String,Integer>>();
		this.MaoDealer = new ArrayList<String>();
	}
	
	public void adicionarJogador(String jogador) {
		jogadores.add(jogador);
		info[0] = true; //Valida jogador adicionado
	}
	
	public void adicionarMao(String[] cartas, int jogador) {
		List<String> mao = new ArrayList<String>();
		for(String carta:cartas) {
			if(carta.length()>1) {
				mao.add(carta);
			}
		}
		info[1] = true; //Valida mao adicionada
		MaoJogadores.put(jogadores.get(jogador), mao);
	}
	
	public void adicionarDinheiro(String[] money, int jogador) {
		HashMap<String,Integer> fichas = new HashMap<String,Integer>();
		fichas.put("1",Integer.valueOf(money[0]));
		fichas.put("5",Integer.valueOf(money[1]));
		fichas.put("10",Integer.valueOf(money[2]));
		fichas.put("20",Integer.valueOf(money[3]));
		fichas.put("50",Integer.valueOf(money[4]));
		fichas.put("100",Integer.valueOf(money[5]));
		info[3] = true; //Valida Dinheiro adicionado
		dinheiro.put(jogadores.get(jogador), fichas);
	}
	
	public void adicionarDealer(String[] mao) {
		for(String carta:mao) {
			if(carta.length()>1) {
				MaoDealer.add(carta);
			}
		}
		info[4] = true; //Valida mao dealer
	}
	
}
