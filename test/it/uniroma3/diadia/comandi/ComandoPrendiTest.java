package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;




public class ComandoPrendiTest {

	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Comando comandoPrendi;
	Labirinto labirinto;

@BeforeEach
public void setUp() throws Exception {
	Labirinto labirinto = new Labirinto("src/it/uniroma3/diadia/labirinto2.txt");

	partita = new Partita(labirinto);
	attrezzo = new Attrezzo("spada", 2);
	comandoPrendi = new ComandoPosa();
	io = new IOConsole(new Scanner(System.in));
	comandoPrendi.setIo(io);
}


@Test
void testEseguiAttrezzoNotNull() throws IOException {
	
	this.comandoPrendi.esegui(partita);
	assertNotNull(this.partita.getGiocatore().getBorsa());
}

@Test
void testEseguiAttrezzoNonPreso() throws IOException {
	this.comandoPrendi.setParametro("ATTREZZO");
	this.comandoPrendi.esegui(partita);
	assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
}
}














//class ComandoPrendiTest {
//	
//	private Partita partita;
//    private ComandoPrendi comandoPrendi;
//
//	@BeforeEach
//	public void setUp() throws IOException {
//		this.partita = new Partita();
//		this.comandoPrendi = new ComandoPrendi();
//		this.comandoPrendi.setIo(new IOConsole());
//		Attrezzo attrezzo = new Attrezzo("attrezzo",1);
//		this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
//	}
//
//	@Test
//	void testEseguiAttrezzoPresoCorrettamente() {
//		this.comandoPrendi.setParametro("attrezzo");
//		this.comandoPrendi.esegui(partita);
//		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
//	}
//	
//	@Test
//	void testEseguiAttrezzoNonPreso() {
//		this.comandoPrendi.setParametro("ATTREZZO");
//		this.comandoPrendi.esegui(partita);
//		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
//	}
//	
//
//}
