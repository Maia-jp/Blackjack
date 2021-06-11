package blackjack.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class modelAPITest {
	

	private ModelAPI testClass;
	
	
	@Test
	public void testInicilizarSucesso() {
		ModelAPI testClass = ModelAPI.iniciar();
		assertEquals(testClass.numeroDeJogadores(),0);
	}
	
	@Test
	public void testNumeroDeJogadoresSucesso() {
		ModelAPI testClass = ModelAPI.iniciar();
		assertEquals(testClass.numeroDeJogadores(),0);
		
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		testClass.adicionarJogador("gama");
		testClass.adicionarJogador("delta");
		
		assertEquals(testClass.numeroDeJogadores(),4);
	}
	
	@Test
	public void testAdicionarJogadorSucesso() {
		ModelAPI testClass = ModelAPI.iniciar();
		List nomesList = Arrays.asList(new String[]{"alpha","beta","gama","delta"});
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		testClass.adicionarJogador("gama");
		testClass.adicionarJogador("delta");
		assertEquals(testClass.numeroDeJogadores(),4);
		
		//verifica se todos os jogadores estão realmente na partida
		for(int i=0;i<testClass.numeroDeJogadores();i++) {
			assertTrue(nomesList.contains(testClass.jogadorNome(i)));
		}
	}
	
	@Test
	public void testDistribuirCartasSucesso() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Epsilon");
		testClass.adicionarJogador("Zeta");
		testClass.distribuirCartas();
		
		//Confere se todas os jogadores possuem cartas nas suas mãos
		for(int i=0;i<testClass.numeroDeJogadores();i++) {
			assertEquals(testClass.jogadorMao(i).size(),2);
			}
	
	}	
	
	@Test
	public void testJogadorAtualNomeSucesso() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Epsilon");
		
		assertEquals(testClass.jogadorAtualNome(),"Epsilon");
		
	}
	
	@Test
	public void testJogadorAtualMao() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Epsilon");
		testClass.distribuirCartas();
		assertEquals(testClass.jogadorAtualMao().size(),2);
	}
	
	@Test
	public void testJogadorAtualCarteira() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		
		LinkedHashMap <String, Integer> testMethod = testClass.jogadorAtualCarteira();
		assertNotNull(testMethod);
		
		assertEquals(testMethod.size(),6);
	
	}
	
	@Test
    public void testNovaRodada() {
        ModelAPI testClass = ModelAPI.iniciar();
        testClass.adicionarJogador("alpha");
        testClass.adicionarJogador("beta");
        
        testClass.pedirStand();
        testClass.pedirDouble();
        
        testClass.proximoJogador();
        
        testClass.apostar("100",10);
        
        testClass.novaRodada();
        
        assertEquals(testClass.checkJogadoresDisponiveis(),true);
        assertEquals(testClass.jogadorAtualNome(),"alpha");
        assertEquals(testClass.totalMontante(),0);
    }
	
	@Test
	public void testClearStandSucesso() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		
		testClass.pedirStand();
		
		testClass.proximaJogada();
		
		testClass.pedirStand();
		
		testClass.clearStand();
		
		assertTrue(testClass.checkJogadoresDisponiveis());
	}
	
	@Test
	public void testCheckJogadoresDisponiveis() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		
		testClass.pedirStand();
		assertTrue(testClass.checkJogadoresDisponiveis());
		
		testClass.proximaJogada();
		
		testClass.pedirStand();
		assertFalse(testClass.checkJogadoresDisponiveis());
	}
	
	@Test
	public void testCheckNovoBaralhoSucess() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		assertFalse(testClass.checkNovoBaralho());

		for(int i=0; i< 52; i++) {
			testClass.pedirHit();
		}

		assertTrue(testClass.checkNovoBaralho());

	}
	
	@Test
	public void testProximoJogador() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		testClass.adicionarJogador("gama");
		testClass.adicionarJogador("delta");
		
		assertEquals(testClass.jogadorAtualNome(),"alpha");
		testClass.proximoJogador();
		assertEquals(testClass.jogadorAtualNome(),"beta");
		testClass.proximoJogador();
		assertEquals(testClass.jogadorAtualNome(),"gama");
		testClass.proximoJogador();
		assertEquals(testClass.jogadorAtualNome(),"delta");
		testClass.proximoJogador();
		assertEquals(testClass.jogadorAtualNome(),"alpha");
		
	}
	
	@Test
	public void testJogadorNome() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		testClass.adicionarJogador("gama");
		testClass.adicionarJogador("delta");
		
		assertEquals(testClass.jogadorNome(0),"alpha");
		assertEquals(testClass.jogadorNome(1),"beta");
		assertEquals(testClass.jogadorNome(2),"gama");
		assertEquals(testClass.jogadorNome(3),"delta");
		
	}
	
	@Test
	public void testJogadorMao() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		testClass.adicionarJogador("gama");
		testClass.adicionarJogador("delta");

		assertEquals(testClass.jogadorMao(0).size(),0);
		assertEquals(testClass.jogadorMao(1).size(),0);
		assertEquals(testClass.jogadorMao(2).size(),0);
		assertEquals(testClass.jogadorMao(3).size(),0);
		
		testClass.distribuirCartas();
		
		assertEquals(testClass.jogadorMao(0).size(),2);
		assertEquals(testClass.jogadorMao(1).size(),2);
		assertEquals(testClass.jogadorMao(2).size(),2);
		assertEquals(testClass.jogadorMao(3).size(),2);
		
		
	}
	
	@Test
	public void testDealerMao() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		
		testClass.distribuirCartas();
		
		assertEquals(testClass.dealerMao().size(),2);
		
	}
	
	@Test
	public void testTotalMontante() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Gama");
		testClass.apostar("100",10);
		
		assertEquals(testClass.totalMontante(),1000);
		
	}
	
	@Test
	public void testApostar() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.apostar("5",10);
		
		assertEquals(testClass.totalMontante(),50);
	}
	
	
	
	
	@Test
	public void testPedirStand() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.pedirStand();
		
		assertTrue(testClass.jogadorAtualCheckStand());
	}
	
	
	@Test
	public void testPedirHit() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.pedirHit();
		assertEquals(testClass.jogadorAtualMao().size(),1);
		testClass.pedirHit();
		assertEquals(testClass.jogadorAtualMao().size(),2);
		testClass.pedirHit();
		assertEquals(testClass.jogadorAtualMao().size(),3);
		
	}
	
	@Test
	public void testJogadorAtualCheckStand() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		
		assertFalse(testClass.jogadorAtualCheckStand());
		
		testClass.pedirStand();
		
		assertTrue(testClass.jogadorAtualCheckStand());
		
	}
	
	@Test
	public void testAtivarDouble() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		//Testar double @ze
	}
	
	@Test
	public void testPedirDouble() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		//Testar double @ze
	}
	
	
	@Test
	public void testproximaJogada() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		
		String j1 = testClass.jogadorAtualNome();
		testClass.proximoJogador();
		assertFalse(j1.equals(testClass.jogadorAtualNome()));
		
	}
	
	@Test
	public void testRemoverJogadorNome() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		testClass.adicionarJogador("gama");
		
		testClass.removerJogadorNome("beta");
		
		assertEquals(testClass.numeroDeJogadores(),2);
		
	}
	
	@Test
	public void testRemoverJogador() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		testClass.adicionarJogador("gama");
		
		testClass.removerJogador();
		
		assertEquals(testClass.numeroDeJogadores(),2);
		
	}
	
	@Test
	public void testSingletonSucesso() {
		ModelAPI testClass = ModelAPI.iniciar();
		ModelAPI dummyClass = ModelAPI.iniciar();
		assertEquals(dummyClass.conferirId(), testClass.conferirId());
		assertEquals(dummyClass, testClass);
		
	}
	
	@Test
	public void testconfereGanhadores() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Ivan");
		testClass.distribuirCartas();
		testClass.apostar("5",20);
		testClass.confereGanhadores();
		assertEquals(0,testClass.totalMontante());
	}
	
	@Test
	public void testValorMaoDealer() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Ivan");
		testClass.distribuirCartas();
		assertEquals(int.class, testClass.valorDealerMao());
	}
	
	@Test
	public void testDealerAcao() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Jorge");
		testClass.distribuirCartas();
		testClass.dealerAcao();
		assertEquals(3, testClass.dealerMao().size());
	}
	
	@Test
	public void testjogadorEspecificoCarteira() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Bach");
		testClass.adicionarJogador("Vivaldi");
		testClass.adicionarJogador("Handel");
		
		
		testClass.apostar("5", 1);
	
		
		assertTrue(testClass.jogadorEspecificoCarteiraTotal(1)>testClass.jogadorAtualCarteiraTotal());
		
	}
	
	@Test
	public void testjogadorAtualCarteiraTotal() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Bach");
		
		assertEquals(500,testClass.jogadorAtualCarteiraTotal());
	}
	
	@Test
	public void testjogadorEspecificoCarteiraTotal() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Bach");
		testClass.adicionarJogador("Beethoven");
		
		testClass.apostar("50", 1);
		
		assertTrue(testClass.jogadorEspecificoCarteiraTotal(1)>testClass.jogadorAtualCarteiraTotal());
		assertEquals(500,testClass.jogadorEspecificoCarteiraTotal(1));
	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFichaNaMesaTotal() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Handel");
		testClass.adicionarJogador("Paganini");
		
		testClass.apostar("100", 1);
		
		Map<String, Integer> testMap = testClass.fichasNaMesaTotal();
		
		assertNull(testMap.get("1"));
		assertNull(testMap.get("5"));
		assertNull(testMap.get("10"));
		assertNull(testMap.get("20"));
		assertNull(testMap.get("50"));
		assertEquals(testMap.get("100"),new Integer(1));
		
		
	}
	
	@Test
	public void testMontanteTotal(){
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Handel");
		testClass.adicionarJogador("Paganini");
		
		testClass.apostar("5", 1);
		
		Map<String, Map<String, Integer>> testMapMap = testClass.montanteTotal();
		
		Map<String, Integer> testMap = testMapMap.get("Handel");
		assertNull(testMap.get("1"));
		assertEquals(testMap.get("5"),new Integer(1));
		assertNull(testMap.get("10"));
		assertNull(testMap.get("20"));
		assertNull(testMap.get("50"));
		assertNull(testMap.get("100"));
		
	}
	
	@Test
	public void testJogadorAtualQuantidadeFichas() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Handel");
		
		testClass.apostar("100", 1);
		
		assertEquals(1,testClass.jogadorAtualQuantidadeFichas("100"));
		
	}

}
