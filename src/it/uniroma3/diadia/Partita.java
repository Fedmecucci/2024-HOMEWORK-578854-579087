package it.uniroma3.diadia;

import java.io.IOException;

//import java.io.IOException;

import it.uniroma3.diadia.ambienti.Labirinto;
//import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	//static final private int CFU_INIZIALI = 20;

	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private boolean finita;
	private Giocatore giocatore;
	
	
	public Partita() throws IOException{
		this.labirinto = new Labirinto();
		this.stanzaCorrente= this.labirinto.getStanzaIniziale();
		this.finita = false;
		this.giocatore = new Giocatore();
		}
	
	//Costruttore di partita per il labirinto
	public Partita(Labirinto labirinto) throws IOException{
		this.labirinto = labirinto;
		this.stanzaCorrente= this.labirinto.getStanzaIniziale();
		this.finita = false;
		this.giocatore = new Giocatore();
		}

    
    public Stanza getStanzaVincente() {
    	return this.labirinto.getStanzaVincente();
    	
    }

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);		
	}


	public Giocatore getGiocatore() {
		return giocatore;
	}


	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}	
}
