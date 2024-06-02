package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	

	private String nomeAttrezzo;
	private IO ioConsole;
	
    @Override
    public void esegui(Partita partita) {
    	Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(partita.getStanzaCorrente().removeAttrezzo(attrezzo)) {
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
			ioConsole.mostraMessaggio("Attrezzo inserito nella borsa");
		}
		else
			ioConsole.mostraMessaggio("Attrezzo non presente nella stanza");
    }
    
    @Override
    public void setParametro(String parametro) {
    	this.nomeAttrezzo = parametro;
    }
    
    public String getNome() {
	    return "prendi";
	}
    public String getParametro() {
		return this.nomeAttrezzo;
    }

	@Override
	public void setIo(IO ioConsole) {
		this.ioConsole=ioConsole;
		
	}
}
