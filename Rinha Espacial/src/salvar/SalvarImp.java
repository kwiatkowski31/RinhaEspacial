package salvar;

//Importando as classes usadas
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//Classe SalvarImp, implementa a SalvarDAO
public class SalvarImp implements SalvarDAO {
	
	//Atributo que recebe a fase a ser salva
	private static int fase;
	
	//Costrutor settando o parametro
	public SalvarImp (int fase){
		this.fase = fase;
		
		escrever();//Chamando o metodo para escrever
	}
	
	//Método que realiza a escritura no tx
	public void escrever() {
		//Objeto para abrir o txt
        File salvar = new File("save.txt");

        try {//Tenta fazer a tarefa
        	
        	//Os metodos de escrita sao diferentes pois eu quero que o txt de save possua somente um numero(caractere no caso)
        	
        	//Objeto para escrever no txt
        	PrintWriter escrever = new PrintWriter(salvar);
        	
        	//Escrevendo o parametro recebido
        	escrever.print(fase);
        	
        	//Fechando o objeto de escrita
        	escrever.close();

        } catch (IOException e) {//Pega a exceção
            e.printStackTrace();//Printa a exceção
        }
    }
}
