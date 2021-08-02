package salvar;

//Importando as classes usadas
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Classe RecordesImp, implementa o SalvarDAO
//Classe usada na classe 'Fases', possui a tarefa de escrever o nome e a pontuação do jogador depois de terminar o jogo
public class RecordesImp implements SalvarDAO {
	
	//Atributos nome e pontos que guardaram o nome e a pontuação enviada em 'Fases'
	static String nome;
	private static int pontos;
	
	//Construtor atribuindo os parametros nos atributos
	public RecordesImp (String nome, int pontos){
		this.nome = nome;
		this.pontos = pontos;
	}
	
	//Método para guardar no txt as informações do jogador
	public void escrever() {
		//Objeto para abrir o arquivo
        File salvar = new File("recordes.txt");

        try {
        	//Objeto para escrever no arquivo e recebe true para escrever em baixo
        	FileWriter escrever = new FileWriter(salvar,true);
        	
        	//Objeto chama o metodo para escrever e envia como argumento o nome e a pontuação(cast para string)
    		escrever.write("\n"+nome);
    		escrever.write("\n"+Integer.toString(pontos));
    		
            //Método flush libera a escrita no arquivo
        	escrever.flush();

            //Método close para fechar o arquivo
        	escrever.close();

        } catch (IOException e) {//Pegando a exceção
            e.printStackTrace();//Printando a exceção
        }
    }
}
