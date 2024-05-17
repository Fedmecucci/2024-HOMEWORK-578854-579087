package it.uniroma3.diadia.attrezzi;
/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo implements Comparable<Attrezzo>{

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	
	
	@Override
	public int compareTo(Attrezzo that) {
		Integer pesoThis = Integer.valueOf(this.getPeso());
	    Integer pesoThat = Integer.valueOf(that.getPeso()); 
		int cmp = pesoThis.compareTo(pesoThat);
		if(cmp==0) {
			return this.getNome().compareTo(that.getNome());
		}
		return cmp;
	}
	
    @Override
    public boolean equals(Object o) {
		Attrezzo that = (Attrezzo) o;
		if (that!=null) {
		  if (this.getNome().equals(that.getNome())) {
			  return this.getPeso()==that.getPeso();
		  }
		  else {
			  return false;
		  }
		}
		else {
			return false;
		}
	}
    
	@Override
	public int hashCode() {
		return this.getNome().hashCode() + this.getPeso();
	}

}