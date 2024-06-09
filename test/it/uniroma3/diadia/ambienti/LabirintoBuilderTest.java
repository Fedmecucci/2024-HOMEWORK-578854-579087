package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;


import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoBuilderTest {

	private LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@BeforeEach
	public void setUp() throws Exception {
		labirintoBuilder = new LabirintoBuilder();
	}
	
	@Test
	public void testMonolocale() throws IOException {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMonolocale2() throws IOException {
		Labirinto monolocale2 = labirintoBuilder
				.addStanzaIniziale("biblioteca")
				.addStanzaVincente("ufficio")
				.getLabirinto();
		assertEquals("biblioteca",monolocale2.getStanzaIniziale().getNome());
		assertNotEquals(nomeStanzaIniziale,monolocale2.getStanzaIniziale().getNome());
		assertNotEquals(nomeStanzaVincente,monolocale2.getStanzaVincente().getNome());
	}
	
	
	
	@Test
	public void testMonolocaleConAttrezzo() throws IOException {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada",1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getStanzaIniziale().getAttrezzo("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}
	
	
	
	@Test
	public void testBilocale() throws IOException {
		Labirinto bilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, Direzione.nord)
				.addAdiacenza(nomeStanzaVincente, nomeStanzaIniziale, Direzione.sud)
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.nord));
		assertEquals(Collections.singletonList(Direzione.nord),bilocale.getStanzaIniziale().getDirezioni());
		assertEquals(Collections.singletonList(Direzione.sud),bilocale.getStanzaVincente().getDirezioni());
	}

	@Test
	public void testTrilocale() throws IOException{
		Labirinto trilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", Direzione.sud)
				.addAdiacenza("biblioteca", nomeStanzaIniziale, Direzione.nord)
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("biblioteca", nomeStanzaVincente, Direzione.est)
				.addAdiacenza(nomeStanzaVincente,"biblioteca" , Direzione.ovest)
				.getLabirinto();	
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.sud).getNome());
	}
	
	
	
	
}
