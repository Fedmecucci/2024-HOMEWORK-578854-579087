package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Strega;
import it.uniroma3.diadia.personaggi.Mago;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  	
	
	/* prefisso della riga contenente il nome stanza buia */
	private static final String STANZE_BUIE_MARKER = "Buia:";  

	/* prefisso della riga contenente il nome stanza bloccata */
	private static final String STANZE_BLOCCATE_MARKER = "Bloccata:";  
	
	/* prefisso della riga contenente il nome stanza bloccata */
	private static final String STANZE_MAGICHE_MARKER = "Magica:";  
	
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeMago> <presentazione> <attrezzo> */
	private static final String PERSONAGGI_MARKER_MAGO = "Mago:";
	
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeStrega> <presentazione> */
	private static final String PERSONAGGI_MARKER_STREGA = "Strega:";
	
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeCane> <presentazione> */
	private static final String PERSONAGGI_MARKER_CANE = "Cane:";
	
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";


	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	
	// Constructor for testing with a Reader
    public CaricatoreLabirinto(Reader reader) throws FormatoFileNonValidoException {
        this.nome2stanza = new HashMap<String, Stanza>();
        this.reader = new LineNumberReader(reader);
    }
    

	public Map<String, Stanza> getNome2stanza() {
		return nome2stanza;
	}


	public void carica() throws FormatoFileNonValidoException, IOException {
		try {
			this.leggiECreaStanze();
//			this.leggiECreaStanzeMagiche();
//			this.leggiECreaStanzeBuie();
			this.leggiInizialeEvincente();
//			this.leggiECreaMaghi();
//			this.leggiECreaCani();
//			this.leggiECreaStreghe();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} 
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException, IOException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER).trim();
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza.trim());
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}
	
//	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
//		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
//		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
//			Stanza stanza = new StanzaMagica(nomeStanza);
//			this.nome2stanza.put(nomeStanza, stanza);
//		}
//	}
//	
//
//	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
//		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
//		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
//			
//			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
//				while (scannerDiLinea.hasNext()) {
//					
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+" non esiste\n"));
//					String nomeStanza = scannerDiLinea.next();
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione dell'attrezzo per vedere la stanza "+specifica+"\n"));
//					String attrezzoPerVedere = scannerDiLinea.next();
//
//					Stanza stanza = new StanzaBuia(nomeStanza, attrezzoPerVedere);
//					this.nome2stanza.put(nomeStanza, stanza);
//				}
//			}
//		} 
//
//	}
//	
//	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
//		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_MAGO);
//		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
//			
//			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
//				while (scannerDiLinea.hasNext()) {
//					
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere il mago non esiste\n"));
//					String nomeStanza = scannerDiLinea.next();
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione del mago ...\n"));
//					String mago = scannerDiLinea.next();
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione del mago\n"));
//					String presentazione = scannerDiLinea.next();					
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione dell'attrezzo per il mago della stanza "+specifica+"\n"));
//					String attrezzo = scannerDiLinea.next();
//
//					AbstractPersonaggio personaggio = new Mago(mago, presentazione, new Attrezzo(attrezzo, 4));
//					this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
//				}
//			}
//		} 
//	}
//	
//	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
//		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_STREGA);
//		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
//			
//			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
//				while (scannerDiLinea.hasNext()) {
//					
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere la strega non esiste\n"));
//					String nomeStanza = scannerDiLinea.next();
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione della strega ...\n"));
//					String strega = scannerDiLinea.next();
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione della strega\n"));
//					String presentazione = scannerDiLinea.next();					
//
//
//					AbstractPersonaggio personaggio = new Strega(strega, presentazione);
//					this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
//				}
//			}
//		} 
//	}
//	
//	private void leggiECreaCani() throws FormatoFileNonValidoException {
//		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_CANE);
//		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {
//			
//			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
//				while (scannerDiLinea.hasNext()) {
//					
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere il cane non esiste\n"));
//					String nomeStanza = scannerDiLinea.next();
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione del cane ...\n"));
//					String cane = scannerDiLinea.next();
//					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione del cane\n"));
//					String presentazione = scannerDiLinea.next();					
//
//
//					AbstractPersonaggio personaggio = new Cane(cane, presentazione);
//					this.nome2stanza.get(nomeStanza).setPersonaggio(personaggio);
//				}
//			}
//		} 
//	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(","); // Usa una virgola seguita da qualsiasi numero di spazi bianchi come delimitatore
	    while (scanner.hasNext()) {
	        result.add(scanner.next().trim()); // Aggiungi alla lista rimuovendo gli spazi bianchi iniziali e finali
	    }
	    scanner.close();
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).trim();
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).trim();
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next().trim();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next().trim();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next().trim();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		nomeStanza = nomeStanza.trim();
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {			

			while (scannerDiLinea.hasNext()) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				String stanzaPartenza = scannerDiLinea.next().trim();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				String dir = scannerDiLinea.next().trim();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				String stanzaDestinazione = scannerDiLinea.next().trim();
				
				
				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		} 
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		stanzaDa = stanzaDa.trim();
	    nomeA = nomeA.trim();
	    dir = dir.trim();
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(Direzione.valueOf(dir), arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
