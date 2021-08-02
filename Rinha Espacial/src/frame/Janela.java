package frame;

//Importante biblioteca swing e classes da awt
import java.awt.Color;
import java.awt.Container;
import javax.swing.*;
//Importante a classe ouvinte
import controle.ControleDeTela;

//Classe Janela, estende o JFrame
public class Janela extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//Criando uma referencia de 'Container'
	Container con;
	//Criando referencias usadas na confecção do menu
	Painel menuTitulo, menuBotoes;
	Titulo titulo;
	Botao b1, b2, b3, b4, b5;
	//Criando a referencia da classe ouvinte
	ControleDeTela controleTela;
	
	//Construtor
	public Janela() {
		this.setTitle("Rinha Espacial");						//Seto o nome do jogo(nome que fica a janela)
		this.setSize(900, 800);									//Seto as dimensoes da tela
		this.setLayout(null);									//Seto o layout do frame como nulo
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Fecha ao clicar no X
		this.setLocationRelativeTo(null);						//Inicia no meio da tela
		this.setResizable(false);								//Permite redimensionar o frame(false)
		this.setAlwaysOnTop(false);								//Tela sempre por cima(false)
		this.getContentPane().setBackground(Color.black);		//Pego o pane padrao e deixo sua cor de fundo preta
		this.setVisible(true);									//Seto a visibilidade do frame(true)
		
		
		//Meu container recebe o pane padrao
		con = this.getContentPane();							
		
		//Instanciando o painel do titulo do menu(x, y, largura, altura)
		menuTitulo = new Painel(0,50,900,200);
		
		//Instanciando o rotulo do menu(Titulo do jogo)
		titulo = new Titulo("Rinha Espacial",130,30,900,200);
		
		//Instanciando o painel dos botoes(x, y, largura, altura)
		menuBotoes = new Painel(200,280,500,500);

		//Instanciando os botoes (nome, x, y, largura, altura)
		b1 = new Botao("Iniciar",120,50,250,70);
		b2 = new Botao("Carregar",120,120,250,70);
		b3 = new Botao("Recordes",120,190,250,70);
		b4 = new Botao("Creditos",120,260,250,70);
		b5 = new Botao("Sair",120,330,250,70);
		
		//Adicionando os botoes no painel de botoes do menu
		menuBotoes.add(b1);
		menuBotoes.add(b2);
		menuBotoes.add(b3);
		menuBotoes.add(b4);
		menuBotoes.add(b5);
		menuTitulo.add(titulo);
		
		//Adicionando os paineis no container
		con.add(menuBotoes);
		con.add(menuTitulo);
		
		//Instanciando o controle de tela(ouvinte)
		//Argumentos: o container - este frame - os botoes
		controleTela = new ControleDeTela(con,this,b1,b2,b3,b4,b5);	
	}
	
	//Metodo para setar os dois painels do menu como invisivel
	public void setMenuOff() {
		menuTitulo.setVisible(false);
		menuBotoes.setVisible(false);
	}
}
	