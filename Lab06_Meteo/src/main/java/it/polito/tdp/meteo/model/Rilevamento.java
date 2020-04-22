package it.polito.tdp.meteo.model;

import java.util.Date;

public class Rilevamento {
	
	private String localita;
	private int giorno;
	private int umidita;

	public Rilevamento(String localita, int giorno, int umidita) {
		this.localita = localita;
		this.giorno = giorno;
		this.umidita = umidita;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public int getGiorno() {
		return giorno;
	}

	public void setData(int giorno) {
		this.giorno = giorno;
	}

	public int getUmidita() {
		return umidita;
	}

	public void setUmidita(int umidita) {
		this.umidita = umidita;
	}

	// @Override
	// public String toString() {
	// return localita + " " + giorno + " " + umidita;
	// }

	@Override
	public String toString() {
		return giorno+" "+String.valueOf(umidita);
	}
	
	
}
