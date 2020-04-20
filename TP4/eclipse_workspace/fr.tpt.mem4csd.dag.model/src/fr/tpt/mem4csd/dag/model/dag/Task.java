/**
 */
package fr.tpt.mem4csd.dag.model.dag;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.Task#getPeriod <em>Period</em>}</li>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.Task#getOwnedPorts <em>Owned Ports</em>}</li>
 * </ul>
 *
 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage#getTask()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='uniqueNames uniquePortNames singleInputConn'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot uniqueNames='not NamedElement.allInstances()-&gt;exists( element | element &lt;&gt; self and element.name = self.name )' uniquePortNames='self.ownedPorts-&gt;forAll( port : Port | not self.ownedPorts-&gt;exists( portIt | port &lt;&gt; portIt and portIt.name = port.name ) )' singleInputConn='self.ownedPorts-&gt;select( port | port.direction = PortDirection::_in )-&gt;\n\t\t\tforAll( inPort | Channel.allInstances()-&gt;select( conn | conn.destPort = inPort )-&gt;size() &lt; 2 )'"
 * @generated
 */
public interface Task extends IdentifiedElement, NamedElement {
	/**
	 * Returns the value of the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Period</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period</em>' attribute.
	 * @see #setPeriod(int)
	 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage#getTask_Period()
	 * @model required="true"
	 * @generated
	 */
	int getPeriod();

	/**
	 * Sets the value of the '{@link fr.tpt.mem4csd.dag.model.dag.Task#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' attribute.
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(int value);

	/**
	 * Returns the value of the '<em><b>Owned Ports</b></em>' containment reference list.
	 * The list contents are of type {@link fr.tpt.mem4csd.dag.model.dag.Port}.
	 * It is bidirectional and its opposite is '{@link fr.tpt.mem4csd.dag.model.dag.Port#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Ports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Ports</em>' containment reference list.
	 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage#getTask_OwnedPorts()
	 * @see fr.tpt.mem4csd.dag.model.dag.Port#getTask
	 * @model opposite="task" containment="true" required="true"
	 * @generated
	 */
	EList<Port> getOwnedPorts();

} // Task
