package controle;

//Importando as classes usadas
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import creditos.Creditos;
//Importando as classes do pacote fases e a classe Janela(frame)
import fases.*;
import frame.Janela;
import recordes.Recordes;

//Classe ControleDeTela, usada como ouvinte aos botoes do menu da classe Janela(frame)
public class ControleDeTela implements ActionListener {
	
	//Referencia usada para guardar o container da classe Janela(frame)
	Container con;
	//Referencias para Janela e os botoes do menu
	Janela janela;
	JButton menub1, menub2, menub3, menub4, menub5;
	//Referencia para a fase, para os recordes e para os creditos
	Fases fases;
	Recordes recordes;
	Creditos creditos;

	//Construtor, recebe os argumentos enviados pela janela como parametro
	public ControleDeTela(Container con, Janela j, JButton b1, JButton b2, JButton b3, JButton b4, JButton b5) {
		//Este con recebe o endereço do con enviado pela Janela
		this.con = con;		
		//Esta referencia de janela recebe o endereço da propria janela que instanciou o objeto desta classe
		janela = j;
		
		//Referencia dos botoes recebendo os endereços do botoes
		//Como a classe 'Botao' extende a JButton, entao posso usar referencias do JButton para guardar
		//Objetos de Botao
		menub1 = b1;
		menub2 = b2;
		menub3 = b3;
		menub4 = b4;
		menub5 = b5;
		
		//Adicionando os botoes como ouvintes das ações(clique)
		menub1.addActionListener(this);
		menub2.addActionListener(this);
		menub3.addActionListener(this);
		menub4.addActionListener(this);
		menub5.addActionListener(this);
	}
	
	//Reescrevendo o metodo para realizar tarefas se alguma ação ocorrer
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Se o botao menub1(referencia de b1) for clicado, execute a tarefa dentro do if
		if(e.getSource() == menub1) {//Novo Jogo
			//Deixa os dois paineis do menu como invisiveis
			janela.setMenuOff();
			
			//Instancia um objeto de fase, seta o layout como nulo, adiciona este painel ao container e requisita o foco
			fases = new Fases(1);//Argumento '1' para avisar que é um novo jogo
			fases.setLayout(null);
			con.add(fases);
			fases.requestFocusInWindow();
	        fases.setFocusable(true);
		}
		//Se o botao menub2(referencia de b2) for clicado, carrega o jogo
		if(e.getSource() == menub2) {//Carregar
			//Mesma lógica que o de cima, mas aqui ele envia o argumento '2' para carregar o jogo 
			janela.setMenuOff();
			
			fases = new Fases(2);
			fases.setLayout(null);
			con.add(fases);
			fases.requestFocusInWindow();
	        fases.setFocusable(true);
		}
		//Se o botao menub3(referencia de b3) for clicado, mostra os recordes do jogo
		if(e.getSource() == menub3) {//Recordes
			//Mesma lógica, mas para o painel dos Recordes
			janela.setMenuOff();
			
			recordes = new Recordes();
			con.add(recordes);
			recordes.requestFocusInWindow();
	        recordes.setFocusable(true);
		}
		//Se o botao menub4(referencia de b4) for clicado, mostra os creditos
		if(e.getSource() == menub4) {//Creditos
			//Mesma lógica, mas para o painel dos Creditos
			janela.setMenuOff();
			
			creditos = new Creditos();
			con.add(creditos);
			creditos.requestFocusInWindow();
			creditos.setFocusable(true);
		}
		//Se o botao menub5(referencia de b5) for clicado, fecha o jogo
		if(e.getSource() == menub5) {//Sair
			System.exit(0);
		}
	}

}
