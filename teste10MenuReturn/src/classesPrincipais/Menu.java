package classesPrincipais;

import javax.swing.JButton;
import javax.swing.JPanel;

import classesAuxiliares.*;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Painel menuTitulo, menuBotoes;
	Label titulo;
	Botao b1, b2, b3, b4, b5;
	
	public Menu() {
		this.setLayout(null);
		menuTitulo = new Painel(0,50,900,200);
		
		titulo = new Label("Rinha Espacial",130,30,900,200,100);
		
		menuBotoes = new Painel(200,280,500,500);

		//[nome, x, y, largura, altura]
		b1 = new Botao("Iniciar",120,50,250,70);
		b2 = new Botao("Carregar",120,120,250,70);
		b3 = new Botao("Recordes",120,190,250,70);
		b4 = new Botao("Creditos",120,260,250,70);
		b5 = new Botao("Sair",120,330,250,70);
		
		menuBotoes.add(b1);
		menuBotoes.add(b2);
		menuBotoes.add(b3);
		menuBotoes.add(b4);
		menuBotoes.add(b5);
		
		menuTitulo.add(titulo);
		
//		this.add(menuTitulo);
//		this.add(menuBotoes);
	}
	
	public JButton getBotao(int nBotao) {
		JButton temp = null;
		
		switch(nBotao) {
			case 1:
				temp = b1;
				break;
			case 2:
				temp = b2;
				break;
			case 3:
				temp = b3;
				break;
			case 4:
				temp = b4;
				break;
			case 5:
				temp = b5;
				break;
		}
		
		return temp;
	}
	
	public JPanel getPanel(int nPanel) {
		JPanel temp = null;
		
		switch(nPanel) {
			case 1:
				temp = menuTitulo;
				break;
			case 2:
				temp = menuBotoes;
				break;
		}
		
		return temp;
	}
}
