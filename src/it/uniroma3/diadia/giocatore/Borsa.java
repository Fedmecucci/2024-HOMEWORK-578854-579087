package it.uniroma3.diadia.giocatore;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezzi;

public class Borsa {

	//public final static int DEFAULT_PESO_MAX_BORSA = 10;
	
	private Map<String, Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	public Borsa() throws IOException {
		this(Proprietà.getInstance().getPesoMax());
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		//if (this.numeroAttrezzi==10)
		//	return false;
		else {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			this.numeroAttrezzi++;
			return true;
		}
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi.values())
			peso += a.getPeso();

		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo);
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo a : this.attrezzi.values())
				s.append(a.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	
	
	 public List<Attrezzo> getContenutoOrdinatoPerPeso() {  //REALIZZATO CON ORDINAMENTO INTERNO
		 List<Attrezzo> result = new ArrayList<>();
		 for(Attrezzo a : this.attrezzi.values())
			 result.add(a);
		 Collections.sort(result);
		 return result;
	 }
	 public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		 SortedSet<Attrezzo> result = new TreeSet<>();
		 result.addAll(this.attrezzi.values());
		 return result;
	 }
	 
	 public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		 SortedSet<Attrezzo> result = new TreeSet<>(new ComparatoreAttrezzi());
		 for(Attrezzo a : this.attrezzi.values())
			 result.add(a);
		 return result;
	 }
	 
	 public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
			Map<Integer, Set<Attrezzo>> peso2attrezzi = new HashMap<>();
			for(Attrezzo attrezzo : this.attrezzi.values()) {
				if(peso2attrezzi.containsKey(attrezzo.getPeso())){
					//questo attrezzo ha un peso che ho già visto
					//pesco il vecchio Set con lo stesso peso e aggiungo il nuovo arrivato
					final Set<Attrezzo> stessoPeso = peso2attrezzi.get(attrezzo.getPeso());
					stessoPeso.add(attrezzo);
				}
				else{
					//questo attrezzo ha un peso mai visto prima
					//creo un nuovo set per ospitare tutti gli attrezzi correnti e futuri con questo peso
					final Set<Attrezzo> nuovoSet = new HashSet<>();
					nuovoSet.add(attrezzo);
					peso2attrezzi.put(attrezzo.getPeso(), nuovoSet);
				}
			}
			return peso2attrezzi;
		}
 }
