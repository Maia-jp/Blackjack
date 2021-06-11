/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
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
	private boolean quit;
	private LinkedHashMap <String, Integer> fichasJogador = new LinkedHashMap<String, Integer>();
	private int totalFichasJogador;
	@SuppressWarnings("unchecked")
	private ArrayList<Carta>[] maoJogador = new ArrayList[2];
	
	Jogador(String nome) {
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
	
	 String getNomeJogador() {
		return nomeJogador;
	}
	
	 void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}
	
	 ArrayList<Carta> getMaoJogador(int mao) {
		return this.maoJogador[mao];
	}
	
	 void recebeCarta(Carta a,int mao) {
		this.maoJogador[mao].add(a);
	}
	
	 void limparMaoJogador(int mao) {
		this.maoJogador[mao].clear();
	}
	
	 int valorMao(int mao) {
		return contagemJogador(mao);
	}
	
	private int contagemJogador(int mao) {
		int total = 0, contador = 0, flag = 0;
		//conta as cartas na mao do JOGADOR
		for(int i = 0; i < this.maoJogador[mao].size(); i++) {
			contador = this.maoJogador[mao].get(i).getValor();
			
			if (contador != -1){
				total += contador;
			}
			else {
				flag += 1;
			}
		}
		if(flag != 0) {
			for(int i = 0; i < flag; i++) {
				total = verificaValorAsJogador(total);
			}
		}
		
		return total;
	}
	
	private int verificaValorAsJogador(int to)
	{
		//verifica se o total vai ser maior ou menor que 21 com a escolha do as
		if(to + 11 > 21) {
			 to += 1;
		}
		else {
			to += 11;
		}
		return to;

	}
	
	//retorna a quantidade de dinheiro do jogador
	 int fichasTotalJogador() {
		return this.getTotalFichasJogador();
	}
	
	//retorna um hashmap com a quantidade de cada ficha que o jogador possui
	 LinkedHashMap <String, Integer> getFichasJogador() {
		return fichasJogador;
	}
	
	 void apostar(int aposta) {
		this.totalFichasJogador=this.getTotalFichasJogador()-aposta;
	}
	
	 void receberAposta(int aposta) {
		this.totalFichasJogador=this.getTotalFichasJogador()+aposta;
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
	
	 void receberFichas(String ficha, int qtd) {
		this.totalFichasJogador=this.getTotalFichasJogador()+(Integer.parseInt(ficha)*qtd);
		this.fichasJogador.replace(ficha,fichasJogador.get(ficha)+qtd);
	}
	
	 void pagarFichas(String ficha, int qtd) {
		this.totalFichasJogador=this.getTotalFichasJogador()-(Integer.parseInt(ficha)*qtd);
		this.fichasJogador.replace(ficha,fichasJogador.get(ficha)-qtd);
	}
		
	 boolean blackjack() {
		if (this.getMaoJogador(0).size() == 2 && this.valorMao(0) == 21) {
			return true;
		}else {
			return false;
		}
	}
	
	 void hit(Carta a,int mao) {
		this.maoJogador[mao].add(a);
	}
	
	 void dobrar(int aposta,int mao) {
			this.totalFichasJogador=this.getTotalFichasJogador()-aposta;
			this.putStand(mao);
			this.putDobrar(mao);
	}	
	
	 boolean split() {
		if((this.maoJogador[0].get(0).getValor()==this.maoJogador[0].get(1).getValor()) && this.split==false) {
			this.maoJogador[1].add(this.maoJogador[0].get(1));
			this.maoJogador[0].remove(1);
			return true;
		}else {
			return false;
		}
	}
	
	 void surrender() {
		this.maoJogador[0].remove(1);
		this.maoJogador[0].remove(0);
		this.putStand(0);
		this.putHit(0);
		this.putSurrender();
	}
	
	 boolean checkStand(int mao) {
		return this.stand[mao];
	}

	private void setStand(boolean stand, int mao) {
		this.stand[mao] = stand;
	}
	
	 void putStand(int mao) {
		this.setStand(true,mao);
		this.putHit(mao);
	}
	
	 void clearStand(int mao) {
		this.setStand(false,mao);
	}
	
	 boolean checkSplit() {
		return this.split;
	}
	
	private void setSplit(boolean split) {
		this.split = split;
	}
	
	 void putSplit() {
		this.setSplit(true);
	}
	
	 void clearSplit() {
		this.setSplit(false);
	}
	
	 boolean checkDobrar(int mao) {
		return this.dobrar[mao];
	}
	
	private void setDobrar(boolean dobrar, int mao) {
		this.dobrar[mao] = dobrar;
	}
	
	 void putDobrar(int mao) {
		this.setDobrar(true,mao);
	}
	
	 void clearDobrar(int mao) {
		this.setDobrar(false,mao);
	}
	
	void setFichasJogador(Map<String, Integer> novasFichas) {
		this.fichasJogador = (LinkedHashMap<String, Integer>) novasFichas;
	}

	 boolean checkHit(int mao) {
		return this.hit[mao];
	}
	
	private void setHit(boolean hit,int mao) {
		this.hit[mao] = hit;
	}
	
	 void putHit(int mao) {
		this.setHit(true,mao);
	}
	
	 void clearHit(int mao) {
		this.setHit(false,mao);
	}
	
	 boolean checkSurrender() {
		return this.surrender;
	}
	
	private void setSurrender(boolean surrender) {
		this.surrender = surrender;
	}
	
	 void putSurrender() {
		this.setSurrender(true);
	}
	
	 void clearSurrender() {
		this.setSurrender(false);
	}
	
	 boolean checkQuit() {
		return this.quit;
	}
	
	private void setQuit(boolean quit) {
		this.quit = quit;
	}
	
	 void putQuit() {
		this.setQuit(true);
	}
	
	 void clearQuit() {
		this.setQuit(false);
	}
	
	 void setTotalFichasJogador(int i) {
		this.totalFichasJogador = i;
	}

	 int getTotalFichasJogador() {
		return totalFichasJogador;
	}
	
}
