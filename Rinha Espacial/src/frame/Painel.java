package frame;

//Chamando as classes usadas
import java.awt.Color;

import javax.swing.JPanel;

//Classe Painel que estende o JPanel
//Usada para criar os paineis do menu
public class Painel extends JPanel {
	
	//Construtor que setta as caracteristicas dos paineis
	public Painel(int x, int y, int largura, int altura) {
		this.setLayout(null);
		this.setBounds(x,y,largura,altura);
		this.setBackground(Color.black);
	}
}
