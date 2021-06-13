package blackjack.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class cartasTest {

	@Test
	public void inicializarTesteSucesso() {
		Carta teste = new Carta("H2");
		assertEquals(2,teste.getValor());
		
	}
	
	@Test
	public void inicializarFiguraTesteSucesso() {
		Carta teste = new Carta("Hk");
		assertEquals(10,teste.getValor());
		
	}
	
	@Test
	public void inicializarAsTesteSucesso() {
		Carta teste = new Carta("Ha");
		assertEquals(-1,teste.getValor());
		
	}
	
	@Test
	public void inicializarAsMaiusculoTesteSucesso() {
		Carta teste = new Carta("HA");
		assertEquals(-1,teste.getValor());
		
	}

}
