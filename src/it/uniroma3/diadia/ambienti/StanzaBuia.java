package it.uniroma3.diadia.ambienti;

import java.io.IOException;

public class StanzaBuia extends Stanza{
	
	private String AttrezzoPerVedere;
	public static final String STANZA_BUIA ="Qui c'è buio pesto";
	
	public StanzaBuia(String nome,String AttrezzoPerVedere) throws IOException {
		super(nome);
		this.AttrezzoPerVedere=AttrezzoPerVedere;
	}
	
	@Override
	public String getDescrizione() {
		if(super.hasAttrezzo(AttrezzoPerVedere))  //con "super" indico che deve utilizzare "hasAttrezzo"/"getDescrizione"
			return super.getDescrizione();        //della classe supertipo
		else {
			return STANZA_BUIA;
		}
	}

}
