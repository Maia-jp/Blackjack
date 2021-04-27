package testeUnitarios.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import blackjack.model.Impl.Baralho;
import blackjack.model.Impl.Carta;

public class baralhoTest {

	@Test
	public void criarBaralhoN1TestSucesso() {
		Baralho testClass = new Baralho(1);
		assertEquals(testClass.getNumeroDeCartas(),52);
	}
	
	
	@Test
	public void criarBaralhoTestSucesso() {
		Baralho testClass = new Baralho(4);
		assertEquals(testClass.getNumeroDeCartas(),52*4);
	}
	
	@Test
	public void pegarCartaTestSucesso() {
		Baralho testClass = new Baralho(4);
		Carta c = testClass.pegarCarta();
		assertEquals(testClass.getNumeroDeCartas(),(52*4)-1);
		assertNotNull(c);
	}
	
	
	@Test
	public void embaralharTestSucesso() {
		Baralho controle = new Baralho(1);
		Baralho testClass = new Baralho(1);
		
		int nCartasIguais = 0;
		for (int i = 0; i < 52; i++) {
			if(controle.pegarCarta().getInfo().equals(testClass.pegarCarta().getInfo()))
				nCartasIguais++;
			}
		
		//Considerado embaralho se <10% das cartas forem iguais
		assertTrue(((float)nCartasIguais/52)<0.10);
		
	}
	
	@Test
	public void existeTodasAsCartasTestSucesso(){
		List<String> mockBaralho = baralhoMock();
		Baralho testClass = new Baralho(1);
		
		for (int i = 0; i < 52; i++) {
			assertTrue(mockBaralho.contains(testClass.pegarCarta().getInfo()));
			}
		
	}
	
	
	
	//Imita(mocka) um baralho
	private List<String> baralhoMock() {

		String[] naipes = {"H","S","C","D"};
		String[] valores = {"2","3","4","5",
				"6","7","8","9","10","j","q",
				"k","a"};
		
		List<String> cartas = new ArrayList<>();
		
		for(String naipe: naipes) {
			for(String valor: valores) {
				cartas.add(naipe+valor);
			}
		}
		
		
		return cartas;	
	}
	

}
