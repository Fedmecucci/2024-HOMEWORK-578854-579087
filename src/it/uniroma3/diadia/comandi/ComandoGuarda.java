package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{

	

	private IO ioConsole;

	@Override
	public void esegui(Partita partita) {
		this.ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

    public String getNome() {
		return "guarda";
	}
	public String getParametro() {
		return null;
	}

	@Override
	public void setIo(IO ioConsole) {
		this.ioConsole=ioConsole;

	}



}
