package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	MeteoDAO dao;
	List<Citta> citta;
	List<Citta> sequenzaOttima;
	int costoMin;

	public Model() {
		dao = new MeteoDAO();
		citta = new ArrayList<Citta>();
	}

	// of course you can change the String output with what you think works best
	public String getUmiditaMedia(String mese) {
		return dao.getUmiditaMedia(mese);
	}
	
	public List<Citta> getAllCitta(String mese) {
		return dao.getAllCitta(mese);
	}
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(String mese) {
		
		citta = getAllCitta(mese);
		sequenzaOttima = new ArrayList<Citta>();
		costoMin = 0;
		
		for(Citta c : citta) {
			c.setRilevamenti(dao.getAllRilevamentiLocalitaMese(mese, c.getNome()));
		}
		
		List<Citta> parziale = new ArrayList<>();
		
		cerca(parziale, 0, 0, 1);
		String s = "";
		
		for(int i=0; i<sequenzaOttima.size(); i++) {
			if(i<9)
				s += "2013-"+mese+"-0"+(i+1)+" "+sequenzaOttima.get(i)+"\n";
			else
				s += "2013-"+mese+"-"+(i+1)+" "+sequenzaOttima.get(i)+"\n";
		}
				
		return s;
	}
	
	public int getCostoMin() {
		return costoMin;
	}
	
	private void cerca(List<Citta> parziale, int costo, int contGiorniDiFila, int giorno) {
		
		if(parziale.size()==NUMERO_GIORNI_TOTALI) {
			boolean giornoAssente = false;
			for(int i=0; i<parziale.size(); i++) {
				if(parziale.get(i).getRilevamenti().get(i)==null)
					giornoAssente = true;
			}
			boolean cittaPresente = true;
			for(Citta c : citta) {
				if(!parziale.contains(c))
					cittaPresente = false;
			}
			if(!giornoAssente && cittaPresente) {
				for(int i=0; i<parziale.size(); i++) {
					costo += parziale.get(i).getRilevamenti().get(i).getUmidita();
					if(i>0 && !parziale.get(i).equals(parziale.get(i-1)))
						costo += COST;
				}
				if((costoMin==0 || costo<costoMin) && contGiorniDiFila>=NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN) {
					costoMin = costo;
					sequenzaOttima = new ArrayList<Citta>(parziale);
				}
			}
			return;
		}
		
		for(Citta c : citta) {
			if(c.getCounter()<NUMERO_GIORNI_CITTA_MAX) {
				if(parziale.size()==0 || !parziale.get(giorno-2).equals(c)) {
					if(NUMERO_GIORNI_CITTA_MAX - c.getCounter() >= NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN) {
						contGiorniDiFila = 0;
						while(contGiorniDiFila<NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN) {
							c.increaseCounter();
							parziale.add(c);
							giorno++;
							contGiorniDiFila++;
						}
						cerca(parziale, costo, contGiorniDiFila, giorno);
						for(int i=0; i<NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN; i++) {
							giorno--;
							parziale.remove(c);
							c.decreaseCounter();
							contGiorniDiFila--;
						}
					}
				}
				else {
					c.increaseCounter();
					parziale.add(c);
					cerca(parziale, costo, contGiorniDiFila++, giorno++);
					giorno--;
					parziale.remove(c);
					c.decreaseCounter();
					contGiorniDiFila--;
				}
			}
		}
	}
}
