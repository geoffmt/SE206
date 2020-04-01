/**
 */
package fr.tpt.mem4csd.dag.model.dag;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.Port#getDirection <em>Direction</em>}</li>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.Port#getDataType <em>Data Type</em>}</li>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.Port#getTask <em>Task</em>}</li>
 * </ul>
 *
 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage#getPort()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='connected'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot connected='Channel.allInstances()->exists( conn | conn.sourcePort = self or conn.destPort = self )'"
 * @generated
 */
public interface Port extends IdentifiedElement, NamedElement {
	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.tpt.mem4csd.dag.model.dag.PortDirection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see fr.tpt.mem4csd.dag.model.dag.PortDirection
	 * @see #setDirection(PortDirection)
	 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage#getPort_Direction()
	 * @model required="true"
	 * @generated
	 */
	PortDirection getDirection();

	/**
	 * Sets the value of the '{@link fr.tpt.mem4csd.dag.model.dag.Port#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see fr.tpt.mem4csd.dag.model.dag.PortDirection
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(PortDirection value);

	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.tpt.mem4csd.dag.model.dag.PortDataType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Type</em>' attribute.
	 * @see fr.tpt.mem4csd.dag.model.dag.PortDataType
	 * @see #setDataType(PortDataType)
	 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage#getPort_DataType()
	 * @model required="true"
	 * @generated
	 */
	PortDataType getDataType();

	/**
	 * Sets the value of the '{@link fr.tpt.mem4csd.dag.model.dag.Port#getDataType <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' attribute.
	 * @see fr.tpt.mem4csd.dag.model.dag.PortDataType
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(PortDataType value);

	/**
	 * Returns the value of the '<em><b>Task</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.tpt.mem4csd.dag.model.dag.Task#getOwnedPorts <em>Owned Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' container reference.
	 * @see #setTask(Task)
	 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage#getPort_Task()
	 * @see fr.tpt.mem4csd.dag.model.dag.Task#getOwnedPorts
	 * @model opposite="ownedPorts" required="true" transient="false"
	 * @generated
	 */
	Task getTask();

	/**
	 * Sets the value of the '{@link fr.tpt.mem4csd.dag.model.dag.Port#getTask <em>Task</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task</em>' container reference.
	 * @see #getTask()
	 * @generated
	 */
	void setTask(Task value);

} // Port
