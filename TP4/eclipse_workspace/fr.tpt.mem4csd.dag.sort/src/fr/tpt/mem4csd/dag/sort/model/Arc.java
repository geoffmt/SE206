package fr.tpt.mem4csd.dag.sort.model;

public class Arc {

	private Node _src;
	private Node _dst;
	
	public Arc(Node src, Node dst)
	{
		_src = src;
		_src.addOutgoingArc(this);
		_dst = dst;
		_dst.addIncomingArc(this);
	}
	
	public Node getSource() {
		return _src;
	}
	
	public void setSourcr(Node src) {
		this._src = src;
	}
	
	public Node getDestination() {
		return _dst;
	}
	public void setDestination(Node _dst) {
		this._dst = _dst;
	}
	
}
