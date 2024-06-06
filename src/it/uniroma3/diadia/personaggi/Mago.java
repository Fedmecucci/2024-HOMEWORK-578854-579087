package it.uniroma3.diadia.personaggi;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{
	
	private Attrezzo attrezzoRegalo;

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Attrezzo attrezzo;
	
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		int pesoDimezzato = attrezzo.getPeso()/2;
		this.attrezzoRegalo = new Attrezzo(attrezzo.getNome(), pesoDimezzato);
		partita.getStanzaCorrente().addAttrezzo(this.attrezzoRegalo);
		this.attrezzoRegalo = null;
		return "Mi ha regalato un attrezzo, gli ho dimezzato il peso e l'ho lasciato cadere nella stanza";
	}
}
