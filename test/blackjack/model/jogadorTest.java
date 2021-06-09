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
        assertFalse(j1.checkStand(0));
        assertFalse(j1.checkStand(1));
        assertFalse(j1.checkDobrar(0));
        assertFalse(j1.checkDobrar(1));
        assertFalse(j1.checkSplit());
        assertFalse(j1.checkHit(0));
        assertFalse(j1.checkHit(1));
        assertFalse(j1.checkSurrender());
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
	public void recebeCartaTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		Carta carta1 = new Carta("Hk");
		j1.recebeCarta(carta1, 0);
		assertFalse(j1.getMaoJogador(0).isEmpty());
	}
	
	@Test
	public void limparMaoJogadorTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		Carta carta1 = new Carta("Hk");
		j1.recebeCarta(carta1, 0);
		assertFalse(j1.getMaoJogador(0).isEmpty());
		j1.limparMaoJogador(0);
		assertTrue(j1.getMaoJogador(0).isEmpty());

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
	public void apostarTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.apostar(50);
		assertEquals(450,j1.getTotalFichasJogador());
	}
	
	@Test
	public void receberApostaTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.receberAposta(50);
		assertEquals(550,j1.getTotalFichasJogador());
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
		assertFalse(j1.getMaoJogador(0).isEmpty());
	}
	
	@Test
	public void dobrarTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertFalse(j1.checkStand(0));
		assertFalse(j1.checkDobrar(0));
		j1.dobrar(50,0);
		assertTrue(j1.checkDobrar(0));
		assertTrue(j1.checkStand(0));
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
	public void surrenderTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		Carta carta1 = new Carta("Hk");
		Carta carta2 = new Carta("Hj");
		j1.recebeCarta(carta1, 0);
		j1.recebeCarta(carta2, 0);
		j1.surrender();
		assertEquals(0, j1.getMaoJogador(0).size());
	}
	
	@Test
	public void checkStandTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertFalse(j1.checkStand(0));
	}
	
	@Test
	public void putStandTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putStand(0);
		assertTrue(j1.checkStand(0));
	}
	
	@Test
	public void clearStandTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putStand(0);
		assertTrue(j1.checkStand(0));
		j1.clearStand(0);
		assertFalse(j1.checkStand(0));
	}
	
	@Test
	public void checkSplitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertFalse(j1.checkSplit());
	}
	
	@Test
	public void putSplitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putSplit();
		assertTrue(j1.checkSplit());
	}
	
	@Test
	public void clearSplitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putSplit();
		assertTrue(j1.checkSplit());
		j1.clearSplit();
		assertFalse(j1.checkSplit());
	}
	
	@Test
	public void checkDobrarTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertFalse(j1.checkDobrar(0));
	}
	
	@Test
	public void putDobrarTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putDobrar(0);;
		assertTrue(j1.checkDobrar(0));
	}
	
	@Test
	public void clearDobrarTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putDobrar(0);;
		assertTrue(j1.checkDobrar(0));
		j1.clearDobrar(0);
		assertFalse(j1.checkDobrar(0));
	}

	@Test
	public void checkHitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertFalse(j1.checkHit(0));
	}
	
	@Test
	public void putHitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putHit(0);;
		assertTrue(j1.checkHit(0));
	}
	
	@Test
	public void clearHitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putHit(0);;
		assertTrue(j1.checkHit(0));
		j1.clearHit(0);
		assertFalse(j1.checkHit(0));
	}
	
	@Test
	public void checkSurrenderTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertFalse(j1.checkSurrender());
	}
	
	@Test
	public void putSurrenderTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putSurrender();;
		assertTrue(j1.checkSurrender());
	}
	
	@Test
	public void clearSurrenderTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putSurrender();;
		assertTrue(j1.checkSurrender());
		j1.clearSurrender();
		assertFalse(j1.checkSurrender());
	}
	
	@Test
	public void checkQuitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		assertFalse(j1.checkQuit());
	}
	
	@Test
	public void putQuitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putQuit();;
		assertTrue(j1.checkQuit());
	}
	
	@Test
	public void clearQuitTesteSucesso() {
		Jogador j1 = new Jogador("Maria");
		j1.putQuit();;
		assertTrue(j1.checkQuit());
		j1.clearQuit();
		assertFalse(j1.checkQuit());
	}
}
