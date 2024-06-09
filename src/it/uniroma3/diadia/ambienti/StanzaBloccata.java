package it.uniroma3.diadia.ambienti;

import java.io.IOException;

public class StanzaBloccata extends Stanza{
	
	private String direzioneBloccata;
	private String attrezzoSbloccante;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) throws IOException {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(super.hasAttrezzo(attrezzoSbloccante)) {
			return super.getStanzaAdiacente(direzione);
		}
		else {
			return this;
		}
	}
	
	@Override
	public String getDescrizione() {
		return this.toString();
	}
	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
    	risultato.append(this.getNome());
    	risultato.append("\nUscite: ");
    	for (Direzione direzione : this.getDirezioni())
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	//risultato.append("\nAttrezzi nella stanza: ");
    	//for (int i=0; i<this.numeroAttrezzi;i++) {
    	//	risultato.append(this.attrezzi[i].toString()+" ");
    	//}
    	risultato.append("\nLa direzione" + this.direzioneBloccata + "è bloccata se nella stanza non è presente l'attrezzo" + this.attrezzoSbloccante);
    	return risultato.toString();
	}

}
