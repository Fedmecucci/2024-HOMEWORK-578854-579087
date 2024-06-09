package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {
	
	private Partita partita;
    private ComandoPrendi comandoPrendi;

	@BeforeEach
	public void setUp() throws IOException {
		this.partita = new Partita();
		this.comandoPrendi = new ComandoPrendi();
		this.comandoPrendi.setIo(new IOConsole());
		Attrezzo attrezzo = new Attrezzo("attrezzo",1);
		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
	}

	@Test
	void testEseguiAttrezzoPresoCorrettamente() {
		this.comandoPrendi.setParametro("attrezzo");
		this.comandoPrendi.esegui(partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}
	
	@Test
	void testEseguiAttrezzoNonPreso() {
		this.comandoPrendi.setParametro("ATTREZZO");
		this.comandoPrendi.esegui(partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
	}
	

}
