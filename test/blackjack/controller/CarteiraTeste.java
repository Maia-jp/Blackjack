package blackjack.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CarteiraTeste {
	
	Carteira testClass;	
	String jogadorNomeTeste = "Beethoven";
	Map<String, Integer> fichasJogadorTeste;
	String carteira = "Kyp1kO2FIfOjtZlIa68J+1noH/s48FLLtwTrDmvEvZjqVkXcjYiaLY8a2+OdOPxQ";
	
	@Before
	public void setup() {
		this.testClass = new Carteira();
		this.fichasJogadorTeste = new HashMap<String, Integer>();
		this.fichasJogadorTeste.put("100", 2);
		this.fichasJogadorTeste.put("50", 2);
		this.fichasJogadorTeste.put("20", 5);
		this.fichasJogadorTeste.put("10", 5);
		this.fichasJogadorTeste.put("5", 8);
		this.fichasJogadorTeste.put("1", 10);
	}

	
	@Test
	public void gerarCarteiraTeste() {
		assertEquals(carteira,testClass.gerarCarteira(fichasJogadorTeste,jogadorNomeTeste));
	}
	
	@Test
	public void validarCarteiraTeste() {
		Map<String, Integer> teste = testClass.validarCarteira(carteira,jogadorNomeTeste);
		assertEquals(fichasJogadorTeste.get("1"),teste.get("1"));
		assertEquals(fichasJogadorTeste.get("5"),teste.get("5"));
		assertEquals(fichasJogadorTeste.get("10"),teste.get("10"));
		assertEquals(fichasJogadorTeste.get("20"),teste.get("20"));
		assertEquals(fichasJogadorTeste.get("50"),teste.get("50"));
		assertEquals(fichasJogadorTeste.get("100"),teste.get("100"));

	}
	
	@Test
	public void validarCarteiraTesteNomeErrado() {
		Map<String, Integer> teste = testClass.validarCarteira(carteira,"Shostakovitch");
		assertEquals(null,teste);
		
		teste = testClass.validarCarteira(carteira,jogadorNomeTeste.substring(0,6));
		assertEquals(null,teste);
		

		
	}
	
	@Test
	public void validarCarteiraEnderecoErrado() {
		Map<String, Integer> teste = testClass.validarCarteira("0101",jogadorNomeTeste);
		assertEquals(null,teste);
		
		teste = testClass.validarCarteira(carteira.substring(10,50),jogadorNomeTeste);
		assertEquals(null,teste);
		
		String carteiraNumeroTrocado = 
				"Kyp2kO2FIfOjtZlIa68J+1noH/s48FLLtwTrDmvEvZjqVkXcjYiaLY8a2+OdOPxQ";
		
		teste = testClass.validarCarteira(carteiraNumeroTrocado,jogadorNomeTeste);
		assertEquals(null,teste);
		
		teste = testClass.validarCarteira("",jogadorNomeTeste);
		assertEquals(null,teste);
		
		
	}
	
	
	
}
