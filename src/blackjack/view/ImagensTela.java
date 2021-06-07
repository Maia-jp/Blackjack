package blackjack.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class ImagensTela extends JPanel{
   
	private CarregaImagens cI;
    private List<String> chave;
    private List<String[]> infosJogadores;
    private int [] conteudo;
    private String valorFicha;
    private String apostaInicialTotal;
    private String nomeJogador;
    private boolean botaoOk;
    private List<String[]> resultadoFinal;
    
    public ImagensTela(CarregaImagens cI) {
    	this.cI = cI;
    	this.botaoOk = false;
     }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.drawImage(cI.getFundoBanca(),0,0, null);
    	g2d.drawImage(cI.getFichaV1(), 60, 500, null);
    	g2d.drawImage(cI.getFichaV5(), 190, 550, null);
    	g2d.drawImage(cI.getFichaV10(), 320, 590, null);
    	g2d.drawImage(cI.getFichaV20(), 460,590, null);
    	g2d.drawImage(cI.getFichaV50(), 590,560, null);
    	g2d.drawImage(cI.getFichaV100(), 720,520, null);
    	g2d.drawImage(cI.getBaralhoAzul(), 340,340, null);
    	g2d.drawImage(cI.getBaralhoVermelho(), 460,340, null);
    	
    	g2d.setFont(new Font("Helvetica", Font.BOLD, 15));
		g2d.setColor(Color.RED);
		g2d.drawString("Jogador: "+nomeJogador, 340, 500);
    	int indice = 1;
    	if(chave != null) {
	    	for(String i : chave) {
	    		g2d.drawImage(cI.getmapBaralho(i), indice+360,120, null);
	    		indice+=20;
	    	}
	    	if (conteudo[1] < 1) {
	    		g2d.drawImage(cI.getBaralhoAzul(), 21+360,120, null);
	    	}
	    	else {
	    		g2d.setFont(new Font("Helvetica", Font.BOLD, 15));
	    		g2d.setColor(Color.RED);
	    		g2d.drawString("Pontos Dealer "+Integer.toString(conteudo[0]), 360, 110);
	    	}
    	}
    	
    	if(infosJogadores != null) {
    		int y = 210;
        	for(String[] i : infosJogadores) {
        		g2d.setFont(new Font("Helvetica", Font.BOLD, 12));
        		g2d.setColor(Color.RED);
        		g2d.drawString("Nome: "+i[0], 30,y);
        		g2d.drawString("Qtd fichas totais: "+i[1], 30,y+22);
        		y += 40;
        	}
    	}
    	
    	if(valorFicha != null) {
    		if(valorFicha == "1") {
    			g2d.drawImage(cI.getFichaV1(), 750, 200, null);
    		}
    		else if(valorFicha == "5") {
    			g2d.drawImage(cI.getFichaV5(), 750, 200, null);
    		}
    		else if(valorFicha == "10") {
    			g2d.drawImage(cI.getFichaV10(), 750, 200, null);
    		}
    		else if(valorFicha == "20") {
    			g2d.drawImage(cI.getFichaV20(), 750, 200, null);
    		}
    		else if(valorFicha == "50") {
    			g2d.drawImage(cI.getFichaV50(), 750, 200, null);
    		}
    		else if(valorFicha == "100") {
    			g2d.drawImage(cI.getFichaV100(), 750, 200, null);
    		}
    		g2d.setFont(new Font("Helvetica", Font.BOLD, 15));
    		g2d.setColor(Color.RED);
    		g2d.drawString("Valor Aposta Inicial: "+apostaInicialTotal, 700, 180);
    	}
    	if(botaoOk == true) {
    		g2d.setFont(new Font("Helvetica", Font.BOLD, 15));
    		g2d.setColor(Color.RED);
    		g2d.drawString("DEAL", 760, 290);
    	}
    	if(resultadoFinal != null) {
    		int y = 200;
    		for(String[] i : resultadoFinal) {
        		g2d.setFont(new Font("Helvetica", Font.BOLD, 11));
        		g2d.setColor(Color.RED);
        		g2d.drawString(i[0], 500,y);
        		g2d.drawString(i[1], 590,y+11);
        		y += 22;
        	}
    	}
    }
    
    public void redesenhar(List<String> mao) {
    	chave = mao;
    	repaint();
    }
    
    public void redesenhar(int [] conteudo) {
    	this.conteudo = conteudo;
    	repaint();
    }
    
    public void redesenhar(String[] valorFicha) {
    	this.valorFicha = valorFicha[0];
    	this.apostaInicialTotal = valorFicha[1];
    	repaint();
    }
    
    public void redesenhar(boolean botaoOk) {
    	this.botaoOk = botaoOk;
    	repaint();
    }
    
    public void redesenhar() {
    	this.botaoOk = false;
    	this.valorFicha = null;
    	repaint();
    }
    
    public void redesenhar(String nomeJogador) {
    	this.nomeJogador = nomeJogador;
    	repaint();
    }
    
    public void redesenhar2(List<String[]> infosJogadores) {
    	this.infosJogadores = infosJogadores;
    	repaint();
    }
    
    public void redesenhar3(List<String[]> resultadoFinal) {
    	this.resultadoFinal = resultadoFinal;
    	repaint();
    }
}
