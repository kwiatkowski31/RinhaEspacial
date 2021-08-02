package recordes;

//Importando as classes usadas
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

//Classe Recordes, estende JPanel
//Classe que mostra os recordes (ranking) dos jogadores
public class Recordes extends JPanel {
	
	//Atributos: titulo do painel, os nomes e a pontua��o
	JLabel titulo;
	static JLabel nome1, nome2, nome3, nome4, nome5,
				pontos1, pontos2, pontos3, pontos4, pontos5;

	//Construtor, seta as caracteristicas do painel
	public Recordes() {
		this.setLayout(null);
		this.setBounds(200,0,500,700);//x,y,largura,altura   200,150,500,500
		this.setBackground(Color.black);
		this.setForeground(Color.white);

		//Instanciando o titulo
		titulo = new Label("Recordes",100,50,500,100,80);//Nome, x, y, largura, altura, tamanho da fonte
		
		//[String, x, y, largura, altura, tamanho da fonte]
		nome1 = new Label("1�     ",20,170,500,100,50);
		nome2 = new Label("2�     ",20,250,500,100,50);
		nome3 = new Label("3�     ",20,330,500,100,50);
		nome4 = new Label("4�     ",20,410,500,100,50);
		nome5 = new Label("5�     ",20,490,500,100,50);
		
		pontos1 = new Label("",400,170,500,100,50);
		pontos2 = new Label("",400,250,500,100,50);
		pontos3 = new Label("",400,330,500,100,50);
		pontos4 = new Label("",400,410,500,100,50);
		pontos5 = new Label("",400,490,500,100,50);
		
		//Adicionando os labels neste painel
		this.add(titulo);
		this.add(nome1);
		this.add(nome2);
		this.add(nome3);
		this.add(nome4);
		this.add(nome5);
		
		this.add(pontos1);
		this.add(pontos2);
		this.add(pontos3);
		this.add(pontos4);
		this.add(pontos5);
		
		//Chamando o m�todo que faz a leitura do txt que guarda os recordes
		ler();
	}
	
//----------------------------------------------PREENCHENDO LABELS----------------------------------------------------------//
	
	//M�todo para leitura dos recordes e setter do texto dos labels
	private static void ler() {
		//Objeto do tipo File sendo instanciado com o nome do arquivo a ser aberto
		File salvar = new File("recordes.txt");
		
		//Variaveis de auxilio
		//Linha recebe a linha do txt que est� sendo lido, nomeTemp guarda o nome que est� sendo lido
		//nomeMaior guarda o nome da maior pontua��o daquele while, numMaior tamb�m, mas guarda a maior pontua��o daquela rodada do while
		//Contador � usado pra saber se a linha � par(pontos) ou impar(nome), numeroTemp guarda a pontuacao da linha atual
		//numeroMaior guarda o maior numero lido dentro do while, e o ultimo guarda o maior numero da ultima leitura
		String linha = "", nomeTemp = null, nomeMaior = null, numMaior = null;
        int contador = 1, numeroTemp = 0, numeroMaior = 0, ultimo = 9999;
		
        
        //Ele roda 5 vezes pois a tabela de recordes possui 5 coloca��es
		for(int i = 0; i < 5; i++) {
			//Tenta fazer a leitura
		    try {
		    	//Mesma l�gica de leitura que o da classe 'Carregar'
		        FileReader fileReader = new FileReader(salvar);
		        BufferedReader bufferedReader = new BufferedReader(fileReader);
		        numeroMaior = 0;//Numero Maior atual recebe 0
		        
		        while( (linha = bufferedReader.readLine() ) != null) {//Enquanto a ultima linha nao chegou

		        	if(contador % 2 != 0) {//Se for impar, pega o nome
				        nomeTemp = linha;
			        }
			        if(contador > 0 && contador % 2 == 0) {//Se for Par pega a pontua��o
			        	numeroTemp = Integer.parseInt(linha);//Cast da linha atual para int

			        	if(numeroTemp < ultimo) {//Se o numero atual for menor que o ultimo numero pego(1� do ranking, por exemplo)
			        		
				        	if(numeroTemp > numeroMaior) {//Se o numero atual for maior que o maior numero guardado at� entao
				        		numeroMaior = numeroTemp;//Atualiza
				        			
				        		nomeMaior = nomeTemp;//Atualiza o nome e o numero maior
				        		numMaior = linha;
				        		
				        	}
			        	} 
			        }
			        contador++;//Acremento o contador
			        
		        }
		        ultimo = Integer.parseInt(numMaior);//Cast do maior numero para inteiro
		        
		        //O ranking possui 5 coloca��es
		        if(i == 0) {//Se o i for igual a zero, � a primeira coloca��o
		        	nome1.setText("1�       " + nomeMaior);//Recebe o nome da maior pontua��o
		        	pontos1.setText(numMaior);//Recebe a maior pontua��o
		        }
		        if(i == 1) {//Mesma l�gica que o de cima
		        	nome2.setText("2�       " + nomeMaior);
		        	pontos2.setText(numMaior);
		        }
		        if(i == 2) {//Mesma l�gica que o de cima
		        	nome3.setText("3�       " + nomeMaior);
		        	pontos3.setText(numMaior);
		        }
		        if(i == 3) {//Mesma l�gica que o de cima
		        	nome4.setText("4�       " + nomeMaior);
		        	pontos4.setText(numMaior);
		        }
		        if(i == 4) {//Mesma l�gica que o de cima
		        	nome5.setText("5�       " + nomeMaior);
		        	pontos5.setText(numMaior);
		        }
		        //Fecho os objetos de leitura
		        fileReader.close();
		        bufferedReader.close();
			} catch (IOException e) {//Pega a exce��o
		    	e.printStackTrace();
		    }
		}
	}
	
}