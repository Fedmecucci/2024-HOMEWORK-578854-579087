package it.uniroma3.diadia;


import java.io.IOException;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	public Partita partita;

	private IO ioConsole;

	private Labirinto labirinto;
	
	
	
	public DiaDia(IO ioConsole) throws IOException {
		this.partita = new Partita();
		this.ioConsole = ioConsole;
	}
	

	
	//costruttore per LabirintoBuilder che passa il labirinto a partita
		public DiaDia(Labirinto labirinto,IO io) throws IOException {
			this.partita = new Partita(labirinto);
			this.labirinto = labirinto;
			this.ioConsole = io;
		}
	
	
	
	public void gioca() throws Exception {
		String istruzione; 

		this.ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.ioConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	public boolean processaIstruzione(String istruzione) throws Exception {
		 AbstractComando comandoDaEseguire = null;
		 FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		
			comandoDaEseguire =  (AbstractComando) factory.costruisciComando(istruzione,this.ioConsole);
   
		 comandoDaEseguire.esegui(this.partita); 
  
		 if (this.partita.vinta())
		     this.ioConsole.mostraMessaggio("Hai vinto!");
		 //if (!this.partita.giocatoreIsVivo())
		 //    this.ioConsole.mostraMessaggio("Hai esaurito i CFU...");
		 return this.partita.isFinita();
	}   

	
	
	public static void main(String[] argc) throws Exception {
		IO ioConsole = new IOConsole();
		
		
		
//		Labirinto labirinto = new LabirintoBuilder().
//				addStanzaVincente("biblioteca")
//				.addStanzaIniziale("atrio").addAttrezzo("osso", 1).
//				addStanza("aulaN11").
//				addStanza("aulaN10").addAttrezzo("lanterna", 3).
//				addStanza("laboratorio").
//				addAdiacenza("atrio","biblioteca","nord").
//				addAdiacenza("atrio","aulaN11","est").
//				addAdiacenza("atrio","aulaN10","sud").
//				addAdiacenza("atrio","laboratorio","ovest").
//				addAdiacenza("aulaN11","laboratorio","est").
//				addAdiacenza("aulaN11","Atrio","ovest").
//				addAdiacenza("aulaN11","Atrio","ovest").
//				addAdiacenza("aulaN11","Atrio","ovest").
//				addAdiacenza("aulaN10","Atrio","nord").
//				addAdiacenza("aulaN10","aulaN11","est").
//				addAdiacenza("aulaN10","laboratorio","ovest").
//				addAdiacenza("laboratorio","atrio","est").
//				addAdiacenza("laboratorio","aulaN11","ovest").
//				addAdiacenza("biblioteca","atrio","sud").
//				getLabirinto();
		
		//Labirinto labirinto = new Labirinto("src/it/uniroma3/diadia/primolabirinto.txt");
		Labirinto labirinto = new Labirinto("src/it/uniroma3/diadia/labirinto2.txt");
		
		DiaDia gioco = new DiaDia(labirinto, ioConsole);
		gioco.gioca();
	}
}