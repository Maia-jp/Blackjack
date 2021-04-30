package blackjack.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class modelAPITest {

	public ModelAPI testClass;
	
	@Before
	public void setUp() throws Exception {
		testClass = ModelAPI.iniciar();
	
	}
	
	//
	//Esse teste é uma "DEMO" do jogo. Ou seja, aqui mostramos como todos os componentes
	//estão funcionando mas em um cenario iterativo e limitado. Basicamente é uma demosntração interativa da API que se
	//referente a model
	//
	@Test
	public void rotinaDeJogoTest() throws IOException {
		
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
			testClass.apostar(20);
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
			
			//Opçoces de jogo [Mesmo tendo todas elas implementadas limitados aqui 
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
				  break; //@Ze implementar double
			  case 0:
				  return;
			}
			
			//@Ale Dealer pensa
			testClass.proximaJogada();
		}
		
		
	}

}
