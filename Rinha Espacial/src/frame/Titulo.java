package frame;

//Chamando as classes usadas
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

//Classe Titulo, estende o JLabel
//Usada para criar o titulo no menu do jogo
public class Titulo extends JLabel {
	
	//Construtor que setta as caracteristicas deste label
	public Titulo(String nome, int x, int y, int largura, int altura) {
		super(nome);
		this.setBounds(x,y,largura,altura);
		this.setForeground(Color.white);
		this.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 100));
	}
}
