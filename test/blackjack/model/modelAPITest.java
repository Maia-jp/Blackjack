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
		//	assertEquals(testClass.jogadorMao(i).size(),2);
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
		testClass.apostar("50", 1,"alpha");
		testClass.apostar("50", 1,"beta");

        testClass.pedirStand("10");
        testClass.pedirDouble("00");
         
        testClass.novaRodada();
        assertEquals(testClass.checkJogadoresDisponiveis(),true);
        assertEquals(testClass.jogadorAtualNome(),"alpha");
        assertEquals(testClass.totalMontante(),0);
    }
	
	
	@Test
	public void testCheckJogadoresDisponiveis() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.apostar("50", 1, "alpha");
		assertTrue(testClass.checkJogadoresDisponiveis());
		testClass.pedirStand("00");

	}
	
	@Test
	public void testProximoJogador() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.adicionarJogador("beta");
		testClass.apostar("50", 1, "alpha");
		testClass.apostar("50", 1, "beta");

		testClass.distribuirCartas();
		
		assertEquals(testClass.jogadorAtualNome(),"alpha");
		testClass.proximoJogador();
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

		assertEquals(testClass.jogadorMao(0,0).size(),0);
		assertEquals(testClass.jogadorMao(1,0).size(),0);
		assertEquals(testClass.jogadorMao(2,0).size(),0);
		assertEquals(testClass.jogadorMao(3,0).size(),0);
		
		testClass.distribuirCartas();
		
		assertEquals(testClass.jogadorMao(0,0).size(),2);
		assertEquals(testClass.jogadorMao(1,0).size(),2);
		assertEquals(testClass.jogadorMao(2,0).size(),2);
		assertEquals(testClass.jogadorMao(3,0).size(),2);
		
		
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
		testClass.apostar("100",10,"Gama");
		
		assertEquals(testClass.totalMontante(),1000);
		
	}
	
	@Test
	public void testApostar() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.apostar("5",10,"alpha");
		
		assertEquals(testClass.totalMontante(),50);
	}
	
	
	
	
	@Test
	public void testPedirStand() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.apostar("5",10,"alpha");
		assertFalse(testClass.jogadorAtualCheckStand(0));
		testClass.pedirStand("00");
		assertTrue(testClass.jogadorAtualCheckStand(0));
	}
	
	
	@Test
	public void testPedirHit() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.pedirHit("00");
		assertEquals(testClass.jogadorAtualMao().size(),1);
	}
	
	@Test
	public void testJogadorAtualCheckStand() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.apostar("5",10,"alpha");
		assertFalse(testClass.jogadorAtualCheckStand(0));
		testClass.pedirStand("00");
		assertTrue(testClass.jogadorAtualCheckStand(0));
		
	}
	
	@Test
	public void testPedirDouble() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.apostar("50",1,"alpha");
		assertEquals(testClass.totalMontante(),50);
		testClass.pedirDouble("00");
	}
	
	@Test
	public void testPedirSplit() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.distribuirCartas();
		testClass.apostar("50",1,"alpha");
		testClass.pedirSplit(0);
	}
	
	@Test
	public void testPedirSurrender() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("alpha");
		testClass.distribuirCartas();
		testClass.apostar("50",1,"alpha");
		testClass.pedirSurrender(0);
		assertEquals(0,testClass.jogadorMao(0, 0).size());
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
		testClass.apostar("5",20,"Ivan");
		testClass.confereGanhadores();
		assertEquals(0,testClass.totalMontante());
	}
	
	@Test
	public void testDealerAcao() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Jorge");
		testClass.distribuirCartas();
		assertNotNull(testClass.dealerMao());
	}
	
	@Test
	public void testjogadorEspecificoCarteira() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Bach");
		testClass.adicionarJogador("Vivaldi");
		testClass.adicionarJogador("Handel");
		testClass.apostar("5", 1,"Bach");
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
		
		testClass.apostar("50", 1,"Bach");
		
		assertTrue(testClass.jogadorEspecificoCarteiraTotal(1)>testClass.jogadorAtualCarteiraTotal());
		assertEquals(500,testClass.jogadorEspecificoCarteiraTotal(1));
	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFichaNaMesaTotal() {
		ModelAPI testClass = ModelAPI.iniciar();
		testClass.adicionarJogador("Handel");
		testClass.adicionarJogador("Paganini");
		
		testClass.apostar("100", 1,"Handel");
		
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
		
		testClass.apostar("5", 1,"Handel");
		
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
		
		testClass.apostar("100", 1,"Handel");
		
		assertEquals(1,testClass.jogadorAtualQuantidadeFichas("100"));
		
	}

}
