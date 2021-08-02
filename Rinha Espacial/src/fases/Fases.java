package fases;

//Classes e métodos importados
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import carregar.*;
import imagens.Sprites;
import personagens.*;
import salvar.*;

//Classe Fases, resonsavel pela construção e controle das fases, personagens, tiro, etc
//Estende o JPanel e implementa o ActionListener
public class Fases extends JPanel implements ActionListener {
	//Serial, coloquei pra tirar o sinal de alerta
	private static final long serialVersionUID = 1L;
	
	//Referencia 'sp' para a classe que recebe as imagens
	//E duas referencias da classe Image do pacote awt para pintura das imagens
	Sprites sp;
	Image img, vida;
	
	//Referencia dos personagens e tiros, monstros sao do tipo lista pois eu instancio 
	//varios monstros durante as fases
	Personagens p1, boss;//p1 se refere ao jogador
	private List<Personagens> monstros;
	Tiro tiroJogador, tiroBoss;
	
	//Referencia para a classe Movimento(KeyAdapter e KeyListener)
	Movimento movimento;
	
	//Referencia para Timer
	Timer timer;
	
	//Variaveis auxiliares, o 'contador' funciona como um cronometro para realizar alguma tarefa
	//O 'cont' serve como um cronometro de duração para o aviso da fase, o 'contSave' também, mas para o aviso do save
	//'pontos' vai guardando a pontuação do jogador, 'faseAtual' guarda o estado do jogo(ex: estado 1 -> jogo vai começar)
	//'controle' serve para avançar de fase, 'avisoSave' serve para avisar que o jogo foi salvo
	private int contador, cont = 0, contSave = 0, pontos, faseAtual;
	private boolean controle, avisoSave;
	
	//Uso para o movimento do boss de um lado para o outro
	private boolean direita = true, esquerda = false;
	
	//Referencias para um TextField e um botao usados para salvar o recorde
	JTextField nome;
	JButton salvar;
	
	//Duas referencias de SalvarDAO, classe abstrata de salvamento
	//Referencia tambem para carregar a fase
	SalvarDAO sv, rs;//Save, RecordeSave
	CarregarDAO cr;
	
	//Construtor de 'Fases', recebe como parametro o 'saveLoad' que informa se o jogo
	//foi Iniciado, ou se ele foi Carregado(opções do menu)
	public Fases(int saveLoad) {	
		
		//Seto as dimensões deste painel
		//Instancio o objeto de 'Sprites' para pegar as imagens
		this.setBounds(0,0,900,800);//x,y,largura,altura
		sp = new Sprites();
		
		//Se foi iniciado, a fase(estado) recebe 1
		if(saveLoad == 1) {//Novo Jogo
			faseAtual = 1;
		} 
		//Se foi carregado, eu instancio o objeto para a classe de carregamento e a fase 
		//recebe o retorno do metodo de 'cr' que vai ler o arquivo 'save.txt' e pegar a fase que foi salva
		else if(saveLoad == 2) {//Carregar Jogo
			cr = new CarregarImp();
			faseAtual = cr.carregar();
		}
		//Instanciando o timer e dando start nele
		timer = new Timer(15, this);
		timer.start();
		
		//Inicializando a fase e atribuindo 'true' para o controle(vai permitir o aviso que aparece na tela -> FASE 1)
		inicializaFase();
		controle = true;
			
	}
//----------------------------------------------INICIALIZANDO A FASE--------------------------------------------------------//
	
