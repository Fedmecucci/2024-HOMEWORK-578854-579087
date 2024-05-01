package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private StanzaBuia stanza;
	private Attrezzo attrezzoSbloccante;
	public static final String STANZA_BUIA ="Qui c'è buio pesto";

	@BeforeEach
	void setUp() throws Exception {
		attrezzoSbloccante = new Attrezzo("lanterna", 3);
		stanza = new StanzaBuia("stanza", "lanterna");
	}

	@Test
	void testGetDescrizioneBuia() {
		assertEquals(stanza.getDescrizione(), STANZA_BUIA);
	}
	
	@Test
	void testGetDescrizioneIlluminata() {
		stanza.addAttrezzo(attrezzoSbloccante);
		assertNotEquals(stanza.getDescrizione(), STANZA_BUIA);
	}

}
