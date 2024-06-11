package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPosaTest {

	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Comando comandoPosa;
	Labirinto labirinto;

@BeforeEach
public void setUp() throws Exception {
	Labirinto labirinto = new Labirinto("src/it/uniroma3/diadia/labirinto2.txt");

	partita = new Partita(labirinto);
	attrezzo = new Attrezzo("spada", 2);
	comandoPosa = new ComandoPosa();
	io = new IOConsole(new Scanner(System.in));
	comandoPosa.setIo(io);
}


@Test
public void testAttrezzoPosato() throws IOException {
	partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
	this.comandoPosa.setParametro("martello");
	this.comandoPosa.esegui(partita);
	assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
}

@Test
public void testAttrezzoPosatoNull() throws IOException {
	this.comandoPosa.setParametro("martello");
	this.comandoPosa.esegui(partita);
	assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
	
}
@Test
void testAttrezzoNonPosato() throws IOException {
   this.comandoPosa.setParametro("ATTREZZO");
	this.comandoPosa.esegui(partita);
	assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("attrezzo"));
}

}












