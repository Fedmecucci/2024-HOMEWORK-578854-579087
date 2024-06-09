package it.uniroma3.diadia.giocatore;

import java.io.IOException;

import it.uniroma3.diadia.Proprietà;

public class Giocatore {

	private Proprietà proprietà;
	private Borsa borsa;
	private int cfu;
	
	public Giocatore() throws IOException {
		this.proprietà = Proprietà.getInstance();
		this.cfu = proprietà.getCFU();
		this.borsa = new Borsa();
	}
	
	public Borsa getBorsa() {
		return borsa;
	}
	public int getCfu() {
		return cfu;
	}
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
}
