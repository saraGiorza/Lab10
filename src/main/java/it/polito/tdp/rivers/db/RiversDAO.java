package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	//passando un fiume si setta la lista di flow (ordinata cronologicamente, per cui sara' poi facile
	//ricavare la prima e l'ultima misurazione)
	public void setRiversFlows(River fiume) {
		
		int fiumeId = fiume.getId();
		final String sql = "SELECT * "
				+ "FROM flow "
				+ "WHERE river = ? "
				+ "ORDER BY day ASC";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, fiumeId);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				LocalDate dd = res.getDate("day").toLocalDate();
				float flusso = res.getFloat("flow"); // m3/s
				Flow f = new Flow(dd, flusso, fiume);
				fiume.setFlows(f);
			}
			
			float flussoMedio = 0;
			
			for(Flow f: fiume.getFlows()) {
				flussoMedio+= f.getFlow();
			}
			flussoMedio = flussoMedio/fiume.getFlows().size();
			fiume.setFlowAvg(flussoMedio);
			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
	}
}
