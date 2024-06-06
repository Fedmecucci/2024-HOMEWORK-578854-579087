package it.uniroma3.diadia.personaggi;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{

	private Attrezzo attrezzoRegalo;
	private Attrezzo attrezzoPreferito;
	
	private static final String MESSAGGIO_MORSO = "Il cane ti ha morso";

	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
		this.attrezzoPreferito = new Attrezzo("preferito", 4);
	}
	
	@Override
	public String agisci(Partita partita) {
		int  cfu = partita.getGiocatore().getCfu();
		cfu = cfu--;
		partita.getGiocatore().setCfu(cfu);
		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(this.attrezzoPreferito.getNome())) {
			this.attrezzoRegalo = attrezzo;
			partita.getStanzaCorrente().addAttrezzo(this.attrezzoRegalo);
			return "Grazie per il mio cibo preferito";
		}
		else {
			int  cfu = partita.getGiocatore().getCfu();
			cfu = cfu--;
			partita.getGiocatore().setCfu(cfu);
			return MESSAGGIO_MORSO;
		}
	}

}
