package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class IOSimulatorTest {
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		
		labirinto = new LabirintoBuilder().
				addStanzaVincente("biblioteca")
				.addStanzaIniziale("atrio").addAttrezzo("osso", 1).
				addStanza("aulaN11").
				addStanza("aulaN10").addAttrezzo("lanterna", 3).
				addStanza("laboratorio").
				addAdiacenza("atrio","biblioteca","nord").
				addAdiacenza("atrio","aulaN11","est").
				addAdiacenza("atrio","aulaN10","sud").
				addAdiacenza("atrio","laboratorio","ovest").
				addAdiacenza("aulaN11","laboratorio","est").
				addAdiacenza("aulaN11","atrio","ovest").
				addAdiacenza("aulaN11","atrio","ovest").
				addAdiacenza("aulaN11","atrio","ovest").
				addAdiacenza("aulaN10","atrio","nord").
				addAdiacenza("aulaN10","aulaN11","est").
				addAdiacenza("aulaN10","laboratorio","ovest").
				addAdiacenza("laboratorio","atrio","est").
				addAdiacenza("laboratorio","aulaN11","ovest").
				addAdiacenza("biblioteca","atrio","sud").
				getLabirinto();
		
	}

	@Test
	public void testComandoVittoria() {
	        HashMap<Integer, String> inputSimulato = new HashMap<>();
	        inputSimulato.put(0, "vai nord");

	        IOSimulator ioSimulator = new IOSimulator(inputSimulato);
	        DiaDia gioco = new DiaDia(labirinto, ioSimulator);

	        gioco.processaIstruzione(ioSimulator.leggiRiga());
	        assertTrue(gioco.partita.vinta());

	        //gioco.processaIstruzione(ioSimulator.leggiRiga());
	        //String aiuto = ioSimulator.getMessaggiStampati().get(ioSimulator.getIndiceStampe() - 1);
	        //assertNotNull(aiuto);
	}
	
	@Test
	public void test5ComandiVaiPrendiPosaVittoria() {
		HashMap<Integer, String> inputSimulato = new HashMap<>();
        inputSimulato.put(0, "vai sud");
        inputSimulato.put(1, "prendi lanterna");
        inputSimulato.put(2, "vai nord");
        inputSimulato.put(3, "posa lanterna");
        inputSimulato.put(4, "vai nord");

        IOSimulator ioSimulator = new IOSimulator(inputSimulato);
        DiaDia gioco = new DiaDia(labirinto, ioSimulator);
        
        assertEquals("atrio", gioco.partita.getStanzaCorrente().getNome());
        
        gioco.processaIstruzione(ioSimulator.leggiRiga());
        assertEquals("aulaN10", gioco.partita.getStanzaCorrente().getNome());
        
        gioco.processaIstruzione(ioSimulator.leggiRiga());
        assertTrue(gioco.partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
        
        gioco.processaIstruzione(ioSimulator.leggiRiga());
        assertEquals("atrio", gioco.partita.getStanzaCorrente().getNome());
        
        gioco.processaIstruzione(ioSimulator.leggiRiga());
        assertTrue(gioco.partita.getGiocatore().getBorsa().isEmpty());
        
        gioco.processaIstruzione(ioSimulator.leggiRiga());
        assertTrue(gioco.partita.vinta());

	}

}
