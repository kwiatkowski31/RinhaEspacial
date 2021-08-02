package personagens;

//Importando as classes usadas
import java.awt.Image;
import java.awt.Rectangle;
//Importante a classe dos Sprites
import imagens.Sprites;

//Classe Personagens, referente aos personagens do jogo
public class Personagens {
	
	//Atrbutos: posicao x e y, estaVivo e a quantidade de vida
	private int posX, posY;
	private boolean estaVivo;
	private int vida = 0;
	
	//Referencias para Sprite e Image
	Sprites p;
	Image img;
	
	//Construtor, recebe o tipo do personagem(caso), a posicao inicial e a quantidade de vida
	public Personagens(int caso, int xInicial, int yInicial, int vida) {
		//Os atributos recebem os parametros
		posX = xInicial;
		posY = yInicial;
		
		p = new Sprites();
		img =  p.getImgPersonagens(caso);//o objeto de 'Image' pega a imagem(ImageIcon) da classe Sprite referente ao seu caso
		
		estaVivo = true;//Inicia vivo
		this.vida = vida;//Recebe a quantidade de vida
	}
	
	//Getter de Rectangle, retorna um retangulo com as dimensoes do personagem
	public Rectangle getBounds() {
		return new Rectangle(posX,posY,img.getHeight(null)-50,img.getWidth(null)-50);
	}
	
	//-----------Setters---------------//
	//Setters para a posicao x e y, setter do atributo 'estaVivo' e setter da vida do personagem
	public void setPosX(int i) {
		posX += i;
	}
	public void setPosY(int i) {
		posY += i;
	}
	public void setEstaVivo(boolean b) {
		estaVivo = b;
	}
	public void setVida(int i) {
		vida += i;
	}
	//-----------Getters---------------//
	//Getters para a imagem, as posicoes x e y, se ele está vivo ou nao e a quantidade de vida
	public Image getImg() {
		return img;
	}
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}	
	public boolean getEstaVivo() {
		return estaVivo;
	}	
	public int getVida() {
		return vida;
	}
	
}
