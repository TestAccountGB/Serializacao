package entities;

import java.io.Serializable;

public class Empresa implements Serializable {//Em todas as classes que usamos na que a gente quer serializar
	//Precisamos implementar Serializable
	
	private static final long serialVersionUID = 3L;
	private String nomeEmpresa;
	
	public Empresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	
	@Override
	public String toString() {
		return "Empresa [nomeEmpresa=" + nomeEmpresa + "]";
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	
	
}
