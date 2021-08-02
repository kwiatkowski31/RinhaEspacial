package carregar;

//Importando as classes usadas
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Classe que implementa o CarregarDAO
public class CarregarImp implements CarregarDAO {
	
	//Construtor vazio
	public CarregarImp() {
	}
	
	//Método que realiza a leitura do 'save.txt' e retorna o valor da fase que foi salvo
	public int carregar() {
		//Objeto do tipo File recebe o nome do arquivo a ser aberto
		File carregar = new File("save.txt");
		String fase = null;//String que recebe a linha do txt
		
		//Tenta realizar a tarefa
		try {
			//Objeto de leitura recebe o arquivo
	        FileReader fileReader = new FileReader(carregar);
	        //BufferedReader recebe este objto de leitura
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
	        //String recebe a linha do txt
	        fase = bufferedReader.readLine();
	       
	        //Fecho os objetos de leituras
	        fileReader.close();
	        bufferedReader.close();
		} catch (IOException e) {//Se nao conseguir, pega a exceção
	    	e.printStackTrace();//Printa a exceção
	    }
		
		return Integer.parseInt(fase);//Cast para inteiro da fase que foi carregada
	}
}
