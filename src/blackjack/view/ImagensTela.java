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
    private int [] conteudo;
    
    public ImagensTela(CarregaImagens cI) {
    	this.cI = cI;
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
    }
    
    public void redesenhar(List<String> mao) {
    	chave = mao;
    	repaint();
    }
    
    public void redesenhar(int [] conteudo) {
    	this.conteudo = conteudo;
    	repaint();
    }
}
