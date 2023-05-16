package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class River {
	private int id;
	private String name;
	private float flowAvg;
	private List<Flow> flows;
	
	public River(int id) {
		this.id = id;
	}

	public River(int id, String name) {
		this.id = id;
		this.name = name;
		this.flows = new LinkedList<Flow>();
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getFlowAvg() {
		return flowAvg;
	}

	public void setFlowAvg(float flowAvg) {
		this.flowAvg = flowAvg;
	}

	public void setFlows(Flow f) {
		this.flows.add(f);
	}

	public LinkedList<Flow> getFlows() {
		return (LinkedList<Flow>) flows;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		River other = (River) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
