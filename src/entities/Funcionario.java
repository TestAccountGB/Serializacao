package entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable{//Temos que implementar a inteface para
	//O java autorizar a serealizacao da classe.
	
	private static final long serialVersionUID = 1L;
	//O que e isso? E o id da classe, imagine assim: A gente gravou uma classe que tem o id gerado pelo java,
	//Esse id vai ser mudado a cada modificacao que voce faz na classe, ou seja, se a gente gravou um objeto
	//Ontem da classe funcionario com o id = 137, e hoje eu fiz umas modificacoes, como por exemplo,
	//Adicionei o endereco nele, o id do funcionario vai pra outro, como 138, e quando a gente for ler
	//Esse arquivo que eu gravei, o java nao vai identificar que e a mesma classe, porque tem ids diferentes.
	//Por isso a gente precisa colocar um Id pra classe.
	
	//Modificador transient...
	
	//Ele serve pra caso a gente nao queira passar algum valor pro arquivo da serializacao, como por exemplo
	//A senha.
	
	//Obs.: Quando o java for ler esse arquivo, os atributos com transient, vao estar com seu padrao de inicializacao, por
	//Exemplo: double = 0, boolean = false, String = null etc.
	private transient double salario;
	
	//Vamos fazer uma situacao hipotetica. Eu tenho um atributo que esteja transient e que eu nao tenha a permissao
	//De mudar. O que eu faco? Eu posso sobrescrever o metodo de escrita e leitura do Object Input e Output
	private transient Empresa empresa;
	
	public Funcionario(String nome, int idade, double salario) {
		super(nome, idade);
		this.salario = salario;
	}
	
	//Obs.: Nunca coloque um atributo static em um construtor, porque o atributo static e da classe e nao do objeto
	
	@Override
	public String toString() {
		return super.toString() + 
				", Funcionario [salario=" + salario + ", Empresa=" + empresa.getNomeEmpresa() + "]";
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
	//O que o java faz? Quando a gente chama o writeObject la na main, primeiramente o java procura se a gente tem
	//O metodo em nossa classe, se a gente tiver, ele vai executar o nosso.
	private void writeObject(ObjectOutputStream oos) {
		
		try {
			oos.defaultWriteObject(); //Aqui a gente ta chamando o writeObject default, ou seja, ele vai executar o normal
			//E depois vai executar o que a gente escrever abaixo.

			System.out.println("Dentro do writeObject");
			
			//Aqui a gente esta escrevendo no arquivo o nome da empresa, como o arquivo nao consegue escrever, temos
			//Que escrever por nos mesmo.
			oos.writeUTF(empresa.getNomeEmpresa());
			//Obs.: O metodo writeUTF(), serve para escrever Strings, temos o writeInt() para numeros inteiros e ect.
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Com o read e a mesma logica, mas temos que escrever o read na mesma ordem que escrevemos o write, porque
	//Imagine assim, a gente usa o writeUTF() pra escrever o nome da empresa na linha 1, entao a gente tem que escrever
	//Para o ObjectInput ler a linha 1 e coloca-la dentro da empresa
	private void readObject(ObjectInputStream ois) {
		
		try {
			ois.defaultReadObject();//Aqui e a mesma coisa que ObjectOutput
			
			System.out.println("Dentro do readObject");
			
			empresa = new Empresa(ois.readUTF());//E depois lemos a linha 1 e colocamos dentro da empresa
			//Temos que instanciar, porque nosso atributo empresa nao e um tipo primitivo. Se fosse apenas uma String
			//Seria assim:
			
			/*public class funcionario {
			 * private transient String nomeEmpresa;
			 * 
			 * private void writeObject(ObjectOutputStream oos) {
			 * try{
			 * oos.defaultWriteObject();
			 * oos.writeUTF(nomeEmpresa);
			 * } catch...
			 * }
			 * 
			 * private void readObject(ObjectInputStream ois) {
			 * try{
			 * ois.defaultReadObject();
			 * nomeEmpresa = ois.readUTF();
			 * } catch...
			 * }
			 */
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
}