package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class StanzaBloccataTest {
	
	private StanzaBloccata stanza;
	private Stanza stanzaAdiacente;
	private Attrezzo attrezzoSbloccante;

	@BeforeEach
	void setUp() throws IOException {
		attrezzoSbloccante = new Attrezzo("attrezzoSbloccante", 4);
		stanza = new StanzaBloccata("stanza", "direzioneBloccata" , "attrezzoSbloccante");
		stanzaAdiacente = new Stanza("adiacente");
	}

	@Test
	void testGetStanzaAdiacenteBloccata() {
		assertEquals(stanza.getStanzaAdiacente(Direzione.est), stanza);
	}
	
	@Test
	void testGetStanzaAdiacenteNonBloccata() {
		stanza.impostaStanzaAdiacente(Direzione.nord, stanzaAdiacente);
		stanza.addAttrezzo(attrezzoSbloccante);
		assertEquals(stanza.getStanzaAdiacente(Direzione.nord), stanzaAdiacente);
	}
	
}