	//Metodo responsavel por inicializar a fase, os personagens, tiro, cenario de fundo, e etc, sempre que pedido
	public void inicializaFase() {
		//-----------Get da img de Fundo e Barra de vida---------------//
		//Atributos do cenario e da barra de vida recebendo suas respectivas imagens
		img = sp.getImgFundo(faseAtual);
		vida = sp.getImgVida(1);
		
		//-----------Criando os Persongens---------------//
		//Instanciando os personagens e os tiros
		//Ordem dos argumentos: (tipo de Personagem, usado para pegar a imagem), posicao x, posicao y, quantidade de vida
		//Casos: 1[Jogador] - [2~4]Monstros - [5~7]Bosses
		p1 = new Personagens(1,400,500, 7);
		boss = new Personagens(faseAtual+4,335,-1000,4);
		if(monstros == null) {//Se for a primeira vez que este metodo foi chamado, monstro estará com null
			monstros = new ArrayList<Personagens>();
		}
		
		//Ordem dos argumentos: (tipo de tiro, usado para pegar a imagem), posicao x, posicao y, e o limite do tiro
		//A posicao deles estao negativos para ficarem fora da tela quando eu instanciar
		tiroJogador = new Tiro(0,-100,-100,800);//tiro do jogador -> de baixo para cima (Y dimminui visto que no java o y aumenta quando vai para baixo)
		tiroBoss = new Tiro(2,-100,-100,-10);//tiro do boss -> de cima para baixo
		
		//-----------Criando Controle do Teclado---------------//
		//Instanciando objeto de 'Movimento', usado para atualizar a posicao do jogador se alguma seta for teclada
		//Adiciono aqui o KeyListener que será o 'movimento'
		movimento = new Movimento();
		this.addKeyListener(movimento);
		
		//Este if eu uso para caso o jogador morrer e aperta o 'Enter' para reiniciar
		//Se a faseAtual for igual a 1, eu zero os pontos
		if(faseAtual == 1) {
			pontos = 0;
		}
		
		//Zero tambem o contador(variavel que serve como cronometro do aviso da fase)
		//A variavel controle(usada para mostrar o aviso da fase) tambem recebe false
		//E o avisoSave recebe false tambem já que este metodo tambem serve como um inicializador da fase
		contador = 0;
		controle = false;
		avisoSave = false;
	}

//---------------------------------------------CHECANDO MUDANÇA DE FASE-----------------------------------------------------//
	
	//Método usado para atualizar a fase se o boss foi morto
	public void atualizaFase() {
		//Se o controle for true, eu mostro a imagem que avisa a fase
		if(controle) {
			//Essa imagem fica na tela até que a variavel 'cont' passe de 100
			img = sp.getImgFundo(faseAtual + 3);
			
			//Se o 'cont' passar de 100 e a faseAtual(estado do jogo) for menor que 4(até a 3º fase do jogo)
			//Entao inicializa a proxima fase do jogo e zera o 'cont' para a proxima
			if(cont > 100 && faseAtual < 4) {//Intervalo para apresentar o aviso da fase
				inicializaFase();
				faseAtual++;
				cont = 0;
			} 
			//Se a faseAtual(estado) for igual a 4, entao a 'faseAtual' recebe 3 e eu inicializo a fase
			//Esse 'else if' eu uso para caso o player carregar um jogo que tava na fase 3
			else if(faseAtual == 4) {
				faseAtual = 3;
				inicializaFase();
				faseAtual++;
				cont = 0;
			}
		}
	}
	
//-----------------------------------------------------COLISÕES-------------------------------------------------------------//
	
