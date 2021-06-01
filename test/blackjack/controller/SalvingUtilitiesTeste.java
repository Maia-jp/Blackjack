package blackjack.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SalvingUtilitiesTeste {
	SavingUtilities testClass;
	List<String> jogadores;
	HashMap<String,HashMap<String,Integer>> dinheiro;
	HashMap<String,List<String>>MaoJogadores;
	List<String> maoDealer;
	
	
	@Before
	public void setup() {
		this.testClass = new SavingUtilities();
		
		//Adicionando jogadores
		jogadores = new ArrayList<>();
		jogadores.add("Bach");
		jogadores.add("Beethoven");
		jogadores.add("Vivaldi");
		jogadores.add("Chopin");
		
		//Adicionando Dinheiro
		dinheiro = new HashMap<String,HashMap<String,Integer>>();
		for(String jogador: jogadores) {
			HashMap<String,Integer> dinheiroJ = new HashMap<String,Integer>();
			dinheiroJ.put("1", 1);
			dinheiroJ.put("5", 2);
			dinheiroJ.put("10", 3);
			dinheiroJ.put("20", 4);
			dinheiroJ.put("50", 5);
			dinheiroJ.put("100", 6);
			
			dinheiro.put(jogador,dinheiroJ);
		}
		
		//Adicionando Cartas
		MaoJogadores = new HashMap<String,List<String>>();
		for(String jogador: jogadores) {
			List<String> mao = new ArrayList<String>();
			mao.add("HK");
			mao.add("D10");
			MaoJogadores.put(jogador,mao);
		}
		
		//Cartas do dealer
		this.maoDealer = new ArrayList<>();
		this.maoDealer.add("SK");
		this.maoDealer.add("SQ");
		
		//Gerar Modelo para salvar
		testClass.gerarModeloSalvar(jogadores, dinheiro, MaoJogadores,this.maoDealer);
	}
	
	
	@Test
	public void testSalvar() {
		//assertTrue(testClass.salvar("./Resource/Save/", "Teste"));
	}
	
	@Test
	public void testCarregar() {
		testClass.carregar("./Resource/Save/Teste.txt");
	}
}
