package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	
	private IOConsole ioConsole = new IOConsole();
	
	@Override
    public void esegui(Partita partita) {
    	this.ioConsole.mostraMessaggio("Comando sconosciuto");
    }
	
	@Override
    public void setParametro(String parametro) {
    }
	
	public String getNome() {
	    return null;
	}
    public String getParametro() {
		return null;
    }
	
}
