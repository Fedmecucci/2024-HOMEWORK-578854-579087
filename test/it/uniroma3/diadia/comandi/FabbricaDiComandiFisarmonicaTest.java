package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabbrica;
	private Partita partita;
	private Stanza stanza1;
	private Stanza stanza2;
	private Attrezzo attrezzo;
	private Borsa borsa;

	@BeforeEach
	void setUp() {
		this.fabbrica = new FabbricaDiComandiFisarmonica();
	   
		this.partita = new Partita();
		this.stanza1 = new Stanza("stanza1");
		this.stanza2 = new Stanza("stanza2");
		partita.setStanzaCorrente(stanza1);
		partita.getStanzaCorrente().impostaStanzaAdiacente("nord", stanza2);
		this.attrezzo = new Attrezzo("attrezzo", 2);
		this.borsa = new Borsa();
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
	}

	@Test
	void testCostruisciComandoVai() {
		Comando comando = this.fabbrica.costruisciComando("vai nord", new IOConsole());
		comando.esegui(partita);
		assertEquals("vai", comando.getNome());
		assertEquals("nord", comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoVaiConParametroNullo() {
		Comando comando = fabbrica.costruisciComando("vai", new IOConsole());
		comando.esegui(partita);
		assertEquals("vai", comando.getNome());
		assertNull(comando.getParametro());
	}

	@Test
	public void testCostruisciComandoAiuto() {
    	Comando comando = fabbrica.costruisciComando("aiuto", new IOConsole());
    	comando.esegui(partita);
		assertEquals("aiuto", comando.getNome());
		assertNull(comando.getParametro());
	}
	
	@Test
	public void testCostruisciComandoPrendi() {
		Comando comando = fabbrica.costruisciComando("prendi chiave", new IOConsole());
		comando.esegui(partita);
		assertEquals("prendi", comando.getNome());
		assertEquals("chiave", comando.getParametro());
	}

}