	//Método que checa as colisoes no jogo
	public void checarColisao(){
		//Referencias da classe Rectangle, retangulo do jogador pega as dimensoes do jogador
		Rectangle jogadorRec = p1.getBounds();
		Rectangle tiroJogadorRec, tiroBossRec, monstroRec, bossRec;
		
		//Referencias da classe Rectangle para os tiros, pega as dimensoes dos mesmos
		tiroJogadorRec = tiroJogador.getBounds();
		tiroBossRec = tiroBoss.getBounds();
		
		//TiroJogador com Monstros
		//Uso um for pra percorrer todos os monstros
		for(int j = 0; j < monstros.size(); j++) {
			//Retangulo do monstro recebe o monstro(j)
			monstroRec = monstros.get(j).getBounds();
			
			//Verifico atraves do metodo 'intersects' se os dois retangulos se intersectaram
			if(tiroJogadorRec.intersects(monstroRec)) {
				//Se sim, o tiro do jogador recebe uma posicao fora da tela(para evitar que um tiro mate mais de um monstro)
				//Removo aquele monstro que foi morto e aumento os pontos em 2(sem usar o metodo pois a variavel é daqui)
				tiroJogador.setPosX(-200);
				monstros.remove(j);
				pontos += 2;
			}
		}
		//Player com Monstros
		//Basicamente mesma lógica que o de cima
		for(int i = 0; i < monstros.size(); i++) {
			monstroRec = monstros.get(i).getBounds();
			
			if(jogadorRec.intersects(monstroRec)) {
				//Se o player tocou o monstro, eu removo o monstro
				//Diminuo a vida do jogador em 1
				//Se o jogador só tiver 1 de vida, entao ele morre
				monstros.remove(i);
				if(p1.getVida() > 1) {
					p1.setVida(-1);
				} else if(p1.getVida() == 1){
					p1.setVida(-1);
					p1.setEstaVivo(false);
				}
			}
		}
		//Player com Boss
		//Mesma lógica
		bossRec = boss.getBounds();
		if(jogadorRec.intersects(bossRec)) {
			//Se o player tocar no boss, ele é empurrado para trás e perde 1 de vida
			p1.setPosY(100);
			
			if(p1.getVida() > 1) {
				p1.setVida(-1);
			} else if(p1.getVida() == 1){
				p1.setVida(-1);
				p1.setEstaVivo(false);
			}
		}
		//Player com TiroBoss
		//Mesma lógica
		if(jogadorRec.intersects(tiroBossRec)) {
			//Se o tiro do boss tocar o jogador, eu mando o tiro para fora da tela
			//Faço isso pois 1 tiro era suficiente para matar o jogador, o tiro ia passando pelo jogador
			//E a cada pixel que o tiro movimentava contava como um toque
			tiroBoss.setPosX(-100);
			
			if(p1.getVida() > 1) {
				p1.setVida(-1);
			} else if(p1.getVida() == 1){
				p1.setVida(-1);
				p1.setEstaVivo(false);
			}
		}
		//TiroJogador com Boss
		//Mesma lógica
		if(tiroJogadorRec.intersects(bossRec)) {
			//Se o tiro do jogador tocar no boss, eu mando o tiro para fora da tela
			tiroJogador.setPosX(-200);
			
			if(boss.getVida() >= 1) {
				//Jogador tem 2 de dano
				boss.setVida(-2);
			} else {
				boss.setEstaVivo(false);
				tiroBoss.setPosX(-100);
				pontos += 100;
			}
		}
	}
	
//-------------------------------------------------------PAINT--------------------------------------------------------------//
	
