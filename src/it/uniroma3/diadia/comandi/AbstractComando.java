package it.uniroma3.diadia.comandi;

import java.io.IOException;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

// implementazione classe astratta comandoper
//eliminare le implementazioni “vuote” dei metodi
//setParametro() dalle classi concrete che
//implementano l'interfaccia Comando (in
//particolare dalle classi che modellano comandi
//privi di parametri come ad es. ComandoGuarda,
//ComandoAiuto)


public  abstract class AbstractComando implements Comando{

	private IO io;
	private String nome;
	private String parametro;
	

	public IO getIo() {
		return io;
	}

	public String getNome() {
		return nome;
	}

	public String getParametro() {
		return parametro;
	}

	public void setIo(IO io) {
		this.io = io;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public  void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	abstract public void esegui(Partita partita) throws IOException;
	
	
}
