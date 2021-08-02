package classesAuxiliares;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel {
	private static final long serialVersionUID = 1L;

	public Label(String nome, int x, int y, int largura, int altura, int tamanhoFonte) {
		super(nome);
		this.setBounds(x,y,largura,altura);
		this.setForeground(Color.white);
		this.setBackground(Color.black);
		this.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, tamanhoFonte));
	}
}