	//Método para pintura na tela
	public void paint(Graphics g) {
		//Casting de Graphics para Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		
		//-----------Cenario de fundo---------------//
		//Se a fase(estado) for menor ou igual ao 4(parte jogavél), entao pinta o cenario
		if(faseAtual <= 4) {
			g2.drawImage(img, 0, 0, null);//Imagem, x, y, ImageObserver
		}
		
		//-----------Tiro---------------//
		//Se o tiro do jogador já saiu da tela, entao permite mostrar a imagem do tiro novamente
		//Argumento do drawImage: imagem do tiro, posicao X, posicao Y e ImageObserver
		if(tiroJogador.getLimiteTiro() > -10) {
			g2.drawImage(tiroJogador.getImg(), tiroJogador.getPosX()+45, tiroJogador.getPosY()+25, this);
		}
		//Mesma lógica que o de cima, mas se o tiro do boss passsou do y == 900(borda inferior), permite pintar
		if(tiroBoss.getLimiteTiro() <= 900) {
			g2.drawImage(tiroBoss.getImg(), tiroBoss.getPosX()+45, tiroBoss.getPosY()+100, this);
		}
		
		//-----------Jogador---------------//
		//Se o jogador está vivo, permite pintar
		if(p1.getEstaVivo()) {
			g2.drawImage(p1.getImg(), p1.getPosX(), p1.getPosY(), this);
		}

		//-----------Monstros---------------//
		//For para percorrer todos os monstros
		for(int i = 0; i < monstros.size(); i++) {
			//Pego monstro por monstro e pinto eles
			Personagens monstro = monstros.get(i);
			g2.drawImage(monstro.getImg(), monstro.getPosX(), monstro.getPosY(), this);
		}
		
		//-----------Boss---------------//
		//Se o 'contador'(cronometro) passar dos 1100, permite pintar o boss
		//1100 pois os monstros só aparecem até o 1000, entao eu dou um intervalo de 100 até o boss aparecer
		if(contador > 1100) {
			//Se o boss está vivo, permite pintar
			if(boss.getEstaVivo()) {
				g2.drawImage(boss.getImg(), boss.getPosX(), boss.getPosY(), this);
			}
		}
		
		//-----------Pontuacao---------------//
		//Pintando com o drawString a pontuação do jogador
		g2.setColor(Color.white);//Cor da string
		g2.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 40));//Fonte da String
		g2.drawString(("Pontuacao: " + pontos), 630, 40);//String, posicao x, posicao y

		//-----------Barra de Vida---------------//
		//Pintando a barra de vida do jogador
		g2.drawImage(vida, 10, 10, null);//Imagem, posicao x, posicao y, ImageObserver
		
		//-----------Game Over---------------//
		//Se o jogador morreu e a faseAtual for menor que 5(até onde é jogavel -> [faseAtual <= 4 ou < 5])
		if(!p1.getEstaVivo() && faseAtual < 5) {
			//Pinto com o drawString que ele morreu, para que o cenario de fundo fique visivel
			g2.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 70));
			g2.drawString(("Você morreu"), 250, 300);//x,y
			g2.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 50));
			g2.drawString(("Aperte ENTER para continuar"), 150, 400);//x,y
		}
		
		//-----------Aviso do estagio atual---------------//
		//Se o 'controle' for true, entao permite pintar o aviso da fase(ex: FASE 1)
		if(controle) {
			g2.drawImage(img, 0, 0, null);
		}
		
		//-----------Fim de Jogo---------------//
		//Se o jogador chegou na fase 5(estado 5) e o boss morreu(talvez isso seja redundante) entao permite pintar
		if(faseAtual == 5 && !boss.getEstaVivo()) {
			//Pintando a imagem de: Parabéns, digite seu nome
			img = sp.getImgFundo(7);
			g2.drawImage(img, 0, 0, null);
			faseAtual = 6;//Estado do jogo recebe 6
		}
		//Se a fase(estado do jogo) estiver no 6, entao instancia o textfield e o botao para salvar nos 'recordes'
		//Uso essa fase 6 para que siga esta ordem de instrução e para que o textfield e o botao ficassem em cima da imagem
		//O textfield ficou, mas o botao para aparecer necessita que eu passe o mouse em cima dele(fica em baixo do textfield)
		if(faseAtual == 6) {
			//Instanciando o text field
			nome = new JTextField("");
			nome.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 40));
			this.add(nome);//Adicionando a este painel
			nome.setBounds(260,450,350,80);//x,y,largura,altura
			
			//Botao para salvar(só aparece se eu passar o mouse em cima)
			salvar = new JButton("Salvar");
			salvar.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 30));
			salvar.setBounds(330,550,200,80);
			salvar.setBackground(Color.black);
			salvar.setForeground(Color.white);
			salvar.addActionListener(this);//Adicionando ouvinte
			this.add(salvar);//Adicionando neste painel
		}
		//Se a faseAtual(estado) for 7(jogador digitou o nome e clicou no botao), entao eu repinto a imagem de parabéns
		//Para ficar por cima do textfield e do botao
		if(faseAtual == 7) {
			img = sp.getImgFundo(7);
			g2.drawImage(img, 0, 0, null);
		}
		
		//-----------Aviso de Save---------------//
		//Se 'avisoSave' for true, permite pintar com o drawString
		if(avisoSave) {
			g2.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 50));
			g2.drawString(("Fase Salva!"), 30, 120);//x,y
		}
		
		//dispose para liberar os recuros, na verdade não sei se no meu caso é necessário
		g2.dispose();
	}
	
