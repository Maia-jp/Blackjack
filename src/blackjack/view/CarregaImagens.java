/* Blackjack
 * Alexandre Bomfim Junior - 1921241
 * Jose Lucas Teixeira Xavier - 1921254
 * Joao Pedro Maia - 1920354
 */
package blackjack.view;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;

class CarregaImagens {
	private BufferedImage fundoBanca;
	private BufferedImage fichaV1;
	private BufferedImage fichaV5;
	private BufferedImage fichaV10;
	private BufferedImage fichaV20;
	private BufferedImage fichaV50;
	private BufferedImage fichaV100;
	private BufferedImage baralhoAzul;
	private BufferedImage baralhoVermelho;
	private BufferedImage []vetBaralho = new BufferedImage[52];
	
	private LinkedHashMap <String, BufferedImage> mapBaralho= new LinkedHashMap<String, BufferedImage>();
	
	CarregaImagens() {
	        try {
	        	fundoBanca = ImageIO.read(getClass().getResource("/blackjackBKG.png"));
	        	fichaV1 = ImageIO.read(getClass().getResource("/ficha 1$.png"));
	        	fichaV5 = ImageIO.read(getClass().getResource("/ficha 5$.png"));
	        	fichaV10 = ImageIO.read(getClass().getResource("/ficha 10$.png"));
	        	fichaV20 = ImageIO.read(getClass().getResource("/ficha 20$.png"));
	        	fichaV50 = ImageIO.read(getClass().getResource("/ficha 50$.png"));
	        	fichaV100 = ImageIO.read(getClass().getResource("/ficha 100$.png"));
	        	baralhoAzul = ImageIO.read(getClass().getResource("/deck1.gif"));
	        	baralhoVermelho = ImageIO.read(getClass().getResource("/deck2.gif"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        carregaBaralho();
	        this.setMapBaralho(mapBaralho);
	}
	
	void carregaBaralho(){
		try {
			vetBaralho[0] = ImageIO.read(getClass().getResource("/ac.gif"));
			vetBaralho[1] = ImageIO.read(getClass().getResource("/ad.gif"));
			vetBaralho[2] = ImageIO.read(getClass().getResource("/ah.gif"));
			vetBaralho[3] = ImageIO.read(getClass().getResource("/as.gif"));
			
			vetBaralho[4] = ImageIO.read(getClass().getResource("/2c.gif"));
			vetBaralho[5] = ImageIO.read(getClass().getResource("/2d.gif"));
			vetBaralho[6] = ImageIO.read(getClass().getResource("/2h.gif"));
			vetBaralho[7] = ImageIO.read(getClass().getResource("/2s.gif"));
			
			vetBaralho[8] = ImageIO.read(getClass().getResource("/3c.gif"));
			vetBaralho[9] = ImageIO.read(getClass().getResource("/3d.gif"));
			vetBaralho[10] = ImageIO.read(getClass().getResource("/3h.gif"));
			vetBaralho[11] = ImageIO.read(getClass().getResource("/3s.gif"));
			
			vetBaralho[12] = ImageIO.read(getClass().getResource("/4c.gif"));
			vetBaralho[13] = ImageIO.read(getClass().getResource("/4d.gif"));
			vetBaralho[14] = ImageIO.read(getClass().getResource("/4h.gif"));
			vetBaralho[15] = ImageIO.read(getClass().getResource("/4s.gif"));
			
			vetBaralho[16] = ImageIO.read(getClass().getResource("/5c.gif"));
			vetBaralho[17] = ImageIO.read(getClass().getResource("/5d.gif"));
			vetBaralho[18] = ImageIO.read(getClass().getResource("/5h.gif"));
			vetBaralho[19] = ImageIO.read(getClass().getResource("/5s.gif"));
			
			vetBaralho[20] = ImageIO.read(getClass().getResource("/6c.gif"));
			vetBaralho[21] = ImageIO.read(getClass().getResource("/6d.gif"));
			vetBaralho[22] = ImageIO.read(getClass().getResource("/6h.gif"));
			vetBaralho[23] = ImageIO.read(getClass().getResource("/6s.gif"));
			
			vetBaralho[24] = ImageIO.read(getClass().getResource("/7c.gif"));
			vetBaralho[25] = ImageIO.read(getClass().getResource("/7d.gif"));
			vetBaralho[26] = ImageIO.read(getClass().getResource("/7h.gif"));
			vetBaralho[27] = ImageIO.read(getClass().getResource("/7s.gif"));
			
			vetBaralho[28] = ImageIO.read(getClass().getResource("/8c.gif"));
			vetBaralho[29] = ImageIO.read(getClass().getResource("/8d.gif"));
			vetBaralho[30] = ImageIO.read(getClass().getResource("/8h.gif"));
			vetBaralho[31] = ImageIO.read(getClass().getResource("/8s.gif"));
			
			vetBaralho[32] = ImageIO.read(getClass().getResource("/9c.gif"));
			vetBaralho[33] = ImageIO.read(getClass().getResource("/9d.gif"));
			vetBaralho[34] = ImageIO.read(getClass().getResource("/9h.gif"));
			vetBaralho[35] = ImageIO.read(getClass().getResource("/9s.gif"));
			
			vetBaralho[36] = ImageIO.read(getClass().getResource("/tc.gif"));
			vetBaralho[37] = ImageIO.read(getClass().getResource("/td.gif"));
			vetBaralho[38] = ImageIO.read(getClass().getResource("/th.gif"));
			vetBaralho[39] = ImageIO.read(getClass().getResource("/ts.gif"));
			
			vetBaralho[40] = ImageIO.read(getClass().getResource("/jc.gif"));
			vetBaralho[41] = ImageIO.read(getClass().getResource("/jd.gif"));
			vetBaralho[42] = ImageIO.read(getClass().getResource("/jh.gif"));
			vetBaralho[43] = ImageIO.read(getClass().getResource("/js.gif"));
			
			vetBaralho[44] = ImageIO.read(getClass().getResource("/qc.gif"));
			vetBaralho[45] = ImageIO.read(getClass().getResource("/qd.gif"));
			vetBaralho[46] = ImageIO.read(getClass().getResource("/qh.gif"));
			vetBaralho[47] = ImageIO.read(getClass().getResource("/qs.gif"));
			
			vetBaralho[48] = ImageIO.read(getClass().getResource("/kc.gif"));
			vetBaralho[49] = ImageIO.read(getClass().getResource("/kd.gif"));
			vetBaralho[50] = ImageIO.read(getClass().getResource("/kh.gif"));
			vetBaralho[51] = ImageIO.read(getClass().getResource("/ks.gif"));
		}catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private void setMapBaralho(LinkedHashMap <String, BufferedImage> mapBaralho) {
        this.mapBaralho.put("Ca",vetBaralho[0]);
        this.mapBaralho.put("Da",vetBaralho[1]);
        this.mapBaralho.put("Ha",vetBaralho[2]);
        this.mapBaralho.put("Sa",vetBaralho[3]);
        
        this.mapBaralho.put("C2",vetBaralho[4]);
        this.mapBaralho.put("D2",vetBaralho[5]);
        this.mapBaralho.put("H2",vetBaralho[6]);
        this.mapBaralho.put("S2",vetBaralho[7]);
        
        this.mapBaralho.put("C3",vetBaralho[8]);
        this.mapBaralho.put("D3",vetBaralho[9]);
        this.mapBaralho.put("H3",vetBaralho[10]);
        this.mapBaralho.put("S3",vetBaralho[11]);
        
        this.mapBaralho.put("C4",vetBaralho[12]);
        this.mapBaralho.put("D4",vetBaralho[13]);
        this.mapBaralho.put("H4",vetBaralho[14]);
        this.mapBaralho.put("S4",vetBaralho[15]);
        
        this.mapBaralho.put("C5",vetBaralho[16]);
        this.mapBaralho.put("D5",vetBaralho[17]);
        this.mapBaralho.put("H5",vetBaralho[18]);
        this.mapBaralho.put("S5",vetBaralho[19]);
        
        this.mapBaralho.put("C6",vetBaralho[20]);
        this.mapBaralho.put("D6",vetBaralho[21]);
        this.mapBaralho.put("H6",vetBaralho[22]);
        this.mapBaralho.put("S6",vetBaralho[23]);
        
        this.mapBaralho.put("C7",vetBaralho[24]);
        this.mapBaralho.put("D7",vetBaralho[25]);
        this.mapBaralho.put("H7",vetBaralho[26]);
        this.mapBaralho.put("S7",vetBaralho[27]);
        
        this.mapBaralho.put("C8",vetBaralho[28]);
        this.mapBaralho.put("D8",vetBaralho[29]);
        this.mapBaralho.put("H8",vetBaralho[30]);
        this.mapBaralho.put("S8",vetBaralho[31]);
        
        this.mapBaralho.put("C9",vetBaralho[32]);
        this.mapBaralho.put("D9",vetBaralho[33]);
        this.mapBaralho.put("H9",vetBaralho[34]);
        this.mapBaralho.put("S9",vetBaralho[35]);
        
        this.mapBaralho.put("C10",vetBaralho[36]);
        this.mapBaralho.put("D10",vetBaralho[37]);
        this.mapBaralho.put("H10",vetBaralho[38]);
        this.mapBaralho.put("S10",vetBaralho[39]);
        
        this.mapBaralho.put("Cj",vetBaralho[40]);
        this.mapBaralho.put("Dj",vetBaralho[41]);
        this.mapBaralho.put("Hj",vetBaralho[42]);
        this.mapBaralho.put("Sj",vetBaralho[43]);
        
        this.mapBaralho.put("Cq",vetBaralho[44]);
        this.mapBaralho.put("Dq",vetBaralho[45]);
        this.mapBaralho.put("Hq",vetBaralho[46]);
        this.mapBaralho.put("Sq",vetBaralho[47]);
        
        this.mapBaralho.put("Ck",vetBaralho[48]);
        this.mapBaralho.put("Dk",vetBaralho[49]);
        this.mapBaralho.put("Hk",vetBaralho[50]);
        this.mapBaralho.put("Sk",vetBaralho[51]);
    }
	
	BufferedImage getFundoBanca() {
		return fundoBanca;
	}
	BufferedImage getFichaV1() {
		return fichaV1;
	}
	BufferedImage getFichaV5() {
		return fichaV5;
	}
	BufferedImage getFichaV10() {
		return fichaV10;
	}
	BufferedImage getFichaV20() {
		return fichaV20;
	}
	BufferedImage getFichaV50() {
		return fichaV50;
	}
	BufferedImage getFichaV100() {
		return fichaV100;
	}
	BufferedImage getBaralhoAzul() {
		return baralhoAzul;
	}
	BufferedImage getBaralhoVermelho() {
		return baralhoVermelho;
	}
	BufferedImage getmapBaralho(String chave) {
		return this.mapBaralho.get(chave);
	}
}
