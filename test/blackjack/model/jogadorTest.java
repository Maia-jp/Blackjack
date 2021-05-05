package blackjack.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class jogadorTest {

	@Test
    public void inicializarJogadorTesteSucesso() {
        Jogador j1 = new Jogador("Maria");
        assertEquals("Maria", j1.getNomeJogador());
        assertEquals(500,j1.fichasTotalJogador());
        assertNotNull(j1.getMaoJogador(0));
        assertNotNull(j1.getMaoJogador(1));
        assertFalse(j1.checkStand());
    }

	@Test
	public void getNomeJogadorTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertEquals("Maria",j1.getNomeJogador());
	}
	
	@Test
	public void setNomeJogadorTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.setNomeJogador("João");
		assertEquals("João",j1.getNomeJogador());
	}
	
	@Test
	public void getMaoJogadorTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertNotNull(j1.getMaoJogador(0));
        assertNotNull(j1.getMaoJogador(1));
	}
	
	@Test
	public void isMaoVaziaTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertTrue(j1.isMaoVazia());
	}
	
	@Test
	public void recebeCartaTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		Carta carta1 = new Carta("Hk");
		j1.recebeCarta(carta1, 0);
		assertFalse(j1.isMaoVazia());
	}
	
	@Test
	public void limparMaoJogadorTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		Carta carta1 = new Carta("Hk");
		j1.recebeCarta(carta1, 0);
		assertFalse(j1.isMaoVazia());
		j1.limparMaoJogador(0);
		assertTrue(j1.isMaoVazia());

	}
	
	@Test
	public void valorMaoTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		Carta carta1 = new Carta("Hk");
		Carta carta2 = new Carta("Hj");
		j1.recebeCarta(carta1, 0);
		j1.recebeCarta(carta2, 0);
		assertEquals(20,j1.valorMao(0));
	}
	
	@Test
	public void fichasTotalJogadorTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertNotSame(450,j1.fichasTotalJogador());
		assertEquals(500,j1.fichasTotalJogador());
	}
	
	@Test
	public void getFichasJogadorTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertNotNull(j1.getFichasJogador());
	}
	
	@Test
	public void receberFichasTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		//j1.receberFichas(50);
		assertEquals(550,j1.fichasTotalJogador());
	}
	
	@Test
	public void pagarFichasTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		//j1.pagarFichas(100);
		assertEquals(400,j1.fichasTotalJogador());
	}
	
	@Test
	public void blackjackTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		Carta carta1 = new Carta("Hk");
		Carta carta2 = new Carta("Hj");
		j1.recebeCarta(carta1, 0);
		j1.recebeCarta(carta2, 0);
		assertFalse(j1.blackjack());
		j1.limparMaoJogador(0);
		Carta carta3 = new Carta("Hk");
		Carta carta4 = new Carta("Ha");
		j1.recebeCarta(carta3, 0);
		j1.recebeCarta(carta4, 0);
		assertTrue(j1.blackjack());
	}
	
	@Test
	public void hitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		Carta carta1 = new Carta("Hk");
		j1.hit(carta1, 0);
		assertFalse(j1.isMaoVazia());
	}
	
	@Test
	public void dobrarTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertFalse(j1.checkStand());
		j1.dobrar(50);
		assertFalse(j1.checkDobrar());
		assertTrue(j1.checkStand());
	}
	
	@Test
	public void splitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		Carta carta1 = new Carta("Hk");
		Carta carta2 = new Carta("Hj");
		j1.recebeCarta(carta1, 0);
		j1.recebeCarta(carta2, 0);
		j1.split();
		assertEquals(1, j1.getMaoJogador(0).size());
		assertEquals(1, j1.getMaoJogador(1).size());
	}
	
	@Test
	public void checkStandTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertFalse(j1.checkStand());
	}
	
	@Test
	public void putStandTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putStand();
		assertTrue(j1.checkStand());
	}
	
	@Test
	public void clearStandTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putStand();
		assertTrue(j1.checkStand());
		j1.clearStand();
		assertFalse(j1.checkStand());
	}
	
	@Test
	public void checkDobrarTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertFalse(j1.checkDobrar());
	}
	
	@Test
	public void putDobrarTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putDobrar();;
		assertTrue(j1.checkDobrar());
	}
	
	@Test
	public void clearDobrarTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putDobrar();;
		assertTrue(j1.checkDobrar());
		j1.clearDobrar();
		assertFalse(j1.checkDobrar());
	}

	@Test
	public void checkSplitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		Carta carta1 = new Carta("Hk");
		Carta carta2 = new Carta("Hj");
		j1.recebeCarta(carta1, 0);
		j1.recebeCarta(carta2, 0);
		assertFalse(j1.checkSplit());
		j1.split();
		assertTrue(j1.checkSplit());
	}
}
