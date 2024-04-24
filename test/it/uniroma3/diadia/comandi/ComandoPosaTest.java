package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {
	
	private Partita partita;
    private ComandoPosa comandoPosa;
	
	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
		this.comandoPosa = new ComandoPosa();
		Attrezzo attrezzo = new Attrezzo("attrezzo",1);
		this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	}

	@Test
	void testAttrezzoPosato() {
		this.comandoPosa.setParametro("attrezzo");
		this.comandoPosa.esegui(partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}
	
	@Test
	void testAttrezzoNonPosato() {
		this.comandoPosa.setParametro("ATTREZZO");
		this.comandoPosa.esegui(partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}

}
