/**
 */
package fr.tpt.mem4csd.dag.model.dag;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.DagSpecification#getOwnedTasks <em>Owned Tasks</em>}</li>
 *   <li>{@link fr.tpt.mem4csd.dag.model.dag.DagSpecification#getSortedTasks <em>Sorted Tasks</em>}</li>
 * </ul>
 *
 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage#getDagSpecification()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sortedTasksSize'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot sortedTasksSize='self.sortedTasks->isEmpty() or self.ownedTasks->size() = self.sortedTasks->size()'"
 * @generated
 */
public interface DagSpecification extends IdentifiedElement, NamedElement {
	/**
	 * Returns the value of the '<em><b>Owned Tasks</b></em>' containment reference list.
	 * The list contents are of type {@link fr.tpt.mem4csd.dag.model.dag.Task}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Tasks</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Tasks</em>' containment reference list.
	 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage#getDagSpecification_OwnedTasks()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Task> getOwnedTasks();

	/**
	 * Returns the value of the '<em><b>Sorted Tasks</b></em>' reference list.
	 * The list contents are of type {@link fr.tpt.mem4csd.dag.model.dag.Task}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sorted Tasks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sorted Tasks</em>' reference list.
	 * @see fr.tpt.mem4csd.dag.model.dag.DagPackage#getDagSpecification_SortedTasks()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<Task> getSortedTasks();

} // DagSpecification
