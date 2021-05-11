package blackjack.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.JFrame;

public class TelaJogador extends JFrame {
	
	public TelaJogador() {
		initialize();
	}
	
	private void initialize() {
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(729, 10, 195, 133);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		Label label = new Label("Nome do Jogador");
		label.setAlignment(Label.CENTER);
		label.setBounds(45, 10, 98, 22);
		panel.add(label);
		
		Label label_1 = new Label("Aposta total: ");
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(10, 38, 98, 22);
		panel.add(label_1);
		
		Label label_1_1 = new Label("Dinheiro Total: ");
		label_1_1.setAlignment(Label.CENTER);
		label_1_1.setBounds(10, 66, 98, 22);
		panel.add(label_1_1);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(10, 10, 713, 641);
		this.getContentPane().add(canvas);
		this.setBounds(100, 100, 950, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
}
