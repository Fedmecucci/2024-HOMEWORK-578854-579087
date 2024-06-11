package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {

	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() throws IOException {
		this.labirinto = new Labirinto();
	}

	@Test
	public void stanzaInizialeSiaDiversaDaNull() {
		assertNull(this.labirinto.getStanzaIniziale());
	}
	
	@Test
	public void stanzaVincenteNonENull() {
		assertNull(this.labirinto.getStanzaVincente());
	}
	
	
}