//------------------------------------------------------ACTION--------------------------------------------------------------//
	
	//Método para realizar tarefas à partir de ações
	//Reescrevendo o actionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Se o boss morreu, o controle para mudar a fase recebe true
		if(!boss.getEstaVivo()) {
			controle = true;
		}
		
		if(controle) {//Se o controle estiver ON adiciona o 'cont'(cronometro de duração do aviso de fase(ex: FASE 1))
			cont++;
		} else {//Se o 'controle' estiver off, então adiciona o contador(cronometro normal do jogo)
			contador++;
		}	
		
		if(avisoSave) {//Se o 'avisoSave' for true, entao eu aumento o cronometro do save
			contSave++;//'contSave' determina quanto tempo a mensagem 'Fase Salve!' do metodo paint, fica na tela
		}
		if(contSave > 50) {//Se o 'contSave' passar de 50(tempo que a mensagem de save fica na tela), 
			avisoSave = false;//entao o 'avisoSave' recebe false e eu zero o 'contSave'
			contSave = 0;
		}

		//-----------Jogo foi iniciado---------------//
		//Se o jogador estiver vivo, e o 'contador'(cronometro normal) for maior que 1(se estiver 0 é porque nao foi iniciado)
		//E a fase(estado) for menor ou igual a 4(parte jogavel), entao permite realizar as tarefas
		if(p1.getEstaVivo() && contador > 1 && faseAtual <= 4) {
			
			//-----------[Jogador] Limite da tela e movimento---------------//
			//Limites da tela para o jogador não sumir
			if(p1.getPosX() > 0 && p1.getPosX() <= 800) {//Se estiver dentro do 'x' 0 e 800 permite movimentar normalmente
				p1.setPosX(movimento.getMovimentoX());
			} else if(p1.getPosX() < 1) {//Se tocar na borda esquerda ele volta pro 1
				p1.setPosX(1);
			} else if(p1.getPosX() > 800) {//Se tocar na borda direita ele recebe -1
				p1.setPosX(-1);
			}
			//Mesma lógica que o de cima, mas para o Y
			if(p1.getPosY() > 0 && p1.getPosY() <= 630) {//630 pois a parte trazeira da nave que vai tocar a borda inferior
				p1.setPosY(movimento.getMovimentoY());
			} else if(p1.getPosY() < 1) {
				p1.setPosY(1);
			} else if(p1.getPosY() > 630) {
				p1.setPosY(-1);
			}
			
			//-----------[Tiro] Criando e movendo o Tiro---------------//
			//Se a tecla tiro foi apertada(tecla ESPAÇO)
			if(movimento.getTiro()) {
				//Se o tiro do jogador nao foi instanciado, eu instancio e já atiro
				if(tiroJogador == null) {
					tiroJogador = new Tiro(1,p1.getPosX(), p1.getPosY(), 800);
				} 
				else {//Se ja foi instanciado, e o tiro nao for mais visivel na tela, entao crio outro
					if(!tiroJogador.getTiroVisivel()) {
						tiroJogador = new Tiro(1,p1.getPosX(), p1.getPosY(), 800);
					}
				}
			}
			//Se o tiro for diferente de nulo
			if(tiroJogador != null) {
				//Se o tiro estiver visivel(nao saiu da tela) eu movimento ele pra cima
				if(tiroJogador.getTiroVisivel()){
					tiroJogador.atualiza(-20, 0);//Velocidade e borda da tela
					movimento.setPermiteTiro(false);//Bloqueio o tiro enquanto o outro nao sumiu da tela
				} else {//Se já saiu da tela, eu permito atirar novamente
					movimento.setPermiteTiro(true);
				}
			}
			
			//-----------[Monstros] Inicializando Monstros---------------//
			//A cada 50 do 'contador'(cronometro) e se o contador for menor que 1000, eu permito criar um monstro
			if(contador % 50 == 0 && contador < 1000) {
				//Criando um objeto de 'Random'
				Random aleatorio = new Random();
				int x = aleatorio.nextInt(700) + 50;//Posicao x do monstro entre 50 e 700
				//Adicionando na lista o monstro criado com a posicao de Random
				//Argumenos: (tipo de monstro, se for na faseAtual ==  2, recebe o primeiro monstro), posicao x, posicao y e a quantidade de vida
				monstros.add(new Personagens(faseAtual,x, -100,1));
			}
					
			//-----------[Monstros] Movimento dos Monstros---------------//
			//Movimentando os montros
			//For para percorrer todos os montros
			for(int i = 0; i < monstros.size(); i++) {
				//Pegando o monstro da posicao 'i'
				Personagens monstro = monstros.get(i);
				
				if(monstro.getEstaVivo()) {//Se o monstro ta vivo, ele se movimenta para baixo
					monstro.setPosY(7);
				} else {//Se nao estiver vivo, eu removo ele
					monstros.remove(i);
				}
				if(monstro.getPosY() > 900) {//Se o monstro saiu da tela desativo ele
					monstro.setEstaVivo(false);
				}
			}
			//-----------[Boss] Surgimento dos Bosses e sua Movimentacao---------------//
			//Se o 'contador' passar de 1100, o boss aparece
			if(contador > 1100) {
				//Enquanto a posicao Y do boss nao chegou no 50, ele vem para baixo
				if(boss.getPosY() < 50) {
					boss.setPosY(5);
				} else {
					//Loop, boss anda de um lado para o outro
					if(boss.getPosX() > 650) {
						esquerda = true;
						direita = false;
					} else if(boss.getPosX() < 50) {
						esquerda = false;
						direita = true;
					}
					if(direita) {
						boss.setPosX(5);
					} else if(esquerda) {
						boss.setPosX(-5);
					}
					//Se o boss está vivo, permite atirar
					if(boss.getEstaVivo()) {
						if(tiroBoss.getLimiteTiro() > 800) {//Se passou da tela, permite novo tiro
							tiroBoss = new Tiro(2,boss.getPosX()+48, boss.getPosY(), -10);//Instanciando tiro
						} else {//Se nao, entao move para baixo
							tiroBoss.atualiza(+20, 900);
						}
					}	
				}
			}
		} else if(movimento.getEnter()) {//Se o jogador morreu, pega o enter para reiniciar o jogo
			//faseAtual recebe 1 pois ele vai recomeçar o jogo do zero
			faseAtual = 1;
			controle = true;//Para mostrar o aviso de 'FASE 1'
			atualizaFase();//Mudando a fase
		} 
		
		//-----------Jogo foi Salvo---------------//
		//Se o jogador apertou a tecla 'S'
		if(movimento.getTeclaS()) {
			//Se a fase for menor ou igual ao 4(menor que 5), crio um objeto da classe de salvamento e o aviso de save recebe true
			if(faseAtual < 5) {
				sv = new SalvarImp(faseAtual-1);//-1 porque o estado do jogo é sempre um maior que a fase do jogo
				avisoSave = true;
			}
		}
		
		//-----------Atualizando a Fase---------------//
		//Se o boss foi morto e a fase(estado) for menor que 4, chama o atualizaFase()
		//Como a 'faseAtual' é sempre um a mais que a fase do jogo, entao ele é só até o 3 porque
		//a fase 3 do jogo equivale a 4 do 'faseAtual', e se matar o boss da fase 3 do jogo o jogo termina
		if(faseAtual < 4 && !boss.getEstaVivo()) {
			atualizaFase();
		} 
		//Se a a fase(estado) for igual a 4 e o boss morreu, entao a fase(estado do jogo) recebe 5
		if(faseAtual == 4 && !boss.getEstaVivo()){
			faseAtual = 5;
		}
		
		//Se o botao de salvar foi apertado
		if(e.getSource() == salvar) {//Salvar
			//Instancio objeto dos recordes
			//Argumentos: nome digitado no textfield e os pontos do jogador
			rs = new RecordesImp(nome.getText(),pontos);
			rs.escrever();//Metodo para escrever no txt
			
			//Mandando o textfield e o botao para fora da tela para nao permitir salvar mais de uma vez
			nome.setBounds(0,-200,0,0);
			salvar.setBounds(0,-200,0,0);
			faseAtual = 7;//estado do jogo recebe 7
		}
		
		//Se o jogo foi iniciado, eu verifico as colisoes e instancio a imagem da barra de vida
		if(contador > 1) {
			checarColisao();
			vida = sp.getImgVida(p1.getVida());
		}
		
		//Chamando o método que verifica se é preciso avançar de fase
		atualizaFase();
		//Se a fase(estado do jogo) for menor que 6(jogo em andamento) então ele repinta
		if(faseAtual < 6)
			repaint();
	}
}
