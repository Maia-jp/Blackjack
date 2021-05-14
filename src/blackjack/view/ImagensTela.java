package blackjack.view;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.IOException;
import java.util.List;

public class ImagensTela extends JPanel{
    private List<String> chave;
    CarregaImagens cI;
    
    public ImagensTela(CarregaImagens cI, List<String> m) {
    	this.cI = cI;
    	chave = m;
     }
    public void paintComponent(Graphics g) {
    	g.drawImage(cI.getFundoBanca(),0,0, null);
        g.drawImage(cI.getFichaV1(), 60, 500, null);
        g.drawImage(cI.getFichaV5(), 190, 550, null);
        g.drawImage(cI.getFichaV10(), 320, 590, null);
        g.drawImage(cI.getFichaV20(), 460,590, null);
        g.drawImage(cI.getFichaV50(), 590,560, null);
        g.drawImage(cI.getFichaV100(), 720,520, null);
        g.drawImage(cI.getBaralhoAzul(), 340,340, null);
        g.drawImage(cI.getBaralhoVermelho(), 460,340, null);
        chamandoFor(g);
    }
    
    private void chamandoFor(Graphics g) {
    	int indice = 1;
    	for(String i : chave) {
    		g.drawImage(cI.getmapBaralho(i), indice+360,120, null);
    		indice+=20;
    	}
    }

}
