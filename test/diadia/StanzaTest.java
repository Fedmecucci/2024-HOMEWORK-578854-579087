package diadia;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import diadia.Stanza;

class StanzaTest {

	    private static final int MAX_ATTREZZI= 10;
	    private static final String ATTREZZO = "AttrezzoDiTest";
		private static final String STANZA = "StanzaTest";
		private static final String STANZA_ADIACENTE = "StanzaAdiacente";
		private static final String NORD = "nord";
		
	    protected Stanza stanza;
	    
	    @BeforeEach
	    public void setUp() {
	    	this.stanza = new Stanza(STANZA);
	    }
	    
	    @Test
	    public void testImpostaStanzaAdiacenteSingola() {
	    	Stanza adiacente = creaStanzaEImpostaAdiacente(this.stanza,STANZA_ADIACENTE ,NORD);
	    	assertEquals(adiacente,this.stanza.getStanzaAdiacente(NORD));
	    }
	    
	   @Test
	    public void testGetStanzaAdiacenteNonEsistente() {
	    	assertNull(this.stanza.getStanzaAdiacente(NORD));
	    }
	    
	    @Test
	     public void testGetStanzaAdiacenteEsistente() {
	    	creaStanzaEImpostaAdiacente(this.stanza, STANZA_ADIACENTE, NORD);
	    	assertNotNull(this.stanza.getStanzaAdiacente(NORD));
	    }
	    
	    @Test
	    public void testGetStanzaAdiacenteDirezioneNonValida() {
	    	creaStanzaEImpostaAdiacente(this.stanza,STANZA_ADIACENTE, NORD);
	    	assertNull(this.stanza.getStanzaAdiacente("nonValida"));
	    }
	    
	  
	    @Test
	    public void testGetDirezionivuoto() {
	    	assertArrayEquals(new String[0], this.stanza.getDirezioni());
	    }
	    
	 // metodo di utilita per risparmiare righe di codice(in cui creo stanza adiacente)
	    
	  private Stanza creaStanzaEImpostaAdiacente(Stanza stanzaDiPartenza, String nomeStanzaAdiacente, String direzione) {
	    	Stanza stanzaAdiacente = new Stanza(nomeStanzaAdiacente);
	    	stanzaDiPartenza.impostaStanzaAdiacente(direzione, stanzaAdiacente);
	    	return stanzaAdiacente;
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

