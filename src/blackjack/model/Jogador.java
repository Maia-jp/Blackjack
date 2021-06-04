package blackjack.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
class Jogador {
	private String nomeJogador;
	private boolean[] stand = new boolean[2];
	private boolean[] dobrar = new boolean[2];
	private boolean split;
	private boolean[] hit = new boolean[2];
	private boolean surrender;
	private LinkedHashMap <String, Integer> fichasJogador = new LinkedHashMap<String, Integer>();
	private int totalFichasJogador;
	@SuppressWarnings("unchecked")
	private ArrayList<Carta>[] maoJogador = new ArrayList[2];
	
	public Jogador(String nome) {
		this.setNomeJogador(nome);
		this.setFichasJogador(fichasJogador);
		this.maoJogador[0] = new ArrayList<>();
		this.maoJogador[1] = new ArrayList<>();
		this.setStand(false,0);
		this.setStand(false,1);
		this.setDobrar(false,0);
		this.setDobrar(false,1);
		this.setSplit(false);
		this.setHit(false,0);
		this.setHit(false,1);
		this.setSurrender(false);
	}
	
	public String getNomeJogador() {
		return nomeJogador;
	}
	
	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}
	
	public ArrayList<Carta> getMaoJogador(int mao) {
		return this.maoJogador[mao];
	}
	
	public void recebeCarta(Carta a,int mao) {
		this.maoJogador[mao].add(a);
	}
	
	public void limparMaoJogador(int mao) {
		this.maoJogador[mao].clear();
	}
	
	public int valorMao(int mao) {
		int soma=0;
		for(int i=0;i<this.maoJogador[mao].size();i++) {
			soma=soma+this.maoJogador[mao].get(i).getValor();
		}
		return soma;
	}
	
	//retorna a quantidade de dinheiro do jogador
	public int fichasTotalJogador() {
		return this.totalFichasJogador;
	}
	
	//retorna um hashmap com a quantidade de cada ficha que o jogador possui
	public LinkedHashMap <String, Integer> getFichasJogador() {
		return fichasJogador;
	}
	
	public void apostar(int aposta) {
		this.totalFichasJogador=this.totalFichasJogador-aposta;
}
	
	private void setFichasJogador(LinkedHashMap <String, Integer> fichasJogador) {
		this.fichasJogador.put("100", 2);
		this.fichasJogador.put("50", 2);
		this.fichasJogador.put("20", 5);
		this.fichasJogador.put("10", 5);
		this.fichasJogador.put("5", 8);
		this.fichasJogador.put("1", 10);
		this.totalFichasJogador=500;
	}
	
	public void receberFichas(String ficha, int qtd) {
		this.totalFichasJogador=this.totalFichasJogador+(Integer.parseInt(ficha)*qtd);
		this.fichasJogador.replace(ficha,fichasJogador.get(ficha)+qtd);
	}
	
	public void pagarFichas(String ficha, int qtd) {
		this.totalFichasJogador=this.totalFichasJogador-(Integer.parseInt(ficha)*qtd);
		this.fichasJogador.replace(ficha,fichasJogador.get(ficha)-qtd);
	}
		
	public boolean blackjack() {
		if (this.getMaoJogador(0).size() == 2 && this.valorMao(0) == 21) {
			return true;
		}else {
			return false;
		}
	}
	
	public void hit(Carta a,int mao) {
		this.maoJogador[mao].add(a);
	}
	
	public void dobrar(int aposta,int mao) {
			this.totalFichasJogador=this.totalFichasJogador-aposta;
			this.putStand(mao);
			this.putDobrar(mao);
			this.putSplit();
	}	
	
	public boolean split() {
		if((this.maoJogador[0].get(0).getValor()==this.maoJogador[0].get(1).getValor()) && this.split==false) {
			this.maoJogador[1].add(this.maoJogador[0].get(1));
			this.maoJogador[0].remove(1);
			this.putSplit();
			return true;
		}else {
			return false;
		}
		
	}
	
	public void surrender(int aposta) {
		this.maoJogador[0].remove(1);
		this.maoJogador[0].remove(0);
		this.totalFichasJogador=this.totalFichasJogador+aposta;
		this.putStand(0);
		this.putDobrar(0);
		this.putSplit();
		this.putHit(0);
		this.putSurrender();
	}
	
	public boolean checkStand(int mao) {
		return this.stand[mao];
	}

	private void setStand(boolean stand, int mao) {
		this.stand[mao] = stand;
	}
	
	public void putStand(int mao) {
		this.setStand(true,mao);
		this.putSplit();
		this.putDobrar(mao);
		this.putHit(mao);
	}
	
	public void clearStand(int mao) {
		this.setStand(false,mao);
	}
	

	private void setSplit(boolean split) {
		this.split = split;
	}
	
	public void putSplit() {
		this.setSplit(true);
	}
	
	public void clearSplit() {
		this.setSplit(false);
	}
	
	public boolean checkDobrar(int mao) {
		return this.dobrar[mao];
	}
	
	private void setDobrar(boolean dobrar, int mao) {
		this.dobrar[mao] = dobrar;
	}
	
	public void putDobrar(int mao) {
		this.setDobrar(true,mao);
	}
	
	public void clearDobrar(int mao) {
		this.setDobrar(false,mao);
	}
	
	public boolean checkSplit() {
		return this.split;
	}

	public boolean checkHit(int mao) {
		return this.hit[mao];
	}
	
	private void setHit(boolean hit,int mao) {
		this.hit[mao] = hit;
	}
	
	public void putHit(int mao) {
		this.setHit(true,mao);
	}
	
	public void clearHit(int mao) {
		this.setHit(false,mao);
	}
	
	public boolean checkSurrender() {
		return this.surrender;
	}
	
	private void setSurrender(boolean surrender) {
		this.surrender = surrender;
	}
	
	public void putSurrender() {
		this.setSurrender(true);
	}
	
	public void clearSurrender() {
		this.setSurrender(false);
	}
	
}
