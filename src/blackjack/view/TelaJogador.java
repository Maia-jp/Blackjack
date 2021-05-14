package blackjack.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.JButton;
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
		
		Panel panelButton = new Panel();
		panelButton.setBackground(Color.WHITE);
		panelButton.setBounds(729, 160, 195, 133);
		this.getContentPane().add(panelButton);
		panelButton.setLayout(null);
		
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
		
		JButton jb = new JButton("STAND");
		jb.setBounds(50,9,100,25);
		jb.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb.setForeground(Color.DARK_GRAY);
		jb.setBackground(Color.LIGHT_GRAY);
		panelButton.add(jb);
		
		JButton jb2 = new JButton("HIT");
		jb2.setBounds(50,39,100,25);
		jb2.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb2.setForeground(Color.DARK_GRAY);
		jb2.setBackground(Color.LIGHT_GRAY);
		panelButton.add(jb2);
		
		JButton jb3 = new JButton("DOUBLE");
		jb3.setBounds(50,69,100,25);
		jb3.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb3.setForeground(Color.DARK_GRAY);
		jb3.setBackground(Color.LIGHT_GRAY);
		panelButton.add(jb3);
		
		JButton jb4 = new JButton("SPLIT");
		jb4.setBounds(50,99,100,25);
		jb4.setFont(new Font("Helvetica", Font.BOLD, 15));
		jb4.setForeground(Color.DARK_GRAY);
		jb4.setBackground(Color.LIGHT_GRAY);
		panelButton.add(jb4);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(10, 10, 713, 641);
		this.getContentPane().add(canvas);
		this.setBounds(100, 100, 950, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
}
