package imagens;

//Importando as classes usadas
import java.awt.Image;
import javax.swing.ImageIcon;

//Classe Sprites, pega as imagens da pasta 'img' aqui do projeto e permite o uso pelas outras classes
public class Sprites {
	
	//Variável que recebe a imagem do ImageIcon
	Image imagem;//Referencia que vai guardar a imagem
	
	//Instanciando os ImageIcon com as imagens do jogo
	ImageIcon avisoFase1 = new ImageIcon("img\\avisoFase1.png");
	ImageIcon avisoFase2 = new ImageIcon("img\\avisoFase2.png");
	ImageIcon avisoFase3 = new ImageIcon("img\\avisoFase3.png");
	ImageIcon fase1 = new ImageIcon("img\\fase1.gif");
	ImageIcon fase2 = new ImageIcon("img\\fase2.gif");
	ImageIcon fase3 = new ImageIcon("img\\fase3.gif");
	ImageIcon gameOver = new ImageIcon("img\\gameOver.png");
	ImageIcon fim = new ImageIcon("img\\fim.png");
	
	ImageIcon jogador1 = new ImageIcon("img\\nave1.png");
	ImageIcon tiroJogador = new ImageIcon("img\\tiro1.png");
	
	ImageIcon monstro1 = new ImageIcon("img\\monstro1.png");
	ImageIcon monstro2 = new ImageIcon("img\\monstro2.png");
	ImageIcon monstro3 = new ImageIcon("img\\monstro3.png");
	
	ImageIcon boss1 = new ImageIcon("img\\boss1.png");
	ImageIcon boss2 = new ImageIcon("img\\boss2.png");
	ImageIcon boss3 = new ImageIcon("img\\boss3.png");
	ImageIcon tiroBoss = new ImageIcon("img\\tiro2.png");
	
	ImageIcon vida0 = new ImageIcon("img\\vida0.png");
	ImageIcon vida1 = new ImageIcon("img\\vida1.png");
	ImageIcon vida2 = new ImageIcon("img\\vida2.png");
	ImageIcon vida3 = new ImageIcon("img\\vida3.png");
	ImageIcon vida4 = new ImageIcon("img\\vida4.png");
	ImageIcon vida5 = new ImageIcon("img\\vida5.png");
	ImageIcon vida6 = new ImageIcon("img\\vida6.png");
	ImageIcon vida7 = new ImageIcon("img\\vida7.png");
	ImageIcon vida8 = new ImageIcon("img\\vida8.png");
	
	//Construtor vazio
	public Sprites() {
	}
	
//-----------------------------------------------------BACKGROUND-------------------------------------------------------------//	
	
	//Imagens de fundo(cenario) e aviso de fases
	public Image getImgFundo(int caso) {//Recebe o tipo da imagem(caso)
		switch(caso) {
			case 1://Se o caso for 1, retorna o cenario da primeira fase, os demais seguem a mesma lógica
				imagem = fase1.getImage();
				break;
			case 2:
				imagem = fase2.getImage();
				break;
			case 3:
				imagem = fase3.getImage();
				break;
			case 4:
				imagem = avisoFase1.getImage();
				break;
			case 5:
				imagem = avisoFase2.getImage();
				break;
			case 6:
				imagem = avisoFase3.getImage();
				break;
			case 7:
				imagem = fim.getImage();
				break;
		}
		return imagem;//Retorna a imagem referente ao caso
	}
	
//--------------------------------------------------PERSONAGENS-------------------------------------------------------------//
	
	//Mesma lógica que o de cima, mas agora para personagens(jogador, monstros e bosses)
	public Image getImgPersonagens(int caso) {
		switch(caso) {
			case 1://Jogador
				imagem = jogador1.getImage();
				break;
				
			case 2://Monstro 1
				imagem = monstro1.getImage();
				break;
			case 3://Monstro 2
				imagem = monstro2.getImage();
				break;
			case 4://Monstro 3
				imagem = monstro3.getImage();
				break;
				
			case 5://Boss1
				imagem = boss1.getImage();
				break;
			case 6://Boss2
				imagem = boss2.getImage();
				break;
			case 7://Boss3
				imagem = boss3.getImage();
				break;
		}
		return imagem;
	}

//-----------------------------------------------------TIRO-----------------------------------------------------------------//

	//Mesma lógica que os de cima, mas agora para os tiros(tiro do jogador e dos bosses)
	public Image getImgTiro(int caso) {
		switch(caso) {
			case 1://Tiro do Jogador
				imagem = tiroJogador.getImage();
				break;
			case 2://Tiro do Boss
				imagem = tiroBoss.getImage();
				break;
		}
		return imagem;
	}

//-------------------------------------------------BARRA DE VIDA------------------------------------------------------------//
	
	//Mesma lógica que os de cima, mas para a barra de vida do jogador
	public Image getImgVida(int caso) {
		switch(caso) {
			case 0://Morto
				imagem = vida0.getImage();
				break;
			case 1://Vida cheia
				imagem = vida1.getImage();
				break;
			case 2://(7/8) de vida
				imagem = vida2.getImage();
				break;
			case 3://(6/8) de vida
				imagem = vida3.getImage();
				break;
			case 4://(5/8) de vida
				imagem = vida4.getImage();
				break;
			case 5://(4/8) de vida
				imagem = vida5.getImage();
				break;
			case 6://(3/8) de vida
				imagem = vida6.getImage();
				break;
			case 7://(2/8) de vida
				imagem = vida7.getImage();
				break;
			case 8://(1/8) de vida
				imagem = vida8.getImage();
				break;
		}
		return imagem;
	}
}
