package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando {

	
	private IO ioConsole;

	@Override
	public void esegui(Partita partita) {
		this.ioConsole.mostraMessaggio("Comando sconosciuto");
	}
	@Override
	public String getNome() {
		return null;
	}
	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public void setIo(IO ioConsole) {
		this.ioConsole= ioConsole;

	}

}
