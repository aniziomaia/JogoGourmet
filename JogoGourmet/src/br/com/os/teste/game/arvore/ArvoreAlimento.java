package br.com.os.teste.game.arvore;


/**
 * classe que representa uma arvore binaria com seus
 * nos esquerdo e direito
 * 
 * @author aniziomaia
 */

public class ArvoreAlimento {
	
	private String nome;
	private ArvoreAlimento noEsquerdo;
	private ArvoreAlimento noDireito;

	public ArvoreAlimento(String nome) {
		this.nome = nome;
	}
	
	public boolean existeNo() {
		return (noDireito != null) && (noEsquerdo != null);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArvoreAlimento getNoEsquerdo() {
		return noEsquerdo;
	}

	public void setNoEsquerdo(ArvoreAlimento noEsquerdo) {
		this.noEsquerdo = noEsquerdo;
	}

	public ArvoreAlimento getNoDireito() {
		return noDireito;
	}

	public void setNoDireito(ArvoreAlimento noDireito) {
		this.noDireito = noDireito;
	}

	
}
