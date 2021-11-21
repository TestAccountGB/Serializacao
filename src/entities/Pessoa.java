package entities;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {//Temos que implementar a inteface para
	//O java autorizar a serealizacao da classe. Tambem temos que implementar nas classes que extendem
	//Ou utilizam uma das outras.
	
	private static final long serialVersionUID = 2L;
	//Mesma logica do que a do Funcionario, mas e bom a gente colocar outro id a cada classe, porque 
	//alguma pessoa pode tentar transformar um Funcionario em uma Pessoa por exemplo. E o java vai deixar
	//Porque os ids sao iguais, e claramente vai dar muito erro.
	
	private String nome;
	private int idade;
	
	public Pessoa(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", idade=" + idade + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
}
