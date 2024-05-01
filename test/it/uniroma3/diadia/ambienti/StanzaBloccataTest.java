package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class StanzaBloccataTest {
	
	private StanzaBloccata stanza;
	private Stanza stanzaAdiacente;
	private Attrezzo attrezzoSbloccante;

	@BeforeEach
	void setUp() {
		attrezzoSbloccante = new Attrezzo("attrezzoSbloccante", 4);
		stanza = new StanzaBloccata("stanza", "direzioneBloccata" , "attrezzoSbloccante");
		stanzaAdiacente = new Stanza("adiacente");
	}

	@Test
	void testGetStanzaAdiacenteBloccata() {
		assertEquals(stanza.getStanzaAdiacente("a caso"), stanza);
	}
	
	@Test
	void testGetStanzaAdiacenteNonBloccata() {
		stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		stanza.addAttrezzo(attrezzoSbloccante);
		assertEquals(stanza.getStanzaAdiacente("nord"), stanzaAdiacente);
	}
	
}
