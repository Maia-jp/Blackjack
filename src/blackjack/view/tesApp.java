package blackjack.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class tesApp {

	private JFrame frame;
	private JTextField jogador1Nome;
	private JTextField jogador2Nome;
	private JTextField jogador3Nome;
	private JTextField jogador4Nome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tesApp window = new tesApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tesApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("BlackJack");
		lblTitulo.setBounds(87, 11, 410, 57);
		frame.getContentPane().add(lblTitulo);
		
		JPanel lblj2 = new JPanel();
		lblj2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblj2.setBounds(10, 79, 564, 112);
		frame.getContentPane().add(lblj2);
		lblj2.setLayout(null);
		
		JLabel lblJ1 = new JLabel("Jogador 1");
		lblJ1.setBounds(10, 11, 85, 14);
		lblj2.add(lblJ1);
		
		JLabel lblJ2 = new JLabel("Jogador 2");
		lblJ2.setBounds(10, 36, 85, 14);
		lblj2.add(lblJ2);
		
		JLabel lblJ3 = new JLabel("Jogador 3");
		lblJ3.setBounds(10, 61, 85, 14);
		lblj2.add(lblJ3);
		
		JLabel lblJ4 = new JLabel("Jogador 4");
		lblJ4.setBounds(10, 90, 85, 14);
		lblj2.add(lblJ4);
		
		jogador1Nome = new JTextField();
		jogador1Nome.setToolTipText("Deixe em branco para nao jogadr");
		jogador1Nome.setBounds(105, 8, 159, 20);
		lblj2.add(jogador1Nome);
		jogador1Nome.setColumns(10);
		
		jogador2Nome = new JTextField();
		jogador2Nome.setToolTipText("Deixe em branco para nao jogadr");
		jogador2Nome.setColumns(10);
		jogador2Nome.setBounds(105, 33, 159, 20);
		lblj2.add(jogador2Nome);
		
		jogador3Nome = new JTextField();
		jogador3Nome.setToolTipText("Deixe em branco para nao jogadr");
		jogador3Nome.setColumns(10);
		jogador3Nome.setBounds(105, 58, 159, 20);
		lblj2.add(jogador3Nome);
		
		jogador4Nome = new JTextField();
		jogador4Nome.setToolTipText("Deixe em branco para nao jogadr");
		jogador4Nome.setColumns(10);
		jogador4Nome.setBounds(105, 87, 159, 20);
		lblj2.add(jogador4Nome);
		
		JButton btnComeçarPartida = new JButton("Começar partida");
		btnComeçarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnComeçarPartida.setBounds(351, 84, 150, 23);
		lblj2.add(btnComeçarPartida);
		
		JLabel lblJogadoresSelecionados = new JLabel("x jogadores selecionados");
		lblJogadoresSelecionados.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogadoresSelecionados.setVerticalAlignment(SwingConstants.TOP);
		lblJogadoresSelecionados.setBounds(346, 9, 160, 64);
		lblj2.add(lblJogadoresSelecionados);
		
		JButton btnCarregar = new JButton("Carregar Partida");
		btnCarregar.setBounds(10, 227, 135, 23);
		frame.getContentPane().add(btnCarregar);
	}
}
