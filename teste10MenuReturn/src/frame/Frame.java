package frame;

import java.awt.Color;
import java.awt.Container;
import javax.swing.*;

import controle.ControleDeTela;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	Container con;
	
	ControleDeTela controleTela;
	
	public Frame() {
		this.setTitle("Rinha Espacial");						//Seto o nome do jogo(nome que fica a janela)
		this.setSize(900, 800);									//Seto as dimensoes da tela
		this.setLayout(null);									//Seto o layout do frame como nulo
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Fecha ao clicar no X
		this.setLocationRelativeTo(null);						//Inicia no meio da tela
		this.setResizable(false);								//Permite redimensionar o frame(false)
		this.setAlwaysOnTop(false);								//Tela sempre por cima(false)
		this.getContentPane().setBackground(Color.black);		//Pego o pane padrao e deixo sua cor de fundo preta

		con = this.getContentPane();							

		controleTela = new ControleDeTela(con);	
		
		this.setVisible(true);
	}
}
	