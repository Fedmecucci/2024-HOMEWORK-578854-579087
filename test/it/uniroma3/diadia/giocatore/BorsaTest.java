package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezzi;

class BorsaTest {

	private Borsa borsa;
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private final static String ATTREZZO = "attrezzoSemplice";
	
	private List<Attrezzo> confronto;
	private Borsa confront;
	private Borsa borsaPeso;
	private Borsa borsa2Peso;
	
	Attrezzo cacciavite;
	Attrezzo martello;
	Attrezzo chiaveInglese;
	Attrezzo martello2;
	
	Attrezzo attrezzo1;
	Attrezzo attrezzo2;
	Attrezzo attrezzo3;
	
	
	@BeforeEach
	public void setUp() throws IOException {
		this.borsa= new Borsa(DEFAULT_PESO_MAX_BORSA);
		
		this.martello = new Attrezzo("Martello", 2);
        this.chiaveInglese = new Attrezzo("Chiave inglese", 3);
        this.cacciavite = new Attrezzo("Cacciavite", 1);
        this.martello2 = new Attrezzo("Martello2", 2);
        
        borsa.addAttrezzo(martello);
        borsa.addAttrezzo(chiaveInglese);
        borsa.addAttrezzo(cacciavite);
        
        confronto = new ArrayList<>();
        confronto.add(cacciavite);
        confronto.add(martello);
        confronto.add(chiaveInglese);
        //confronto.add(martello); //se cambio il loro ordine, non risultano più ordinati per peso e il test dà falso
        
        confront = new Borsa();  //ordina anche senza comparatore, poiché usa il compareTo definito da me dentro Attrezzo
        confront.addAttrezzo(chiaveInglese);
        confront.addAttrezzo(cacciavite);
        confront.addAttrezzo(martello);
        
        borsa2Peso = new Borsa();  //ordina anche senza comparatore, poiché usa il compareTo definito da me dentro Attrezzo
        borsa2Peso.addAttrezzo(martello);
        borsa2Peso.addAttrezzo(martello2);
        
        borsaPeso = new Borsa();
        attrezzo1 = new Attrezzo("Attrezzo1", 1);
        attrezzo2 = new Attrezzo("Attrezzo2", 2);
        attrezzo3 = new Attrezzo("Attrezzo3", 1);
        borsaPeso.addAttrezzo(attrezzo1);
        borsaPeso.addAttrezzo(attrezzo2);
        borsaPeso.addAttrezzo(attrezzo3);
        
	}
	
	@Test
	public void TestBorsaConUnAttrezzoSiaDiversaDaNull() {
	     Attrezzo attrezzo = CreoUnAttrezzoEMettiInBorsa(this.borsa,ATTREZZO,1 );
	     assertEquals(attrezzo,this.borsa.getAttrezzo(ATTREZZO));
		}
	
	@Test
	public void TestAttrezzoTroppoPesante() {
		Attrezzo attrezzoPesante =  new Attrezzo("Attrezzo troppo pesante",DEFAULT_PESO_MAX_BORSA+1 );
		assertFalse(this.borsa.addAttrezzo(attrezzoPesante));
	}
	
	@Test
	public void verificoCheLaBorsaSiaVuota() {
		assertNull(this.borsa.getAttrezzo(null));
	}
	
	@Test
	public void verificoInserimento() {
		Attrezzo attrezzo = CreoUnAttrezzoEMettiInBorsa(this.borsa,"spada",1 );
		assertNotEquals(attrezzo,this.borsa.getAttrezzo(ATTREZZO));
	}
	
	@Test
	public void testContenutoOrdinatoPerPeso() {
        // Ottieni il contenuto ordinato per peso
        List<Attrezzo> contenutoOrdinato = borsa.getContenutoOrdinatoPerPeso();
        assertEquals(contenutoOrdinato, confronto);
	}
	
	@Test
	public void testSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> contenutoOrdinato = confront.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = contenutoOrdinato.iterator();
        assertEquals(cacciavite,i.next());
        assertEquals(martello, i.next());
        assertEquals(chiaveInglese,i.next());
	}
	
	@Test
	public void testSetOrdinatoPerPesoConPesoUguale() {
		SortedSet<Attrezzo> contenutoOrdinato = borsa2Peso.getSortedSetOrdinatoPerPeso();
		Iterator<Attrezzo> i = contenutoOrdinato.iterator();
        assertEquals(martello, i.next());
        assertEquals(martello2,i.next());
	}
	
	@Test
	public void testContenutoOrdinatoPerNome() {
        // Ottieni il contenuto ordinato per nome
        SortedSet<Attrezzo> contenutoOrdinato = confront.getContenutoOrdinatoPerNome();
        Iterator<Attrezzo> i = contenutoOrdinato.iterator();
        assertEquals(cacciavite,i.next());
        assertEquals(chiaveInglese,i.next());
        assertEquals(martello, i.next());
        //assertEquals(contenutoOrdinato, confront);
	}
	
	@Test
	public void testContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> raggruppamento = borsaPeso.getContenutoRaggruppatoPerPeso();
		assertEquals(2, raggruppamento.size());
        assertTrue(raggruppamento.get(1).contains(attrezzo1));
        assertTrue(raggruppamento.get(1).contains(attrezzo3));
        assertTrue(raggruppamento.get(2).contains(attrezzo2));
	}
	
	
	//metodo di utilità per l'aggiunta di attrezzi nella borsa
	private Attrezzo CreoUnAttrezzoEMettiInBorsa(Borsa borsa,String nomeAttrezzo,int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		borsa.addAttrezzo(attrezzo);
		return attrezzo;        
	}
	
}
