package blackjack.model;
import static org.junit.Assert.*;

import org.junit.Test;


public class DealerTest {
	
	@Test
	public void dealerinicializa() {
		//Inicializando dealer
		Dealer d = new Dealer("Nome");
		assertSame("Nome", d.getNomeJogador());
	}
	
	@Test
	public void recebeRetornaCartaTestNull() {
		//Criando Dealer Carta
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		//recebendo uma carta
		d.receberCarta(teste);
		
		//recebendo segunda carta
		Carta teste1 = new Carta("HJ");
		d.receberCarta(teste1);
		
		//verificando qtds cartas o dealer possui
		assertNotNull(d.verificarMao());
	}
	
	@Test
	public void recebeRetornaCartaTest() {
		//Criando Dealer Carta
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		//recebendo uma carta
		d.receberCarta(teste);
		
		//recebendo segunda carta
		Carta teste1 = new Carta("HJ");
		d.receberCarta(teste1);
		
		//verificando qtds cartas o dealer possui
		assertEquals(2, d.qtdCartasDealer());
	}
	
	@Test
	public void limpaMaoTestSucesso() {
		
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		d.receberCarta(teste);
		Carta teste1 = new Carta("HJ");
		d.receberCarta(teste1);
	
		//removendo todas as cartas
		d.limpaMao();
		assertEquals(0, d.qtdCartasDealer());
	}
	
	@Test
	public void valorMaoSemCartaAsTestSucesso() {
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		d.receberCarta(teste);
		assertEquals(10, d.valorMao());
	}
	
	@Test
	public void valorMaoComCartaAsTestSucesso() {
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		
		d.receberCarta(teste);
		
		Carta teste1 = new Carta("HA");
		d.receberCarta(teste1);
		//Adicionando cartas na mão do jogador  Ás e Reis o dealer vai escolher o Ás = 11
		assertEquals(21, d.valorMao());
	}
	
	@Test
	public void valorMaoComCartaAs2TestSucesso() {
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		d.receberCarta(teste);
		
		Carta teste1 = new Carta("HA");
		d.receberCarta(teste1);
		
		Carta teste2 = new Carta("H5");
		d.receberCarta(teste2);
		//Adicionando cartas na mão do jogador  Ás e Reis e 5 o dealer vai escolher o Ás = 1
		
		assertEquals(26, d.valorMao());
	}
	
	@Test
	public void veBlackJackTestBoolTrue() {
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		
		d.receberCarta(teste);
		
		Carta teste1 = new Carta("HA");
		d.receberCarta(teste1);
		//Adicionando cartas na mão do jogador  Ás e Reis o dealer vai escolher o Ás = 11
		//BLACKJACK
		assertTrue(d.blackJackDealer());
	}
	
	@Test
	public void veBlackJackTestBoolFalse() {
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		
		d.receberCarta(teste);
		
		Carta teste1 = new Carta("H4");
		d.receberCarta(teste1);
		//Não deu BlackJack
		assertFalse(d.blackJackDealer());
	}
	
	@Test
	public void veBlackJackTestBoolMaisCartasFalse() {
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		
		d.receberCarta(teste);
		
		Carta teste1 = new Carta("H9");
		d.receberCarta(teste1);
		
		Carta teste2 = new Carta("H2");
		d.receberCarta(teste2);
		
		//Adicionando mais de duas cartas na mão do jogador iqual a 21
		//Não deu BLACKJACK
		assertFalse(d.blackJackDealer());
	}
	
	@Test
	public void checkEstrategiaTest1() {
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		
		d.receberCarta(teste);
		
		Carta teste1 = new Carta("H9");
		d.receberCarta(teste1);
		//não pode pegar mais cartas, possui mais que 17 pontos.
		assertEquals(1, d.checkEstrategia());
	}
	
	@Test
	public void checkEstrategiaTest2() {
		Dealer d = new Dealer("Nome");
		Carta teste = new Carta("Hk");
		
		d.receberCarta(teste);
		
		Carta teste1 = new Carta("H5");
		d.receberCarta(teste1);
		//pode pegar mais cartas.
		assertEquals(2, d.checkEstrategia());
	}
	
}
