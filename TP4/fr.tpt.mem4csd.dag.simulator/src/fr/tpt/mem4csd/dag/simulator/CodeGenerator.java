package fr.tpt.mem4csd.dag.simulator;

import java.io.IOException;

import fr.tpt.mem4csd.dag.model.dag.DagSpecification;
import fr.tpt.mem4csd.dag.model.dag.Port;
import fr.tpt.mem4csd.dag.model.dag.PortDataType;
import fr.tpt.mem4csd.dag.model.dag.PortDirection;
import fr.tpt.mem4csd.dag.model.dag.Task;

public class CodeGenerator {

	private final DagSpecification _theDag;
	private final CodeGeneratorUtils _utils;
	
	public CodeGenerator(DagSpecification theDag, String filePath) {
		_utils = new CodeGeneratorUtils(filePath);
		_theDag = theDag;
	}
	
	public void generateDagCode() {
		_utils.addTextNewLine("#include <stdio.h>");
		_utils.addTextNewLine("#include \"delay_until.h\"");
		_utils.addTextNewLine("");

		for(Task t : _theDag.getOwnedTasks()) {
			for(Port p:t.getOwnedPorts()) {
				
				// TODO TP: Generate the code for declaring the global variables of each port
				// Use the _utils variable to generate the code 
			}
		}
		
		_utils.addTextNewLine("");
		
		// Task --> functions
		for(Task t:_theDag.getOwnedTasks()) {
			generateTaskCode(t);
		}
		
		_utils.addTextNewLine("");
		_utils.addTextNewLine("int main(int argc, char* argv[]) {");
		_utils.incrementIdent();
		
		int pgcd = pgcd();
		System.out.println("Base period = "+pgcd);
		_utils.addTextNewLine("unsigned int base_period = "+pgcd+";");
		_utils.addTextNewLine("unsigned int it=0;");
		_utils.addTextNewLine("set_start_time();");
		_utils.addTextNewLine("while(1) {");
		_utils.incrementIdent();
		
		// Generate the calls according to the sorted order of tasks
		for ( final Task task : _theDag.getSortedTasks() ) {
			System.out.println("generate call to task " + task.getName());
			
			_utils.addTextNewLine("if ((it*base_period)%"+task.getPeriod()+"==0){");
			_utils.incrementIdent();
			generateTaskCall( task );
			_utils.decrementIdent();
			_utils.addTextNewLine("}");
		}
		
		_utils.addTextNewLine("it++;");
		
		_utils.addTextNewLine("delay_until(it, base_period);");
		
		_utils.decrementIdent();
		_utils.addTextNewLine("}");
		
		
		_utils.addTextNewLine("return 0;");
		_utils.decrementIdent();
		_utils.addTextNewLine("}");
		
		
		try {
			_utils.save();
		} catch (IOException e) {
			System.err.println("Could not save generated code file. Erroneous path?");
			e.printStackTrace();
		}
	}

	
	private void generateTaskCall(Task t) {
		_utils.addText(t.getName()+"(");
		
		// parameters
		for(Port p:t.getOwnedPorts()) {
			
			// TODO TP: Code the call of the task function passing the appropriate arguments
		}
		
		_utils.addTextNoIdent(");");
		_utils.addTextNewLine("");
		
		for(Port p:t.getOwnedPorts()) {
			if(!p.getDirection().equals(PortDirection.OUT)) {
				continue;
			}
			
//			for(Channel c: _theDag.getOwnedChannels()) {
//				if(	c.getSourceTask().equals(t) &&
//					c.getSourcePort().equals(p)) {
					
					// TODO TP: Code the affectation of the value of the destination port of the channel
//				}
//			}
		}
	}

	private void generateTaskCode(Task t) {
		_utils.addText("void " + t.getName() +"(");
		
		for(Port p: t.getOwnedPorts()) {
			PortDataType portType = p.getDataType();
			
			if(portType.equals(PortDataType.FLOAT)) {
				_utils.addTextNoIdent("float ");
			}
			else {
				_utils.addTextNoIdent("int ");
			}
			
			PortDirection portDir = p.getDirection();
			
			if(portDir.equals(PortDirection.OUT)) {
				_utils.addTextNoIdent("* ");
			}
			
			_utils.addTextNoIdent(p.getName());
			
			if(t.getOwnedPorts().get(t.getOwnedPorts().size()-1)!=p) {
				_utils.addTextNoIdent(", ");
			}
		}
		
		_utils.addTextNewLine(") {");
		
		_utils.incrementIdent();
		
		generateTaskBody(t);
		
		_utils.decrementIdent();
		_utils.addTextNewLine("}");
		_utils.addTextNewLine("");
	}

	private void generateTaskBody(Task t) {
		_utils.addTextNewLine("printf(\"In task " + 
				t.getName()+"\\n\");");
		
		for(Port p: t.getOwnedPorts()) {
			generatePortDisplay(p);
			
			// TODO TP: Increment the values of the output ports
		}
	}

	private void generatePortDisplay(Port p) {
		_utils.addText("printf(\"\\t ");
		
		boolean isInput = p.getDirection().equals(PortDirection.IN); 
		
		if(isInput) {
			_utils.addTextNoIdent("received ");
		}
		else {
			_utils.addTextNoIdent("sending ");
		}
		
		_utils.addTextNoIdent("on port " + p.getName());
		
		PortDataType portType = p.getDataType();
		
		if(portType.equals(PortDataType.FLOAT)) {
			_utils.addTextNoIdent(" %f");
		}
		else if(portType.equals(PortDataType.INT)) {
			_utils.addTextNoIdent(" %d");
		}
		
		_utils.addTextNoIdent("\\n\", ");
		
		if(!isInput) {
			_utils.addTextNoIdent("*");
		}
		
		_utils.addTextNoIdent(p.getName());
		
		_utils.addTextNewLine(");");
	}
	
	private int pgcd() {
		int res=0;
		
		for(Task t:_theDag.getOwnedTasks())	{
			if(res==0) {
				res=t.getPeriod();
			}
			else {
				res = pgcd(res,t.getPeriod());
			}
		}
		
		return res;
	}
	
	private int pgcd(int a,int b) {
		int r = a;
		
		while (r!=0) {
			r = a%b; 
			a=b; 
			b=r;
		}
		
		return Math.abs(a);
	}
}
