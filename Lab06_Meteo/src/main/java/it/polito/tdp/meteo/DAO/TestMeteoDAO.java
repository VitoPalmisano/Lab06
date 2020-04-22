package it.polito.tdp.meteo.DAO;

import java.util.List;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;

public class TestMeteoDAO {

	public static void main(String[] args) {
		
		MeteoDAO dao = new MeteoDAO();

		List<Citta> list = dao.getAllCitta("02");

		// STAMPA: localita, giorno, mese, anno, umidita (%)
		for (Citta c : list) {
			System.out.format(c.getNome()+"\n");
		}
		
		System.out.println(dao.getAllRilevamentiLocalitaMese("01", "Genova"));
		
		System.out.println(dao.getAllRilevamentiLocalitaMese("06", "Milano"));
	
		System.out.println(dao.getAllRilevamentiLocalitaMese("05", "Torino"));
		

	}

}
