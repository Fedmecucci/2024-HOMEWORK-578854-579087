package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto {

	private Stanza appoggio;
	private ArrayList<Stanza> stanze;

	public LabirintoBuilder() {
		super();
		this.appoggio= null;
		this.stanze = new ArrayList<Stanza>();
	}

	public Labirinto getLabirinto() {
		return this;
	}

	public LabirintoBuilder addStanzaIniziale(String s) {
		this.appoggio = new Stanza(s);
		stanze.add(appoggio);
		this.setStanzaIniziale(appoggio);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String s) {
		this.appoggio = new Stanza(s);
		stanze.add(appoggio);
		this.setStanzaVincente(appoggio);
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nome, int p) {
		this.appoggio.addAttrezzo(new Attrezzo(nome,p));
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String nome,String direzione,String pass) {
		Stanza s= new StanzaBloccata(nome,direzione,pass);
		this.appoggio = s;
		this.stanze.add(s);
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String nome,String luce) {
		Stanza s= new StanzaBuia(nome,luce);
		this.appoggio = s;
		this.stanze.add(s);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome,int soglia) {
		Stanza s= new StanzaMagica(nome,soglia);
		this.appoggio=s;
		this.stanze.add(s);
		return this;
		
	}
	
	public LabirintoBuilder addAdiacenza(String s1,String s2,String dir) {
		Stanza nonInizializzata = null;
		Stanza adiacente = null;
		for(Stanza stanza : stanze) {
			if(stanza.getNome()==s1)
				nonInizializzata=stanza;
			if(stanza.getNome()==s2)
				adiacente = stanza;
		}
		nonInizializzata.impostaStanzaAdiacente(dir, adiacente);
		return this;
	}
	
	public LabirintoBuilder addStanza(String s) {
		this.appoggio= new Stanza(s);
		stanze.add(appoggio);
		return this;
	}
}
