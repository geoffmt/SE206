/**
 */
package fr.tpt.mem4csd.dag.model.dag.util;

import fr.tpt.mem4csd.dag.model.dag.*;
import fr.tpt.mem4csd.dag.sort.TopologicalSort;
import fr.tpt.mem4csd.dag.sort.model.Arc;
import fr.tpt.mem4csd.dag.sort.model.Graph;
import fr.tpt.mem4csd.dag.sort.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage
 * @generated
 */
public class DagValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final DagValidator INSTANCE = new DagValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "fr.tpt.mem4csd.dag.model.dag";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DagValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return DagPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case DagPackage.IDENTIFIED_ELEMENT:
				return validateIdentifiedElement((IdentifiedElement)value, diagnostics, context);
			case DagPackage.NAMED_ELEMENT:
				return validateNamedElement((NamedElement)value, diagnostics, context);
			case DagPackage.DAG_SPECIFICATION:
				return validateDagSpecification((DagSpecification)value, diagnostics, context);
			case DagPackage.TASK:
				return validateTask((Task)value, diagnostics, context);
			case DagPackage.PORT:
				return validatePort((Port)value, diagnostics, context);
			case DagPackage.CHANNEL:
				return validateChannel((Channel)value, diagnostics, context);
			case DagPackage.PORT_DIRECTION:
				return validatePortDirection((PortDirection)value, diagnostics, context);
			case DagPackage.PORT_DATA_TYPE:
				return validatePortDataType((PortDataType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTask(Task task, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(task, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(task, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(task, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(task, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(task, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(task, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(task, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(task, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(task, diagnostics, context);
		if (result || diagnostics != null) result &= validateTask_uniqueNames(task, diagnostics, context);
		if (result || diagnostics != null) result &= validateTask_uniquePortNames(task, diagnostics, context);
		if (result || diagnostics != null) result &= validateTask_singleInputConn(task, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the uniqueNames constraint of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String TASK__UNIQUE_NAMES__EEXPRESSION = "not NamedElement.allInstances()->exists( element | element <> self and element.name = self.name )";

	/**
	 * Validates the uniqueNames constraint of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTask_uniqueNames(Task task, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(DagPackage.Literals.TASK,
				 task,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "uniqueNames",
				 TASK__UNIQUE_NAMES__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the uniquePortNames constraint of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String TASK__UNIQUE_PORT_NAMES__EEXPRESSION = "self.ownedPorts->forAll( port : Port | not self.ownedPorts->exists( portIt | port <> portIt and portIt.name = port.name ) )";

	/**
	 * Validates the uniquePortNames constraint of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTask_uniquePortNames(Task task, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(DagPackage.Literals.TASK,
				 task,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "uniquePortNames",
				 TASK__UNIQUE_PORT_NAMES__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the singleInputConn constraint of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String TASK__SINGLE_INPUT_CONN__EEXPRESSION = "self.ownedPorts->select( port | port.direction = PortDirection::_in )->\n" +
		"\t\t\tforAll( inPort | Channel.allInstances()->select( conn | conn.destPort = inPort )->size() < 2 )";

	/**
	 * Validates the singleInputConn constraint of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTask_singleInputConn(Task task, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(DagPackage.Literals.TASK,
				 task,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "singleInputConn",
				 TASK__SINGLE_INPUT_CONN__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIdentifiedElement(IdentifiedElement identifiedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(identifiedElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePort(Port port, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(port, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(port, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(port, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(port, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(port, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(port, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(port, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(port, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(port, diagnostics, context);
		if (result || diagnostics != null) result &= validatePort_connected(port, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the connected constraint of '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String PORT__CONNECTED__EEXPRESSION = "Channel.allInstances()->exists( conn | conn.sourcePort = self or conn.destPort = self )";

	/**
	 * Validates the connected constraint of '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePort_connected(Port port, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(DagPackage.Literals.PORT,
				 port,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "connected",
				 PORT__CONNECTED__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateChannel(Channel channel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(channel, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(channel, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(channel, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(channel, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(channel, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(channel, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(channel, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(channel, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(channel, diagnostics, context);
		if (result || diagnostics != null) result &= validateChannel_dataType(channel, diagnostics, context);
		if (result || diagnostics != null) result &= validateChannel_portsDirections(channel, diagnostics, context);
		if (result || diagnostics != null) result &= validateChannel_periods(channel, diagnostics, context);
		return result;
	}

	/**
	 * Validates the dataType constraint of '<em>Channel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateChannel_dataType(Channel channel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		
		boolean validate;
		validate = (channel.getSourcePort().getDataType() == channel.getDestPort().getDataType());
		
		if (!validate) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "dataType", getObjectLabel(channel, context) },
						 new Object[] { channel },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the portsDirections constraint of '<em>Channel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateChannel_portsDirections(Channel channel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		boolean validate;
		validate = (channel.getSourcePort().getDirection() == PortDirection.OUT && channel.getDestPort().getDirection() == PortDirection.IN);
		
		if (!validate) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "portsDirections", getObjectLabel(channel, context) },
						 new Object[] { channel },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the periods constraint of '<em>Channel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateChannel_periods(Channel channel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		boolean validate;
		validate = (channel.getSourceTask().getPeriod() <= channel.getDestTask().getPeriod());
		
		if (!validate) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "periods", getObjectLabel(channel, context) },
						 new Object[] { channel },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDagSpecification(DagSpecification dagSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(dagSpecification, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dagSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dagSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dagSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dagSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dagSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dagSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dagSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dagSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateDagSpecification_sortedTasksSize(dagSpecification, diagnostics, context);
		if (result || diagnostics != null) result &= validateDagSpecification_dag(dagSpecification, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the sortedTasksSize constraint of '<em>Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String DAG_SPECIFICATION__SORTED_TASKS_SIZE__EEXPRESSION = "self.sortedTasks->isEmpty() or self.ownedTasks->size() = self.sortedTasks->size()";

	/**
	 * Validates the sortedTasksSize constraint of '<em>Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDagSpecification_sortedTasksSize(DagSpecification dagSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(DagPackage.Literals.DAG_SPECIFICATION,
				 dagSpecification,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "sortedTasksSize",
				 DAG_SPECIFICATION__SORTED_TASKS_SIZE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * Validates the dag constraint of '<em>Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateDagSpecification_dag(DagSpecification dagSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		
		List<Node> nodeList = new ArrayList<Node>();
		EList<Task> tasks = dagSpecification.getOwnedTasks();
		EList<Channel> channels = dagSpecification.getOwnedChannels();
		
	    for(int i = 0; i < tasks.size(); i++)
	    {
	    	Node node = new Node();
	    	node.setName(tasks.get(i).getQualifiedName());
	    	nodeList.add(node);
	    }
	    
		Graph dag = new Graph(nodeList);
	    
	    for (int i = 0; i < channels.size(); i++) {
	    	Task task_src = channels.get(i).getSourceTask();
	    	Task task_dst = channels.get(i).getDestTask();
	    	if (task_src == null || task_dst == null) {
	    		continue;
	    	}
	
	    	Node source_node = dag.findNodeByName(task_src.getQualifiedName());
	    	Node dest_node = dag.findNodeByName(task_dst.getQualifiedName());
	    	if (dest_node == null || source_node == null) {
	    		continue;	
	    	}
	    	new Arc(source_node, dest_node);
	    }
		
		Stack<Node> s = new Stack<Node>();
		
		if (TopologicalSort.sort(dag, s)) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "dag", getObjectLabel(dagSpecification, context) },
						 new Object[] { dagSpecification },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamedElement(NamedElement namedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(namedElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortDirection(PortDirection portDirection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortDataType(PortDataType portDataType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //DagValidator
