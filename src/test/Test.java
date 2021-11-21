package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import entities.Empresa;
import entities.Funcionario;

public class Test {

	public static void main(String[] args) {
		
		//O que e sereliazacao? Sereliazacao e basicamente a gente exportar um OBJETO nao e uma classe,
		//Para o computador, para ele ficar fisico. Pra que serve? Basicamente serve quando a gente quer
		//Enviar um objeto pra outra pessoa, ou importar ele pro banco de dados etc.
		
		Path path = Paths.get(System.getProperty("user.home"), "Desktop", "Funcionario.ser");
		//Aqui passamos o path daonde a gente quer que fique o nosso objeto. E a extensao "ser" significa
		//Esterializado (Serialized)
		
		gravarFuncionario(path);
		leitorFuncionario(path);
	}
	
	private static void gravarFuncionario(Path path) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
			//Por padrao objetos Output e Input precisam ser fechados, por isso a gente faz com try-with-resources
			
			//Logo em cima instanciamos um objeto especializado em objetos :D. Como voce sabe Output 
			//Significa saida de informacao, e Input, entrada de informacao.
			
			//Para a exportacao de um objeto e a mesma logica do streaming, a gente vai pegar byte por byte
			//E exportar, mas a gente nao precisa fazer essa parte, o java ja faz automaticamente
			
			//Vamos criar um objeto para a exportacao...
			//Informacoes dele...
			Funcionario func = new Funcionario( "Augusto", 16, 1600);
			Empresa emp = new Empresa("Empresa Generica");
			func.setEmpresa(emp);
			
			//Depois e so usar o metodo de escrever objeto e pronto xD.
			
			//Obs.: Por padrao as classes do java vem com a serializacao desativada, voce tem implementar
			//Ele na classe
			oos.writeObject(func);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void leitorFuncionario(Path path) {
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))){
			
			//Primeiramente a gente tem que criar um objeto que vai receber as informacoes
			Funcionario func;
			
			//No read e a parte mais facil, o readObject retorna um objeto, e como a gente sabe que esse objeto
			//E um "Funcionario", a gente faz um cast explicito.
			func = (Funcionario) ois.readObject();
			
			System.out.println(func);
			//Aqui estou botando um sysout que vai puxar o toString do Funcionario, mas claro que o certo a fazer
			//Era botar esse metodo para retornar um Funcionario, para que ai sim, botar em um objeto.
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}
