package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */

public class ComandoVai extends AbstractComando {
	
	


	public IO ioConsole;
	private Direzione direzione;
	public String nome;
	public String parametro;
	
	/*** esecuzione del comando*/
	@Override
	public void esegui(Partita partita) {
	    Stanza stanzaCorrente = partita.getStanzaCorrente();
	    nome = partita.getStanzaCorrente().getNome();
	    Stanza prossimaStanza = null;
	    if(this.direzione==null) {
	    	this.ioConsole.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
	    	return;
	    }
	    prossimaStanza = stanzaCorrente.getStanzaAdiacente(this.direzione);
	    if(prossimaStanza==null) {
	    	this.ioConsole.mostraMessaggio("Direzione inesistente");
	    	return;
	    }
	    partita.setStanzaCorrente(prossimaStanza);
	    ioConsole.mostraMessaggio(partita.getStanzaCorrente().getNome());
	    partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	    
	    parametro = partita.getStanzaCorrente().getNome();
	}
	
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = Direzione.valueOf(parametro);
	}
	
	public String getNome() {
	    return "vai";
	}
	
	@Override
    public String getParametro() {
		return String.valueOf(this.direzione);
    }

	@Override
	public void setIo(IO ioConsole) {
		this.ioConsole = ioConsole;
		}
}
