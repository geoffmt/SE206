package fr.tpt.mem4csd.dag.sort.model;

import java.util.List;

public class Graph {

	private List<Node> _g;
	
	public Graph(List<Node> nodeList)
	{
		_g = nodeList;
	}

	public List<Node> getNodes() {
		return _g;
	}
	
}
