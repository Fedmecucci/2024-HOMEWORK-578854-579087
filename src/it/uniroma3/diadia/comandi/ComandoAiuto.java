package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", "saluta", "interagisci", "regala"};

	private IO ioConsole;

	/**
	 * Stampa informazioni di aiuto.
	 */
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			this.ioConsole.mostraMessaggio(elencoComandi[i]+" ");
		this.ioConsole.mostraMessaggio("");
	}

	@Override
   public String getNome() {
		return "aiuto";
	}
	
	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public void setIo(IO ioConsole) {
		this.ioConsole=ioConsole;

	}

}
