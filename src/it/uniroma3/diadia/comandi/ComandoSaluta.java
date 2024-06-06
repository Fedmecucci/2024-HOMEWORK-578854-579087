package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	
	private static final String NON_PRESENTE = "Il personaggio non è presente";
	private IO io;

	@Override
	public void esegui(Partita partita) {
		 AbstractPersonaggio personaggio;
		 personaggio = partita.getStanzaCorrente().getPersonaggio();
		 if (personaggio!=null) {
		    io.mostraMessaggio(personaggio.saluta());
		 }
		 else {
			 io.mostraMessaggio(NON_PRESENTE);
		 }
		
	}
	
	@Override
	public void setIo(IO ioConsole) {
		this.io = ioConsole;
	}
	
	@Override
	public String getNome() {
		return "saluta";
	}

}
