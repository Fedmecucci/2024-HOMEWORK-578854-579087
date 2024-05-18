package it.uniroma3.diadia;

import java.util.HashMap;
import java.util.Map;

public class IOSimulator implements IO {
	
	private Map<Integer, String> righeDaLeggere;
	private Map<Integer, String> messaggiStampati;
	private int indiceRighe;
	private int indiceStampe;
	
	public IOSimulator(HashMap<Integer, String> righeArrivate) {
	    this.righeDaLeggere = righeArrivate;
	    this.messaggiStampati = new HashMap<Integer, String>();
	    this.indiceRighe = 0;
	    this.indiceStampe = 0;
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiStampati.put(indiceStampe, msg);
		this.indiceStampe++;
	}

	@Override
	public String leggiRiga() {
		String riga = this.righeDaLeggere.get(this.indiceRighe);
		this.indiceRighe++;
		return riga;
	}

}
