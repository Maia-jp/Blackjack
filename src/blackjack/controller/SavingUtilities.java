package blackjack.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import blackjack.model.ModelAPI;

public class SavingUtilities {
	List<String> modeloSalvar;
	
	//Gera um Modelo de salvamento
	public void gerarModeloSalvar(List<String> jogadores,
			HashMap<String,Integer> dinheiro,
			int rodada) {
		List<String> modeloSalvar = new ArrayList<>();
		
		modeloSalvar.add("=====BLACKJACK=====");
		modeloSalvar.add("Partida: "+ Instant.now().getEpochSecond());
		
		modeloSalvar.add("Jogadores:");
		jogadores.forEach(j->modeloSalvar.add(j));
		//Padding
		for(int i =jogadores.size(); i<4;i++) {
			modeloSalvar.add("-");
		}
		
		modeloSalvar.add("Dinheiro:");
		for(String jogador:jogadores) {
			String dinheiroJogador = dinheiro.get(jogador) + "";
			
			modeloSalvar.add(dinheiroJogador);
		}
		
		//Padding
		for(int i =jogadores.size(); i<4;i++) {
					modeloSalvar.add("-");
		}
		//Rodada
		modeloSalvar.add("Rodada:");
		modeloSalvar.add(rodada+"");
		
		 this.modeloSalvar = modeloSalvar;
	}
	
	//Salva
	public boolean salvar(String path,String name) {
		 try {
		      if(this.modeloSalvar == null ||this.modeloSalvar.isEmpty()) {
		    	  System.out.println("[ERRO][SavingUtilities]Um modelo deve ser gerado primeiro");
		    	  return false;
		      }
		      criarArquivo(path,name);
		      FileWriter myWriter = new FileWriter(path+"/"+name+".txt");
		      
		      this.modeloSalvar.forEach(line->{
				try {
					myWriter.write(line+"\n");
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("[ERRO][SavingUtilities]Erro ao salvar linha");
				}
			});
		      
		      myWriter.close();
		      return true;
		    } catch (IOException e) {
		    	System.out.println("[ERRO][SavingUtilities]Erro");
		      e.printStackTrace();
		      return false;
		    }
	}
	
	//Criar Arquivo
	private void criarArquivo(String path,String name) {
		try {
		      File myObj = new File(path+"/"+name+".txt");
		      if (myObj.createNewFile()) {
		        System.out.println("Arquivo Criado: " + myObj.getName());
		      } else {
		        System.out.println("Arquivo sobreescrito");
		      }
		    } catch (IOException e) {
		      System.out.println("[ERRO][SavingUtilities]Erro");
		      e.printStackTrace();
		    }
	}
	
	
	//Carregar arquivo
	public SaveDTO carregar(String path) {
		SaveDTO dto = new SaveDTO();
		
		try {
		      File myObj = new File(path);
		      Scanner myReader = new Scanner(myObj);
		      int linha =0;
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        if(!data.equals("-")) {
		        	if(linha >=3 && linha <=6) {
		        		dto.adicionarJogador(data);
		        	}
		        	if(linha >=8 && linha <=11) {
		        		dto.adicionarDinheiro(data,linha-8);
		        	}
		        	if(linha == 13) {
		        		dto.adicionarRodada(data);
		        	}
		        	
		        }
		        System.out.println(data);
		       linha++;
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return dto;
	}
}
