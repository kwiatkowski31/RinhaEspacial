package classesPrincipais;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class InstanciaFases extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	InstanciaFases(int saveLoad){
		//Aqui eu coloco o actionPerformed e o paint
		//Nas classes das fases eu instancio as personagens
		//e coisas relacionadas ao mapa
		//e depois puxo elas pra cá
		//Coloco aqui um controlador pra saber qual fase é
		//e aqui eu coloco tambem algum tipo de retorno para o menu
	}
	
	public void paint(Graphics gh) {
		Graphics2D g = (Graphics2D) gh;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
