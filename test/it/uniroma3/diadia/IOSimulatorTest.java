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
		
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("LabCampusOne").
				addStanzaVincente("Biblioteca").
				addAdiacenza("LabCampusOne","Biblioteca","ovest").
				getLabirinto();
		
	}

	@Test
	public void testComandi() {
	        HashMap<Integer, String> inputSimulato = new HashMap<>();
	        inputSimulato.put(0, "vai nord");
	        inputSimulato.put(1, "prendi chiave");
	        inputSimulato.put(2, "posa chiave");
	        inputSimulato.put(3, "aiuto");

	        IOSimulator ioSimulator = new IOSimulator(inputSimulato);
	        DiaDia gioco = new DiaDia(labirinto, ioSimulator);

	        gioco.processaIstruzione(ioSimulator.leggiRiga());
	        assertEquals();

	        gioco.processaIstruzione(ioSimulator.leggiRiga());
	        assertTrue();

	        gioco.processaIstruzione(ioSimulator.leggiRiga());
	        assertFalse();

	        gioco.processaIstruzione(ioSimulator.leggiRiga());
	        //String aiuto = ioSimulator.getMessaggiStampati().get(ioSimulator.getIndiceStampe() - 1);
	        //assertNotNull(aiuto);
	}

}
