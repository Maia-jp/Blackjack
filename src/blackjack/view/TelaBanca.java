package blackjack.view;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaBanca extends JFrame implements Observador{
	private final int LARG_DEFAULT=891;
	private final int ALT_DEFAULT=700;
	public TelaBanca(CarregaImagens cI, List<String> m, int valorDealer) {
		inicializar(cI, m);
	}
	private void inicializar(CarregaImagens cI, List<String> m) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		setResizable(false);
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x+100,y,LARG_DEFAULT,ALT_DEFAULT);
		ImagensTela s = new ImagensTela(cI, m);
        add(s);
        s.repaint();
        
	}
	
	//Metodos Observador
	@Override
	public void executar(Object obj,int ID) {
		switch (ID)
		{
		     case 1:
		    	 if(obj.getClass().equals(ArrayList.class)) {
		    		 ArrayList<String> maoDealer = (ArrayList<String>) obj;
		    		 System.out.println("DEALER:"+maoDealer);
		    
		    	 }else {
		    		 System.out.println("[ERRO][Tela Banca][Observer] ID 1 deve receber um ArrayList, foi recebido:" + obj.getClass());
		    	 }
		     
		}
				
	}
	
}
