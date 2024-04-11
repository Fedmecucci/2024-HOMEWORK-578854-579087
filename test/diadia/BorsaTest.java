package diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BorsaTest {

	private Borsa borsa;
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private final static String ATTREZZO = "attrezzoSemplice";
	
	@BeforeEach
	public void setUp() {
		this.borsa= new Borsa(DEFAULT_PESO_MAX_BORSA);
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
	
	
	//metodo di utilità per l'aggiunta di attrezzi nella borsa
	private Attrezzo CreoUnAttrezzoEMettiInBorsa(Borsa borsa,String nomeAttrezzo,int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		borsa.addAttrezzo(attrezzo);
		return attrezzo;        
	}
	
}
