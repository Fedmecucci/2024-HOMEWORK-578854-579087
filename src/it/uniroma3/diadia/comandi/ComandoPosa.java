package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	
	private IOConsole ioConsole = new IOConsole();
	private String nomeAttrezzo;
	
    public void esegui(Partita partita){
    	Attrezzo a = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		if(a==null)
			this.ioConsole.mostraMessaggio("Attrezzo non presente nella borsa");
		boolean aggiunta;
		if(a != null) {
			aggiunta = partita.getStanzaCorrente().addAttrezzo(a);
			if(aggiunta==true)
				this.ioConsole.mostraMessaggio("Attrezzo posato nella stanza");
			else
				this.ioConsole.mostraMessaggio("Attrezzo non posato nella stanza poiché piena");
		}
    }
    public void setParametro(String parametro) {
    	this.nomeAttrezzo = parametro;
    }
    
    public String getNome() {
	    return "posa";
	}
    public String getParametro() {
		return this.nomeAttrezzo;
    }
}
