package diadia;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {

	private Partita partita;
	
	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
		
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
