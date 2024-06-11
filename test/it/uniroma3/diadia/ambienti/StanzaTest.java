package it.uniroma3.diadia.ambienti;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	    private static final int MAX_ATTREZZI= 10;
	    private static final String ATTREZZO = "AttrezzoDiTest";
		private static final String STANZA = "StanzaTest";
		private static final String STANZA_ADIACENTE = "StanzaAdiacente";
		private static final String NORD = "nord";
		
		protected Attrezzo a;
		protected Map<String,Attrezzo> attrezzi;
		protected Set<String> directions;
	    protected Stanza stanza;
	    protected int numeroAttrezzi;
	    
	    @BeforeEach
	    public void setUp() throws IOException {
	    	this.stanza = new Stanza(STANZA);
	    	this.a = new Attrezzo(ATTREZZO, 24);
	    	this.attrezzi = new HashMap<String,Attrezzo>();
	    	directions = new HashSet<String>();
	    }
	    
	    @Test
	    public void testImpostaStanzaAdiacenteSingola() throws IOException {
	    	Stanza adiacente = creaStanzaEImpostaAdiacente(this.stanza,STANZA_ADIACENTE ,Direzione.nord);
	    	assertEquals(adiacente,this.stanza.getStanzaAdiacente(Direzione.nord));
	    }
	    
	   @Test
	    public void testGetStanzaAdiacenteNonEsistente() {
	    	assertNull(this.stanza.getStanzaAdiacente(Direzione.nord));
	    }
	    
	    @Test
	     public void testGetStanzaAdiacenteEsistente() throws IOException {
	    	creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, Direzione.nord);
	    	assertNotNull(this.stanza.getStanzaAdiacente(Direzione.nord));
	    }
	    
	    @Test
	    public void testGetStanzaAdiacenteDirezioneNonValida() throws IOException {
	    	creaStanzaEImpostaAdiacente(this.stanza,STANZA_ADIACENTE, Direzione.nord);
	    	assertNull(this.stanza.getStanzaAdiacente(Direzione.est));
	    }
	    
	  
	    @Test
	    public void testGetDirezionivuoto() {
	    	assertEquals(this.directions, this.stanza.getDirezioni());
	    }
	    
	    
	    //Nuovi test dopo implementazione collezioni
	    @Test
	    public void testGetDirezioniVuoto() throws IOException {
	    	//this.directions.add(NORD);
	    	//creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, Direzione.nord);
	    	assertEquals(this.directions, this.stanza.getDirezioni());
	    }
	    
	    @Test
	    public void testgetAttrezziNonVuoto() {
	    	this.attrezzi.put(this.a.getNome(), this.a);
	    	this.stanza.addAttrezzo(this.a);
	    	assertEquals(this.stanza.getAttrezzi(), this.attrezzi);
	    }
	    @Test
	    public void testgetAttrezziVuoto() {
	    	attrezzi.put(this.a.getNome(), this.a);
	    	//this.stanza.addAttrezzo(this.a);
	    	assertNotEquals(this.stanza.getAttrezzi(), this.attrezzi);
	    }
	    
	    @Test
	    public void testAddAttrezzoRiuscito() {
	    	this.attrezzi.put(a.getNome(), a);
	    	this.stanza.addAttrezzo(a);
	    	assertEquals(this.stanza.getAttrezzi(), this.attrezzi);
	    }
	    
	    @Test
	    public void testAddAttrezzoNonRiuscito() {
	    	this.stanza.setNumeroAttrezzi(10);
	    	this.attrezzi.put(a.getNome(), a);
	    	this.stanza.addAttrezzo(a);
	    	assertNotEquals(this.stanza.getAttrezzi(), this.attrezzi);
	    }
	    
	    @Test
	    public void removeAttrezzoSuMapVuota() {
	    	assertEquals(false, this.stanza.removeAttrezzo(a));
	    }
	    
	    @Test
	    public void removeAttrezzoSuMapNonVuota() {
	    	this.stanza.addAttrezzo(a);
	    	assertEquals(true, this.stanza.removeAttrezzo(a));
	    }
	    
	    
	 // metodo di utilita per risparmiare righe di codice(in cui creo stanza adiacente)
	    
	  private Stanza creaStanzaEImpostaAdiacente(Stanza stanzaDiPartenza, String nomeStanzaAdiacente, Direzione direzione) throws IOException {
	    	Stanza stanzaAdiacente = new Stanza(nomeStanzaAdiacente);
	    	stanzaDiPartenza.impostaStanzaAdiacente(direzione, stanzaAdiacente);
	    	return stanzaAdiacente;
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

