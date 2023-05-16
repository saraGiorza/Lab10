package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		LinkedList<River> fiumi = (LinkedList<River>) dao.getAllRivers();
		System.out.println(dao.getAllRivers());
		for(River r: fiumi) {
			dao.setRiversFlows(r);
		}
		for(Flow f: fiumi.getFirst().getFlows()) {
			System.out.println(f);
		}
	}

}
