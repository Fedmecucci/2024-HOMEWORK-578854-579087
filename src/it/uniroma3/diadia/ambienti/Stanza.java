package it.uniroma3.diadia.ambienti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	//static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	//static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
    private Map<String, Attrezzo> attrezzi;
    public int numeroAttrezzi;
    private Map<Direzione, Stanza> stanzeAdiacenti;
    private int numeroStanzeAdiacenti;
	
	private AbstractPersonaggio personaggio;
	//private int numeroMaxDirezioni;
	private int numeroMaxAttrezzi;
	
	private boolean aggiornato;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     * @throws IOException 
     */
    public Stanza(String nome) throws IOException {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<>();
        
        Proprietà proprietà = Proprietà.getInstance();
        //this.numeroMaxDirezioni = proprietà.getNumeroMaxDirezioni();
        this.numeroMaxAttrezzi = proprietà.getLimitePesoAttrezzi();
        this.personaggio = new Mago("mago", "a", new Attrezzo("ATTREZZO", numeroMaxAttrezzi));
        
        //this.personaggio = new Cane("cane", "a");
        //this.personaggio = new Strega("strega", "a");
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanzaAdiacente) {
    	if (this.stanzeAdiacenti.containsKey(direzione)) {
			this.stanzeAdiacenti.put(direzione,stanzaAdiacente);
			aggiornato = true;
    	}
        if(!aggiornato) {
    	    this.stanzeAdiacenti.put(direzione, stanzaAdiacente);
            this.numeroStanzeAdiacenti++;
        }
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}
	
	public Map<Direzione, Stanza> getAdiacenti() {
		return this.stanzeAdiacenti;
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Map<String, Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }
    
    ///// METODI PER ALCUNI TEST
    public int getNumeroAttrezzi() {
    	return this.numeroAttrezzi;
    }
    public void setNumeroAttrezzi(int num) {
    	this.numeroAttrezzi = num;
    }
    ///////
    
    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(this.numeroAttrezzi < this.numeroMaxAttrezzi) {
    		this.attrezzi.put(attrezzo.getNome(), attrezzo);
        	this.numeroAttrezzi++;
        	return true;
    	}
        else {
        	return false;
        }
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	risultato.append(this.getDirezioni().toString());
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo a : this.attrezzi.values()) {
    		risultato.append(a.toString()+" ");
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null)
			return false;
		return this.attrezzi.remove(attrezzo.getNome(), attrezzo);
	}


	public Set<Direzione> getDirezioni() {
	    return this.stanzeAdiacenti.keySet();
    }
	
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
	   this.personaggio = personaggio;
	}
	public AbstractPersonaggio getPersonaggio() {
	   return this.personaggio;
	 }

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		Stanza that = (Stanza) o;
		return this.getNome().equals(that.getNome());
	}

	

}