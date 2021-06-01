package blackjack.controller;

import static org.junit.Assert.assertEquals;
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
		assertTrue(testClass.salvar("./Resource/Save/", "Teste"));
	}
	
	@Test
	public void testCarregar() {
		SaveDTO dto = testClass.carregar("./Resource/Save/Teste.txt");
		
		for(int i=0;i<jogadores.size();i++) {
			assertEquals(jogadores.get(i),dto.jogadores.get(i));
		}
		
		for(String j : jogadores) {
			assertEquals(dinheiro.get(j).get("1"),dto.dinheiro.get(j).get("1"));
			assertEquals(dinheiro.get(j).get("5"),dto.dinheiro.get(j).get("5"));
			assertEquals(dinheiro.get(j).get("10"),dto.dinheiro.get(j).get("10"));
			assertEquals(dinheiro.get(j).get("20"),dto.dinheiro.get(j).get("20"));
			assertEquals(dinheiro.get(j).get("50"),dto.dinheiro.get(j).get("50"));
			assertEquals(dinheiro.get(j).get("100"),dto.dinheiro.get(j).get("100"));
		}
		
		for(String j : jogadores) {
			assertEquals(MaoJogadores.get(j).get(0),dto.MaoJogadores.get(j).get(0));
			assertEquals(MaoJogadores.get(j).get(1),dto.MaoJogadores.get(j).get(1));
		}
	}
	
	@Test
	public void testSalvarMenosJogadores() {
		jogadores.subList(0, 2);
		testClass.gerarModeloSalvar(jogadores, dinheiro, MaoJogadores,maoDealer);
		assertTrue(testClass.salvar("./Resource/Save/", "Teste2"));
		
	}
	
	@Test
	public void testCarregarMenosJogadores() {
		SaveDTO dto = testClass.carregar("./Resource/Save/Teste2.txt");
		for(int i=0;i<jogadores.size();i++) {
			assertEquals(jogadores.get(i),dto.jogadores.get(i));
		}
		
		for(String j : jogadores) {
			assertEquals(dinheiro.get(j).get("1"),dto.dinheiro.get(j).get("1"));
			assertEquals(dinheiro.get(j).get("5"),dto.dinheiro.get(j).get("5"));
			assertEquals(dinheiro.get(j).get("10"),dto.dinheiro.get(j).get("10"));
			assertEquals(dinheiro.get(j).get("20"),dto.dinheiro.get(j).get("20"));
			assertEquals(dinheiro.get(j).get("50"),dto.dinheiro.get(j).get("50"));
			assertEquals(dinheiro.get(j).get("100"),dto.dinheiro.get(j).get("100"));
		}
		
		for(String j : jogadores) {
			assertEquals(MaoJogadores.get(j).get(0),dto.MaoJogadores.get(j).get(0));
			assertEquals(MaoJogadores.get(j).get(1),dto.MaoJogadores.get(j).get(1));
		}
	}
}
