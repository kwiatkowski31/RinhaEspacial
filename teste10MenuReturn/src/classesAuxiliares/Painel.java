package classesAuxiliares;

import java.awt.Color;

import javax.swing.JPanel;

public class Painel extends JPanel {
	private static final long serialVersionUID = 1L;

	public Painel(int x, int y, int largura, int altura) {
		this.setLayout(null);
		this.setBounds(x,y,largura,altura);
		this.setBackground(Color.black);
	}
}
