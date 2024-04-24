package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
    private IOConsole ioConsole = new IOConsole();
	
	@Override
    public void esegui(Partita partita) {
		this.ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
    }
	
	@Override
    public void setParametro(String parametro) {
    }
	
	public String getNome() {
	    return "guarda";
	}
    public String getParametro() {
		return null;
    }

}
