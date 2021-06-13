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
	HashMap<String,Integer> dinheiro;
	int rodada;
	
	
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
		dinheiro = new HashMap<String,Integer>();
		for(String jogador: jogadores) {
			dinheiro = new HashMap<String,Integer>();
			dinheiro.put("Bach", 001);
			dinheiro.put("Beethoven", 010);
			dinheiro.put("Vivaldi", 100);
			dinheiro.put("Chopin", 111);
			
		}
		
		//Gerando rodada
		rodada = 1;
		
		//Gerar Modelo para salvar
		testClass.gerarModeloSalvar(jogadores, dinheiro, rodada);
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
			assertEquals(dinheiro.get(j),dto.dinheiro.get(j));
			assertEquals(dinheiro.get(j),dto.dinheiro.get(j));
			assertEquals(dinheiro.get(j),dto.dinheiro.get(j));
			assertEquals(dinheiro.get(j),dto.dinheiro.get(j));
			assertEquals(dinheiro.get(j),dto.dinheiro.get(j));
			assertEquals(dinheiro.get(j),dto.dinheiro.get(j));
		}
		
		
		assertEquals(1, dto.rodada);
			
		
	}
	
	@Test
	public void testSalvarMenosJogadores() {
		testClass.gerarModeloSalvar(jogadores.subList(0, 2), dinheiro, rodada);
		assertTrue(testClass.salvar("./Resource/Save/", "Teste2"));
		
	}
	
//	@Test
	public void testCarregarMenosJogadores() {
		SaveDTO dto = testClass.carregar("./Resource/Save/Teste2.txt");
		for(int i=0;i<jogadores.size();i++) {
			assertEquals(jogadores.get(i),dto.jogadores.get(i));
		}
		
		for(String j : jogadores) {
			assertEquals(dinheiro.get(j),dto.dinheiro.get(j));
		}
		
	}
}
