package blackjack.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
class Jogador {
	private String nomeJogador;
	private boolean stand;
	private boolean dobrar;
	private boolean maoVazia;
	private LinkedHashMap <String, Integer> fichasJogador = new LinkedHashMap<String, Integer>();
	private int totalFichasJogador;
	@SuppressWarnings("unchecked")
	private ArrayList<Carta>[] maoJogador = new ArrayList[2];
	
	public Jogador(String nome) {
		this.setNomeJogador(nome);
		this.setFichasJogador(fichasJogador);
		this.maoJogador[0] = new ArrayList<>();
		this.maoJogador[1] = new ArrayList<>();
		this.setMaoVazia(true);
		this.setStand(false);
		this.setDobrar(false);
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

	public boolean isMaoVazia() {
		return maoVazia;
	}

	private void setMaoVazia(boolean maoVazia) {
		this.maoVazia = maoVazia;
	}
	
	public void recebeCarta(Carta a,int mao) {
		this.maoJogador[mao].add(a);
		this.setMaoVazia(false);
	}
	
	public void limparMaoJogador(int mao) {
		this.maoJogador[mao].clear();
		this.setMaoVazia(true);
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

	private void setFichasJogador(LinkedHashMap <String, Integer> fichasJogador) {
		this.fichasJogador.put("100", 2);
		this.fichasJogador.put("50", 2);
		this.fichasJogador.put("20", 5);
		this.fichasJogador.put("10", 5);
		this.fichasJogador.put("5", 8);
		this.fichasJogador.put("1", 10);
		this.totalFichasJogador=500;
	}
	
	public void receberFichas(int quantia) {
		this.totalFichasJogador=this.totalFichasJogador+quantia;
		for (Map.Entry<String, Integer> entry : this.fichasJogador.entrySet()) {
		    this.fichasJogador.replace(entry.getKey(),entry.getValue()+quantia/Integer.parseInt(entry.getKey()));
		    quantia=quantia%Integer.parseInt(entry.getKey());
		    if(quantia==0){
		        break;
		    }
        }
	}
	
	public void pagarFichas(int aposta) {
		this.totalFichasJogador=this.totalFichasJogador-aposta;
		for (Map.Entry<String, Integer> entry : this.fichasJogador.entrySet()) {
		    this.fichasJogador.replace(entry.getKey(),entry.getValue()-aposta/Integer.parseInt(entry.getKey()));
		    aposta=aposta%Integer.parseInt(entry.getKey());
		    if(aposta==0){
		        break;
		    }
        }
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
		this.setMaoVazia(false);
	}
	
	public void dobrar(int apostaDoMontante) {
		if(this.fichasTotalJogador()>=apostaDoMontante) {
			this.pagarFichas(apostaDoMontante);
			this.setStand(true);
			this.setDobrar(true);
		}	
	}	
	
	public void split() {
		if(this.maoJogador[0].get(0).getValor()==this.maoJogador[0].get(1).getValor()) {
			this.maoJogador[1].add(this.maoJogador[0].get(1));
			this.maoJogador[0].remove(1);
		}
	}
	
	public boolean checkStand() {
		return this.stand;
	}

	private void setStand(boolean stand) {
		this.stand = stand;
	}
	
	public void putStand() {
		this.setStand(true);
	}
	
	public void clearStand() {
		this.setStand(false);
	}
	
	public boolean checkDobrar() {
		return this.dobrar;
	}
	
	private void setDobrar(boolean dobrar) {
		this.dobrar = dobrar;
	}
	
	public void putDobrar() {
		this.setDobrar(true);
	}
	
	public void clearDobrar() {
		this.setDobrar(false);
	}
	
	public boolean checkSplit() {
		if(this.maoJogador[1].isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	
}
