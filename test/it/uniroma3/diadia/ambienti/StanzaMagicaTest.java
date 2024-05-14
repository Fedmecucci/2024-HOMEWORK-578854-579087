package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	private StanzaMagica stanza;
	private Attrezzo attrezzo;
	private Map<String,Attrezzo> attrezzi;

	@BeforeEach
	void setUp() throws Exception {
		stanza = new StanzaMagica("stanza");
		attrezzo = new Attrezzo("attrezzo", 4);
		attrezzi = new HashMap<>();
	}

	@Test
	void testModificaAttrezzoEffettiva() {
		assertNotEquals(attrezzo, this.stanza.modificaAttrezzo(attrezzo));
	}
	
	@Test
	void testAddAttrezzoEffettivo() {
		assertEquals(true, this.stanza.addAttrezzo(attrezzo));
	}
	
	@Test
	void testAddAttrezzoNonMagico() {
		this.stanza.addAttrezzo(attrezzo);
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		assertEquals(this.stanza.getAttrezzi(), this.attrezzi);
	}

}
