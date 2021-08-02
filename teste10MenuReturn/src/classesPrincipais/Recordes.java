package classesPrincipais;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classesAuxiliares.*;

public class Recordes extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JLabel titulo;
	Botao b1;
	static JLabel nome1, nome2, nome3, nome4, nome5,
				pontos1, pontos2, pontos3, pontos4, pontos5;

	public Recordes() {
		this.setLayout(null);
		this.setBounds(200,0,500,800);//x,y,largura,altura   200,150,500,500
		this.setBackground(Color.black);
		this.setForeground(Color.white);

		titulo = new Label("Recordes",100,50,500,100,80);
		
		//[String, x, y, largura, altura]
		nome1 = new Label("1º     ",20,170,500,100,50);
		nome2 = new Label("2º     ",20,250,500,100,50);
		nome3 = new Label("3º     ",20,330,500,100,50);
		nome4 = new Label("4º     ",20,410,500,100,50);
		nome5 = new Label("5º     ",20,490,500,100,50);
		
		pontos1 = new Label("",400,170,500,100,50);
		pontos2 = new Label("",400,250,500,100,50);
		pontos3 = new Label("",400,330,500,100,50);
		pontos4 = new Label("",400,410,500,100,50);
		pontos5 = new Label("",400,490,500,100,50);
		
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
		
		b1 = new Botao("Voltar",150,650,220,60);//x,y,largura,altura
		
		this.add(b1);
		
		ler();
	}
	
//----------------------------------------------PREENCHENDO LABELS----------------------------------------------------------//

	private static void ler() {
		File salvar = new File("recordes.txt");
		
		//Variaveis de auxilio
		String linha = "", nomeTemp = null, nomeMaior = null, numMaior = null;
        int contador = 1, numeroTemp = 0, numeroMaior = 0, ultimo = 9999;
		
        
        //Ele roda 5 vezes pois a tabela de recordes possui 5 colocações
		for(int i = 0; i < 5; i++) {
			
		    try {
		        FileReader fileReader = new FileReader(salvar);
		        BufferedReader bufferedReader = new BufferedReader(fileReader);
		        numeroMaior = 0;
		        
		        while( (linha = bufferedReader.readLine() ) != null) {

		        	if(contador % 2 != 0) {//Se for impar, pega o nome
				        nomeTemp = linha;
			        }
			        if(contador > 0 && contador % 2 == 0) {//Se for Par pega a pontuação
			        	numeroTemp = Integer.parseInt(linha);

			        	if(numeroTemp < ultimo) {
			        		
				        	if(numeroTemp > numeroMaior) {
				        		numeroMaior = numeroTemp;
				        			
				        		nomeMaior = nomeTemp;
				        		numMaior = linha;
				        		
				        	}
			        	}
			        }
			        contador++;
		        }
		        ultimo = Integer.parseInt(numMaior);
		        	
		        if(i == 0) {
		        	nome1.setText("1º       " + nomeMaior);
		        	pontos1.setText(numMaior);
		        }
		        if(i == 1) {
		        	nome2.setText("2º       " + nomeMaior);
		        	pontos2.setText(numMaior);
		        }
		        if(i == 2) {
		        	nome3.setText("3º       " + nomeMaior);
		        	pontos3.setText(numMaior);
		        }
		        if(i == 3) {
		        	nome4.setText("4º       " + nomeMaior);
		        	pontos4.setText(numMaior);
		        }
		        if(i == 4) {
		        	nome5.setText("5º       " + nomeMaior);
		        	pontos5.setText(numMaior);
		        }
		        
		        fileReader.close();
		        bufferedReader.close();
			} catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	public JButton getBotao() {
		return b1;
	}
}