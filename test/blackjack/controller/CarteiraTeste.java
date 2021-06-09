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
	int fichasJogadorTeste;
	String carteira = "ix86iVM7kq5t2A4G3B5RVZY66T9aeMV+188D8KaOJfo=";
	
	@Before
	public void setup() {
		this.testClass = new Carteira();
		this.fichasJogadorTeste = 500;
	}

	
	@Test
	public void gerarCarteiraTeste() {
		String t = testClass.gerarCarteira(fichasJogadorTeste,jogadorNomeTeste);
		assertEquals(carteira,testClass.gerarCarteira(fichasJogadorTeste,jogadorNomeTeste));
	}
	
	@Test
	public void validarCarteiraTeste() {
		int teste = testClass.validarCarteira(carteira,jogadorNomeTeste);
		assertEquals(teste,500);

	}
	
	@Test
	public void validarCarteiraTesteNomeErrado() {
		int teste = testClass.validarCarteira(carteira,"Shostakovitch");
		assertEquals(teste,-1);
		
		teste = testClass.validarCarteira(carteira,jogadorNomeTeste.substring(0,6));
		assertEquals(teste,-1);
		

		
	}
	
	@Test
	public void validarCarteiraEnderecoErrado() {
		int  teste = testClass.validarCarteira("0101",jogadorNomeTeste);
		assertEquals(-1,teste);
		
		teste = testClass.validarCarteira(carteira.substring(10,20),jogadorNomeTeste);
		assertEquals(-1,teste);
		
		String carteiraNumeroTrocado = 
				"Kyp2kO2FIfOjtZlIa68J+1noH/s48FLLtwTrDmvEvZjqVkXcjYiaLY8a2+OdOPxQ";
		
		teste = testClass.validarCarteira(carteiraNumeroTrocado,jogadorNomeTeste);
		assertEquals(-1,teste);
		
		teste = testClass.validarCarteira("",jogadorNomeTeste);
		assertEquals(-1,teste);
		
	}
	
	
	
}
