package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

/**
 * Comando "Fine".
 */

public class ComandoFine implements Comando {
	
    private IOConsole ioConsole = new IOConsole();

	@Override
    public void esegui(Partita partita) {
    	this.ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
    }
	
	@Override
	public void setParametro(String parametro) {
	}
	
	public String getNome() {
	    return "fine";
	}
    public String getParametro() {
		return "";
    }
}
