package blackjack.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

class Jogador {
	private String nomeJogador;
	private int flag=0;
	private LinkedHashMap <String, Integer> fichasJogador = new LinkedHashMap<String, Integer>();
	private ArrayList<Carta>[] maoJogador = new ArrayList[2];
	
	public Jogador(String nome) {
		this.setNomeJogador(nome);
		this.setFichasJogador(fichasJogador);
		this.maoJogador[flag] = new ArrayList<>();
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
	
	public void split() {
		if(this.maoJogador[flag].get(0).getValor()==this.maoJogador[flag].get(1).getValor()) {
			this.maoJogador[flag+1].add(this.maoJogador[flag].get(1));
			this.maoJogador[flag].remove(1);
		}
	}
	
	public ArrayList<Carta> getMaoJogador() {
		return this.maoJogador[flag];
	}

	public void setMaoJogador(Carta a) {
		this.maoJogador[flag].add(a);
	}
	
	public void limparMaoJogador() {
		this.maoJogador[flag].clear();
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
	
	//aposta minima de $20 e maxima de $100
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
