package fr.tpt.mem4csd.dag.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import fr.tpt.mem4csd.dag.sort.model.Arc;
import fr.tpt.mem4csd.dag.sort.model.Graph;
import fr.tpt.mem4csd.dag.sort.model.Node;

public class Main {

	public static void main(String[] args) {
		
		List<Node> nodeList = new ArrayList<Node>();
		
		Node n0 = new Node();
		n0.setName("n0");
		
		Node n1 = new Node();
		n1.setName("n1");
		
		Node n2 = new Node();
		n2.setName("n2");

		Node n3 = new Node();
		n3.setName("n3");
		
		Node n4 = new Node();
		n4.setName("n4");
		
		new Arc(n0, n1);
//		new Arc(n1, n0);
//		new Arc(n1, n2);
		new Arc(n2, n3);
		new Arc(n3, n4);
		new Arc(n4, n1);
		
		nodeList.add(n0);
		nodeList.add(n1);
		nodeList.add(n2);
		nodeList.add(n3);
		nodeList.add(n4);
		
		Graph dag = new Graph(nodeList);
		
		Stack<Node> s = new Stack<Node>();
		boolean cycle = TopologicalSort.sort(dag, s);
		if(cycle)
			System.out.println("The graph is not a DAG");
		
		while(!s.isEmpty())
		{
			Node n = s.pop();
			System.out.println("node "+n.getName());
		}
		
		
	}

}
