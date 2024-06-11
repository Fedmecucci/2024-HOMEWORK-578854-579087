package it.uniroma3.diadia.ambienti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {


	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	
	public Labirinto() throws IOException {
		//this.creaStanze();
	}
	
	public Labirinto(String nomeFile) throws FormatoFileNonValidoException, IOException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}	 

	 /**
     * Crea tutte le stanze e le porte di collegamento
	 * @throws IOException 
     */
//    private void creaStanze() throws IOException {
//
//		/* crea gli attrezzi */
//    	Attrezzo lanterna = new Attrezzo("lanterna",3);
//		Attrezzo osso = new Attrezzo("osso",1);
//    	
//		/* crea stanze del labirinto */
//		Stanza atrio = new Stanza("Atrio");
//		Stanza aulaN11 = new Stanza("Aula N11");
//		Stanza aulaN10 = new Stanza("Aula N10");
//		Stanza laboratorio = new Stanza("Laboratorio Campus");
//		Stanza biblioteca = new Stanza("Biblioteca");
//		
//		/* collega le stanze */
//		atrio.impostaStanzaAdiacente("nord", biblioteca);
//		atrio.impostaStanzaAdiacente("est", aulaN11);
//		atrio.impostaStanzaAdiacente("sud", aulaN10);
//		atrio.impostaStanzaAdiacente("ovest", laboratorio);
//		aulaN11.impostaStanzaAdiacente("est", laboratorio);
//		aulaN11.impostaStanzaAdiacente("ovest", atrio);
//		aulaN10.impostaStanzaAdiacente("nord", atrio);
//		aulaN10.impostaStanzaAdiacente("est", aulaN11);
//		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
//		laboratorio.impostaStanzaAdiacente("est", atrio);
//		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
//		biblioteca.impostaStanzaAdiacente("sud", atrio);
//
//        /* pone gli attrezzi nelle stanze */
//		aulaN10.addAttrezzo(lanterna);
//		atrio.addAttrezzo(osso);
//
//		// il gioco comincia nell'atrio
//        stanzaIniziale = atrio;  
//		stanzaVincente = biblioteca;
//    }
	
	
	//costruttore classe nidificata
	public static LabirintoBuilder newBuilder() throws FormatoFileNonValidoException, IOException {
		return new LabirintoBuilder();
	}
	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	//===========================================
		public static class LabirintoBuilder {
			
			
			

			private Stanza appoggio;
		private ArrayList<Stanza> stanze;
		//
		public LabirintoBuilder() throws IOException {
			super();
			this.appoggio= null;
			this.stanze = new ArrayList<Stanza>();
		}
		
			public Labirinto getLabirinto() {
			return this.getLabirinto();
		}
		
			public LabirintoBuilder addStanzaIniziale(String s) throws IOException {
			this.appoggio = new Stanza(s);
				stanze.add(appoggio);
			this.getLabirinto().setStanzaIniziale(appoggio);
			return this;
		}
		
			public LabirintoBuilder addStanzaVincente(String s) throws IOException {
				this.appoggio = new Stanza(s);
				stanze.add(appoggio);
			this.getLabirinto().setStanzaVincente(appoggio);
			return this;
		}
		
			public LabirintoBuilder addAttrezzo(String nome, int p) {
 			this.appoggio.addAttrezzo(new Attrezzo(nome,p));
			return this;
			}
		
		public LabirintoBuilder addStanzaBloccata(String nome,String direzione,String pass) throws IOException {
				Stanza s= new StanzaBloccata(nome,direzione,pass);
				this.appoggio = s;
			this.stanze.add(s);
			return this;
			}
		
			public LabirintoBuilder addStanzaBuia(String nome,String luce) throws IOException {
				Stanza s= new StanzaBuia(nome,luce);
				this.appoggio = s;
				this.stanze.add(s);
			return this;
			}
			
			public LabirintoBuilder addStanzaMagica(String nome,int soglia) throws IOException {
				Stanza s= new StanzaMagica(nome,soglia);
				this.appoggio=s;
				this.stanze.add(s);
				return this;
				
			}
			
			public LabirintoBuilder addAdiacenza(String s1,String s2,Direzione dir) {
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
			
		public LabirintoBuilder addStanza(String s) throws IOException {
				this.appoggio= new Stanza(s);
				stanze.add(appoggio);
			return this;
		}
			
		}

}
