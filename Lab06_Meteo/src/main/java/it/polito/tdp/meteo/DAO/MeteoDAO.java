package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public String getUmiditaMedia(String mese){
		final String sql = "SELECT Localita, AVG(umidita) AS media FROM situazione WHERE Data LIKE ? GROUP BY localita ORDER BY data ASC";

		String umiditaMedie = "";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, "2013-"+mese+"-%");
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				umiditaMedie += rs.getString("Localita")+": "+rs.getDouble("media")+"\n";
			}

			conn.close();
			return umiditaMedie;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<Citta> getAllCitta(String mese) {

		final String sql = "SELECT Localita FROM situazione WHERE Data LIKE ? GROUP BY Localita";

		List<Citta> citta = new ArrayList<Citta>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, "2013-"+mese+"-%");
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Citta c = new Citta(rs.getString("Localita"));
				citta.add(c);
			}

			conn.close();
			return citta;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(String mese, String localita) {

		final String sql = "SELECT DAY(Data) AS giorno, umidita FROM situazione WHERE Data LIKE ? AND Localita=? AND DAY(DATA)<16 ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, "2013-"+mese+"-%");
			st.setString(2, localita);
			
			ResultSet rs = st.executeQuery();
			int count = 1;

			while (rs.next()) {
				
				Rilevamento r = new Rilevamento(localita, rs.getInt("giorno"), rs.getInt("Umidita"));
				while(count<=rs.getInt("giorno")) {
					if(rs.getInt("giorno")==(count))
						rilevamenti.add(r);
					else
						rilevamenti.add(null);
					count++;
				}
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


}
