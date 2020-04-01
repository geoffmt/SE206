package fr.tpt.mem4csd.dag.sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import fr.tpt.mem4csd.dag.sort.model.Arc;
import fr.tpt.mem4csd.dag.sort.model.Graph;
import fr.tpt.mem4csd.dag.sort.model.Node;

public class TopologicalSort {
	
	private enum Status {
		ON_STACK,
		VISITED
	}
	
	public static boolean sort(Graph g, Stack<Node> s)
	{
		List<Node> nodeList = g.getNodes();
		boolean rootExists = false;
		for(Node n : nodeList)
		{
			boolean isRoot = n.getIncomingArcs().isEmpty();
			if(!isRoot)
				continue;
			rootExists = true;
			boolean cycle = sort(n, s);
			if(cycle)
				return true;
		}
		if(!rootExists)
			return true;
		if(nodeList.size() != s.size())
		{
			s.clear();
			return true;
		}
		
		return false;
	}
	
	private static boolean sort(Node n, Stack<Node> s)
	{
		Map<Node, Status> visited = new HashMap<Node, Status>();	
		return sort(n,s, visited);
	}

	private static boolean sort(Node n, Stack<Node> s, Map<Node, Status> visited) {
		visited.put(n, Status.ON_STACK);
		for(Arc outArc: n.getOutgoingArcs())
		{
			Node dst = outArc.getDestination();
			boolean onStack = visited.containsKey(dst) && 
					visited.get(dst)==Status.ON_STACK;
			if(onStack)
				return true;
			boolean alreadyVisited = visited.containsKey(dst) && 
					visited.get(dst)==Status.VISITED;
			if(alreadyVisited)
				continue;
			else
			{
				boolean cycle = sort(dst, s, visited);
				if(cycle)
					return true;
			}
		}
		if(!s.contains(n))
			s.push(n);
		visited.put(n, Status.VISITED);
		return false;
		
	}
	
}
