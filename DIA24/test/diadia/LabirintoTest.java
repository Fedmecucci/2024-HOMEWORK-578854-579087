package diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {

	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		this.labirinto = new Labirinto();
	}

	@Test
	public void stanzaInizialeSiaDiversaDaNull() {
		assertNotNull(this.labirinto.getStanzaIniziale());
	}
	
	@Test
	public void stanzaVincenteNonENull() {
		assertNotNull(this.labirinto.getStanzaVincente());
	}
	
	
}
