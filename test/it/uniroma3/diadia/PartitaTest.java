package it.uniroma3.diadia;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class PartitaTest {

	private Partita partita;
	
	@BeforeEach
	public void setUp() throws IOException, FormatoFileNonValidoException {
		Labirinto labirinto = new Labirinto("src/it/uniroma3/diadia/labirinto2.txt");
		this.partita = new Partita(labirinto);		
	}
	
	@Test
	public void testGetStanzaVincenteNotNull() {
		assertNotNull(this.partita.getStanzaVincente());
	}

	@Test
	public void testStanzaCorrenteEVincente() {
		this.partita.setStanzaCorrente(this.partita.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testPartitaNonVintaQuandoIniziaLaPartita() {
		assertFalse(this.partita.vinta());
	}
	

	
}
