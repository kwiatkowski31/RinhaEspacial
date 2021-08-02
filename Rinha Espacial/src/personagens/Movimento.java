package personagens;

//Imporante os métodos da clase event
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Classe Movimento, estende o KeyAdapter e implementa o KeyListener
//Esta classe é responsavel por capturar a teclad apertada e realizar tarefas sobre as teclas apertadas
public class Movimento extends KeyAdapter implements KeyListener {
	//Variaveis auxiliares
	private int movX, movY;//Guarda o movimento x e y, usada na classe 'Fases'
	private boolean tiro, permiteTiro, teclaEnter, teclaS;//Variaveis para saber se alguma tecla auxiliar foi apertada
	
	//Construtor
	public Movimento() {
		movX = 0;//Movimento inicia zerado
		movY = 0;
		
		//Permite o tiro do jogador, tiro(apertou para tirar) é false, tecla Enter e S tambem sao false(nao foram apertadas)
		permiteTiro = true;
		tiro = false;
		teclaEnter = false;
		teclaS = false;
	}
	
	//-----------Getters---------------//
	//Getters do movimento, do tiro apertado, do enter apertado e do S apertado
	public int getMovimentoX() {
		return movX;
	}
	public int getMovimentoY() {
		return movY;
	}
	public boolean getTiro() {
		return tiro;
	}
	public boolean getEnter() {
		return teclaEnter;
	}
	public boolean getTeclaS() {
		return teclaS;
	}
	
	//Setter, usado na classe 'Fases', se o tiro estiver visivel entao NÃO permite um novo tiro até que ele suma da tela ou acerte um inimigo
	public void setPermiteTiro(boolean b) {
		permiteTiro = b;
	}
	
	//Tecla pressionada, recebe o objeto do KeyEvent para capturar a tecla
	public void keyPressed(KeyEvent tecla) {
		//Variavel auxiliar recebe o codigo da tecla pressionada
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP) {//Se o codigo equivale a tecla da seta para cima
			movY = -7;//Auxiliar do movimento recebe -7(Na classe Fases eu chamo o get dessa varivel para atualizar o movimento do jogador)
		}
		if(codigo == KeyEvent.VK_DOWN) {//Mesma lógica
			movY = 7;
		}
		if(codigo == KeyEvent.VK_RIGHT) {//Mesma lógica
			movX = 7;
		}
		if(codigo == KeyEvent.VK_LEFT) {//Mesma lógica
			movX = -7;
		}
		if(codigo == KeyEvent.VK_SPACE) {//Se o espaço foi pressionado
			if(permiteTiro) {//Se permite o tiro(tiro nao esta visivel na tela)
				tiro = true;//O tiro é disparado(Na classe Fases eu chamo o get dessa variavel para poder atirar)
			}
		}
		if(codigo == KeyEvent.VK_ENTER) {//Se o enter foi pressionado, o aviso da teclaEnter recebe true
			teclaEnter = true;
		}
		if(codigo == KeyEvent.VK_S) {//Mesma lógica, mas para a tecla S
			teclaS = true;
		}
	}
	
	//Método para realizar tarefa se determinada tecla foi solta(deixou de ser pressionada)
	public void keyReleased(KeyEvent tecla) {
		//Mesma lógica que o KeyPressed
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP) {//Se a seta de cima foi solta, eu paro de movimentar, os demais seguem a mesma lógica
			movY = 0;
		}
		if(codigo == KeyEvent.VK_DOWN) {
			movY = 0;
		}
		if(codigo == KeyEvent.VK_RIGHT) {
			movX = 0;
		}
		if(codigo == KeyEvent.VK_LEFT) {
			movX = 0;
		}
		if(codigo == KeyEvent.VK_SPACE) {
			tiro = false;
		}
		if(codigo == KeyEvent.VK_ENTER) {
			teclaEnter = false;
		}
		if(codigo == KeyEvent.VK_S) {
			teclaS = false;
		}
	}
	
	//Funcionada como um KeyPressed, mas exclusivo para teclas de caractere
	@Override
	public void keyTyped(KeyEvent tecla) {//Nao foi usada, está aí somente para tirar o aviso de error da classe
		int codigo = tecla.getKeyCode();
	}
}