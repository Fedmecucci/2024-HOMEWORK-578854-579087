package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {

	private IO ioConsole;
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		Attrezzo a = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		if(a==null)
			this.ioConsole.mostraMessaggio("Attrezzo non presente nella borsa");
		String stampa;
		if(a != null) {
			stampa = partita.getStanzaCorrente().getPersonaggio().riceviRegalo(a, partita);
				this.ioConsole.mostraMessaggio(stampa);
		}
	}
	
    @Override
    public void setParametro(String parametro) {
    	this.nomeAttrezzo = parametro;
    }
    
    @Override
    public String getNome() {
	    return "regala";
	}
    @Override
    public String getParametro() {
		return this.nomeAttrezzo;
    }
	@Override
	public void setIo(IO ioConsole) {
		this.ioConsole=ioConsole;
	}

}
