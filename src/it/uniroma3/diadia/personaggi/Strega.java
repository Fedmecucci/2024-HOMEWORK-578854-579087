package it.uniroma3.diadia.personaggi;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	private Attrezzo attrezzoRegalo;

	private Stanza stanza;
	private Stanza stanzaAdiacente;

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) throws IOException {
		stanza = partita.getStanzaCorrente();
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		int max = 0;
		int min = 10;
		Stanza stanzaMax = new Stanza("");
        Stanza stanzaMin = new Stanza("");
		Map<Direzione, Stanza> stanzeAd = stanza.getAdiacenti();
		Collection<Stanza> stanze = stanzeAd.values();
        for(Stanza s : stanze) {
        	if(s.getNumeroAttrezzi()>max)
        		stanzaMax = s;
        	if(s.getNumeroAttrezzi()<min)
        		stanzaMin = s;
        }
		if(personaggio.haSalutato()) {
			partita.setStanzaCorrente(stanzaMax);
		}
		else {
			partita.setStanzaCorrente(stanzaMin);
		}
		
		return "Ti ho spostato in una stanza adiacente";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		this.attrezzoRegalo = attrezzo;
		return "Ahahahahah";
	}
	
	

}
