package personagens;

//Importando as classes usadas
import java.awt.Image;
import java.awt.Rectangle;
//Importante a classe Sprites, para pegar a imagem do tiro
import imagens.Sprites;

//Classe Tiro, referente aos tiros do jogo
public class Tiro {

	//Atributos da posicao x e y, se o tiro está visivel ou nao e o limite do tiro
	private int posX, posY;
	private boolean tiroVisivel;
	private int limiteTiro;
	
	//Referencias usadas para a imagem do tiro
	Image img;
	Sprites t;
	
	//Construtor, parametros: tipo do tiro(caso), posicao inicial x e y e o limite do tiro
	public Tiro(int caso, int x, int y, int limite) {
		//Atributos recebendo os parametros
		posX = x;
		posY = y;
		tiroVisivel = true;//Tiro começa visivel
		limiteTiro = limite;//Recebendo o limite
		
		//Pegando a imagem
		t = new Sprites();
		img =  t.getImgTiro(caso);
	}
	
	//Atualizando a posicao do tiro
	public void atualiza(int velocidade, int bordaJanela) {
		posY += velocidade;
		limiteTiro += velocidade;
		
		//Se o tiro for menor ou igual a borda da janela do jogo, entao ele fica false
		if(limiteTiro <= bordaJanela) {
			tiroVisivel = false;
		}
	}
	
	//Retorno um retangulo com as dimensoes do tiro
	public Rectangle getBounds() {
		return new Rectangle(posX,posY,16,40);
	}
	
	//-----------Setter---------------//
	//Setter da posicao X(posicao do jogador ou do boss)
	public void setPosX(int i) {
		posX = i;
	}
	
	//-----------Getters---------------//
	//Geters do limite do tiro, se o tiro esta visivel ou nao, das posicoes x e y e da imagem do tiro
	public int getLimiteTiro() {
		return limiteTiro;
	}
	public boolean getTiroVisivel() {
		return tiroVisivel;
	}
	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	public Image getImg() {
		return img;
	}
	
}
