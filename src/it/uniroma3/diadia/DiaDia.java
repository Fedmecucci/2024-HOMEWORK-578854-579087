package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;

	private IOConsole ioConsole;

	public DiaDia(IOConsole ioConsole) {
		this.partita = new Partita();
		this.ioConsole = ioConsole;
	}

	public void gioca() {
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
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(comandoDaEseguire.getNome()==null) {
			this.ioConsole.mostraMessaggio("Devi digitare un comando");
			return false;
		}

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		
		else
			this.ioConsole.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.ioConsole.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:
	private void prendi(String nomeAttrezzo) {
		
		Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if(this.partita.getStanzaCorrente().removeAttrezzo(attrezzo)) {
			this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
			this.ioConsole.mostraMessaggio("Attrezzo inserito nella borsa");
		}
		else
			this.ioConsole.mostraMessaggio("Attrezzo non presente nella stanza");
	}
	private void posa(String nomeAttrezzo) {
		
		Attrezzo a = this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		if(a==null)
			this.ioConsole.mostraMessaggio("Attrezzo non presente nella borsa");
		boolean aggiunta;
		if(a != null) {
			aggiunta = this.partita.getStanzaCorrente().addAttrezzo(a);
			if(aggiunta==true)
				this.ioConsole.mostraMessaggio("Attrezzo posato nella stanza");
			else
				this.ioConsole.mostraMessaggio("Attrezzo non posato nella stanza poiché piena");
		}
	}

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.ioConsole.mostraMessaggio(elencoComandi[i]+" ");
		this.ioConsole.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.ioConsole.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.ioConsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu--);
		}
		this.ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole ioConsole = new IOConsole();
		DiaDia gioco = new DiaDia(ioConsole);
		gioco.gioca();
	}
}