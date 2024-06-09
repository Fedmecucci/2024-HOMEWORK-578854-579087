package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	
	private Giocatore giocatore;

	@BeforeEach
	public void setUp() throws IOException {
		 this.giocatore = new Giocatore();
	}

	
	@Test
	public void CfuInizialiSianoUgualiA20() {
		assertEquals(20,this.giocatore.getCfu());
	}
	
	@Test
	public void CfuInizialiNonSianoUgualiAZero() {
		assertNotEquals(0,this.giocatore.getCfu());
	}
}
