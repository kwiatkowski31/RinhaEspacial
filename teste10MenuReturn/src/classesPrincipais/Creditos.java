package classesPrincipais;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classesAuxiliares.Botao;

public class Creditos extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel titulo, creditos;
	Botao b1;

	public Creditos() {
		this.setLayout(null);
		this.setBounds(0,0,900,900);//x,y,largura,altura
		this.setBackground(Color.black);
		this.setForeground(Color.white);

		//[x, y, largura, altura]
		titulo = new JLabel("Creditos");
		titulo.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 90));
		titulo.setBounds(280,-250,500,700);
		titulo.setForeground(Color.white);
		titulo.setBackground(Color.black);
		
		creditos = new JLabel();
		creditos.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 25));
		creditos.setBounds(50,-50,800,900);
		creditos.setForeground(Color.white);
		creditos.setBackground(Color.black);
		creditos.setText("<html>Técnicas de Programação<br>"
						+"Projeto desenvolvido por : Johny Kwiatkowski Oh [2090333]<br><br>"
						+ "Sprite do Jogador : <br>"
						+ "https://br.pinterest.com/pin/331577591312126212/<br><br>"
						+ "Sprites dos inimigos : <br>"
						+ "https://craftpix.net/freebies/free-pixel-art-enemy-spaceship-2d-sprites/<br><br>"
						+ "Sprites dos bosses : <br>"
						+ "https://www.pngwing.com/en/free-png-sgxpb<br>"
						+ "https://www.pngegg.com/en/png-ymocz<br>"
						+ "https://www.pngwing.com/en/free-png-scrwn<br>"
						+ "</html>");
		
		b1 = new Botao("Voltar",330,650,220,60);//x,y,largura,altura
		
		this.add(b1);
		this.add(titulo);
		this.add(creditos);
		
		System.out.println("CREDITOS");
	}
	
	public JButton getBotao() {
		return b1;
	}
}
