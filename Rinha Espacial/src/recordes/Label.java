package recordes;

//Importando as classes usadas
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

//Classe Label, estende o JLabel
//Usada para criar label predefinido
public class Label extends JLabel {
	
	//Construtor, setta as caracteristicas
	public Label(String nome, int x, int y, int largura, int altura, int tamanhoFonte) {
		super(nome);
		this.setBounds(x,y,largura,altura);
		this.setForeground(Color.white);
		this.setBackground(Color.black);
		this.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, tamanhoFonte));
	}
}
