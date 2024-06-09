package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoVaiTest {
	
	private Partita partita;
	private ComandoVai comandoVai;
	
	@BeforeEach
	public void setUp() throws IOException {
		this.partita = new Partita();
		this.comandoVai =  new ComandoVai();
		this.comandoVai.setIo(new IOConsole());
		Stanza stanza1 = new Stanza("stanza1");
        Stanza stanza2 = new Stanza("stanza2");
        stanza1.impostaStanzaAdiacente(Direzione.nord, stanza2);
        this.partita.setStanzaCorrente(stanza1);
	}

	@Test
	void testEseguiDirezioneGiusta() {
		this.comandoVai.setParametro("nord");
		this.comandoVai.esegui(partita);
		assertEquals("stanza2", this.partita.getStanzaCorrente().getNome());
	}
	@Test
	void testEseguiAltraDirezione() {
		this.comandoVai.setParametro("sud");
		this.comandoVai.esegui(partita);
		assertNotEquals("stanza2", this.partita.getStanzaCorrente().getNome());
	}
	

}
