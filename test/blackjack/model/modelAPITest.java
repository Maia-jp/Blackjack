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
	public void confereGanhadores() {
		//@ Ale
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
	
	
	//testar dealer acao @Ale
	
	
	
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
	
	
	
	//
	//Esse teste é uma "DEMO" do jogo. Ou seja, aqui mostramos como todos os componentes
	//estão funcionando mas em um cenario iterativo e limitado! Basicamente é uma demosntração interativa da API que se
	//referente a model do que aconteceria em 1 rodada.
	//
	@Test
	public void rodarTestIterativo() throws IOException {
		if(true)	// COMENTE ESSA LINHA PARA INICIAR O TESTE INTERATIVO
			return; // COMENTE ESSA LINHA PARA INICIAR O TESTE INTERATIVO
		ModelAPI testClass = ModelAPI.iniciar();
		rotinaDeJogoTest(testClass);
	}
	
	
	public void rotinaDeJogoTest(ModelAPI testClass) throws IOException {
		
		
		//Analise stdin Usuario
		Scanner sc= new Scanner(System.in);      //System.in is a standard input stream  
		
		
		//Inicio do jogo - adiciona jogadores, para facilitar teremos 2 jogadores
		try {
			testClass.adicionarJogador("Anubis");
			testClass.adicionarJogador("Horus");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		//Fase de apostas, para 
		//simplificar o teste todos os jogadores irão apostar 1 vez um 20 moedas 
		boolean loop = true;
		while(loop) {
			testClass.apostar("1",20);
			testClass.pedirStand();
			testClass.proximaJogada();
			
			if(!testClass.checkJogadoresDisponiveis())
				break;
		}
		
		testClass.clearStand();
		
		//Jogadores recebem cartas
		testClass.distribuirCartas();

		while(true) {
			if(!testClass.checkJogadoresDisponiveis()){
				testClass.confereGanhadores();
				System.out.println("--------Fim to teste Iterativo-------:");
				testClass = null;
				return;
			}
			
			//Exibe info do jogador atual
			System.out.println("--------Jogada atual -------:");
			System.out.println("Joador atual: " + testClass.jogadorAtualNome());
			System.out.print("Cartas:[ ");
			for(String carta : testClass.jogadorAtualMao()) {
				System.out.print(carta+" ");
			}
			System.out.println("]");
			
			LinkedHashMap <String, Integer> fichas = testClass.jogadorAtualCarteira();
			System.out.format("|Ficha de 1:  %3d	|Ficha de 5:   %d3|\n", fichas.get("1"),fichas.get("5"));
			System.out.format("|Ficha de 10: %3d	|Ficha de 20:  %d3|\n", fichas.get("10"),fichas.get("20"));
			System.out.format("|Ficha de 50: %3d	|Ficha de 100: %d3|\n", fichas.get("50"),fichas.get("100"));
			
			
			//Exibe cartas do Dealer [ainda nao esta sendo considerado a carta "oculta" para fins de teste]
			System.out.println("\n|OUTROS JOGADORES|");
			System.out.print("DEALER:[ ");
			for(String carta : testClass.dealerMao()){
				System.out.print(carta+" ");
			}
			System.out.println("]");
			
			//Exibe cartas de outros jogadores
			for(int i=0;i<testClass.numeroDeJogadores();i++) {
				if(!testClass.jogadorAtualNome().equals(testClass.jogadorNome(i))){
					System.out.format("%s:[ ",testClass.jogadorNome(i));
					for(String carta : testClass.jogadorMao(i)){
						System.out.print(carta+" ");
					}
					System.out.println("]");
				}
			}
			
			//Exibe montante da mesa
			System.out.println("Montante total na mesa: "+testClass.totalMontante());
			
			//Opçoces de jogo [Mesmo tendo todas elas implementadas, nos deixamos esse teste com limitações
			//para nao acrescentar complexidade visto que isso é apenas uma demonstracao
			//iterativa]
			System.out.println("\n|Opçoes - digite o numero da opção|");
			System.out.println("|1|-Stand	|2|-Hit");
			System.out.println("|3|-Double	|0|-EXIT");
			
			System.out.print("Digite a opcao: ");
			int opt= sc.nextInt(); 
			
			
			switch(opt) {
			  case 1:
				  testClass.pedirStand();
			  case 2:
				  testClass.pedirHit();
			  case 3:
				  testClass.pedirDouble();
			}
			

			testClass.proximaJogada();
		}
		
	}

}
