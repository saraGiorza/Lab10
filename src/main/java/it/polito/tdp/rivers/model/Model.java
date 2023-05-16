package it.polito.tdp.rivers.model;

import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO dao = new RiversDAO();
	
	public List<River> getRivers(){
		return dao.getAllRivers();
	}
	
	public void setRiverFlows(River r) {
		dao.setRiversFlows(r);
	}
	
	//vedi se riesci a scrivere un metodo per riunire i 2 e chiamare la simulazione solo una volta
	public int giorniFail(River r, float k) {
		Simulatore sim = new Simulatore(r, k);
		sim.initialize();
		sim.run();		
		return sim.getGiorniFail();
	}
	
	public float riempimentoMedio(River r, float k) {
		Simulatore sim = new Simulatore(r, k);
		sim.initialize();
		sim.run();		
		return sim.getOccupazioneMediaBacino();		
	}
	
}
