package fr.tpt.mem4csd.dag.sort.model;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private List<Arc> _outgoing_arcs = new ArrayList<Arc>();
	
	private List<Arc> _incoming_arcs = new ArrayList<Arc>();;
	
	private String _name;
	
	public List<Arc> getOutgoingArcs() {
		return _outgoing_arcs;
	}
	
	void addOutgoingArc(Arc outgoing_arc) {
		this._outgoing_arcs.add(outgoing_arc);
	}
	
	public List<Arc> getIncomingArcs() {
		return _incoming_arcs;
	}
	
	void addIncomingArc(Arc _incoming_arc) {
		this._incoming_arcs.add(_incoming_arc);
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}
	
	
}
