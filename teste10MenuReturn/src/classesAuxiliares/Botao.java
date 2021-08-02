package classesAuxiliares;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Botao extends JButton {
	private static final long serialVersionUID = 1L;

	public Botao(String nome, int x, int y, int largura, int altura) {
		super(nome);
		super.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 40));
		super.setBounds(x,y,largura,altura);
		super.setBackground(Color.black);
		super.setForeground(Color.white);
//		super.setBorder(null);
	}
}
