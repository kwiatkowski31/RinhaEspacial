package frame;

//Chamando as classes usadas
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

//Classe Botao que estende o JButton
//Usada para criar os botoes do menu
public class Botao extends JButton {
	
	//Construtor que setta as caracteristicas dos botoes
	public Botao(String nome, int x, int y, int largura, int altura) {
		super(nome);
		super.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 40));
		super.setBounds(x,y,largura,altura);
		super.setBackground(Color.black);
		super.setForeground(Color.white);
	}
}
