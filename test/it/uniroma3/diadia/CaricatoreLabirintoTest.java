package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import java.io.StringReader;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirintoTest {

    private CaricatoreLabirinto caricatore;
    
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCaricaLabirintoMonolocale() throws Exception {
        // Arrange
        String specificaLabirinto = "Stanze: Stanza1\n" +
                                    "Inizio: Stanza1\n" +
                                    "Vincente: Stanza1\n" +
                                    "Attrezzi: Spada 3 Stanza1\n" +
                                    "Uscite: Stanza1 nord Stanza1";
        
        caricatore = new CaricatoreLabirinto(new StringReader(specificaLabirinto));

        // Act
        caricatore.carica();
        
        assertEquals(" Stanza1", this.caricatore.getStanzaIniziale().getNome());
		assertEquals(" Stanza1", this.caricatore.getStanzaVincente().getNome());

    }

    @Test
    public void testCaricaLabirintoBilocale() throws Exception {
        // Arrange
        String specificaLabirinto = "Stanze: Stanza1, Stanza2\n" +
                                    "Inizio: Stanza1\n" +
                                    "Vincente: Stanza2\n" +
                                    "Attrezzi: Spada 3 Stanza1, Scudo 2 Stanza2\n" +
                                    "Uscite: Stanza1 nord Stanza2, Stanza2 sud Stanza1";
        
        caricatore = new CaricatoreLabirinto(new StringReader(specificaLabirinto));

        // Act
        caricatore.carica();
        
        assertEquals(" Stanza1", this.caricatore.getStanzaIniziale().getNome());
		assertEquals(" Stanza2", this.caricatore.getStanzaVincente().getNome());
		
		Attrezzo expected = new Attrezzo("Spada", 3);
		assertEquals(expected, this.caricatore.getStanzaIniziale().getAttrezzo("Spada"));
		Attrezzo scudo = new Attrezzo("Scudo", 2);
		assertEquals(scudo, this.caricatore.getStanzaVincente().getAttrezzo("Scudo"));
		
		//assertEquals(" Stanza2", this.caricatore.getStanzaIniziale().getStanzaAdiacente("nord").getNome());

    }

}
